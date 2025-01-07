package org.magicmaker.alternismundimagicae.spelleffects;

import org.magicmaker.alternismundimagicae.spelleffects.spelleffects.Blast;
import org.magicmaker.alternismundimagicae.spelleffects.spelleffects.Flight;
import org.magicmaker.alternismundimagicae.spelleffects.spelleffects.Heal;
import org.magicmaker.alternismundimagicae.spelleffects.spelleffects.Mark;
import org.manadependants.manalib.classes.Spell;
import org.manadependants.manalib.data.SpellEffect;

public class Spells {
    //Spell Effects
    private static final SpellEffect FLIGHT = new Flight();
    private static final SpellEffect BLAST = new Blast();
    private static final SpellEffect MARK = new Mark();
    private static final SpellEffect HEAL = new Heal();

    //Spells
    public static final Spell flightSpell = new Spell("flight", "Alis Dei", "Free yourself from the strings of GRAVITY", 500, 25, FLIGHT);
    public static final Spell blastSpell = new Spell("blast", "Exitium", "The Arcane will guide your blow", 10, 5, BLAST);
    public static final Spell markSpell = new Spell("mark", "Vestigium", "A sliver of one's soul grants power", 10, 5, MARK);
    public static final Spell healSpell = new Spell("heal", "Sanctitas", "Let the light of renewal flow through you", 100, 60, HEAL);


}
