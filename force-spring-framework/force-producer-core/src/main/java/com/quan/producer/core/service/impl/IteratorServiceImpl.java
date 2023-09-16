package com.quan.producer.core.service.impl;

import com.quan.producer.core.adapter.iterator.IteratorAdapter;
import com.quan.producer.core.adapter.iterator.IteratorAdapterComposite;
import com.quan.producer.core.context.Context;
import com.quan.producer.core.context.ContextConstants;
import com.quan.producer.core.service.IteratorService;
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
