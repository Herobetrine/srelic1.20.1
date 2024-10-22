package com.dinzeer.srelic.registry;

import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.srelic.Srelic.MODID;

public class SRslashArtRegsitry {
    public static final DeferredRegister<SlashArts> SLASH_ARTS =
            DeferredRegister.create(SlashArts.REGISTRY_KEY, MODID);

    public static final RegistryObject<SlashArts> XDRIVE;
    public static final RegistryObject<SlashArts> WitherBreaker;
    public static final RegistryObject<SlashArts> EXPLANATION;
    public static final RegistryObject<SlashArts> THO;
    public static final RegistryObject<SlashArts> BIG_DRIVE;
static {
    XDRIVE= SLASH_ARTS.register("xdrive", () -> new SlashArts((e) ->
            SRComboRegsitry.EXdrive.getId()));
    WitherBreaker=SLASH_ARTS.register("wither_breaker",()->new SlashArts((e) ->
            SRComboRegsitry.WITHER_BREAKER.getId()));
    EXPLANATION =SLASH_ARTS.register("explashon",()->new SlashArts((e)->SRComboRegsitry.Explosion_driven.getId()));
    THO=SLASH_ARTS.register("tho",()->new SlashArts((e)->SRComboRegsitry.THO.getId()));
    BIG_DRIVE=SLASH_ARTS.register("big_drive",()->new SlashArts((e)->SRComboRegsitry.BIGDRIVE_VERTICAL.getId()));
}
}
