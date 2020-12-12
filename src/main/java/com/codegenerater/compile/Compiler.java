package com.codegenerater.compile;

import com.codegenerater.model.Model;

/**
 * @description: 语法树解析
 * @author: chenshize02
 * @create: 2020-12-12 15:38
 **/
public interface Compiler {
    Model compile(String syntax);
}
