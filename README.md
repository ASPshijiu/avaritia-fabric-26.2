# Avaritia 26 (Unofficial)

面向 Minecraft Java 26.2 的非官方 Fabric 无尽贪婪适配。经典核心与本项目选定的 Re:Avaritia 扩展功能均已完成，当前发布通道为 `1.0.0-rc.1`。

## 运行要求

- Minecraft Java `26.2`
- Java `25`
- Fabric Loader `0.19.3` 或兼容的更新版
- Fabric API `0.155.2+26.2`

客户端和专用服务器都必须安装本模组与 Fabric API。不需要 Porting Lib、Architectury、JEI、REI、EMI、Mod Menu 或配置库。

## 安装

1. 安装 Minecraft 26.2 与 Fabric Loader。
2. 将 `avaritia-fabric-26.2-1.0.0-rc.1.jar` 和 Fabric API 放入实例的 `mods` 目录。
3. 客户端直接启动；专用服务器接受 EULA 后正常启动即可。

本项目使用新的 `avaritia26` 命名空间，只保证新建世界；不承诺从旧版 Avaritia、Re:Avaritia 或其他移植版迁移存档与物品 ID。升级前请备份世界。

## 内容与验证

- 9×9 终极合成、压缩配方、中子收集器/压缩机、数据驱动奇点与大容量箱子。
- 经典材料链、食物、武器、工具、护甲，以及选定的 Re:Avaritia 扩展物品和方块。
- 14 个默认奇点；另有 13 个科技奇点在对应 `c:ingots/*` 标签由其他模组提供时自动启用。
- 中英文资源、进度解锁、专用服务器、单人世界、OpenGL 与实验性 Vulkan 路径。

逐项状态见 [FEATURE_MATRIX.md](FEATURE_MATRIX.md)，可复核的测试结果见 [TEST_REPORT.md](TEST_REPORT.md)，发布限制见 [KNOWN_ISSUES.md](KNOWN_ISSUES.md)。

## 构建与校验

```bash
JAVA_HOME=/path/to/java-25 ./gradlew clean build
```

`check` 会自动解析全部 JSON，并检查配方与进度、方块与战利品表、中英文键以及模型纹理引用的一致性。构建输出位于 `build/libs/`。

工具链基线：Fabric Loom `1.17.16`、Gradle `9.5.1`。完整历史设计见 [DEVELOPMENT_PLAN.md](DEVELOPMENT_PLAN.md)。

## 项目身份与许可证

本项目是社区维护的非官方适配，与 Avaritia 原作者及其他移植项目没有官方关系。

新编写的代码采用 [MIT](LICENSE) 许可证。来自 Re:Avaritia 的纹理与声音素材采用 CC BY-NC-SA 4.0，因此包含这些素材的构建产物仅可用于非商业用途；逐项署名、修改说明和许可证链接见 [ASSETS.md](ASSETS.md)，固定行为参考见 [UPSTREAM_SOURCES.md](UPSTREAM_SOURCES.md)。
