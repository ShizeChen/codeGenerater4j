package com.codegenerater.extract;


import com.codegenerater.container.GlobalContext;
import com.codegenerater.container.GlobalContextAware;
import com.codegenerater.model.Model;
import com.codegenerater.model.loader.ModelAttrLoader;
import com.codegenerater.model.loader.PropertiesAttrLoader;
import com.google.common.collect.Lists;

import java.util.List;


/**
 * 预置数据提取器
 *
 * @author XD.Wang
 * Date 2020-7-25.
 */
public abstract class AbstractExtractor implements Extractor, GlobalContextAware {

    protected GlobalContext globalContext;

    @Override
    public void setGlobalContext(GlobalContext globalContext) {
        this.globalContext = globalContext;
    }

    private final List<ModelAttrLoader> modelAttrLoaderList = Lists.newArrayList(
            PropertiesAttrLoader.getLoader()
    );

    protected void loadCustomizedAttrs(Model model) {
        modelAttrLoaderList.forEach(loader -> {
            loader.loadTo(model);
        });
    }

}
