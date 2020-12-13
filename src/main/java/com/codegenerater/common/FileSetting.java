package com.codegenerater.common;

import cn.hutool.core.util.StrUtil;

/**
 * @author created by XD.Wang
 * Date 2020/8/18.
 */
public class FileSetting {

    private String prefix;

    private String suffix;

    private String fileName;

    private FileType fileType;

    private String fileDir;

    public static final FileSetting JAVA_FILE = of(StrUtil.EMPTY, StrUtil.EMPTY, StrUtil.EMPTY, StrUtil.EMPTY, FileType.JAVA);

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public static FileSetting of(String prefix, String suffix, String customizedFileName, String fileDir, FileType ext) {
        FileSetting setting = new FileSetting();
        setting.setFileType(ext);
        setting.setPrefix(prefix);
        setting.setSuffix(suffix);
        setting.setFileDir(fileDir);
        setting.setFileName(customizedFileName);
        return setting;
    }

    public static FileSetting ofJavaFile(String fileDir) {
        FileSetting setting = new FileSetting();
        setting.setFileType(FileType.JAVA);
        setting.setPrefix(StrUtil.EMPTY);
        setting.setSuffix(StrUtil.EMPTY);
        setting.setFileDir(fileDir);
        setting.setFileName(StrUtil.EMPTY);
        return setting;
    }

    public static FileSetting ofMyBatisXmlFile(String fileDir, String prefix, String suffix) {
        FileSetting setting = new FileSetting();
        setting.setFileType(FileType.MYBATIS_XML);
        setting.setPrefix(prefix);
        setting.setSuffix(suffix);
        setting.setFileDir(fileDir);
        setting.setFileName(StrUtil.EMPTY);
        return setting;
    }


}
