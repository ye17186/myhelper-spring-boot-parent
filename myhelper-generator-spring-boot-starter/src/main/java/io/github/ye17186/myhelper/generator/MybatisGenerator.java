package io.github.ye17186.myhelper.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import io.github.ye17186.myhelper.mybatis.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;

/**
 * @author ye17186
 * @since 2023-03-01
 */
public class MybatisGenerator {

    @Getter
    @Builder
    public static class Config {

        private String host;
        private String username;
        private String password;
        private String parentPkg;
        private String entityPkg;
        private String mapperPkg;
        private String xmlPkg;
        private String xmlDir;
        private String outputDir;
        private String tbPrefix;

        public void run(String... tbName) {

            MybatisGenerator.run(this, tbName);
        }
    }

    public static Config.ConfigBuilder config() {

        return new Config.ConfigBuilder();
    }

    private static void run(Config config, String... table) {

        FastAutoGenerator.create(config.getHost(), config.getUsername(), config.getPassword())
                .globalConfig(builder -> builder
                        .author("ye17186")
                        .outputDir(config.getOutputDir())
                        .disableOpenDir()
                )
                .packageConfig(builder -> builder
                        .parent(config.getParentPkg())
                        .entity(config.getEntityPkg())
                        .mapper(config.getMapperPkg())
                        .pathInfo(Collections.singletonMap(OutputFile.xml, config.getXmlDir()))
                )
                .strategyConfig(builder -> builder
                        .addInclude(table)
                        .addTablePrefix(config.getTbPrefix())
                        .entityBuilder()
                        .enableLombok()
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .superClass(BaseEntity.class)
                        .formatFileName("%sEntity")
                        .enableFileOverride()
                        .build()
                        .mapperBuilder()
                        .formatMapperFileName("%sMapper")
                        .formatXmlFileName("%sMapper")
                        .enableFileOverride()
                        .build())
                .templateEngine(new FreemarkerTemplateEngine())
                .templateConfig(builder -> builder
                        .disable(TemplateType.CONTROLLER, TemplateType.SERVICE, TemplateType.SERVICE_IMPL))
                .execute();
    }
}
