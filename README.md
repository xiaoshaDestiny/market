调研 COLA 生成的应用结构之后，发现缺失防腐层、对包结构并未增加严格的访问限制，浅述一下自己目前对领域驱动设计的理解，也动手实践一个代码功能划分清晰、代码组织力强的应用结构。
帮助： 

1、理解领域驱动设计

2、在并不那么规范的实际开发中运用它，快乐、专业、稳定、无痛的组织代码

# 一、扒一扒
COLA 
GitHub  [https://github.com/alibaba/COLA](https://github.com/alibaba/COLA)
官方介绍  [https://blog.csdn.net/significantfrank/article/details/110934799](https://blog.csdn.net/significantfrank/article/details/110934799)

# 二、想一想
大部分数据密集型的Java业务应用，业务建设工作可以盘点一下：

## 1、服务于自身产品的功能建设（服务生成）

代码体现：依赖DB、ES、Oss 等设施，且这些设施的 Owner 就是应用所有人，也就是本应用开发者，可以对这些设施维护（变更、升级、兼容等）

## 2、依赖其他服务提供在运行时的支持（服务消费）

代码体现：消费其他服务的RPC、Http形式接口，在领域驱动设计中，这部分代码将会移到Facade（防腐层），通常的步骤是：建立二方服务依赖，传参调用、处理异常、返回结果解析和使用

## 3、其他服务也必然会依赖本应用提供的服务（服务提供）

代码体现：服务提供API，并在内部实现API，需要将服务本身的结果，转换为标准的输入输出，通过网络形式传递给二方或三方系统。

二方定义：公司内部应用，共用一套服务注册发现中心，可以直接提纲RPC、Feign形式接口
三方定义：其他公司，一般在做开放服务接口时会需要，此时需要提供HTTP形式接口，并开放服务，需要考虑信息安全问题

定时任务：本质上是服务应用自身的，帮助产品建设的功能模块

消息发送：将主动调用二方服务，替换为发送消息并的异步解耦形式，最终转换为了基础设施调用

消息消费：将二方服务调用自身的过程，替换为自身主动去获取数据，最终转换为了服务提供

# 三、看一看结果
**框架要做什么：对DDD方法论落地，在代码编辑时对开发人员进行限制，当编码时引入了一个不应当在该位置出现的结构或者服务，IDE环境能给出不可用提示。**

| 限制 | 是否满足                                                                  |
| --- |-----------------------------------------------------------------------|
| 适配层，只可访问应用层服务，对领域模型、数据库对象、二方结果等限制不能访问 | 满足                                                                    |
| 应用层，作为实际入口，对外接入前端、Cilent端的参数，只可见参数、返回值、领域模型。无领域建设必要时，可以直接访问基础设施接口 | 满足                                                                    |
| 领域层，领域层是否可以直接使用基础设施的实现？严格规范来说，不可以。如果不可访问，导致Manager和Mapper是必须实现的，代码冗余度会加剧。考虑再三，决定将该限制放开，在领域层可以直接使用Mapper | 未严格限制，根据需要开放可选。严格规范： Domain - Manager - Mapper 不限制：可直接 Domain -Mapper |
| 基础设施层，不可将上层所有服务和模型，领域模型、接口出入参等均不可见 | 满足                                                                    |
| 防腐层：不可将上层所有服务和模型，仅对接二方 | 满足                                                                    |


举例，以「ProjectModel」来看下代码的组织形式，先梳理下最长链路会使用的结构

- **Adapter**
   - PC前端入参：ProjectModifyDTO
   - PC前端出参：SingleResponse<Boolean>
- **Application**
   - API入参定义：ProjectCreateRequest
   - API出参定义：RpcResponse<ProjectInfoDTO>
- **Domain**
   - 领域模型：ProjectModel
- **Infrastructure**
   - 持久层：ProjectDO
- **Facade**
   - 防腐层结构：TenantDTO
   - RPC层二方返回原始结果：RpcResponse<TenantInfo>


| 层级                   | 职责范围                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | 服务、结构 可见范围                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
|----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Adapter**          | 前端适配：ProjectController.java 接收前端参数、定义访问路径、请求交给应用层处理 ![image.png](https://cdn.nlark.com/yuque/0/2024/png/2355110/1709704060198-e8f9d0d6-a885-4c1c-bdf7-a313f1fed843.png#averageHue=%231f2124&clientId=ufb11f149-e9ee-4&from=paste&height=555&id=uefc396b0&originHeight=555&originWidth=1024&originalType=binary&ratio=1&rotation=0&showTitle=false&size=51053&status=done&style=none&taskId=u8f7bfc33-65d9-4a1e-bb36-2c34cc45514&title=&width=1024)                               | 访问范围：服务：仅仅 Application  服务、对外 API 服务 结构：仅前端和API层面定义的参数 ![image.png](https://cdn.nlark.com/yuque/0/2024/png/2355110/1709704477396-bb9394cb-6460-4d2c-a72b-eb0b5943b586.png#averageHue=%231f2024&clientId=ufb11f149-e9ee-4&from=paste&height=246&id=uf2f5b671&originHeight=849&originWidth=956&originalType=binary&ratio=1&rotation=0&showTitle=false&size=75606&status=done&style=none&taskId=u7f438c98-acfd-41a2-805c-ffc671873de&title=&width=277)                                                                                       |
| **Application**      | 应用层：ProjectApplicationServiceImpl.java 参数处理校验、转换结构、调用领域服务、结果处理 ![image.png](https://cdn.nlark.com/yuque/0/2024/png/2355110/1709704831190-3c0a63df-9a8d-4f8c-883b-f9a8cbb09e8c.png#averageHue=%23202125&clientId=ufb11f149-e9ee-4&from=paste&height=490&id=ufe680ef0&originHeight=927&originWidth=895&originalType=binary&ratio=1&rotation=0&showTitle=false&size=77003&status=done&style=none&taskId=uc76e14a9-4533-48fe-ba1e-aa322ceedd4&title=&width=473)                      | 访问范围： 服务：API 与 Application 同级别可见、领域服务、基础设施层服务可见，但不能直接访问基础设层的Mapper实现、同理对防腐层的结果可见，但是对二方依赖的处理不能直接使用 结构：Adapter层的出入参、领域模型、支持模型可见，对DO、二方服务的原始结果不可见 ![image.png](https://cdn.nlark.com/yuque/0/2024/png/2355110/1709704901428-bfde98e2-3555-4b9f-b044-f24feb2142dd.png#averageHue=%231f2024&clientId=ufb11f149-e9ee-4&from=paste&height=855&id=u59d3246d&originHeight=855&originWidth=954&originalType=binary&ratio=1&rotation=0&showTitle=false&size=75343&status=done&style=none&taskId=ufa8dcb52-d6af-480c-8e59-e2e402b7022&title=&width=954) |
| **Domain**           | ProjectDomainService.java对领域对象校验、依赖基础设施和防腐结果，完成领域模型的建设 ![image.png](https://cdn.nlark.com/yuque/0/2024/png/2355110/1709705290643-f0394fa1-6c3e-4468-ba27-a0744cb24fb1.png#averageHue=%23202125&clientId=ufb11f149-e9ee-4&from=paste&height=390&id=u6e8027e4&originHeight=766&originWidth=765&originalType=binary&ratio=1&rotation=0&showTitle=false&size=54185&status=done&style=none&taskId=uf5877e9b-fed2-404e-8c9a-e87f48e27d6&title=&width=389)                              | 访问范围： 服务： 上层不可见，下层防腐层接口、基础设施等都可见。**对于二方原始结果、Mapper等服务，这个单独再说，目前暂定可见。**![image.png](https://cdn.nlark.com/yuque/0/2024/png/2355110/1709707263810-781bd062-d753-4744-b269-996417f41dfb.png#averageHue=%231f2023&clientId=ufb11f149-e9ee-4&from=paste&height=856&id=ud20ef3e8&originHeight=856&originWidth=953&originalType=binary&ratio=1&rotation=0&showTitle=false&size=75878&status=done&style=none&taskId=u5ff7320f-a7a5-4102-987b-b5215337756&title=&width=953) |
| **Infrastructure**  **Facade** | 基础设施和防腐结果在层级上是一致的，原因是：都是为领域模型提供支撑，支持的来源可以是二方服务（防腐），也可以是自身可维护的DB、Oss、ES、Redis等（基础设施） ![image.png](https://cdn.nlark.com/yuque/0/2024/png/2355110/1709707730731-ed8a50ee-fff6-43e0-be05-0cc56ac76e8e.png#averageHue=%231f2124&clientId=ufb11f149-e9ee-4&from=paste&height=451&id=u5d6f5729&originHeight=901&originWidth=819&originalType=binary&ratio=1&rotation=0&showTitle=false&size=62959&status=done&style=none&taskId=uabb087ad-b235-4d88-82c1-90f4fc3760f&title=&width=410) | 可见范围：上层不可见，基础设施不可见防腐的原始结果，防腐不可见Mappper![image.png](https://cdn.nlark.com/yuque/0/2024/png/2355110/1709707868851-67dfd8f7-cc00-4f8b-be1d-9f7ad5b0bade.png#averageHue=%231f2024&clientId=ufb11f149-e9ee-4&from=paste&height=809&id=udc0a10b5&originHeight=809&originWidth=1001&originalType=binary&ratio=1&rotation=0&showTitle=false&size=76136&status=done&style=none&taskId=ufa0931a7-f8c3-41a7-9e9b-3ea14e0c7bc&title=&width=1001) |


# 四、结果展示（欢迎吐槽）
对COLA安装后的项目依赖进行改造，github：[https://github.com/xiaoshaDestiny/market](https://github.com/xiaoshaDestiny/market)
![image.png](https://cdn.nlark.com/yuque/0/2024/png/2355110/1709709682935-dd69b182-56f6-4c16-9ecc-ee1603abfe80.png#averageHue=%232f3236&clientId=ufb11f149-e9ee-4&from=paste&height=432&id=u9fc1d933&originHeight=432&originWidth=552&originalType=binary&ratio=1&rotation=0&showTitle=false&size=27331&status=done&style=none&taskId=ub3b11e97-f869-4341-b700-4a0ad94ca7e&title=&width=552)

| 模块名称 | 依赖项目内                                                                             | 依赖COLA                            |
| --- |-----------------------------------------------------------------------------------|-----------------------------------|
| market-adapter | market-application、market-component-common、market-component-client                |                                   |
| market-application | market-component-common、market-component-client、market-domain-core、market-domain-adapter |                |
| market-component-client | 无  该包给二方用，RPC参数                                         | cola-component-exception、cola-component-dto |
| market-component-common | 无  该包给二方用，前端请求结构                                     | cola-component-exception、cola-component-dto |
| market-domain-adapter | 无  该包定义了Domian 依赖什么样的接口完成自己的核心工作                 | cola-component-exception、cola-component-domain-starter |
| market-domain-core | market-domain-adapter、market-facade、market-infrastructure                         | |
| market-facade | market-domain-adapter                                                             | |
| market-infrastructure | market-domain-adapter                                                             | |
| start | market-domain-adapter                                                             | |

