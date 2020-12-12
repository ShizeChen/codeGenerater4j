package com.codegenerater.io;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.log.StaticLog;
import com.google.common.base.Charsets;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 基于NIO的简易文本工具
 *
 * @author created by XD.Wang
 * Date 2020/7/12.
 */
final public class NioTextFileAccessor {

    public static String loadFile(String pathStr) {
        try {
            Path path = Paths.get(pathStr);
            return String.join("\n", Files.readAllLines(path));
        } catch (IOException e) {
            StaticLog.error("external file load failed！", e);
            throw ExceptionUtil.wrapRuntime(e);
        }
    }

    public static String loadEmbedFile(String fileName) {
        try {
            URL url = NioTextFileAccessor.class.getClassLoader().getResource(fileName.startsWith("/") ? fileName.substring(1) : fileName);
            if (url == null) {
                throw new RuntimeException("embed file not found!" + fileName);
            }
            if (URLUtil.isJarURL(url)) {
                String path = url.getPath();
                String jarPath = path.substring(0, path.indexOf("!"));
                JarFile jarFile = URLUtil.getJarFile(URLUtil.url(jarPath));
                return loadResourceFromJarRoot(jarFile, fileName);
            } else {
                Path path = Paths.get(url.getFile());
                return String.join("\n", Files.readAllLines(path));
            }
        } catch (Exception e) {
            StaticLog.error("embed file load failed！", e);
            throw ExceptionUtil.wrapRuntime(e);
        }
    }

    private static String loadResourceFromJarRoot(JarFile jar, String resourceName) throws IOException {
        JarEntry entry = jar.getJarEntry(resourceName);
        if (entry == null) {
            throw new RuntimeException("embed jar resource not found!" + resourceName);
        }
        InputStream inputStream = jar.getInputStream(entry);
        return IoUtil.read(inputStream, Charsets.UTF_8);
    }

    public static void writeText(String text, String pathStr) {
        try {
            Path path = Paths.get(pathStr);
            Files.write(path, text.getBytes());
        } catch (IOException e) {
            StaticLog.error("external file write failed！", e);
            throw ExceptionUtil.wrapRuntime(e);
        }
    }

    public static void createFile(String text, String pathStr, String fileName) {
        try {
            Path path = Paths.get(pathStr + "/" + fileName);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            Files.write(path, text.getBytes());
            StaticLog.info("export file -- {} -- completed!", fileName);
        } catch (IOException e) {
            StaticLog.error("external file create failed！！", e);
            throw ExceptionUtil.wrapRuntime(e);
        }
    }

}
