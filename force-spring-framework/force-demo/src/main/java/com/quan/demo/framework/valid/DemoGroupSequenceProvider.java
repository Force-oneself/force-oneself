package com.quan.demo.framework.valid;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.Collections;
import java.util.List;

/**
 * DemoGroupSequenceProvider
 *
 * @author Force-oneself
 * @date 2022-12-25
 */
public class DemoGroupSequenceProvider implements DefaultGroupSequenceProvider<ValidObject> {

    @Override
    public List<Class<?>> getValidationGroups(ValidObject object) {
        Long id = object.getId();
        if (id != null) {
            return Collections.singletonList(CRUD.Create.class);
        }
        return null;
    }
}
