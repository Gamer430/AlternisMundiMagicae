package org.magicmaker.alternismundimagicae.spelleffects;

import org.magicmaker.alternismundimagicae.spelleffects.spelleffects.Flight;
import org.manadependants.manalib.classes.Spell;
import org.manadependants.manalib.data.SpellEffect;

public class Spells {
    //Spell Effects
    private final SpellEffect FLIGHT = new Flight();

    //Spells
    public static final Spell flightSpell = new Spell("flight", "Alis Dei", "Free yourself from the strings of GRAVITY", 500, 25, FLIGHT);

}
