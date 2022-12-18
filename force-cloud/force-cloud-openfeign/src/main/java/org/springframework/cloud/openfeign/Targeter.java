package org.springframework.cloud.openfeign;

import feign.Feign;
import feign.Target;
import org.springframework.cloud.openfeign.FeignClientFactoryBean;
import org.springframework.cloud.openfeign.FeignContext;

/**
 * Targeter
 *
 * @author Force-oneself
 * @date 2022-11-25
 */
public interface Targeter {

    <T> T target(FeignClientFactoryBean factory, Feign.Builder feign, FeignContext context,
                 Target.HardCodedTarget<T> target);
}
