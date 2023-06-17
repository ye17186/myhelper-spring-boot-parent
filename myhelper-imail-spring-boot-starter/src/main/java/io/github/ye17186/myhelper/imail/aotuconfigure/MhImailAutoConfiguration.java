package io.github.ye17186.myhelper.imail.aotuconfigure;

import io.github.ye17186.myhelper.imail.aotuconfigure.properties.MhImailProperties;
import io.github.ye17186.myhelper.imail.mapper.SysImailGroupMapper;
import io.github.ye17186.myhelper.imail.service.MhSysImailService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 站内信模块自动配置
 *
 * @author ye17186
 * @since 2023-02-08
 */
@AutoConfiguration
@EnableConfigurationProperties(MhImailProperties.class)
@MapperScan("io.github.ye17186.myhelper.imail.mapper")
public class MhImailAutoConfiguration {

    /**
     * 站内信发布器
     */
    @Bean
    public MhSysImailService imailPublisher() {

        return new MhSysImailService();
    }
}
