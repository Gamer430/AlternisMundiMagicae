package org.magicmaker.alternismundimagicae;

import net.fabricmc.api.ModInitializer;
import org.magicmaker.alternismundimagicae.spelleffects.SpellRegistryInit;

public class Alternismundimagicae implements ModInitializer {
    public static String MODID = "alternismundimagicae";
    @Override
    public void onInitialize() {
        SpellRegistryInit.initSpells();
    }
}
