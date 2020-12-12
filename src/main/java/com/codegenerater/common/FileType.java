package com.codegenerater.common;

/**
 * @author created by XD.Wang
 * Date 2020/9/11.
 */
public enum FileType {

    JAVA(".java"), MYBATIS_XML(".xml");

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
