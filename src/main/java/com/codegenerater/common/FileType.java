package com.codegenerater.common;

/**
 * @author: chenshize02
 */
public enum FileType {

    JAVA(".java"), MYBATIS_XML(".xml"), TXT(".txt");

    private String ext;

    FileType(String ext) {
        this.ext = ext;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

}
