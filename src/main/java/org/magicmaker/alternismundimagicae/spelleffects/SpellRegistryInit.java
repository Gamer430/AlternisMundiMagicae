package org.magicmaker.alternismundimagicae.spelleffects;

import org.magicmaker.alternismundimagicae.Alternismundimagicae;
import org.manadependants.manalib.logic.extra.spellRegistry;

public class SpellRegistryInit {
    public static void initSpells(){
        spellRegistry.register(Alternismundimagicae.MODID, Spells.flightSpell);
    }
}
