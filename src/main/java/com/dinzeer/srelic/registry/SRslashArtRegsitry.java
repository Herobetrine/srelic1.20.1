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
    public static final RegistryObject<SlashArts> Rappa;
    public static final RegistryObject<SlashArts> HQUAN;
    public static final RegistryObject<SlashArts> FORDEADCRY;
    public static final RegistryObject<SlashArts> NEODRIVE;
    public static final RegistryObject<SlashArts> HOMERUN;
    public static final RegistryObject<SlashArts> BIG_DRIVEF;
    public static final RegistryObject<SlashArts> YUNLI;
    public static final RegistryObject<SlashArts> BIGSLASH;
    public static final RegistryObject<SlashArts> Heita;
    public static final RegistryObject<SlashArts> SummoningThunderNi;
    public static final RegistryObject<SlashArts> confused;
static {
    XDRIVE= SLASH_ARTS.register("xdrive", () -> new SlashArts((e) ->
            SRComboRegsitry.EXdrive.getId()));
    WitherBreaker=SLASH_ARTS.register("wither_breaker",()->new SlashArts((e) ->
            SRComboRegsitry.WITHER_BREAKER.getId()));
    EXPLANATION =SLASH_ARTS.register("explashon",()->new SlashArts((e)->SRComboRegsitry.Explosion_driven.getId()));
    THO=SLASH_ARTS.register("tho",()->new SlashArts((e)->SRComboRegsitry.THO.getId()));
    BIG_DRIVE=SLASH_ARTS.register("big_drive",()->new SlashArts((e)->SRComboRegsitry.BIGDRIVE_VERTICAL.getId()));
    Rappa=SLASH_ARTS.register("rappa",()->new SlashArts((e)->SRComboRegsitry.Rappa.getId()));
    HQUAN=SLASH_ARTS.register("black_hole",()->new SlashArts((e)->SRComboRegsitry.HQUAN.getId()));
    FORDEADCRY=SLASH_ARTS.register("dead_cry",()->new SlashArts((e)->SRComboRegsitry.Four_Slash.getId()));
    NEODRIVE=SLASH_ARTS.register("neo_drive",()->new SlashArts((e)->SRComboRegsitry.NEODRIVE.getId()));
    HOMERUN=SLASH_ARTS.register("home_run",()->new SlashArts((e)->SRComboRegsitry.HOMERUN.getId()));
    BIG_DRIVEF=SLASH_ARTS.register("big_drived",()->new SlashArts((e)->SRComboRegsitry.BIGDRIVED.getId()));
    YUNLI=SLASH_ARTS.register("yunli",()->new SlashArts((e)->SRComboRegsitry.YUNLI.getId()));
    BIGSLASH=SLASH_ARTS.register("bigslash",()->new SlashArts((e)->SRComboRegsitry.BIGSLASH.getId()));
    Heita=SLASH_ARTS.register("heita",()->new SlashArts((e)->HeitaComBoRegistry.CIRCLE_SLASH.getId()));
    SummoningThunderNi=SLASH_ARTS.register("summoning_thunder_ni",()->new SlashArts((e)->SRComboRegsitry.SummoningThunderNi.getId()));
    confused=SLASH_ARTS.register("confused",()->new SlashArts((e)->SRComboRegsitry.confused.getId()));
}
}
