package ${meta.pkg}.vo;

<#list meta.imports as import>
    import ${import};
</#list>
import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
* ${meta.type}VO.java
*
* @author Force-oneself
* @date ${.now?string("yyyy-MM-dd")}
*/
@Data
@ApiModel("${meta.type?uncap_first}")
public class ${meta.type}VO implements Serializable {

    private static final long serialVersionUID = 1L;

<#list meta.fields as field>
    /**
    * ${field.describe}
    */
    @ApiModelProperty("${field.describe}")
    private ${field.type} ${field.name};

</#list>
}