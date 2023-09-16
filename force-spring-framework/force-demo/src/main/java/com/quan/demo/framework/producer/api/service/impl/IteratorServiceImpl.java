package com.quan.demo.framework.producer.api.service.impl;

import com.quan.demo.framework.producer.api.adapter.iterator.IteratorAdapter;
import com.quan.demo.framework.producer.api.adapter.iterator.IteratorAdapterComposite;
import com.quan.demo.framework.producer.api.context.Context;
import com.quan.demo.framework.producer.api.context.ContextConstants;
import com.quan.demo.framework.producer.api.service.IteratorService;
import org.springframework.util.Assert;


/**
 * @author Force-oneself
 * @description IteratorServiceImpl
 * @date 2021-09-28
 **/
public class IteratorServiceImpl<T> implements IteratorService<T> {

    private final IteratorAdapterComposite<T> adapterComposite;

    public IteratorServiceImpl(IteratorAdapterComposite<T> adapterComposite, Context<T> context) {
        Assert.notNull(adapterComposite, "必须指定合适的适配器");
        this.adapterComposite = adapterComposite;
        context.setObject(ContextConstants.ITERATOR_COUNT_TOTAL, this.adapterComposite.count());
    }

    @Override
    public IteratorAdapter<T> iterator() {
        return adapterComposite;
    }
}
