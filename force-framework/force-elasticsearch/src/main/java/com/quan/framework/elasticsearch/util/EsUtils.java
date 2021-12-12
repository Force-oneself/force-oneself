package com.quan.framework.elasticsearch.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author Force-oneself
 * @Description EsUtils
 * @date 2021-11-29
 */
public final class EsUtils {

    public static RestHighLevelClient getClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
    }
}
