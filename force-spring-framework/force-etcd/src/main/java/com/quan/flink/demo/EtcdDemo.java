package com.quan.flink.demo;

import com.quan.flink.util.EtcdUtils;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.Lease;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.kv.PutResponse;
import io.etcd.jetcd.lease.LeaseGrantResponse;
import io.etcd.jetcd.options.PutOption;

import java.util.concurrent.CompletableFuture;

public class EtcdDemo {
    public static void main(String[] args) {

        crudDemo();
    }

    private static void crudDemo() {
        try (// 连接到etcd服务器
             Client client = EtcdUtils.getClient()) {
            // 获取KV客户端
            KV kvClient = client.getKVClient();

            // 异步put操作
            ByteSequence key = ByteSequence.from("hello".getBytes());
            ByteSequence value = ByteSequence.from("world".getBytes());
            CompletableFuture<PutResponse> putResponseFuture = kvClient.put(key, value);
            // 同步获取结果
            PutResponse putResponse = putResponseFuture.get();

            // 输出结果
            System.out.println("Put response: " + putResponse);

            // 获取并输出前面put的值
            CompletableFuture<GetResponse> getResponseFuture = kvClient.get(key);
            GetResponse getResponse = getResponseFuture.get();
            getResponse.getKvs().forEach(kv -> System.out.println("Get response: " + kv));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void distributedLockDemo() {
        int ttl = 5;
        String lockKey = "lock-key";
        try (// 连接到etcd服务器
             Client client = EtcdUtils.getClient()) {
            // 获取分布式锁客户端
            KV kvClient = client.getKVClient();
            Lease leaseClient = client.getLeaseClient();
            LeaseGrantResponse lease = leaseClient.grant(ttl).get();

            // 添加锁
            CompletableFuture<PutResponse> lockFuture = kvClient.put(
                    ByteSequence.from(lockKey.getBytes()),
                    ByteSequence.from("lock-value".getBytes()),
                    PutOption.builder()
                            .withLeaseId(lease.getID())
                            .build()
            );
            lockFuture.get();
        } catch (Exception e) {
            EtcdUtils.getClient().getKVClient().delete(ByteSequence.from(lockKey.getBytes()));
            throw new RuntimeException(e);
        }
    }
}