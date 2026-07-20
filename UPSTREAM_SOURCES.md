# 上游来源与适配边界

本文件冻结 Avaritia 26 的行为参考与许可证边界。开发时只能引用这里记录的固定版本，不跟随移动分支自动更新。

## 项目决策

- 项目名：Avaritia 26 (Unofficial)
- Mod ID：`avaritia26`
- Java 包名：`io.github.aspshijiu.avaritia26`
- 首要目标：Minecraft Java 26.2 + Fabric
- 存档策略：新世界优先，不承诺直接加载旧 Avaritia/Re:Avaritia 存档
- 发布策略：公开、免费、非官方；不得暗示与上游作者存在官方关系
- 代码许可证：本项目新代码使用 MIT
- 核心依赖：Fabric API；不移植 Porting Lib、Architectury 或 Forge/NeoForge 平台层

## 固定参考

### Re:Avaritia Fabric 1.20.1

- 仓库：https://github.com/Nova-Committee/Re-Avaritia
- 分支：`fabric/1.20.1`
- Commit：[`4ec454e0847cefcd08434722ec7c6704e7564fd5`](https://github.com/Nova-Committee/Re-Avaritia/commit/4ec454e0847cefcd08434722ec7c6704e7564fd5)
- 用途：旧 Fabric 注册、菜单、配方、数据包和运行行为参考
- 禁止：复制其 Porting Lib、Forge Config API Port、Fabric ASM 兼容层作为新工程基础

### Re:Avaritia NeoForge 1.21.1

- 仓库：https://github.com/Nova-Committee/Re-Avaritia
- 分支：`neo/1.21.1`
- Commit：[`c331b7f4838667e6200b24c99dc45459e0b9dafb`](https://github.com/Nova-Committee/Re-Avaritia/commit/c331b7f4838667e6200b24c99dc45459e0b9dafb)
- 用途：现代内容清单、Data Component、网络、菜单、配方、机器、工具和渲染行为参考
- 禁止：复制 NeoForge 平台注册、capability、event bus 或网络封装

### Avaritia 1.1x

- 仓库：https://github.com/Morpheus1101/Avaritia
- 1.12.2 参考 Commit：[`d8cca83114cf831883b9a4c7734dd2d03ea6f485`](https://github.com/Morpheus1101/Avaritia/commit/d8cca83114cf831883b9a4c7734dd2d03ea6f485)
- 用途：经典材料链、9×9 合成、炽焰之啄颅剑、终局装备和原始玩法语义对照
- 禁止：复制 Forge 1.12.2 平台实现、CodeChickenLib 集成或旧网络代码

### Fabric 26.2 官方模板

- 仓库：https://github.com/FabricMC/fabric-example-mod
- 分支：`26.2`
- 用途：Gradle、Loom、Loader、Java 25、source set 和 CI 基线
- 模板许可证：CC0-1.0

## 许可证规则

- Re:Avaritia 明确声明代码为 MIT，素材为 CC BY-NC-SA 4.0；两者必须分别记录和分发许可证。
- 当前仓库没有复制 Re:Avaritia 代码；已复用的素材逐项记录在 `ASSETS.md`。
- 以后每次复制或改写上游文件时，必须在本文件追加“文件来源清单”，记录目标文件、上游文件、commit 和许可证。
- 若导入 Re:Avaritia 素材，本项目包含这些素材的分发必须保持非商业，并提供署名与 CC BY-NC-SA 4.0 许可证文本。
- AvaritiaNeo 只允许做黑盒行为对照，不从发布 JAR 反编译粘贴代码。
- 未能确认来源或许可证的文件不得进入仓库。

## 文件来源清单

- `src/client/resources/assets/avaritia26/textures/item/diamond_lattice.png` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/resource/crystal/diamond_lattice.png`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，像素未修改。
- `src/client/resources/assets/avaritia26/textures/item/crystal_matrix_ingot.png` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/resource/crystal/crystal_matrix_ingot.png`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，像素未修改。
- `src/client/resources/assets/avaritia26/textures/item/neutron_pile.png`、`neutron_nugget.png`、`neutron_ingot.png` ← Re:Avaritia 同名 `src/main/resources/assets/avaritia/textures/item/resource/neutron/` 素材，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，像素未修改。
- `src/client/resources/assets/avaritia26/textures/block/crystal_matrix.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/block/resource/crystal_matrix.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，内容未修改。
- `src/client/resources/assets/avaritia26/textures/block/neutron.png` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/block/resource/neutron.png`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，像素未修改。
- `src/client/resources/assets/avaritia26/textures/block/compressed_crafting_table.png`、`double_compressed_crafting_table.png` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/block/machine/craft/compressed.png`、`double_compressed.png`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，像素未修改。
- `src/client/resources/assets/avaritia26/textures/block/extreme_crafting_table_side.png`、`extreme_crafting_table_top.png` 及对应 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/block/machine/craft/extreme_side.png`、`extreme_top.png` 及对应 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，内容未修改。
- 其余已提交源码、配置和文档均为本项目新写；Gradle Wrapper 来自 Fabric 官方 26.2 CC0 模板。
