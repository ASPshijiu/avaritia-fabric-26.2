# 无尽贪婪（Avaritia）Minecraft 26.2 Fabric 适配开发计划

> 调查日期：2026-07-20
> 文档状态：实施前基线 v1（历史计划，已于 2026-07-21 完成首个 RC 实施）
> 实施结果：采用手工维护资源加 `validateResources` 构建门禁，替代原计划中的全量 DataGen；当前逐项状态与证据见 `FEATURE_MATRIX.md` 和 `TEST_REPORT.md`

## 1. 执行摘要

Minecraft Java 26.2 已于 2026-06-16 正式发布，不是快照版；Fabric 也已有针对 26.2 的正式 Loader、Fabric API 与 Loom 工具链。因此项目可以立即启动，不需要等待生态就绪。[Minecraft 26.2 发布说明](https://www.minecraft.net/en-us/article/minecraft-java-edition-26-2)｜[Fabric 26.2 迁移公告](https://fabricmc.net/2026/06/15/262.html)

本项目不应把旧 Fabric 分支直接改成 `26.2` 后逐个修编译错误。现有最完整的 Fabric 参考 Re:Avaritia 停留在 1.20.1，依赖 Porting Lib、Forge Config API Port、Fabric ASM 等旧平台层；Porting Lib 没有 26.2 版本，而且分支本身混有 Forge/NeoForge 兼容代码。直接升级会同时面对映射体系、注册、网络、数据生成、组件、渲染和依赖失效，风险不可控。

推荐路线是：

1. 新建一个单模块、原生 Java 25 的 Fabric 26.2 工程。
2. 以 Re:Avaritia 的 MIT 代码为主要业务逻辑参考，以其 Fabric 1.20.1 分支了解旧 Fabric 行为，以 NeoForge 1.21.1/26.1.2 版本核对现代行为。
3. 按功能纵向迁移，不复制旧平台适配层；核心运行依赖只保留 Fabric API。
4. 先完成静态模型与完整玩法，再单独攻克宇宙材质、护甲和容器等特殊渲染。
5. 把 JEI 等配方查看器放到后期可选适配，不让第三方模组阻塞核心发布。

预计工作量：**44–73 人日**。单名熟悉 Minecraft/Fabric 的开发者约 **9–15 周**；两名开发者合理分工约 **6–9 周**。该估算假设已有可合法使用的素材；若需要从零重绘纹理、模型、声音和着色器，应另开美术工作流并重新估算。

## 2. 调查结论与工程基线

### 2.1 锁定的目标版本

| 组件 | 固定版本 | 说明 |
|---|---:|---|
| Minecraft Java | `26.2` | 正式版，仅声明支持 26.2 |
| Java | `25` | 26.2 官方运行时要求 |
| Gradle Wrapper | `9.5.1` | Fabric 26.2 官方模板基线 |
| Fabric Loom | `1.17.16` | 固定正式版本，不用 `SNAPSHOT` |
| Fabric Loader | `0.19.3` | 26.2 稳定 Loader |
| Fabric API | `0.155.2+26.2` | 当前正式 26.2 版本，包含 DataGen 修复 |
| 数据包格式 | `107.1` | 26.2 最终格式 |
| 资源包格式 | `88.0` | 26.2 最终格式 |
| IDE | IntelliJ IDEA `2025.3+` | Java 25 与当前 Mixin 开发基线 |

版本依据：[26.2 Mojang 元数据](https://piston-meta.mojang.com/v1/packages/9e272020887b72a23b1a525c5d8e74d2d7aa8222/26.2.json)｜[Loader 版本数据](https://meta.fabricmc.net/v2/versions/loader/26.2)｜[Fabric API 0.155.2+26.2](https://github.com/FabricMC/fabric-api/releases/tag/0.155.2%2B26.2)｜[Loom 1.17.16](https://maven.fabricmc.net/net/fabricmc/fabric-loom/1.17.16/)｜[Fabric 26.2 示例工程](https://github.com/FabricMC/fabric-example-mod/tree/26.2)

### 2.2 26.2 必须正视的变化

- 26.1 起 Minecraft 不再混淆；26.2 直接使用 Mojang 随游戏发布的名称，不再添加 Yarn 或 `mappings` 依赖。[Fabric 26.1 公告](https://fabricmc.net/2026/03/14/261.html)
- 构建使用 `net.fabricmc.fabric-loom`、标准 Gradle `implementation`/`compileOnly` 和普通 `jar`，不能照抄旧工程的 `modImplementation`、`remapJar`。
- 方块、方块物品和普通物品的 ID/`ResourceKey` 分离；注册与 DataGen 都必须以 key 为基准。
- `valueLookupBuilder`、`FabricIntrinsicHolderTagsProvider` 等旧 DataGen 路径已移除。
- `FabricEntityTypeBuilder` 已移除；若实现无尽箭矢等自定义实体，使用 vanilla `EntityType.Builder`。
- OpenGL 仍是默认后端，Vulkan 是实验后端；直接调用 `org.lwjgl.opengl.*` 的旧渲染必须移除。
- 26.2 使用 reversed-Z 深度缓冲，并调整了渲染 extraction/submit 阶段。宇宙纹理、光环、护甲和箱子渲染是本项目最高技术风险。
- GUI/HUD、资源包、实体 predicate、部分 tag 和 shader 均有不兼容变化，不能只以“能编译”判定迁移成功。

迁移依据：[Fabric 26.2 迁移公告](https://fabricmc.net/2026/06/15/262.html)｜[Fabric 端口指南](https://docs.fabricmc.net/develop/porting/)｜[Fabric 渲染基础](https://docs.fabricmc.net/develop/rendering/basic-concepts)

### 2.3 上游候选评估

| 上游 | 可用价值 | 不能直接作为 26.2 基线的原因 | 本计划用途 |
|---|---|---|---|
| [Re:Avaritia](https://github.com/Nova-Committee/Re-Avaritia) | 代码与功能最完整；代码许可证明确为 MIT | Fabric 分支停在 1.20.1，平台依赖重；最新现代分支以 NeoForge 为主 | 主要代码、功能和数据参考 |
| [Avaritia 1.1x](https://github.com/Morpheus1101/Avaritia) | 经典行为、配方和 registry ID 参考；MIT | API 年代太旧，最后发布到 1.18.2 beta | 经典玩法对照，不移植平台代码 |
| [AvaritiaNeo](https://github.com/AquaThree/AvaritiaNeo) | 有较新的 Fabric 1.21.1 成品，可做运行行为对照 | 公开源码主要是 NeoForge 且构建文件不完整；源码与 Fabric 发布物不可完全对应 | 只做黑盒对照，不反编译复制 |
| [AvaritiaReborn](https://github.com/IAFEnvoy/AvaritiaReborn) | 纯 Fabric 结构相对简单 | 停在 1.20.1、已归档、功能未完成且许可证元数据有冲突 | 仅参考少量 Fabric 结构 |
| [原版 Avaritia](https://github.com/SpitefulFox/Avaritia) | 最早的玩法语义 | Forge 1.7.10；仓库授权信息与发布页需额外核实，原项目 FAQ 也不支持 Fabric 路线 | 不直接复制代码或素材 |

实施前必须把实际采用的上游 commit SHA 固定到 `UPSTREAM_SOURCES.md`。建议至少固定：Re:Avaritia `fabric/1.20.1` 作为旧 Fabric 平台行为参考、`neo/1.21.1` 或其 26.1.2 发布物作为现代行为参考。禁止开发过程中自动追踪上游移动分支。

## 3. 产品边界与“适配完成”的定义

### 3.1 1.0 必须交付的经典核心（P0）

#### 材料与成长链

- 钻石晶格、晶态矩阵锭、晶态矩阵方块。
- 中子堆、中子粒、中子锭、中子块。
- 无尽催化剂、无尽锭、无尽块。
- 终望珍珠、物质团、唱片碎片。
- 终极炖菜、寰宇肉丸。
- 内置奇点：铜、铁、金、青金石、红石、下界石英、钻石、绿宝石、紫水晶、下界合金。
- JSON 数据驱动的自定义奇点和无尽奇点。

#### 方块、机器与合成

- 压缩工作台、二重压缩工作台、9×9 终极工作台。
- 9×9 有序和无序配方、剩余物、配方同步与数据生成。
- 中子收集器。
- 中子压缩机及数据驱动压缩配方。
- 无尽箱子及其保存、同步和菜单。

#### 武器、工具、护甲与特殊物品

- 寰宇支配之剑、炽焰之啄颅剑。
- 无尽斧、镐、铲、锄与天堂陨落长弓。
- 无尽头盔、胸甲、护腿、靴子。
- 工具范围采掘、武器伤害、护甲飞行/免伤、长弓弹射物等经典能力。
- 手持、物品栏、掉落物、盔甲和方块形态的宇宙视觉效果。

#### 工程完整性

- `en_us` 和 `zh_cn` 本地化。
- 全部配方、tag、战利品表、进度、模型和纹理。
- 单人、局域网和专用服务器支持。
- 新世界保存、退出、重进和跨维度稳定。
- 无 JEI、Mod Menu、Cloth Config 等可选模组时仍可完整运行。

### 3.2 1.0 后交付（P1）

这些功能在 Gate 0 的功能矩阵里逐项核对；若用户的指定源版本确实包含且要求完全对齐，可将对应项上调到 P0：

- 无尽桶、额外中子收集器/压缩机等级。
- 更多终极工作台等级、终极锻造台/铁砧等扩展方块。
- 无尽十字弩、三叉戟、重锤、戒指、图腾、雨伞、时钟等 Re:Avaritia 扩展。
- 黑洞箱子、额外工具等级和额外奇点包。
- JEI 配方显示与转移；待 EMI/REI 发布 26.2 稳定版后再做相应适配。
- Mod Menu/配置界面。
- KubeJS、CraftTweaker、Jade、饰品槽及其他科技模组集成。

### 3.3 明确不在首版范围（P2）

- Forge、NeoForge 或 Architectury 多加载器架构。
- 同时支持 26.1、26.2、26.3 快照。
- 为旧版所有附属模组提供一对一兼容层。
- 在没有明确需求时建立公共 API、大型抽象框架或脚本语言。
- 将实验性 Vulkan 的画面做到与 OpenGL 像素级完全一致；首版 Vulkan 门槛是不崩溃、不丢模型、不出现粉黑/全黑材质。

## 4. Gate 0：开始编码前必须冻结的决策

当前仓库为空，尚不能确定“我的无尽贪婪”具体指哪个源码版本。以下四项不是文档问题，而是实施输入：

1. **行为基线**：指定要对齐的源码仓库、分支/commit、发布 JAR 和游戏版本。若无其他指定，按 Re:Avaritia 经典核心 + 现代版行为执行。
2. **项目身份**：确定正式名称、Maven group、包名和 `modid`。推荐新名称并注明“受 Avaritia 启发的非官方 Fabric 适配”，避免暗示官方关系。
3. **存档策略**：选择“全新 26.2 模组，不承诺旧存档兼容”或“保留指定旧版 namespace/registry ID，提供迁移”。默认推荐前者；后者会显著扩大测试和授权风险。
4. **发布与素材策略**：明确项目是私人使用、免费公开发布，还是涉及赞助/平台收益；据此决定是否可用 CC BY-NC-SA 素材，或必须取得许可/全部重绘。

Gate 0 交付物：

- `FEATURE_MATRIX.md`：源版本全部内容，逐项标记 P0/P1/P2、目标 registry ID、行为差异和测试状态。
- `UPSTREAM_SOURCES.md`：每个复用文件的来源 URL、commit、原许可证和本项目处理方式。
- `LICENSES/`、`NOTICE`：代码与素材分别归档授权文本和署名。
- 一份源版本行为录像/截图与测试世界，只用于对照，不直接复制不明来源内容。

## 5. 许可证与素材方案

Re:Avaritia 仓库的代码为 MIT；其 README 对素材采用 CC BY-NC-SA 4.0。两类文件不能用同一份许可证笼统覆盖。[Re:Avaritia LICENSE](https://github.com/Nova-Committee/Re-Avaritia/blob/16e3d6fd39d79b0c3376ce0c492d29133a9343c4/LICENSE)｜[Re:Avaritia 仓库](https://github.com/Nova-Committee/Re-Avaritia)

执行规则：

- 复用/改写 MIT 代码时保留版权与 MIT 许可文本，并在来源清单中记录文件映射。
- 复用纹理、模型、声音、字体或 shader 时，逐项确认素材来源；Re:Avaritia 素材按 CC BY-NC-SA 4.0 署名和同方式共享。
- 若计划商业化、接受收益或希望采用宽松素材许可证，先取得素材作者书面许可，或重绘全部素材。
- 不从许可证元数据不一致的 AvaritiaNeo Fabric JAR 反编译并复制实现。
- 不直接复制原始 SpitefulFox 仓库内容，除非完成授权核实。
- 发布页、README 和模组菜单中明确标注非官方项目并链接参考上游。

这是一套工程风险控制规则，不代替正式法律意见。

## 6. 技术架构

### 6.1 工程形态

- 单个 Fabric 模组、单个 Gradle module、Java 25。
- 使用 Mojang 官方名称；不引入 Yarn、Architectury、Porting Lib 或多加载器 common 层。
- 使用 Loom 的客户端/通用代码分离：通用入口不能引用 `net.minecraft.client` 类。
- 核心强依赖仅为 Fabric Loader + Fabric API。
- JEI、Mod Menu 等通过 `compileOnly` 和独立兼容入口接入，缺失时不加载相关类。
- 不为了未来可能的扩展提前设计插件系统；真正出现第二个实现后再抽象。

建议的最小包结构：

```text
<base-package>/
  AvaritiaMod.java
  registry/       # ID、ResourceKey 与注册
  content/        # item、block、entity、menu、block entity
  crafting/       # 9x9 与压缩配方
  singularity/    # Codec、reload、同步
  network/        # payload 与服务端校验
  datagen/        # recipe、tag、loot、model、lang
  client/         # screen、model、renderer、shader
  compat/jei/     # 后期可选适配
```

### 6.2 注册与 ID

- 分别建立 `ModBlockIds`、`ModBlockItemIds`、`ModItemIds`，先创建 `ResourceKey`，再传入 properties 和注册逻辑。
- Block、BlockItem、Item、EntityType、BlockEntityType、MenuType、RecipeType、RecipeSerializer、DataComponentType 各有单一注册入口。
- 每个 ID 在代码注册、DataGen 和功能矩阵中一一对应；禁止字符串散落在实现类中。
- 如果 Gate 0 要求旧存档兼容，源 namespace 和 path 必须在首次提交前冻结，后续不得随意改名。

### 6.3 数据与持久化

- 物品上的奇点 ID、工具模式、物质团内容等使用注册过的 Data Component，不再堆叠自定义 NBT 字符串。
- 方块实体只保存恢复玩法所需的数据；缓存、渲染状态和可重算数据不写入存档。
- 9×9 配方和压缩配方使用标准 `RecipeType`/`RecipeSerializer`，由数据包加载。
- 奇点采用有版本号的 Codec JSON，例如 `data/<namespace>/avaritia/singularities/*.json`；字段至少包括 ID、输入 tag/item、数量、颜色与显示名策略。
- 数据包重载时由服务端构建权威奇点表，再用 `CustomPacketPayload` + `StreamCodec` 同步客户端只读显示数据。
- 对 JSON 设置明确的字段校验、数量上限、重复 ID 错误和友好日志；不能静默丢弃坏数据。

参考：[Fabric 自定义数据组件](https://docs.fabricmc.net/develop/items/custom-data-components)｜[Fabric 网络文档](https://docs.fabricmc.net/develop/networking)

### 6.4 服务器权威与网络安全

- 合成、工具范围操作、机器进度、箱子内容和护甲能力全部以服务端为准。
- 客户端 payload 只表达意图，不直接提交结果；服务端校验当前菜单、距离、槽位、手持物、权限、冷却和数值范围。
- 所有集合长度、字符串长度、坐标范围和单次操作量都设置上限；坏包记录并拒绝，不造成崩溃或复制物品。
- 菜单关闭、维度切换、死亡、断线、区块卸载和数据包重载都必须能安全取消进行中的操作。

### 6.5 合成与机器

- 9×9 配方在输入槽变化时重算，不在每帧或每个服务端 tick 全量扫描。
- 有序配方支持固定、偏移和镜像语义；无序配方正确处理重复 ingredient、tag 和剩余物。
- 终极工作台 shift-click 必须通过专门测试防止越界和复制。
- 中子收集器的被动 tick 逻辑保持常数级；中子压缩机只在输入、配方或运行状态变化时做完整匹配。
- 区块卸载前持久化必要进度；重载后不得快进、回退或重复产物。
- 如支持漏斗/传输，明确每一侧的插入/取出规则，并以同一事务完成输入扣除和产物写入。

### 6.6 武器、工具与护甲

- Gate 0 先逐项记录源版本行为：作用范围、目标筛选、方块过滤、创造玩家/PvP、不可破坏方块、死亡掉落、飞行和伤害例外。
- 范围采掘只在服务端执行，并尊重世界边界、方块硬度、Fabric 破坏回调和第三方保护模组的拒绝结果。
- 大范围动作有硬上限；预计超过单 tick 预算时分批处理，不能一次同步破坏任意体积。
- 工具不得删除方块实体内容、穿越未加载区块或绕过不可破坏方块。
- 护甲飞行在穿上、脱下、死亡、登出、换维度和进入/退出创造模式时恢复正确能力，不能留下永久飞行状态。
- “无敌”与“秒杀”行为按行为矩阵实现，不能为了复刻视觉描述而绕过服务端权限或破坏存档。

### 6.7 客户端与宇宙渲染

实施分两步：

1. 先提供完整的普通 JSON 模型/纹理 fallback，确保所有物品、方块和盔甲可见且玩法可验收。
2. 再使用 Blaze3D/Fabric 后端无关的渲染接口实现宇宙纹理、光环、动画、盔甲和箱子效果。

约束：

- 禁止直接引用 `org.lwjgl.opengl.*`，禁止假设 OpenGL 全局状态。
- 自定义深度比较、投影、Z 偏移和 shader 全部按 reversed-Z 重新推导。
- 分别测试物品栏、GUI、第一/第三人称手持、掉落物、物品展示框、方块实体、实体装备和附魔光效叠加。
- OpenGL 是完整视觉验收后端；Vulkan 首版至少保证启动、进世界和上述场景不崩溃、不缺模型、不出现粉黑/全黑材质。
- 特效失败时回退到静态模型，不允许因为 shader 初始化失败导致客户端无法进入世界。

### 6.8 Mixin 规则

- 优先使用 vanilla/Fabric API 的注册、事件、回调和扩展点。
- 只有无法通过公共 API 实现的行为才加入 Mixin；每个 Mixin 在注释/清单中说明原因、目标方法和替代方案。
- 所有 target、Accessor 和 invoker 必须在 26.2 运行时验证。编译成功不等于注入成功。
- 每个关键 Mixin 至少有启动烟雾测试或 GameTest；注入计数不符合预期时应快速失败并给出清晰日志。

## 7. 实施阶段、工期与完成条件

| 阶段 | 工作内容 | 完成条件（Definition of Done） | 人日 |
|---|---|---|---:|
| 0. 基线与授权 | 冻结行为源、mod 身份、功能矩阵、registry ID、许可证与素材来源 | Gate 0 四项决策完成；所有 P0 有来源和验收描述 | 2–4 |
| 1. 工程脚手架 | Java 25、Gradle、Loom、Loader、FAPI、分离 source set、CI、日志和测试框架 | `clean build`、DataGen、客户端、专用服务器、最小 GameTest 全通过；无动态依赖 | 1–2 |
| 2. ID、材料与资源 | 注册框架、Data Component、材料链、基础方块、lang、model、tag、loot、recipe | 所有 P0 基础内容可获取/合成；启动与 DataGen 零错误；二次 DataGen 零 diff | 3–5 |
| 3. 9×9 合成 | 三类工作台、菜单、screen、有序/无序 serializer、剩余物、同步 | P0 配方全通过；shift-click/断线/重载无复制和丢失；专用服务器可用 | 5–8 |
| 4. 机器与奇点 | 收集器、压缩机、配方、数据驱动奇点、reload、客户端同步 | 内置与自定义奇点完整闭环；保存/区块卸载/重启/重载正确；100 台压力场景达标 | 6–10 |
| 5. 武器工具护甲 | 剑、弓、箭、五类工具、四件护甲、能力状态机 | 功能矩阵逐项通过；服务端权威；保护回调、死亡/换维度/登出与性能测试通过 | 8–12 |
| 6. 存储与特殊物品 | 无尽箱子、物质团、终望珍珠、食物及剩余 P0 逻辑 | 极限容量、嵌套、保存、菜单与网络测试无复制/崩溃；行为与基线一致 | 3–5 |
| 7. UI 与宇宙渲染 | 最终 GUI、宇宙材质、盔甲、方块实体、光环、粒子、fallback | OpenGL 视觉基准通过；Vulkan 最低门槛通过；无 raw OpenGL 调用 | 7–12 |
| 8. 数据与可选兼容 | 全量 DataGen、进度、JEI 可选适配、存档迁移（若选择） | FAPI-only 和 JEI-present 两种实例均通过；旧存档矩阵按 Gate 0 结论验收 | 4–7 |
| 9. 加固与发布 | 回归、性能、安全、多人、打包、文档、许可证、发布候选 | 全部门禁通过；无阻断/高危已知问题；可复现 JAR/source JAR 与测试报告齐全 | 5–8 |
| **总计** |  |  | **44–73** |

里程碑：

- **M1 可构建骨架**：阶段 1 完成。
- **M2 纵向切片**：一个材料 → 9×9 配方 → 压缩 → 奇点 → 无尽锭的闭环跑通。
- **M3 可玩 Alpha**：阶段 4 完成，预计累计 17–29 人日。
- **M4 功能完整 Beta**：阶段 6 完成。
- **M5 视觉完整 Beta**：阶段 7 完成。
- **M6 Release Candidate**：阶段 9 全部门禁通过。

## 8. 详细验证计划

### 8.1 每次合并的自动验证

- Java 25 环境运行 `./gradlew clean build`。
- 运行 DataGen 两次，第二次必须零文件变化；提交的生成资源必须与源码一致。
- 运行 Fabric Loader JUnit、服务端 GameTest 和适合自动化的客户端 GameTest。
- 启动无图形专用服务器，加载测试世界并检查日志中 registry、recipe、tag、loot、advancement、Mixin 错误。
- 扫描通用源码，禁止 `net.minecraft.client`；扫描全仓库，禁止 `org.lwjgl.opengl`、Yarn `mappings`、`remapJar` 和动态依赖版本。
- 构建产物记录 Git commit、依赖锁定信息和 SHA-256。

参考：[Fabric 自动化测试文档](https://docs.fabricmc.net/develop/automatic-testing)

### 8.2 合成测试矩阵

- 9×9 有序：准确位置、偏移、镜像、不应匹配的缺料/多料/错位。
- 9×9 无序：重复材料、同 tag 不同 item、空槽、数量边界。
- 剩余物：容器物品、耐久催化剂、背包已满、shift-click、连续快速合成。
- 数据包：添加、覆盖、删除、坏 JSON、重复 ID、服务运行中 `/reload`。
- 多人：两人同时打开/操作，延迟和断线发生在取出结果前后。

### 8.3 机器与存档测试矩阵

- 输入不足、数量刚好、超量输入、输出被占用、配方中途变化。
- 区块卸载/重载、服务端重启、维度卸载、机器被破坏时的内容回收。
- 漏斗或管道的每一面、同时插入/取出、满库存和无效输入。
- 100 台运行机器的基准场景：参考硬件上 p95 服务端 tick 保持低于 50 ms，模组机器逻辑总自耗目标低于 5 ms/tick；如未达标则优化后才能进入 Beta。
- 奇点数量和颜色同步在新加入、重连、数据包 reload 后一致。

### 8.4 武器、工具与护甲测试矩阵

- 生存/创造/旁观玩家、PVP 开关、驯服实体、Boss、无敌实体和高生命目标。
- 普通方块、不可破坏方块、含方块实体方块、流体、世界边界、未加载区块和受保护区域。
- 范围操作的朝向、模式切换、耐久/冷却、背包满、掉落物数量与 tick 尖峰。
- 全部伤害源、虚空、`/kill`、死亡保留规则；确切例外按 Gate 0 行为矩阵判定。
- 盔甲逐件穿脱、整套穿脱、死亡、重生、登出、换维度、创造模式切换，验证飞行能力不泄漏。
- 长弓的蓄力、无限弹药规则、弹射物保存/卸载、命中同步与友军伤害。

### 8.5 渲染测试矩阵

- OpenGL 与 Vulkan 各执行：启动 → 进世界 → 打开物品栏 → 手持 → 第三人称 → 掉落物 → 展示框 → 穿戴护甲 → 打开箱子 → 跨维度。
- 宇宙效果与附魔光效、透明方块、天气、夜视、GUI 缩放和不同 FOV 组合。
- reversed-Z 下近距离、远距离、与实体/方块交叠时的深度截图对照。
- shader 缺失、编译失败或后端不支持时验证静态 fallback。
- 使用固定测试世界和相机位生成截图，Release Candidate 与批准基准进行人工差异审查。

### 8.6 兼容与多人矩阵

| 实例 | 必测组合 |
|---|---|
| 最小客户端/服务端 | Loader + Fabric API + 本模组 |
| 配方查看 | 上述组合 + JEI 26.2（仅在采用其稳定/可接受版本后） |
| 菜单配置 | 上述组合 + Mod Menu/Cloth Config（只有实现配置 UI 时） |
| 多人 | 一个专用服务器 + 两个客户端，含高延迟、重连和恶意边界 payload |
| 存档 | 新世界；若 Gate 0 选择兼容，再加指定旧版世界的副本 |

截至调查日 JEI 已有 26.2 Fabric 构建，但处于快速迭代阶段；EMI 尚未出现可确认的 26.2 发布。因此查看器必须是可选增强，不能成为 1.0 核心依赖。[JEI 项目页](https://modrinth.com/mod/jei)

## 9. 不可跳过的发布门禁

### 9.1 构建门禁

- 固定 Java 25、Gradle 9.5.1、Loom 1.17.16、Loader 0.19.3、FAPI 0.155.2+26.2。
- 不允许 `SNAPSHOT`、`latest.release`、`+` 等动态版本。
- `fabric.mod.json` 至少约束：`minecraft: ~26.2`、`fabricloader: >=0.19.3`、`java: >=25`。
- 不声明支持 26.3 快照；升级另开分支和测试周期。

### 9.2 注册与数据门禁

- 每个 block/item/entity/block entity/menu/recipe/component 均在 ID 清单、注册和 DataGen 中一一对应。
- 启动日志没有重复注册、缺 ID、缺默认组件或客户端类加载错误。
- DataGen 二次运行零 diff；DP 107.1、RP 88.0 的所有 JSON 在客户端和专用服务器零加载错误。
- 所有 Mixin 在实际 26.2 运行时验证命中，不以编译通过代替。

### 9.3 功能与安全门禁

- `FEATURE_MATRIX.md` 的所有 P0 项为通过，差异均有明确批准。
- 单人、双人专服、新世界、保存重载、区块卸载、跨维度和数据包 reload 通过。
- 没有已知复制物品、存档损坏、绕过权限、远程崩服或无限内存/CPU 消耗问题。
- payload 的每个客户端输入均由服务端校验并有边界。

### 9.4 渲染门禁

- 仓库不含 raw OpenGL 调用。
- OpenGL 全部视觉场景通过。
- Vulkan 至少不崩溃、不缺模型、不出现粉黑/全黑材质；未达到完全一致的效果必须在发布说明中列出。
- shader 失败有 fallback，不能阻止玩家加载世界。

### 9.5 发布门禁

- 生成可复现的正式 JAR、source JAR、SHA-256、变更日志和已知问题清单。
- LICENSE、NOTICE、素材署名和逐文件来源清单随源码/分发包提供。
- 发布页明确依赖、仅支持 26.2、客户端与服务端安装要求、存档兼容结论和非官方身份。
- 使用正式候选 JAR 做一次干净客户端和干净专用服务器安装，不使用 IDE classpath 代替分发验证。

## 10. 风险登记

| 风险 | 等级 | 触发信号 | 控制措施 | 退出条件 |
|---|---|---|---|---|
| 素材授权/项目身份不清 | 高 | 无逐文件来源、计划商业化、沿用原品牌 | Gate 0 冻结来源；取得书面许可或重绘；新名称和非官方声明 | NOTICE 与素材清单审查完成 |
| 没有可直接升级的 26.2 Fabric 源码 | 高 | 大量 Forge/Porting Lib 类型进入新工程 | 干净 Fabric 工程；只逐功能移植业务逻辑 | 核心依赖仅 FAPI，平台层无遗留 |
| 宇宙渲染与 Vulkan/reversed-Z | 高 | 黑屏、粉黑纹理、深度穿插、崩溃 | 静态 fallback 先行；后端无关 API；固定截图场景 | OpenGL/Vulkan 门禁通过 |
| 功能基线模糊 | 高 | 多个上游行为冲突、不断追加功能 | 固定 commit/JAR 与 FEATURE_MATRIX | P0/P1/P2 获得确认并冻结 |
| 范围采掘/机器造成卡服 | 高 | tick >50 ms、跨未加载区块、实体爆量 | 服务端限额、分批、缓存匹配、压力测试 | 性能预算达标且无持续尖峰 |
| 菜单/网络导致复制或崩服 | 高 | shift-click、断线、双客户端竞态异常 | 服务端事务、payload 校验、故障注入测试 | 多人边界矩阵全通过 |
| 旧存档迁移损坏 | 高 | namespace/ID 改动、方块实体 schema 变化 | 默认不承诺；如选择兼容则固定映射并只在副本测试 | 指定版本迁移报告通过 |
| 可选生态尚不稳定 | 中 | JEI API/版本快速变化，EMI 无 26.2 | 隔离适配、compileOnly、核心无依赖 | 有/无查看器均可运行 |
| 26.3 导致范围漂移 | 中 | 开发中追逐快照 API | 只支持 `~26.2`；后续单独端口 | 26.2 RC 发布，不混入 26.3 |

## 11. 交付物清单

- 可构建的 Fabric 26.2 Java 源码仓库与固定 Gradle Wrapper。
- 正式 JAR、source JAR 和 SHA-256。
- `FEATURE_MATRIX.md`、`UPSTREAM_SOURCES.md`、LICENSE、NOTICE、素材署名。
- DataGen 源码及生成的 recipes/tags/loot/models/lang/advancements。
- 自动测试、GameTest、固定测试世界、渲染基准截图和最终测试报告。
- 用户文档：安装、依赖、玩法变化、配置/数据包格式、服务器部署、已知问题。
- 若选择旧存档兼容：单独的迁移指南、备份警告、支持的源版本和验证报告。
- Modrinth/CurseForge 发布说明草案，明确非官方身份和上游来源。

## 12. 第一轮实际开发顺序

确认 Gate 0 后，第一轮不要批量搬运全部类。按下列纵向切片实施：

1. 创建 26.2 工程并跑通客户端、专服、DataGen 和一个 GameTest。
2. 注册钻石晶格、晶态矩阵锭和一个基础方块，验证 ID/DataGen/资源链。
3. 实现最小 9×9 工作台与一条真实配方。
4. 实现中子压缩机的一条压缩配方。
5. 实现一个数据驱动奇点并完成服务端 reload/客户端同步。
6. 合成一个无尽催化剂，形成第一条完整可玩的终局生产链。
7. 纵向切片通过全部门禁后，才按阶段表扩展剩余内容。

这样可以最早暴露 26.2 的注册、菜单、网络、DataGen 和存档问题；如果切片不稳定，不应提前投入高成本宇宙渲染或大批素材迁移。

## 13. 调查来源

### Mojang / Fabric 第一方

- [Minecraft Java Edition 26.2 发布说明](https://www.minecraft.net/en-us/article/minecraft-java-edition-26-2)
- [Mojang Java 版本清单](https://piston-meta.mojang.com/mc/game/version_manifest_v2.json)
- [Fabric 26.2 迁移公告](https://fabricmc.net/2026/06/15/262.html)
- [Fabric 26.1 无混淆公告](https://fabricmc.net/2026/03/14/261.html)
- [Fabric 26.2 示例工程](https://github.com/FabricMC/fabric-example-mod/tree/26.2)
- [Fabric API 0.155.2+26.2](https://github.com/FabricMC/fabric-api/releases/tag/0.155.2%2B26.2)
- [Fabric 端口文档](https://docs.fabricmc.net/develop/porting/)
- [Fabric 自动测试文档](https://docs.fabricmc.net/develop/automatic-testing)

### Avaritia 上游与现有端口

- [Nova-Committee/Re-Avaritia](https://github.com/Nova-Committee/Re-Avaritia)
- [Re:Avaritia Fabric 1.20.1 分支](https://github.com/Nova-Committee/Re-Avaritia/tree/fabric/1.20.1)
- [Re:Avaritia NeoForge 1.21.1 分支](https://github.com/Nova-Committee/Re-Avaritia/tree/neo/1.21.1)
- [Morpheus1101/Avaritia](https://github.com/Morpheus1101/Avaritia)
- [AquaThree/AvaritiaNeo](https://github.com/AquaThree/AvaritiaNeo)
- [IAFEnvoy/AvaritiaReborn](https://github.com/IAFEnvoy/AvaritiaReborn)
