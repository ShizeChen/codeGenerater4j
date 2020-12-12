package com.codegenerater.group.embed;


import com.codegenerater.group.spec.Group;
import com.codegenerater.group.spec.GroupSpec;
import com.codegenerater.group.spec.Groups;

import static com.codegenerater.templates.embed.EmbedTemplates.*;

/**
 * @author created by wuhuilin
 * Date 2020/9/14.
 */
@Groups
public class BaseGroupsConfig {

    public static final String STANDARD_GROUP = "standard";

    @Group("standard")
    public GroupSpec standardGroup() {
        GroupSpec g = new GroupSpec();
        g.addTemplate(EXAMPLE);
        g.addTemplate(MODEL);
        return g;
    }
}
