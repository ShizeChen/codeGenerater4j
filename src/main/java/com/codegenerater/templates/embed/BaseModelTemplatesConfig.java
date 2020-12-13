package com.codegenerater.templates.embed;


import cn.hutool.core.util.StrUtil;
import com.codegenerater.common.FileSetting;
import com.codegenerater.common.FileType;
import com.codegenerater.templates.spec.Template;
import com.codegenerater.templates.spec.TemplateSpec;
import com.codegenerater.templates.spec.Templates;

import static com.codegenerater.templates.embed.EmbedTemplates.*;

/**
 * @author: chenshize02
 */
@Templates
public class BaseModelTemplatesConfig {

    private static final String SERVICE_POJO_TPL_PATH = "/ftl/pojo.ftl";

    private static final String SERVICE_EXAMPLE_TPL_PATH = "/ftl/example.ftl";

    private static final String MYSQL_DDL_TPL_PATH = "/ftl/mySqlDDL.ftl";


    @Template(MODEL)
    public TemplateSpec pojoTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("MODEL");
        spec.setPath(SERVICE_POJO_TPL_PATH);
        spec.addAttr("modelType", "");
        spec.setFileSetting(FileSetting.ofJavaFile("/model/"));
        return spec;
    }

    @Template(DO_POJO)
    public TemplateSpec modelTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("DO-POJO");
        spec.setPath(SERVICE_POJO_TPL_PATH);
        spec.addAttr("modelType", "DO");
        spec.setFileSetting(FileSetting.ofJavaFile("/model/"));
        return spec;
    }

    @Template(BIZ_POJO)
    public TemplateSpec bizTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("BIZ_POJO");
        spec.setPath(SERVICE_POJO_TPL_PATH);
        spec.addAttr("modelType", "BO");
        spec.setFileSetting(FileSetting.ofJavaFile("/model/"));
        return spec;
    }

    @Template(QUERY_POJO)
    public TemplateSpec queryTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("QUERY_POJO");
        spec.setPath(SERVICE_POJO_TPL_PATH);
        spec.addAttr("modelType", "Query");
        spec.setFileSetting(FileSetting.ofJavaFile("/model/"));
        return spec;
    }

    @Template(DTO_POJO)
    public TemplateSpec dtoTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("DTO_POJO");
        spec.setPath(SERVICE_POJO_TPL_PATH);
        spec.addAttr("modelType", "DTO");
        spec.setFileSetting(FileSetting.ofJavaFile("/model/"));
        return spec;
    }

    @Template(EXAMPLE)
    public TemplateSpec exampleTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("EXAMPLE");
        spec.setPath(SERVICE_EXAMPLE_TPL_PATH);
        spec.setFileSetting(FileSetting.ofJavaFile("/example/"));
        return spec;
    }

    @Template(MYSQL_DDL)
    public TemplateSpec mysqlDdlTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("MYSQL_DDL");
        spec.setPath(MYSQL_DDL_TPL_PATH);
        spec.setFileSetting(FileSetting.of(StrUtil.EMPTY, StrUtil.EMPTY, StrUtil.EMPTY, "/ddl/", FileType.TXT));
        return spec;
    }
}
