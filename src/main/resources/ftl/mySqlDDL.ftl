CREATE TABLE `${_t_name}` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
<#list _f as filed>
    `${filed._f_u}` ${filed._f_jdbct} NOT NULL DEFAULT '${filed._f_ddl_dv}' COMMENT '${filed._f_comment}',
</#list>
PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '${_m_comment}'