package org.magicmaker.alternismundimagicae;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.magicmaker.alternismundimagicae.extraLogic.SpellTM;
import org.magicmaker.alternismundimagicae.extraLogic.spellListInit;

public class Alternismundimagicae implements ModInitializer {
    public static String MODID = "alternismundimagicae";
    @Override
    public void onInitialize() {
        spellListInit.init();
        ServerTickEvents.END_WORLD_TICK.register(SpellTM::tick);
    }
}
