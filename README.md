# naive-rpc
A simple RPC exercise program.

## 配置

### 服务提供端

依赖：
```xml
    <dependency>
        <groupId>com.goribun</groupId>
        <artifactId>naive-server</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```
配置文件：naive-rpc.properties

可配置RPC接口扫描路径

```
    scanPackage=cn.wangxs.crm.*.service.*
```

可配置项目名

```
    appName=crm
```

### 服务调用端

依赖：
```xml
    <dependency>
        <groupId>com.goribun</groupId>
        <artifactId>naive-client</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```

配置文件：naive-rpc.properties

可配置服务host（约定为appName=http://xxx.com）

```
    crm=http://api.crm.wangxs.cn
```

## Demo

- 提供方

```java
    @RpcService
    public interface IDemoService {
        String sayHello(User user, Activity activity);
    }
```

- 调用方方

 ```java
     IDemoService demoService = RpcClient.refer(IDemoService.class);
     demoService.sayHello(user, activity);
 ```
 
## TODO

- client打包

- 异常处理及传递
