package ${meta.pkg}.controller;

import org.springframework.web.bind.annotation.*;
import ${serviceInterface.packageName}.${serviceInterface.fileName};
import lombok.AllArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;

import java.util.Collections;

/**
 * ${meta.type}Controller.java
 * @author ${author!}
 * @date ${.now?string('yyyy-MM-dd')}
 */
@RequestMapping("${meta.type?uncap_first}")
@RestController
@AllArgsConstructor
@Api(value = "${meta.type?uncap_first}接口", tags = "${meta.type?uncap_first}接口")
public class ${meta.type}Controller {

    private final ${meta.type}Service ${meta.type?uncap_first}Service;

    @ApiOperation(value = "根据id获取")
    @PostMapping("findById")
    public R${'<'}${meta.type}${'>'} findById(@RequestParam("id") Long id) {
        return R.data(${meta.type?uncap_first}Service.getById(id));
    }

    @ApiOperation(value = "保存")
    @PostMapping("save")
    public R${'<'}Boolean${'>'} save(@Validated @RequestBody ${meta.type}DTO condition) {
        return R.data(${meta.type?uncap_first}Service.save(BeanUtil.copyProperties(condition, ${meta.type}.class)));
    }

    @ApiOperation(value = "更新")
    @PostMapping("update")
    public R${'<'}Boolean${'>'} update(@Validated @RequestBody ${meta.type}DTO condition) {
        return R.data(${meta.type?uncap_first}Service.updateById(BeanUtil.copyProperties(condition, ${meta.type}.class)));
    }

    @ApiOperation(value = "删除")
    @PostMapping("delete")
    public R${'<'}Boolean${'>'} delete(@RequestParam("id") Long id) {
        return R.data(${meta.type?uncap_first}Service.deleteLogic(Collections.singleton(id)));
    }
}