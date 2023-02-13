package com.quan.demo.oss;

import java.io.InputStream;
import java.util.Map;

/**
 * @author Force-oneself
 * @date 2023-01-29
 */
public interface FileResource extends ObjectResource {

    InputStream content();

    Map<String, Object> attr();

//    String bucketName();

    default long fileSize() {
        return 0;
    }

    default String contentType() {
        return null;
    }
}
