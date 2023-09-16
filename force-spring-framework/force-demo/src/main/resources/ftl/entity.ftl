package ${meta.pkg};

<#list meta.imports as import>
import ${import};

</#list>

/**
 * ${meta.type}.java
 *
 * @author Force-oneself
 * @date ${.now?string("yyyy-MM-dd")}
 */
public class ${meta.type} {

<#list meta.fields as field>
    /**
     * ${field.describe}
     */
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
