# myhelper-spring-boot-parent
## 使用方法
### maven坐标
```xml
<dependency>
    <groupId>io.github.ye17186</groupId>
    <artifactId>myhelper-xxx-spring-boot-starter</artifactId>
    <version>0.0.5</version>
</dependency>
```

### 各模块使用介绍
这里的文档待补充

## 更新日志

### V0.0.5
#### myhelper-core-spring-boot-starter
- 新增CryptoUtils加解密工具类，支持RSA、AES加解密
#### myhelper-datasource-spring-boot-starter
- 新增myhelper-datasource-spring-boot-starter模块

### V0.0.4
#### 基础
- spring-boot版本升级 `2.7.8` -> `2.7.15`
- easy-excel支持

#### myhelper-oss-spring-boot-starter
- 直增支持`getObj()`方法，用于获取oss对象

#### myhelper-minio-spring-boot-starter
注意，本模块后续版本中将被移除，请使用`myhelper-oss-spring-boot-starter`代替
- 直增支持`getObj()`方法，用于获取oss对象

### V0.0.3
#### myhelper-core-spring-boot-starter
- 参数校验提示信息message问题修复

#### myhelper-job-spring-boot-starter
- 集成基于xxl-job的分布式调度模块

#### myhelper-token-spring-boot-starter
- 支持多账户体系的登录
- 模块部分代码优化

### V0.0.2 
#### myhelper-oss-spring-boot-starter
- 集成对象存储服务，兼容AWS、阿里云OSS、Minio

#### myhelper-web-spring-boot-starter
- 优化web应用的跨域配置