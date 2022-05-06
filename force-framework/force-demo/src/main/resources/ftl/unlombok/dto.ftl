package ${meta.pkg}.dto;

<#list meta.imports as import>
    import ${import};
</#list>
import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
* ${meta.type}DTO.java
*
* @author Force-oneself
* @date ${.now?string("yyyy-MM-dd")}
*/
@ApiModel("${meta.type?uncap_first}")
public class ${meta.type}DTO implements Serializable {

    private static final long serialVersionUID = 1L;

<#list meta.fields as field>
    /**
    * ${field.describe}
    */
    @ApiModelProperty("${field.describe}")
    private ${field.type} ${field.name};

</#list>

<#list meta.fields as param>
    public void set${param.name?cap_first}(${param.type} ${param.name}) {
    this.${param.name} = ${param.name};
    }

    public ${param.type} get${param.name?cap_first}() {
    return this.${param.name};
    }

</#list>
}
