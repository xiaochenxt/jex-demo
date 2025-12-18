# Native Demo 项目说明

这是一个基于 Avaje 技术栈的轻量级原生 Web 服务项目。

## 技术架构

### 核心框架
- [avaje-jex](https://github.com/avaje/avaje-jex) - Web 服务器
- [avaje-inject](https://github.com/avaje/avaje-inject) - 依赖注入
- [avaje-jsonb](https://github.com/avaje/avaje-jsonb) - JSON 序列化
- [avaje-config](https://github.com/avaje/avaje-config) - 配置管理

### 构建工具
- Maven 构建
- 配置了 GraalVM 原生镜像插件 (native-maven-plugin)

### JDK 版本
- 使用 JDK 25

## 项目结构
```
src/main/
├── java/com/dc/
│   ├── WebApplication.java (应用入口)
│   └── HelloController.java (示例控制器)
├── resources/
│   ├── META-INF/native-image/
│   │   ├── native-image.properties (原生镜像配置)
│   │   └── resource-config.json (资源包含配置)
│   ├── static/ (静态资源目录)
│   │   └── index.html
│   └── application.properties (应用配置)
```

## 核心功能

### Web 应用入口 (WebApplication.java)
- 启动 Jex 服务器并配置端口 (默认 33111)
- 集成静态资源服务
- 配置文件上传功能
- 配置错误处理

### API 控制器 (HelloController.java)
- `/hello` GET 接口返回 "Hello World!"
- `/ip` GET 接口返回 IP 信息
- `/sse` GET 接口演示 Server-Sent Events
- `/upload` POST 接口处理文件上传

### 静态资源
- 包含一个简单的 index.html 页面

## 配置信息
- 应用端口：33111 (在 application.properties 中配置)
- 原生镜像最大堆大小：10MB
- 静态资源包含模式：`\Qstatic/*.*\E`

这个项目是一个典型的轻量级、高性能的原生 Web 服务，适合用于构建 REST API 和提供静态内容服务，并且已经配置好支持 GraalVM 原生镜像编译。