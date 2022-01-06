package com.quan.producer.core.service.impl;

import com.quan.producer.core.adapter.store.StorageAdapter;
import com.quan.producer.core.adapter.store.StorageAdapterComposite;
import com.quan.producer.core.service.StorageService;

/**
 * @author Force-oneself
 * @description StorageServiceImpl
 * @date 2021-09-28
 **/
public class StorageServiceImpl<T> implements StorageService<T> {

    private final StorageAdapterComposite<T> storageAdapterComposite;

    public StorageServiceImpl(StorageAdapterComposite<T> storageAdapterComposite) {
        this.storageAdapterComposite = storageAdapterComposite;
    }

    @Override
    public StorageAdapter<T> storageAdapter() {
        return storageAdapterComposite;
    }
}
