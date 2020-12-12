package com.codegenerater.bootstrap;


import com.codegenerater.common.GlobalConfig;
import com.codegenerater.compile.Compiler;
import com.codegenerater.exporter.CodeExporter;
import com.codegenerater.extract.table.TableExtractor;

/**
 * 预制好组件实现类型的启动器
 *
 * @author XD.Wang
 */
public class DdlCodeGenerator extends Bootstrap {

    protected final GlobalConfig.Creator creator = new GlobalConfig.Creator();

    public DdlCodeGenerator setWorkspacePath(String workspacePath) {
        creator.setWorkspacePath(workspacePath);
        return this;
    }

    public DdlCodeGenerator setOutputDir(String outputPath) {
        creator.setOutputDir(outputPath);
        return this;
    }

    public DdlCodeGenerator setDdlName(String ddlName) {
        creator.setDdlName(ddlName);
        return this;
    }

    public DdlCodeGenerator setCompiler(Class<? extends Compiler> compiler) {
        creator.setCompiler(compiler);
        return this;
    }

    public DdlCodeGenerator setTemplatesPath(String templatesPath) {
        creator.setTemplatesPath(templatesPath);
        return this;
    }

    public DdlCodeGenerator setRemovePrefixIfExist(String removePrefixIfExist) {
        creator.setRemovePrefixIfExist(removePrefixIfExist);
        return this;
    }

    @Override
    protected GlobalConfig configureContext() {
        return creator.setExporter(CodeExporter.class)
                .setExtractor(TableExtractor.class)
                .init();
    }

}
