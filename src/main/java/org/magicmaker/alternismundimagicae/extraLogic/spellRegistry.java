package org.magicmaker.alternismundimagicae.extraLogic;

import org.magicmaker.alternismundimagicae.uniqueclasses.Spell;

import java.util.HashMap;
import java.util.Map;

public class spellRegistry {
    private static final Map<String, Spell> SPELLS = new HashMap<>();

    public static void register(String id, Spell spell) {
        SPELLS.put(id, spell);
    }

    public static Spell get(String id) {
        return SPELLS.get(id);
    }

    public static Map<String, Spell> getAllSpells() {
        return SPELLS;
    }
}
