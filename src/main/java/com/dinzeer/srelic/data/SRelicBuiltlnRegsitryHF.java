package com.dinzeer.srelic.data;

import cn.mmf.energyblade.Energyblade;
import cn.mmf.slashblade_addon.data.SlashBladeAddonBuiltInRegistry;
import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.blade.SRItem;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRslashArtRegsitry;
import mods.flammpfeil.slashblade.client.renderer.CarryType;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.registry.SlashArtsRegistry;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.PropertiesDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.RenderDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

public class SRelicBuiltlnRegsitryHF {
    //萨姆的变身器·HF
    public static final ResourceKey<SlashBladeDefinition> samu_hf=SRelicBuiltInRegsitry.register("samu_hf");
    //天火圣裁
    public static final ResourceKey<SlashBladeDefinition> FIRE_HF=SRelicBuiltInRegsitry.register("the_fire_hf");
    //绯红女皇
    public static final ResourceKey<SlashBladeDefinition> RED_QUEEN_HF=SRelicBuiltInRegsitry.register("red_queen_hf");
    public static void registerAll(BootstapContext<SlashBladeDefinition> bootstrap) {
        bootstrap.register(
                samu_hf, new SlashBladeDefinition(Energyblade.FORGE_ENERGY_BLADE.getId(), Srelic.prefix("samu_hf"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/samu.png"))
                                .modelName(Srelic.prefix("model/stairail/samu.obj"))
                                .effectColor(65500)
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(25)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.PATH_OF_FINALITY.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.FIRE_FLY.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(SRelicBuiltInRegsitry.getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(SRelicBuiltInRegsitry.getEnchantmentID(Enchantments.UNBREAKING), 7),
                                new EnchantmentDefinition(SRelicBuiltInRegsitry.getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(FIRE_HF,
                new SlashBladeDefinition(Energyblade.FORGE_ENERGY_BLADE.getId(),Srelic.prefix("the_fire_hf"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/the_fire.jpg"))
                                .modelName(Srelic.prefix("model/honkai/the_fire.obj"))
                                .effectColor(16760832)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(23)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .maxDamage(200)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.FLAMEROSION.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(SRelicBuiltInRegsitry.getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(SRelicBuiltInRegsitry.getEnchantmentID(Enchantments.FIRE_ASPECT), 1),
                                new EnchantmentDefinition(SRelicBuiltInRegsitry.getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(RED_QUEEN_HF,
                new SlashBladeDefinition(Energyblade.FORGE_ENERGY_BLADE.getId(),Srelic.prefix("red_queen_hf"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/dmc/red_queen.png"))
                                .modelName(Srelic.prefix("model/dmc/red_queen.obj"))
                                .effectColor(16711697)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(27)
                                .slashArtsType(SRslashArtRegsitry.XDRIVE.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.NERO_DEVIL_BREAKER.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(SRelicBuiltInRegsitry.getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(SRelicBuiltInRegsitry.getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(SRelicBuiltInRegsitry.getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(SRelicBuiltInRegsitry.getEnchantmentID(Enchantments.MOB_LOOTING), 3)

                        )));
    }
}
