package com.codegenerater.render;


import com.codegenerater.model.Model;

/**
 * 渲染器
 *
 * @author XD.Wang
 * Date 2020-7-25.
 */
public interface Render {

    /**
     * 渲染
     *
     * @param model
     * @return
     */
    View render(Model model);

}
