package ${bean.packageFullName?lower_case};

<#list bean.imports as import>
import ${import};
</#list>

public class ${bean.entityName?cap_first} {

<#list bean.fields as field>
    private ${field.type} ${field.name};

</#list>
<#list bean.fields as field>
    public void set${field.name?cap_first}(${field.type} ${field.name}){
        this.${field.name} = ${field.name};
    }

    public ${field.type} get${field.name?cap_first}(){
        return this.${field.name};
    }

</#list>
}