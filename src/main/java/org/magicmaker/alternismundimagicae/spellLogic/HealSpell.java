package org.magicmaker.alternismundimagicae.spellLogic;

import net.minecraft.entity.player.PlayerEntity;
import org.magicmaker.alternismundimagicae.data.SpellEffect;

public class HealSpell implements SpellEffect {
    private final int healAmount;

    public HealSpell(int healAmount){
        this.healAmount = healAmount;
    }
    @Override
    public void apply(PlayerEntity caster) {
        caster.heal((float) this.healAmount);
    }
}
