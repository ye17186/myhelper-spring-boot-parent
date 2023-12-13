package io.github.ye17186.myhelper.mybatis.autoconfigure.properties;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author ye17186
 * @since 2023-02-08
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.my-helper.mybatis")
public class MhMybatisProperties {

    private boolean enabled = true;
    /**
     * 基础配置
     */
    @NestedConfigurationProperty
    private MybatisConfig config = new MybatisConfig();

    /**
     * 逻辑删除配置
     */
    @NestedConfigurationProperty
    private MybatisLogicDelProperties logicDelConfig = new MybatisLogicDelProperties();

    @Getter
    @Setter
    public static class MybatisConfig {

        /**
         * mapper xml所在位置
         */
        private String[] mapperLocations;

        /**
         * mapper扫描路径
         */
        private String[] mapperScans;

        /**
         * 数据库ID类型，默认自增
         */
        private IdType idType = IdType.AUTO;
    }

    @Setter
    @Getter
    public static class MybatisLogicDelProperties {

        private boolean enabled = true;

        private String filed = "deleted";

        private String delValue = "1";

        private String notDelValue = "0";
    }
}
