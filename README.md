# 项目介绍
自定义的spring-boot的dubbo starter，为spring-boot相关的项目使用dubbo提供简易的方式并集成spring-boot的auto configuration

# 版本

| 本项目版本 | dubbo版本       |
| ----- | ------------- |
| 1.0.0 | dubbox自定义版本2.8.4-banyan |

# 打包
修改相关的maven私服地址

```shell
gradle clean install
```

# 使用方式
## 依赖

```shell
compile "org.banyan.spring.boot:spring-boot-starter-dubbo:1.0.0"
```

## 集成
在spring-boot项目的application.properties文件中加入相关的配置项，并赋予正确的值
- spring.service.dubbo.registry.name=xxx
- spring.service.dubbo.registry.address=ip1:2181,ip2:2181,ip3:2181
- spring.service.dubbo.registry.check=true
- spring.service.dubbo.registry.register=true
- spring.service.dubbo.registry.protocol=zookeeper
- spring.service.dubbo.registry.retries=0
- spring.service.dubbo.registry.timeout=60000
- spring.service.dubbo.registry.protocolName=dubbo
- spring.service.dubbo.registry.payload=10485760

## 使用
### 生产者
- 将上述配置项赋予正确的值
- 接口定义

``` java
public interface ServiceE {

    public String invokeE();
}
```

- 服务实现

``` java
@Service
public class ServiceEImpl implements ServiceE {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceEImpl.class);

    @Override
    public String invokeE() {
        LOGGER.info("调用服务E");
        return "E";
    }
}
```

- 暴露服务

```java
@Configuration
public class DubboConfiguration {

    @Autowired
    private DubboService dubboService;
    @Autowired
    private ServiceE serviceEImpl;

    @PostConstruct
    public void exportDubboService() {
        this.dubboService.export(ServiceE.class, this.serviceEImpl);
    }
}
```

### 消费者
- 将上述配置项赋予正确的值(最后两项配置项无需配置)
- 服务引用

```java
@Configuration
public class DubboConfiguration {

    @Autowired
    private DubboService dubboService;

    @Bean
    public ServiceE serviceE() {
        return this.dubboService.get(ServiceE.class);
    }
}
```

- 服务调用

```java
@Service
public class ServiceCImpl implements ServiceC {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceCImpl.class);

    @Autowired
    private ServiceE serviceE;

    @Override
    public String invokeC() {
        LOGGER.info("调用服务E");
        return this.serviceE.invokeE();
    }
}
```
