package com.ye186.thirdparty.myhelper.mybatis.customizer;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.ye186.thirdparty.myhelper.mybatis.aotuconfigure.properties.MhMybatisProperties;

/**
 * @author ye1718620
 * @date 2023-02-09
 */
public class MhMybatisPlusPropertiesCustomizer implements MybatisPlusPropertiesCustomizer {

    private final MhMybatisProperties properties;

    public MhMybatisPlusPropertiesCustomizer(MhMybatisProperties properties) {
        this.properties = properties;
    }

    @Override
    public void customize(MybatisPlusProperties mpProperties) {

        customizeConfig(mpProperties, this.properties.getConfig());
        customizeLogicDel(mpProperties.getGlobalConfig().getDbConfig(), this.properties.getLogicDelConfig());
    }

    /**
     * 定制化Mybatis配置
     *
     * @param mpProperties mp配置
     * @param config       Mybatis配置
     */
    private void customizeConfig(MybatisPlusProperties mpProperties, MhMybatisProperties.MybatisConfig config) {

        if (config.getMapperLocations() != null) {
            mpProperties.setMapperLocations(config.getMapperLocations());
        }

        if (config.getIdType() != null) {
            mpProperties.getGlobalConfig().getDbConfig().setIdType(config.getIdType());
        }
    }

    /**
     * 定制化逻辑删除
     *
     * @param mpDbConfig    mp db 配置
     * @param delProperties 逻辑删除配置
     */
    private void customizeLogicDel(GlobalConfig.DbConfig mpDbConfig,
                                   MhMybatisProperties.MybatisLogicDelProperties delProperties) {

        if (delProperties.isEnabled()) {
            mpDbConfig.setLogicDeleteField(delProperties.getFiled());
            mpDbConfig.setLogicDeleteValue(delProperties.getDelValue());
            mpDbConfig.setLogicNotDeleteValue(delProperties.getNotDelValue());
        } else {
            mpDbConfig.setLogicDeleteField(null);
        }
    }
}
