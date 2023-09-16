package com.quan.demo.framework.valid;

import org.hibernate.validator.group.GroupSequenceProvider;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ValitdatorController
 *
 * @author Force-oneself
 * @date 2022-12-25
 */
@RestController
@RequestMapping("/valid")
@GroupSequenceProvider(DemoGroupSequenceProvider.class)
public class ValidatorController {


    @GetMapping
    public ValidObject find(@Validated ValidObject object) {
        return object;
    }

    @PostMapping
    public ValidObject create(@Validated(value = CRUD.Create.class) @RequestBody ValidObject object) {
        return object;
    }

    @DeleteMapping
    public ValidObject delete(@Validated ValidObject object) {
        return object;
    }

    @PutMapping
    public ValidObject update(@Validated @RequestBody ValidObject object) {
        return object;
    }
}
