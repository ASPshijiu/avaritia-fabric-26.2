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
- `src/client/resources/assets/avaritia26/textures/gui/extreme_crafting_table.png` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/gui/craft/extreme_crafting_table_gui.png`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，像素未修改。
- `src/client/resources/assets/avaritia26/textures/item/record_fragment.png` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/resource/record_fragment.png`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，像素未修改。
- `src/client/resources/assets/avaritia26/textures/item/ultimate_stew.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/foods/ultimate_stew/layer_0.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，内容未修改。
- `src/client/resources/assets/avaritia26/textures/item/ultimate_stew_overlay.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/foods/ultimate_stew/stew_overlay.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，内容未修改。
- `src/client/resources/assets/avaritia26/textures/item/cosmic_meatballs.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/foods/cosmic_meatballs/layer_0.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，内容未修改。
- `src/client/resources/assets/avaritia26/textures/item/endest_pearl.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/misc/endest_pearl/layer_0.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，内容未修改。
- `src/client/resources/assets/avaritia26/sounds/gaping_void.ogg` ← Re:Avaritia `src/main/resources/assets/avaritia/sounds/gaping_void.ogg`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，音频未修改。
- `src/client/resources/assets/avaritia26/textures/item/matter_cluster.png` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/misc/matter_cluster/empty.png`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，像素未修改。
- `src/client/resources/assets/avaritia26/textures/item/full_matter_cluster.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/misc/matter_cluster/full_matter_cluster.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，内容未修改。
- `src/client/resources/assets/avaritia26/textures/item/singularity.png`、`singularity_overlay.png` 与 `.png.mcmeta` ← Re:Avaritia 同名 `src/main/resources/assets/avaritia/textures/item/resource/singularity/` 素材，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，内容未修改。
- `src/client/resources/assets/avaritia26/textures/block/neutron_compressor/` 下 6 个素材文件 ← Re:Avaritia `src/main/resources/assets/avaritia/textures/block/machine/compressor/` 下同名压缩机素材，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，内容未修改。
- `src/client/resources/assets/avaritia26/textures/gui/neutron_compressor.png` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/gui/machine/neutron_compressor.png`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，像素未修改。
- `src/client/resources/assets/avaritia26/textures/item/infinity_catalyst.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/resource/infinity/infinity_catalyst.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，内容未修改。
- `src/client/resources/assets/avaritia26/textures/item/infinity_ingot.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/resource/infinity/infinity_ingot.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，仅调整资源路径；元数据删除一处尾随空白，动画语义未修改。
- `src/client/resources/assets/avaritia26/textures/block/infinity.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/block/resource/infinity.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，内容未修改。
- `src/client/resources/assets/avaritia26/textures/item/eternal_singularity.png`、`eternal_singularity_overlay.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/resource/singularity/eternal_singularity.png`、`eternal_singularity2.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，内容未修改。
- `src/client/resources/assets/avaritia26/textures/block/neutron_collector/` 下 6 个素材文件 ← Re:Avaritia `src/main/resources/assets/avaritia/textures/block/machine/collector/` 下基础收集器的 `frame`、`collector_front`、`collector_side_left`、`collector_side_right`、`collector_top` 同名素材，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，内容未修改。
- `src/client/resources/assets/avaritia26/textures/gui/neutron_collector.png` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/gui/machine/neutron_collector.png`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，像素未修改。
- `src/client/resources/assets/avaritia26/textures/item/blaze_cube.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/resource/blaze/blaze_cube.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，内容未修改。
- `src/client/resources/assets/avaritia26/textures/block/blaze_cube_block.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/block/resource/blaze_cube_block.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，内容未修改。
- `src/client/resources/assets/avaritia26/textures/item/skull_fire_sword.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/tools/blaze_sword/layer_0.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，仅调整资源路径与文件名。
- `src/main/java/io/github/aspshijiu/avaritia26/item/SkullFireSwordItem.java`、`src/main/java/io/github/aspshijiu/avaritia26/event/ModCombatEvents.java` 与 `src/main/resources/data/avaritia26/recipe/skull_fire_sword.json` 依据经典 Avaritia `ItemSwordSkulls.java`、`AvaritiaEventHandler#onLivingDrops` 和 `avaritia_recipes/extreme/items/tools/skullfire_sword.json` 的行为及配方语义重新实现，参考 commit `d8cca83114cf831883b9a4c7734dd2d03ea6f485`，MIT；注册路径按本项目新世界矩阵使用 `skull_fire_sword`，不复制 Forge 事件实现。
- `src/client/resources/assets/avaritia26/textures/item/infinity_sword.png`、`infinity_sword_overlay.png` 及对应 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/tools/infinity_sword/layer_0.png`、`layer_1.png` 及对应 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，仅调整资源路径与文件名。
- `src/main/java/io/github/aspshijiu/avaritia26/item/InfinitySwordItem.java`、`src/main/java/io/github/aspshijiu/avaritia26/event/ModCombatEvents.java` 与 `src/main/resources/data/avaritia26/recipe/infinity_sword.json` 依据经典 Avaritia `ItemSwordInfinity.java` 和 `avaritia_recipes/extreme/items/tools/infinity_sword.json` 的单体终结、零耐久损耗及 9×9 配方语义重新实现，参考 commit `d8cca83114cf831883b9a4c7734dd2d03ea6f485`，MIT；不复制 Forge 事件、旧自定义伤害源或 CodeChickenLib 渲染实现。
- `src/client/resources/assets/avaritia26/textures/item/infinity_hoe.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/tools/infinity_hoe/layer_0.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，仅调整资源路径与文件名。
- `src/main/java/io/github/aspshijiu/avaritia26/item/InfinityHoeItem.java` 与 `src/main/resources/data/avaritia26/recipe/infinity_hoe.json` 依据经典 Avaritia `ItemHoeInfinity.java` 和 `avaritia_recipes/extreme/items/tools/infinity_hoe.json` 的 9×9 范围耕作、补土、清理覆土、潜行单格及零耐久损耗语义重新实现，参考 commit `d8cca83114cf831883b9a4c7734dd2d03ea6f485`，MIT；不复制 Forge 事件捕获或旧自定义实体实现。
- `src/client/resources/assets/avaritia26/textures/item/infinity_pickaxe.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/tools/infinity_pickaxe/layer_0.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，仅调整资源路径与文件名。
- `src/main/java/io/github/aspshijiu/avaritia26/item/InfinityPickaxeItem.java`、`ModToolEvents.java` 与 `src/main/resources/data/avaritia26/recipe/infinity_pickaxe.json` 依据经典 Avaritia `ItemPickaxeInfinity.java`、`ToolHelper#aoeBlocks` 和同名 9×9 配方的时运 X、锤模式、范围挖掘、物质团掉落及零耐久语义重新实现，参考 commit `d8cca83114cf831883b9a4c7734dd2d03ea6f485`，MIT；方块权限改用 Fabric 事件链，不复制 Forge 事件或 CodeChickenLib 射线实现。
- `src/client/resources/assets/avaritia26/textures/item/infinity_shovel.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/tools/infinity_shovel/layer_0.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，仅调整资源路径与文件名。
- `src/main/java/io/github/aspshijiu/avaritia26/item/InfinityShovelItem.java`、`ModToolEvents.java` 与 `src/main/resources/data/avaritia26/recipe/infinity_shovel.json` 依据经典 Avaritia `ItemShovelInfinity.java` 和同名 9×9 配方的土径交互、毁灭者模式、范围挖掘、物质团掉落及零耐久语义重新实现，参考 commit `d8cca83114cf831883b9a4c7734dd2d03ea6f485`，MIT；复用 Fabric 方块权限事件链，不复制 Forge 事件或 CodeChickenLib 射线实现。
- `src/client/resources/assets/avaritia26/textures/item/infinity_axe.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/tools/infinity_axe/layer_0.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，仅调整资源路径与文件名。
- `src/main/java/io/github/aspshijiu/avaritia26/item/InfinityAxeItem.java`、`ModToolEvents.java` 与 `src/main/resources/data/avaritia26/recipe/infinity_axe.json` 依据经典 Avaritia `ItemAxeInfinity.java`、`AEOCrawlerTask.java` 和同名 9×9 配方的连锁砍树、潜行范围清理、物质团掉落、攻击属性及零耐久语义重新实现，参考 commit `d8cca83114cf831883b9a4c7734dd2d03ea6f485`，MIT；权限与掉落改用 Fabric 事件链，不复制 Forge 任务调度或 CodeChickenLib 射线实现。
- `src/client/resources/assets/avaritia26/textures/item/infinity_bow*.png`、对应 `.png.mcmeta` 与 `textures/entity/heaven_arrow.png` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/tools/infinity_bow/` 下基础拉弓素材及 `textures/entity/heaven_arrow.png`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，仅调整物品素材路径与文件名。
- `src/main/java/io/github/aspshijiu/avaritia26/item/InfinityBowItem.java`、`HeavenArrowEntity.java`、`HeavenSubArrowEntity.java` 与 `src/main/resources/data/avaritia26/recipe/infinity_bow.json` 依据经典 Avaritia 同名实现的 13 tick 自动满蓄力、无弹药消耗、20 点箭矢基础伤害、命中方块生成 10 支天降箭雨、创造限定回收、零耐久及 9×9 配方语义重新实现，参考 commit `d8cca83114cf831883b9a4c7734dd2d03ea6f485`，MIT；实体注册、同步、渲染和附魔事件改用 Fabric/Minecraft 26.2 API，不复制 CodeChickenLib 模型包装。
- `src/client/resources/assets/avaritia26/textures/item/infinity_helmet.png`、`infinity_chestplate.png`、`infinity_pants.png`、`infinity_boots.png` 与 `textures/entity/equipment/` 下两张无尽护甲纹理 ← Re:Avaritia 四件护甲 `layer_0.png` 及 `textures/models/armor/infinity_armor_layer_1.png`、`infinity_armor_layer_2.png`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，仅调整 Minecraft 26.2 资源路径与文件名，像素未修改。
- `InfinityArmorItem.java`、`ModArmorMaterials.java`、`ModArmorEvents.java` 与四件护甲配方依据经典 Avaritia `ItemArmorInfinity.java`、`AbilityHandler.java`、`AvaritiaEventHandler.java` 和对应 9×9 配方的零耐久、6/16/12/6 防御、头盔呼吸饱食夜视、胸甲飞行净化、护腿灭火、靴子速度跳跃跨步及完整套装免伤免死语义重新实现，参考 commit `d8cca83114cf831883b9a4c7734dd2d03ea6f485`，MIT；装备组件、原生属性、服务端 tick 与 Fabric 生物事件均为 26.2 适配实现，不复制 Forge 能力或事件代码。
- `src/client/resources/assets/avaritia26/textures/item/neutron_gear.png` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/resource/neutron/neutron_gear.png`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，仅调整资源路径，像素未修改；注册与普通 3×3 配方依据固定 Fabric 参考重新实现。
- `src/client/resources/assets/avaritia26/textures/item/infinity_nugget.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/resource/infinity/infinity_nugget.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，仅调整资源路径与元数据排版，像素及动画语义未修改；物品的 EPIC 稀有度、防火、说明文字及无尽锭九分解/九合一配方依据固定 Re:Avaritia 语义重新实现。
- `src/client/resources/assets/avaritia26/textures/item/enhancement_core.png` 与 `.png.mcmeta` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/misc/enhancement_core.png` 与 `.png.mcmeta`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，仅调整资源路径与元数据排版，像素及动画语义未修改；物品属性、说明文字与精确 9×9 配方依据固定 Re:Avaritia 语义重新实现。
- `src/client/resources/assets/avaritia26/textures/item/upgrade_smithing_template.png` ← Re:Avaritia `src/main/resources/assets/avaritia/textures/item/misc/upgrade_smithing_template.png`，commit `c331b7f4838667e6200b24c99dc45459e0b9dafb`，CC BY-NC-SA 4.0，仅调整资源路径；原版锻造模板槽位提示适配为 26.2 的 `container/slot/*` 精灵，精确 9×9 初次配方及输出两个模板但保留无尽催化剂的复制配方依据固定 Re:Avaritia 语义重新实现。
- `CompressedChestBlock.java`、`CompressedChestBlockEntity.java`、`CompressedChestMenu.java` 与压缩箱子配方依据固定 Re:Avaritia 的 243 槽、内容随方块物品保存、27×9 菜单和八箱子包围中子齿轮配方语义重新实现；方块实体、菜单同步和静态模型改用 Minecraft/Fabric 26.2 原生 API。
- `ExtremeSmithingTableBlock.java`、`ExtremeSmithingMenu.java`、`ExtremeSmithingRecipe.java` 与终焉锻造台 9×9 配方依据固定 Re:Avaritia 的五输入槽、模板/基底/三追加材料、基底组件继承和一次消耗语义重新实现；追加材料按无序三元素集合逐一匹配，修正固定上游允许三份重复材料的缺陷，菜单与配方同步改用 Minecraft/Fabric 26.2 原生 API。
- `InfinityChestBlock.java`、`InfinityChestBlockEntity.java`、`InfinityChestMenu.java` 与 `InfinityChestContents.java` 依据固定 Re:Avaritia 的 300 槽、每槽 `Integer.MAX_VALUE` 上限、方块物品携带内容和强化锻造配方语义重新实现；全部 300 槽改为 30×10 可见布局，修正固定上游仅显示 243 槽的问题，并使用 26.2 原生槽位点击/快捷移动逻辑代替旧版整套鼠标协议重写。
- 高阶中子收集器/压缩机、灵魂耕地、幽匿/下界/末地工作台、中子指环和无尽升级组件相关源码与配方依据固定 Re:Avaritia 同名方块、方块实体、菜单、物品及配方的行为重新实现；注册、持久化、传输和屏幕均使用 Minecraft/Fabric 26.2 API，不复制 NeoForge 平台层。
- `InfinityTotemItem`、`InfinityUmbrellaItem`、`InfinityClockItem`、`SideConfigCardItem`、`InfinityBucketItem`、`InfinityCrossbowItem`、`InfinityShieldItem`、`InfinityTridentItem`、`InfinityMaceItem` 及其实体、组件、菜单与配方依据固定 Re:Avaritia 同名内容的行为重新实现；客户端输入均由服务端菜单或 payload 校验。
- 晶态矩阵与烈焰工具套装、弓箭和投射物相关源码与配方依据固定 Re:Avaritia 同名内容的攻击属性、模式、范围操作、弹道和强化锻造语义重新实现；方块权限、掉落与实体同步改用 Fabric/Minecraft 26.2 API。
- `NeutronHorseArmorItem`、无尽鞘翅、终焉之砧与贪婪蛋糕相关源码和配方依据固定 Re:Avaritia 同名内容的属性、穿戴、修复/超限附魔、食用增益与强化锻造语义重新实现；分别适配 26.2 装备组件、菜单、下落方块和交互 API，并修正上游终焉之砧生存模式不可取出结果及蛋糕缺少支撑校验的问题。
- 13 种科技奇点依据固定 Re:Avaritia `ModSingularities` 与生成 JSON 的通用锭标签、颜色和条件启用语义适配为 Fabric `tags_populated` 资源条件；仅在对应 `c:ingots/*` 标签存在时参与压缩与终局动态配方。
- 其余已提交源码、配置和文档均为本项目新写；Gradle Wrapper 来自 Fabric 官方 26.2 CC0 模板。
