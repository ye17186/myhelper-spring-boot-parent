package io.github.ye17186.myhelper.web.slf4j;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.TtlLogbackMDCAdapter;
import org.slf4j.spi.MDCAdapter;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.lang.NonNull;

/**
 * @author yemeng20
 */
@Slf4j
public class TtlMDCAdapterInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {

        MDCAdapter ignore = TtlLogbackMDCAdapter.getInstance();
        log.info("【MyHelper】【Slf4j】 加载TtlLogbackMDCAdapter成功");
    }
}
