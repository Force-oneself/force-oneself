package com.quan.demo.oss;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * DefaultOssService
 *
 * @author Force-oneself
 * @date 2023-01-28
 */
public class DefaultOSS implements OSS {

    public void upload(String fileName, InputStream content) {

        // 上传
        FileResource uploader = new FileResource() {
            @Override
            public InputStream content() {
                return content;
            }

            @Override
            public Map<String, Object> attr() {
                return Collections.emptyMap();
            }

            @Override
            public String objectKey() {
                return fileName;
            }

        };
        ObjectStorage storage = this.route(uploader);
        // 上传
        storage.upload(uploader);
    }

    public void remove(String fileName) {
        ObjectResource objectResource = () -> fileName;
        ObjectStorage storage = this.route(objectResource);
        storage.remove(objectResource);
    }

    public boolean exits(String fileName) {
        ObjectResource objectResource = () -> fileName;
        ObjectStorage storage = this.route(objectResource);
        return storage.exist(objectResource);
    }

    public void download(String fileName, Consumer<FileResource> downloadConsumer) {
        ObjectResource objectResource = () -> fileName;
        ObjectStorage storage = this.route(objectResource);
        try {
            downloadConsumer.accept(storage.download(objectResource));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ObjectStorage route(ObjectResource resource) {
        return storages().get(0);
    }

    public List<ObjectStorage> storages() {
        return new ArrayList<>();
    }
}