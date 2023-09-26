package com.quan.boot.mvc.limit;

import com.quan.boot.mvc.config.RateLimitAutoConfig;
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
//@EnableWebMvc
public class RateLimitTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void rateLimit() throws Exception {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        Thread.sleep(200);
                        mvc.perform(get("/limit").accept(MediaType.APPLICATION_JSON))
                                .andExpect(result -> System.out.println(result.getResponse().getContentAsString()));
                    } catch (Exception ignore) {
                        System.err.println("限流");
                    }
                }
            }).start();
        }
        Thread.currentThread().join();
    }

}


