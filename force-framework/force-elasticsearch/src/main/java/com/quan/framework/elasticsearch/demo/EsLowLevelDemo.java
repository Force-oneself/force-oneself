package com.quan.framework.elasticsearch.demo;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;

import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

/**
 * EsRestClientDemo
 *
 * @author Force-oneself
 * @date 2022-04-21
 */
public class EsLowLevelDemo {

    public RestClient restClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        // 集群
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200, "http"),
                new HttpHost("localhost", 9201, "http")).build();

        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http"));

        // 设置头
        header(builder);
        // 节点异常监听
        failureListener(builder);
        // 节点选择器
        nodeSelector(builder);
        // Request请求配置回调
        requestConfigCallback(builder);
        // 客户端配置回调
        httpClientConfigCallback(builder);
        return builder.build();
    }

    private void failureListener(RestClientBuilder builder) {
        builder.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(Node node) {

            }
        });
    }

    private void header(RestClientBuilder builder) {
        String apiKeyId = "uqlEyn8B_gQ_jlvwDIvM";
        String apiKeySecret = "HxHWk2m4RN-V_qg9cDpuX";
        String apiKeyAuth
                = Base64.getEncoder().encodeToString((apiKeyId + ":" + apiKeySecret).getBytes(StandardCharsets.UTF_8));
        Header[] defaultHeaders = new Header[]{
                new BasicHeader("header", "value"),
                // token授权头信息
                new BasicHeader("Authorization", "Bearer u6iuAxZ0RG1Kcm5jVFI4eU4tZU9aVFEwT2F3"),
                // 自定义api token
                new BasicHeader("Authorization", "ApiKey " + apiKeyAuth)
        };
        builder.setDefaultHeaders(defaultHeaders);
    }

    private void requestConfigCallback(RestClientBuilder builder) {
        builder.setRequestConfigCallback(
                new RestClientBuilder.RequestConfigCallback() {
                    @Override
                    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                        return requestConfigBuilder
                                .setConnectTimeout(5000)
                                .setSocketTimeout(60000);
                    }
                });
    }

    private void httpClientConfigCallback(RestClientBuilder builder) {
        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("user", "password"));
        // 客户端配置回调
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {

                return httpClientBuilder
                        // 禁止认证缓存
                        .disableAuthCaching()
                        // basic认证
                        .setDefaultCredentialsProvider(credentialsProvider)
                        // 设置客户端io线程数
                        .setDefaultIOReactorConfig(IOReactorConfig.custom()
                                .setIoThreadCount(1)
                                .build())
                        // 设置代理服务
                        .setProxy(new HttpHost("proxy", 9000, "http"));
            }
        });
    }

    private void nodeSelector(RestClientBuilder builder) {
        builder.setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS);
        // 自定义Node选择器
        builder.setNodeSelector(new NodeSelector() {
            @Override
            public void select(Iterable<Node> nodes) {
                /*
                 * Prefer any node that belongs to rack_one. If none is around
                 * we will go to another rack till it's time to try and revive
                 * some of the nodes that belong to rack_one.
                 */
                boolean foundOne = false;
                for (Node node : nodes) {
                    String rackId = node.getAttributes().get("rack_id").get(0);
                    if ("rack_one".equals(rackId)) {
                        foundOne = true;
                        break;
                    }
                }
                if (foundOne) {
                    Iterator<Node> nodesIt = nodes.iterator();
                    while (nodesIt.hasNext()) {
                        Node node = nodesIt.next();
                        String rackId = node.getAttributes().get("rack_id").get(0);
                        if ("rack_one".equals(rackId) == false) {
                            nodesIt.remove();
                        }
                    }
                }
            }
        });
    }

    public void request() throws Exception {
        Request request = new Request(
                "GET",
                "/");
        RestClient restClient = restClient();
        // 同步请求
        Response response = restClient.performRequest(request);
        // 异步请求
        Cancellable cancellable = restClient.performRequestAsync(request,
                new ResponseListener() {
                    @Override
                    public void onSuccess(Response response) {

                    }

                    @Override
                    public void onFailure(Exception exception) {

                    }
                });

        // 参数设置
        request.addParameter("pretty", "true");

        // body设置
        request.setEntity(new NStringEntity(
                "{\"json\":\"text\"}",
                ContentType.APPLICATION_JSON));
        // string 默认：ContentType: application/json
        request.setJsonEntity("{\"json\":\"text\"}");

        // 设置可选参数
        request.setOptions(requestOptions());
        // 自定义可选参数
        RequestOptions.Builder options = RequestOptions.DEFAULT.toBuilder();
        options.addHeader("cats", "knock things off of other things");
        request.setOptions(options);

        // 多文档并发操作
        NStringEntity[] documents = new NStringEntity[]{
                new NStringEntity("{\"json\":\"text\"}", ContentType.APPLICATION_JSON),
                new NStringEntity("{\"json\":\"text2\"}", ContentType.APPLICATION_JSON)
        };
        final CountDownLatch latch = new CountDownLatch(documents.length);
        for (int i = 0; i < documents.length; i++) {
            Request req = new Request("PUT", "/posts/doc/" + i);
            //let's assume that the documents are stored in an HttpEntity array
            req.setEntity(documents[i]);
            restClient.performRequestAsync(
                    req,
                    new ResponseListener() {
                        @Override
                        public void onSuccess(Response response) {

                            latch.countDown();
                        }

                        @Override
                        public void onFailure(Exception exception) {

                            latch.countDown();
                        }
                    }
            );
        }
        latch.await();
    }

    public RequestOptions requestOptions() {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        builder.addHeader("Authorization", "Bearer " + "TOKEN");
        builder.setHttpAsyncResponseConsumerFactory(
                new HttpAsyncResponseConsumerFactory
                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
        return builder.build();
    }


    public void response() throws Exception {
        RestClient restClient = restClient();
        Response response = restClient.performRequest(new Request("GET", "/"));
        RequestLine requestLine = response.getRequestLine();
        HttpHost host = response.getHost();
        // 响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
        // 响应头
        Header[] headers = response.getHeaders();
        // 响应体
        String responseBody = EntityUtils.toString(response.getEntity());
    }
}
