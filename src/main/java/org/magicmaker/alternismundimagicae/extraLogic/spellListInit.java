package org.magicmaker.alternismundimagicae.extraLogic;

import net.minecraft.util.Identifier;
import org.magicmaker.alternismundimagicae.Alternismundimagicae;
import org.magicmaker.alternismundimagicae.spellLogic.FlightSpell;
import org.magicmaker.alternismundimagicae.uniqueclasses.Spell;

public class spellListInit {

    public static Identifier id(String path) {
        return Identifier.of(Alternismundimagicae.MODID, path);
    }

    public static void init(){
        spellRegistry.register("flight", new Spell("flight", "Flight", "Pandite alas, et adsurgite per aera.", 1000, 1, 200, new FlightSpell(id("flight"))));
    }

    public static void registerSpell(String id, Spell spell) {
        spellRegistry.register(id, spell);
    }
}
