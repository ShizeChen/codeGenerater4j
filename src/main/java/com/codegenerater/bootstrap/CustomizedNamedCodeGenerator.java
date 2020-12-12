package com.codegenerater.bootstrap;


import com.codegenerater.common.GlobalConfig;
import com.codegenerater.exporter.CodeExporter;
import com.codegenerater.extract.table.CustomizedNamedExtractor;

/**
 * 预制好组件实现类型的启动器
 *
 * @author XD.Wang
 */
public class CustomizedNamedCodeGenerator extends DdlCodeGenerator {

    @Override
    protected GlobalConfig configureContext() {
        return creator.setExporter(CodeExporter.class)
                .setExtractor(CustomizedNamedExtractor.class)
                .init();
    }

}
