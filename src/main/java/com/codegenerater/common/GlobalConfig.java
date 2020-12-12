package com.codegenerater.common;


import com.codegenerater.compile.Compiler;
import com.codegenerater.exporter.Exporter;
import com.codegenerater.extract.Extractor;

import java.util.Optional;

/**
 * 全局配置
 *
 * @author created by XD.Wang
 * Date 2020/7/12.
 */
public class GlobalConfig {

    private final String workspacePath;
    private final String outputDir;
    private final String ddlName;
    private final String templatesConfigScanPath;
    private final String groupsConfigScanPath;
    private final String removePrefixIfExist;
    private final Class<? extends Compiler> compiler;
    private final Class<? extends Extractor> extractor;
    private final Class<? extends Exporter> exporter;

    public GlobalConfig(String workspacePath, String outputDir, String ddlName, String templatesConfigScanPath, Class<? extends Compiler> compiler, Class<? extends Extractor> extractor, Class<? extends Exporter> exporter, String groupsConfigScanPath, String removePrefixIfExist) {
        // 可默认的配置
        this.outputDir = Optional.ofNullable(outputDir).orElse("output");
        this.ddlName = Optional.ofNullable(outputDir).orElse("ddl.sql");
        this.templatesConfigScanPath = Optional.ofNullable(templatesConfigScanPath).orElse(null);
        this.groupsConfigScanPath = Optional.ofNullable(groupsConfigScanPath).orElse(null);
        this.removePrefixIfExist = Optional.ofNullable(removePrefixIfExist).orElse(null);
        this.workspacePath = Optional.ofNullable(workspacePath).orElseThrow(() -> new RuntimeException("work space required！"));
        // 可扩展的配置
        this.compiler = Optional.ofNullable(compiler).orElseThrow(() -> new RuntimeException("resolver required！！"));
        this.extractor = Optional.ofNullable(extractor).orElseThrow(() -> new RuntimeException("extractor required！！"));
        this.exporter = Optional.ofNullable(exporter).orElseThrow(() -> new RuntimeException("exporter required！！"));
    }

    public String getWorkspacePath() {
        return workspacePath;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public String getDdlName() {
        return ddlName;
    }

    public String getTemplatesConfigScanPath() {
        return templatesConfigScanPath;
    }

    public String getGroupsConfigScanPath() {
        return groupsConfigScanPath;
    }

    public String getRemovePrefixIfExist() {
        return removePrefixIfExist;
    }

    public Class<? extends Compiler> getCompiler() {
        return compiler;
    }

    public Class<? extends Extractor> getExtractor() {
        return extractor;
    }

    public Class<? extends Exporter> getExporter() {
        return exporter;
    }

    /**
     * 按需构建全局配置
     *
     * @author created by XD.Wang
     * Date 2020/7/12.
     */
    public static class Creator {

        private String workspacePath;
        private String outputDir;
        private String ddlName;
        private String templatesPath;
        private String groupsConfigScanPath;
        private String removePrefixIfExist;
        private Class<? extends Compiler> resolver;
        private Class<? extends Extractor> extractor;
        private Class<? extends Exporter> exporter;

        public void setWorkspacePath(String workspacePath) {
            this.workspacePath = workspacePath;
        }

        public void setOutputDir(String outputDir) {
            this.outputDir = outputDir;
        }

        public void setDdlName(String ddlName) {
            this.ddlName = ddlName;
        }

        public void setTemplatesPath(String templatesPath) {
            this.templatesPath = templatesPath;
        }

        public Creator setResolver(Class<? extends Compiler> resolver) {
            this.resolver = resolver;
            return this;
        }

        public Creator setExtractor(Class<? extends Extractor> extractor) {
            this.extractor = extractor;
            return this;
        }

        public Creator setExporter(Class<? extends Exporter> exporter) {
            this.exporter = exporter;
            return this;
        }

        public void setGroupsConfigScanPath(String groupsConfigScanPath) {
            this.groupsConfigScanPath = groupsConfigScanPath;
        }

        public void setRemovePrefixIfExist(String removePrefixIfExist) {
            this.removePrefixIfExist = removePrefixIfExist;
        }

        public GlobalConfig init() {
            return new GlobalConfig(workspacePath, outputDir, ddlName, templatesPath, resolver, extractor, exporter, groupsConfigScanPath, removePrefixIfExist);
        }
    }
}


