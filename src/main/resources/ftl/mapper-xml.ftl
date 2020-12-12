<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="${_base_pk}.mapper.${_m_cf}Mapper">

    <resultMap id="BaseResultMap" type="${_m_cf}DO">
        <#list _f as filed>
            <!-- 字段含义：${filed._f_comment} -->
            <id column="${filed._f_u}" property="${filed._f_c}" jdbcType="${filed._f_jdbct}"/>
        </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <#list _f as filed>
            ${filed._f_u},
        </#list>
<#--        <#list _f as filed>-->
<#--            ${filed?is_last?_f_u}-->
<#--        </#list>-->
    </sql>

    <sql id="Table_Name">
        ${_m_pref}${_m_u}
    </sql>

    <sql id="Example_Where_Clause">
        <where>
            <foreach collection="oredCriteria" item="criteria" separator="or">
                <if test="criteria.valid">
                    <trim prefix="(" suffix=")" prefixOverrides="and">
                        <foreach collection="criteria.criteria" item="criterion">
                            <choose>
                                <when test="criterion.noValue">
                                    ${r'and ${criterion.condition}'}
                                </when>
                                <when test="criterion.singleValue">
                                    ${r'and ${criterion.condition} #{criterion.value}'}
                                </when>
                                <when test="criterion.betweenValue">
                                    ${r'and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}'}
                                </when>
                                <when test="criterion.listValue">
                                    and ${r'${criterion.condition}'}
                                    <foreach collection="criterion.value" item="listItem" open="(" close=")"
                                             separator=",">
                                        ${r'#{listItem}'}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>

    <sql id="Update_By_Example_Where_Clause">
        <where>
            <foreach collection="example.oredCriteria" item="criteria" separator="or">
                <if test="criteria.valid">
                    <trim prefix="(" suffix=")" prefixOverrides="and">
                        <foreach collection="criteria.criteria" item="criterion">
                            <choose>
                                <when test="criterion.noValue">
                                    ${r'and ${criterion.condition}'}
                                </when>
                                <when test="criterion.singleValue">
                                    ${r'and ${criterion.condition} #{criterion.value}'}
                                </when>
                                <when test="criterion.betweenValue">
                                    ${r'and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}'}
                                </when>
                                <when test="criterion.listValue">
                                    and ${r'${criterion.condition}'}
                                    <foreach collection="criterion.value" item="listItem" open="(" close=")"
                                             separator=",">
                                        ${r'#{listItem}'}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>

    <select id="select${_m_cf}ListByExample" resultMap="BaseResultMap" parameterType="${_base_pk}.example.${_m_cf}Example">
        select
        <if test="distinct">
            distinct
        </if>
        <include refid="Base_Column_List"/>
        from
        <include refid="Table_Name"/>
        <if test="_parameter != null">
            <include refid="Example_Where_Clause"/>
        </if>
        <if test="orderByClause != null">
            order by ${r'${orderByClause}'}
        </if>
    </select>

    <select id="select${_m_cf}ById" resultMap="BaseResultMap" parameterType="${_pk._f_jst}">
        select
        <include refid="Base_Column_List"/>
        from
        <include refid="Table_Name"/>
        where
        ${_pk._f_u} = ${r'#{'}${_pk._f_c},jdbcType=${_pk._f_jdbct}}
    </select>

    <select id="select${_m_cf}ListByParam" resultMap="BaseResultMap" parameterType="${_m_cf}DO">
        select
        <include refid="Base_Column_List"/>
        from
        <include refid="Table_Name"/>
        where
        1 = 1
        <#list _f as filed>
            <if test="${filed._f_c}!= null">
                and ${filed._f_u} = ${r'#{'}${filed._f_c},jdbcType=${filed._f_jdbct}}
            </if>
        </#list>
    </select>

    <delete id="delete${_m_cf}ById" parameterType="${_pk._f_jst}">
        delete from
        <include refid="Table_Name"/>
        ${_pk._f_u} = ${r'#{'}${_pk._f_c},jdbcType=${_pk._f_jdbct}}
    </delete>

    <update id="update${_m_cf}ById" parameterType="${_m_cf}DO">
        update
        <include refid="Table_Name"/>
        <set>
            <#list _f as filed>
                <if test="${filed._f_c}!= null">
                    ${filed._f_u} = ${r'#{'}${filed._f_c},jdbcType=${filed._f_jdbct}},
                </if>
            </#list>
        </set>
        ${_pk._f_u} = ${r'#{'}${_pk._f_c},jdbcType=${_pk._f_jdbct}}
    </update>

    <insert id="insert${_m_cf}" parameterType="${_m_cf}DO" useGeneratedKeys="true" keyProperty="id">
        insert into
        <include refid="Table_Name"/>
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list _f as filed>
                <if test="${filed._f_c} != null">
                    ${filed._f_u},
                </if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list _f as filed>
                <if test="${filed._f_c} != null">
                    ${r'#{'}${filed._f_c},jdbcType=${filed._f_jdbct}},
                </if>
            </#list>
        </trim>
    </insert>

    <insert id="batchInsert${_m_cf}DO" parameterType="${_m_cf}">
        insert into
        <include refid="Table_Name"/>
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list _f as filed>
                ${filed._f_u},
            </#list>
        </trim>
        values
        <foreach collection="list" item="item" separator=",">
            <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list _f as filed>
                ${r'#{item.'}${filed._f_c},jdbcType=${filed._f_jdbct}},
            </#list>
            </trim>
        </foreach>
    </insert>

</mapper>