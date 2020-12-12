package com.codegenerater.extract.table;

import com.codegenerater.container.ManagedBean;
import com.codegenerater.extract.AbstractExtractor;
import com.codegenerater.io.NioTextFileAccessor;
import com.codegenerater.model.Model;
import com.google.common.base.Joiner;

import java.util.Optional;

/**
 * 表数据提取器
 *
 * @author XD.Wang
 * Date 2020-7-25.
 */
@ManagedBean
public class TableExtractor extends AbstractExtractor {

    @Override
    public Model extract() {
        String ddlPath = Joiner.on("/").join(globalContext.getConfig().getWorkspacePath(), globalContext.getConfig().getDdlName());
        String ddlStr = NioTextFileAccessor.loadFile(ddlPath);
        Model model = globalContext.calByComponent(globalContext.getConfig().getCompiler(), compiler -> compiler.compile(ddlStr));
        processModelBeforeUsed(model);
        return model;
    }

    protected void processModelBeforeUsed(Model model) {
        Optional.ofNullable(globalContext.getConfig().getRemovePrefixIfExist()).ifPresent(prefix -> model.setName(model.getName().replaceAll(prefix, "")));
        loadCustomizedAttrs(model);
    }

}
