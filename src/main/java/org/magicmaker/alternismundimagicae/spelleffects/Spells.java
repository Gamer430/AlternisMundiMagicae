package org.magicmaker.alternismundimagicae.spelleffects;

import org.magicmaker.alternismundimagicae.spelleffects.spelleffects.*;
import org.manadependants.manalib.classes.Spell;
import org.manadependants.manalib.data.SpellEffect;

public class Spells {
    //Spell Effects
    private static final SpellEffect FLIGHT = new Flight();
    private static final SpellEffect BLAST = new Blast();
    private static final SpellEffect MARK = new Mark();
    private static final SpellEffect HEAL = new Heal();
    private static final SpellEffect INVISIBILITY = new Invisibility();
    private static final SpellEffect SHIELD = new Shield();

    //Spells
    public static final Spell flightSpell = new Spell("flight", "Alis Dei", "Harness the winds and defy gravity, granting the power of flight at will", 500, 25, FLIGHT);
    public static final Spell blastSpell = new Spell("blast", "Exitium", "Unleash a burst of raw magical energy, striking foes with devastating force", 10, 5, BLAST);
    public static final Spell markSpell = new Spell("mark", "Vestigium", "Place an arcane mark upon a target, linking them to powerful spell interactions", 10, 5, MARK);
    public static final Spell healSpell = new Spell("heal", "Sanctitas", "Radiate a wave of restorative energy, healing yourself or nearby allies.", 100, 60, HEAL);
    public static final Spell shieldSpell = new Spell("shield", "Aegis Invicta", "A shimmering barrier surrounds you, reducing incoming damage by 80% for a limited time.", 500, 1200, SHIELD);
    public static final Spell invisibilitySpell = new Spell("invisibility", "Umbra Absoluta", "Cloak yourself in perfect shadows, rendering you completely unseen to the world", 200, 60, INVISIBILITY);


}
