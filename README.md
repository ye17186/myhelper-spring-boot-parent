# myhelper-spring-boot-parent
## 使用方法
### maven坐标
```xml
<dependency>
    <groupId>io.github.ye17186</groupId>
    <artifactId>myhelper-xxx-spring-boot-starter</artifactId>
    <version>0.0.9</version>
</dependency>
```

### 各模块使用介绍
这里的文档待补充

## 更新日志
### V0.0.9
#### myhelper-web-spring-boot-starter
- MhLoginInterceptor支持多账户提现登录

### V0.0.8
#### myhelper-core-spring-boot-starter
- 新增密码处理相关工具类 PasswordUtils
- 新增全局唯一请求ID BaseRequest#mhTraceId
- IdUtils新增基于日期的唯一ID
- CollectionUtils支持对数组进行判空
- 新增密码工具类，并支持基于zxcvbn的密码强度预估
- SpringBoot默认线程池支持`keepAliveSeconds`参数，核心线程数、最大线程数默认值分别修改为`1`、`200`
- guava版本升级 `31.1-jre` -> `33.2.0-jre`
- easyexcel版本升级 `3.3.2` -> `3.3.4`
#### myhelper-web-spring-boot-starter
- 系统日志模块升级，可参考：MhWebApiAdviceAUtoConfiguration
- 移动多个增强Handler的包
#### myhelper-mybatis-spring-boot-starter
- mybatis-plus版本升级 `3.5.5` -> `3.5.6`
#### myhelper-token-spring-boot-starter
- sa-token版本升级 `1.37.0` -> `1.38.0`
#### myhelper-knife4j-spring-boot-starter
- knife4j版本升级 `4.3.0` -> `4.5.0`

### V0.0.6
#### myhelper-core-spring-boot-starter
- CryptoUtils新增支持MD5加密
- 修正RSA拼写错误问题
- 新增字符串掩码工具类 DesensitizedUtils
- 移除部分无用代码
#### myhelper-datasource-spring-boot-starter
- druid版本升级 1.2.9 -> 1.2.16
#### myhelper-mybatis-spring-boot-starter
- mybatis-plus版本升级 3.5.3.2 -> 3.5.5
#### myhelper-oss-spring-boot-starter
- aws-java-sdk-s3版本升级 1.12.444 -> 1.12.470

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