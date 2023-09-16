package com.quan.cloud.gateway.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.function.Predicate;

/**
 * @author Force-oneself
 * @date 2023-09-13
 */
public class DemoAbstractRoutePredicateFactory extends AbstractRoutePredicateFactory<DemoAbstractRoutePredicateFactory.Config> {

    public DemoAbstractRoutePredicateFactory(Class<Config> configClass) {
        super(configClass);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return null;
    }

    public static class Config {
        private @NotNull ZonedDateTime datetime;

        public Config() {
        }

        public ZonedDateTime getDatetime() {
            return this.datetime;
        }

        public void setDatetime(ZonedDateTime datetime) {
            this.datetime = datetime;
        }
    }
}
