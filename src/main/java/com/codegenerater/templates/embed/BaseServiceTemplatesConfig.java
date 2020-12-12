package com.codegenerater.templates.embed;


import com.codegenerater.common.FileSetting;
import com.codegenerater.templates.spec.Template;
import com.codegenerater.templates.spec.TemplateSpec;
import com.codegenerater.templates.spec.Templates;

import static com.codegenerater.templates.embed.EmbedTemplates.*;


/**
 * @author created by XD.Wang
 * Date 2020/9/4.
 */
@Templates
public class BaseServiceTemplatesConfig {

    private static final String SERVICE_TPL_PATH = "/ftl/service.ftl";

    private static final String SERVICE_IMPL_TPL_PATH = "/ftl/service-impl.ftl";

    private static final String MYBATIS_MAPPER_TPL_PATH = "/ftl/mapper.ftl";

    private static final String MYBATIS_MAPPER_XML_TPL_PATH = "/ftl/mapper-xml.ftl";

    @Template(SERVICE)
    public TemplateSpec serviceTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("SERVICE");
        spec.setPath(SERVICE_TPL_PATH);
        spec.addAttr("serviceType", "Service");
        spec.addAttr("serviceOutSuf", "BO");
        spec.addAttr("serviceInSuf", "Query");
        spec.setFileSetting(FileSetting.ofJavaFile("/service/"));
        return spec;
    }

    @Template(SER_REPOSITORY)
    public TemplateSpec repositoryTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("SER_REPOSITORY");
        spec.setPath(SERVICE_TPL_PATH);
        spec.addAttr("serviceType", "Repository");
        spec.addAttr("serviceOutSuf", "DO");
        spec.addAttr("serviceInSuf", "Query");
        spec.setFileSetting(FileSetting.ofJavaFile("/repository/"));
        return spec;
    }

    @Template(SER_MANAGER)
    public TemplateSpec managerTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("SER_MANAGER");
        spec.setPath(SERVICE_TPL_PATH);
        spec.addAttr("serviceType", "Manager");
        spec.addAttr("serviceOutSuf", "DTO");
        spec.addAttr("serviceInSuf", "Query");
        spec.setFileSetting(FileSetting.ofJavaFile("/manager/"));
        return spec;
    }

    @Template(SERVICE_IMPL)
    public TemplateSpec serviceImplTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("SERVICE_IMPL");
        spec.setPath(SERVICE_IMPL_TPL_PATH);
        spec.addAttr("subServiceType", "Repository");
        spec.addAttr("serviceType", "Service");
        spec.addAttr("serviceOutSuf", "BO");
        spec.addAttr("serviceInSuf", "Query");
        spec.setFileSetting(FileSetting.ofJavaFile("/service/impl/"));
        return spec;
    }

    @Template(SER_IMPL_REPOSITORY)
    public TemplateSpec repositoryImplTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("SER_IMPL_REPOSITORY");
        spec.setPath(SERVICE_IMPL_TPL_PATH);
        spec.addAttr("subServiceType", "Mapper");
        spec.addAttr("serviceType", "Repository");
        spec.addAttr("serviceOutSuf", "DO");
        spec.addAttr("serviceInSuf", "Query");
        spec.setFileSetting(FileSetting.ofJavaFile("/repository/impl/"));
        return spec;
    }

    @Template(SER_IMPL_MANAGER)
    public TemplateSpec managerImplTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("SER_IMPL_MANAGER");
        spec.setPath(SERVICE_IMPL_TPL_PATH);
        spec.addAttr("serviceType", "Manager");
        spec.addAttr("subServiceType", "Service");
        spec.addAttr("serviceOutSuf", "DTO");
        spec.addAttr("serviceInSuf", "Req");
        spec.setFileSetting(FileSetting.ofJavaFile("/manager/impl/"));
        return spec;
    }

    @Template(SER_IMPL_THRIFT)
    public TemplateSpec apiImplTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("SER_IMPL_THRIFT");
        spec.setPath(SERVICE_IMPL_TPL_PATH);
        spec.addAttr("serviceType", "ThriftApi");
        spec.addAttr("subServiceType", "Manager");
        spec.addAttr("serviceOutSuf", "");
        spec.addAttr("serviceInSuf", "");
        spec.setFileSetting(FileSetting.ofJavaFile("/api/impl/"));
        return spec;
    }

    @Template(MYBATIS_MAPPER)
    public TemplateSpec mapperTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("MYBATIS_MAPPER");
        spec.setPath(MYBATIS_MAPPER_TPL_PATH);
        spec.addAttr("serviceOutSuf", "DO");
        spec.addAttr("serviceInSuf", "DO");
        spec.setFileSetting(FileSetting.ofJavaFile("/mapper/"));
        return spec;
    }

    @Template(MYBATIS_XML_MAPPER)
    public TemplateSpec mapperXmlTemplate() {
        TemplateSpec spec = new TemplateSpec();
        spec.setDescription("MYBATIS_XML_MAPPER");
        spec.setPath(MYBATIS_MAPPER_XML_TPL_PATH);
        spec.setFileSetting(FileSetting.ofMyBatisXmlFile("/mapper/gen/", "", "Mapper.xml"));
        return spec;
    }

}
