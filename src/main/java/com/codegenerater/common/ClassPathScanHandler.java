package com.codegenerater.common;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

/**
 * 类路径扫描工具
 *
 * @author XD.Wang
 * Date 2020-8-2.
 */
public class ClassPathScanHandler {

    /**
     * StaticLog.
     */
    private static final String CLASS_EXTENSION_NAME = ".class";
    /**
     * 是否排除内部类 true->是 false->否.
     */
    private boolean excludeInner = true;
    /**
     * 过滤规则适用情况 true—>搜索符合规则的 false->排除符合规则的.
     */
    private boolean checkInOrEx = true;
    /**
     * 过滤规则列表 如果是null或者空，即全部符合不过滤.
     */
    private List<String> classFilters = null;

    /**
     * 必须设定路径！
     */
    public ClassPathScanHandler() {
    }

    /**
     * 指定更详细的参数
     *
     * @param excludeInner 是否排除内部类 true->是 false->否<br>.
     * @param checkInOrEx  过滤规则适用情况 true—>搜索符合规则的 false->排除符合规则的<br>
     * @param classFilters 自定义过滤规则，如果是null或者空，即全部符合不过滤
     */
    public ClassPathScanHandler(Boolean excludeInner, Boolean checkInOrEx,
                                List<String> classFilters) {
        this.excludeInner = excludeInner;
        this.checkInOrEx = checkInOrEx;
        this.classFilters = classFilters;
    }

    /**
     * scan the package.
     *
     * @param basePackage the basic class package's string.
     * @param recursive   whether to search recursive.
     * @return Set of the found classes.
     */
    public Set<Class<?>> getPackageAllClasses(String basePackage, boolean recursive) {
        if (StrUtil.isEmpty(basePackage)) {
            return new HashSet<>();
        }
        Set<Class<?>> classes = new LinkedHashSet<>();
        String packageName = basePackage;
        String t = ".";
        if (packageName.endsWith(t)) {
            packageName = packageName.substring(0, packageName.lastIndexOf('.'));
        }
        String package2Path = packageName.replace('.', '/');

        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(package2Path);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF_8");
                    doScanPackageClassesByFile(classes, packageName, filePath, recursive);
                } else if ("jar".equals(protocol)) {
                    doScanPackageClassesByJar(packageName, url, recursive, classes);
                }
            }
        } catch (IOException e) {
            StaticLog.error("IOException error:", e);
        }

        TreeSet<Class<?>> sortedClasses = new TreeSet<>((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        sortedClasses.addAll(classes);
        return sortedClasses;
    }

    /**
     * 以jar的方式扫描包下的所有Class文件<br>.
     *
     * @param basePackage eg：michael.utils.
     * @param url         the url.
     * @param recursive   whether to search recursive.
     * @param classes     set of the found classes.
     */
    private void doScanPackageClassesByJar(String basePackage, URL url, final boolean recursive,
                                           Set<Class<?>> classes) {
        String package2Path = basePackage.replace('.', '/');
        JarFile jar;
        try {
            jar = ((JarURLConnection) url.openConnection()).getJarFile();
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                if (!name.startsWith(package2Path) || entry.isDirectory()) {
                    continue;
                }
                // 判断是否递归搜索子包
                if (!recursive && name.lastIndexOf('/') != package2Path.length()) {
                    continue;
                }
                // 判断是否过滤 inner class
                char c = '$';
                if (this.excludeInner && name.indexOf(c) != -1) {
                    continue;
                }
                String classSimpleName = name.substring(name.lastIndexOf('/') + 1);
                // 判定是否符合过滤条件
                if (this.filterClassName(classSimpleName)) {
                    String className = name.replace('/', '.');
                    className = className.substring(0, className.length() - 6);
                    try {
                        classes.add(Thread.currentThread().getContextClassLoader().loadClass(className));
                    } catch (ClassNotFoundException e) {
                        StaticLog.error("LoadClass Exception:URL is ===>" + url.getPath(), e);
                    } catch (NoClassDefFoundError error) {
                        StaticLog.error("LoadClass error:URL is ===>" + url.getPath(), error);
                    }
                }
            }
        } catch (IOException e) {
            StaticLog.error("IOException error:URL is ===>" + url.getPath(), e);
        } catch (Throwable e) {
            StaticLog.error("ScanPackageClassesByJar error:URL is ===>" + url.getPath(), e);
        }
    }

    /**
     * 以文件的方式扫描包下的所有Class文件.
     *
     * @param classes     set of the found classes.
     * @param packageName the package name for scanning.
     * @param packagePath the package path for scanning.
     * @param recursive   whether to search recursive.
     */
    private void doScanPackageClassesByFile(
            Set<Class<?>> classes, String packageName, String packagePath, boolean recursive) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] files = dir.listFiles(pathname -> filterClassFileByCustomization(pathname, recursive));
        if (null == files || files.length == 0) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                doScanPackageClassesByFile(classes, packageName + "." + file.getName(), file.getAbsolutePath(), recursive);
            } else {
                String className = file.getName().substring(0,
                        file.getName().length() - CLASS_EXTENSION_NAME.length());
                try {
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    StaticLog.error("LoadClass exception: ===>" + className, e);
                } catch (NoClassDefFoundError error) {
                    StaticLog.error("LoadClass error: ===>" + className, error);
                }
            }
        }
    }

    /**
     * filter the class file from the customized rules.
     *
     * @param file      the class file to be filtered.
     * @param recursive whether search recursive.
     * @return true : match,  false: not match.
     */
    private boolean filterClassFileByCustomization(File file, boolean recursive) {
        if (file.isDirectory()) {
            return recursive;
        }
        String filename = file.getName();
        char c = '$';
        if (excludeInner && filename.indexOf(c) != -1) {
            return false;
        }
        return filterClassName(filename);
    }

    /**
     * 类名过滤.
     *
     * @param className the class name.
     * @return whether to be filtered.
     */
    private boolean filterClassName(String className) {
        if (!className.endsWith(CLASS_EXTENSION_NAME)) {
            return false;
        }
        if (null == this.classFilters || this.classFilters.isEmpty()) {
            return true;
        }
        String tmpName = className.substring(0, className.length() - 6);
        boolean flag = false;
        for (String str : classFilters) {
            flag = matchInnerClassname(tmpName, str);
            if (flag) {
                break;
            }
        }
        return (checkInOrEx && flag) || (!checkInOrEx && !flag);
    }

    /**
     * 内部类处理
     *
     * @param className    名称
     * @param filterString 过滤规则
     * @return true or false.
     */
    private boolean matchInnerClassname(String className, String filterString) {
        String reg = "^" + filterString.replace("*", ".*") + "$";
        Pattern p = Pattern.compile(reg);
        return p.matcher(className).find();
    }

}
