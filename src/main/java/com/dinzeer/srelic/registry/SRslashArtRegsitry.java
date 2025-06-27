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
    public static final RegistryObject<SlashArts> Bloodspirit;
    public static final RegistryObject<SlashArts> Closingsong;
    public static final RegistryObject<SlashArts> pure_elegy;
    public static final RegistryObject<SlashArts> ButterflyDance;
    public static final RegistryObject<SlashArts> Judgmentcube;
    public static final RegistryObject<SlashArts> the_moment_when_the_scale_collapses;
    public static final RegistryObject<SlashArts> SonAta;
    public static final RegistryObject<SlashArts> Whitenight;
    public static final RegistryObject<SlashArts> BreakSky;
    public static final RegistryObject<SlashArts> Icemusic;
    public static final RegistryObject<SlashArts> RedScarslash;
    public static final RegistryObject<SlashArts> SKY_EXPLOSION_SWORD;
    public static final RegistryObject<SlashArts> SKY_WAVE_EDGE;
    public static final RegistryObject<SlashArts> CELESTIAL_STRIKE;
    public static final RegistryObject<SlashArts> ICE_EDGE;
    public static final RegistryObject<SlashArts> AFFLORDITE;
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
    Bloodspirit=SLASH_ARTS.register("bloodspirit",()->new SlashArts((e)->SRComboRegsitry.Bloodspirit.getId()));
    Closingsong=SLASH_ARTS.register("closingsong",()->new SlashArts((e)->SRComboRegsitry.Closingsong.getId()));
    pure_elegy=SLASH_ARTS.register("pure_elegy",()->new SlashArts((e)->SRComboRegsitry.pure_elegy.getId()));
    ButterflyDance=SLASH_ARTS.register("butterfly_dance",()->new SlashArts((e)->SRComboRegsitry.ButterflyDance.getId()));
    Judgmentcube=SLASH_ARTS.register("judgment_cube",()->new SlashArts((e)->SRComboRegsitry.Judgmentcube.getId()));
    the_moment_when_the_scale_collapses= SLASH_ARTS.register("the_moment_when_the_scale_collapses",()->new SlashArts((e)->SRComboRegsitry.the_moment_when_the_scale_collapses.getId()));
    SonAta=SLASH_ARTS.register("son_ata",()->new SlashArts((e)->SRComboRegsitry.SonAta.getId()));
    Whitenight=SLASH_ARTS.register("whitenight",()->new SlashArts((e)->SRComboRegsitry.Whitenight.getId()));
    BreakSky=SLASH_ARTS.register("break_sky",()->new SlashArts((e)->SRComboRegsitry.BreakSky.getId()));
    Icemusic=SLASH_ARTS.register("ice_music",()->new SlashArts((e)->SRComboRegsitry.Icemusic.getId()));
    RedScarslash=SLASH_ARTS.register("red_scarslash",()->new SlashArts((e)->SRComboRegsitry.RedScarslash.getId()));
    SKY_EXPLOSION_SWORD=SLASH_ARTS.register("sky_explosion_sword",()->new SlashArts((e)->SRComboRegsitry.SKY_EXPLOSION_SWORD.getId()));
    SKY_WAVE_EDGE=SLASH_ARTS.register("sky_wave_edge",()->new SlashArts((e)->SRComboRegsitry.SKY_WAVE_EDGE.getId()));
    CELESTIAL_STRIKE=SLASH_ARTS.register("celestial_strike",()->new SlashArts((e)->SRComboRegsitry.CELESTIAL_STRIKE.getId()));
    ICE_EDGE=SLASH_ARTS.register("ice_edge",()->new SlashArts((e)->SRComboRegsitry.ICE_EDGE.getId()));
    AFFLORDITE=SLASH_ARTS.register("afflordite",()->new SlashArts((e)->SRComboRegsitry.AFFLORDITE.getId()));
}
}
