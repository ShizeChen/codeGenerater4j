package com.codegenerater.model.loader;

import com.dlin.model.Model;
import com.dlin.model.enums.BaseBuiltInAttr;

/**
 * 用户属性兜底
 *
 * @author created by XD.Wang
 * Date 2020/9/4.
 */
public abstract class DefaultGlobalAttrLoader implements ModelAttrLoader {

    public static final String DEFAULT_AUTHOR = "XD.Wang";

    public static final String DEFAULT_CUSTOMIZED_MODEL_NAME = null;

    public static final String DEFAULT_BASE_PACKAGE = "com.sankuai.meituan.waimai.service.decoration.domain";

    @Override
    public void loadTo(Model model) {
        model.addBuiltInAttr(BaseBuiltInAttr.BASE_AUTHOR.key(), getAuthorSetting());
        model.addBuiltInAttr(BaseBuiltInAttr.BASE_PACKAGE.key(), getBasePackageSetting());
    }

    protected String getAuthorSetting() {
        return DEFAULT_AUTHOR;
    }

    protected String getCustomizedModelSetting() {
        return DEFAULT_CUSTOMIZED_MODEL_NAME;
    }

    protected String getBasePackageSetting() {
        return DEFAULT_BASE_PACKAGE;
    }

}
