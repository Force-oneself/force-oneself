package com.quan.boot.mvc;

import com.quan.boot.mvc.controller.MvcControllerTest;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author Force-oneself
 * @date 2023-09-24
 */
@SpringBootConfiguration
@Import({MvcControllerTest.class})
public class BootMvcApplicationTest {

}
