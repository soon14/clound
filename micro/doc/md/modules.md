## 模块
``` lua
micro
├── micro-ui -- 前端工程[8000]
├── micro-auth -- 授权服务提供[3000]
├── micro-common -- 系统公共模块 
├    ├── common-core -- 公共工具类核心包
├    ├── common-data -- 数据相关
├    ├── common-job -- 定时任务
├    ├── common-log -- 日志服务
├    ├── common-minio -- 文件系统
├    └── common-security -- 安全工具类
├    └── common-swagger -- Swagger Api文档生成
├    └── common-transaction -- 分布式事务工具包
├── micro-config -- 配置中心[8888]
├── micro-eureka -- 服务注册与发现[8761]
├── micro-gateway -- Spring Cloud Gateway网关[9999]
├── micro-upms -- 通用用户权限管理模块
├    └── micro-upms-api -- 通用用户权限管理系统公共api模块
├    └── micro-upms-biz -- 通用用户权限管理系统业务处理模块[4000]
└── micro-visual  -- 图形化模块 
├    ├── micro-monitor -- Spring Boot Admin监控 [5001]
├    ├── micro-daemon -- 分布式调度中心[5002]
├    └── micro-code-gen -- 图形化代码生成[5003]
├    └── micro-tx-manager -- micro分布式事务解决方案[5004]
├    └── micro-activiti -- 工作流模块[5005]
	 
```
