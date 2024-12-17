package org.magicmaker.alternismundimagicae.spellLogic;

import io.github.ladysnake.pal.AbilitySource;
import io.github.ladysnake.pal.Pal;
import io.github.ladysnake.pal.VanillaAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.magicmaker.alternismundimagicae.data.SpellEffect;

public class FlightSpell implements SpellEffect {
    private final AbilitySource abilitySource;

    public FlightSpell(Identifier abilityID){
        this.abilitySource = Pal.getAbilitySource(abilityID);
    }

    @Override
    public void apply(PlayerEntity caster) {
        if(abilitySource.grants(caster, VanillaAbilities.ALLOW_FLYING)){
            abilitySource.revokeFrom(caster, VanillaAbilities.ALLOW_FLYING);
        }else{
            abilitySource.grantTo(caster, VanillaAbilities.ALLOW_FLYING);
        }
    }
}
