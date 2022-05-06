<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${meta.pkg}.${meta.type}Mapper">

    <resultMap id="BaseResultMap" type="${tableClass.fullClassName}">
        <#list meta.fields as field>
            <id property="${field.fieldName}" column="${field.columnName}" jdbcType="${field.jdbcType}"/>
        </#list>
        <#list tableClass.baseFields as field>
            <result property="${field.fieldName}" column="${field.columnName}" jdbcType="${field.jdbcType}"/>
        </#list>
    </resultMap>

</mapper>
