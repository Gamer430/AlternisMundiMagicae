package org.magicmaker.alternismundimagicae.spelleffects.spelleffects;

import io.github.ladysnake.pal.AbilitySource;
import io.github.ladysnake.pal.Pal;
import io.github.ladysnake.pal.VanillaAbilities;
import net.minecraft.entity.player.PlayerEntity;
import org.magicmaker.alternismundimagicae.Alternismundimagicae;
import org.manadependants.manalib.classes.Spell;
import org.manadependants.manalib.data.SpellEffect;

public class Flight implements SpellEffect {
    private final AbilitySource FLIGHT = Pal.getAbilitySource(Alternismundimagicae.MODID, "flight");

    @Override
    public void apply(PlayerEntity playerEntity, double v, Spell spell) {
        if(FLIGHT.grants(playerEntity, VanillaAbilities.ALLOW_FLYING)){
            FLIGHT.revokeFrom(playerEntity, VanillaAbilities.ALLOW_FLYING);
        }else{
            FLIGHT.grantTo(playerEntity,VanillaAbilities.ALLOW_FLYING);
        }
    }
}
