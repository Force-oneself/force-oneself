package com.quan.boot.mvc.limit;

import com.quan.boot.mvc.config.RateLimitAutoConfig;
import com.quan.boot.mvc.limit.local.LeakyBucketRateLimiter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @author Force-oneself
 * @date 2023-09-25
 */
@RunWith(SpringRunner.class)
@WebMvcTest
@Import({RateLimitAutoConfig.class})
public class RateLimitTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void mvcLimit() throws Exception {
//        start("/limit/lsw");
//        start("/limit/lc");
//        start("/limit/llb");
        start("/limit/ltb");
    }

    @Test
    public void limit() throws InterruptedException {
//        start(new TokenBucketRateLimiter(2));
        start(new LeakyBucketRateLimiter(5, 1));
//        start(new SlidingWindowRateLimiter(1000, 100));
//        start(new CounterRateLimiter(1000, 100));
    }

    private static void start(RateLimiter r) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
//                        Thread.sleep(200);
                    if (r.rateLimit()) {
                        System.out.println("成功");
                    }
                }
            }).start();
        }
        Thread.currentThread().join();
    }

    private void start(String path) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(200);
                        mvc.perform(get(path).accept(MediaType.APPLICATION_JSON))
                                .andExpect(result -> System.out.println(System.currentTimeMillis() + ": " + result.getResponse().getContentAsString()));
                    } catch (Exception ignore) {
//                        System.err.println("限流");
                    }
                }
            }).start();
        }
        Thread.currentThread().join();
    }


}


