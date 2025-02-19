package org.magicmaker.alternismundimagicae.spelleffects.spelleffects;

import net.minecraft.entity.player.PlayerEntity;
import org.magicmaker.alternismundimagicae.cca.PlayerEntityCCARegistry;
import org.magicmaker.alternismundimagicae.cca.entitycca.PlayerDataImpl;
import org.magicmaker.alternismundimagicae.cca.entitycca.interfaces.PlayerData;
import org.magicmaker.alternismundimagicae.classes.TaskScheduler;
import org.manadependants.manalib.classes.Spell;
import org.manadependants.manalib.data.SpellEffect;

public class Shield implements SpellEffect {
    public void shield(PlayerEntity player){
        PlayerData playerData = PlayerEntityCCARegistry.PLAYER_DATA.get(player);
        if(!playerData.getShielded()) {
            playerData.setShielded(true);
            player.setGlowing(true);

            TaskScheduler.schedule(() -> {
                playerData.setShielded(false);
                player.setGlowing(false);
            }, 50*20);
        }

    }

    @Override
    public void apply(PlayerEntity playerEntity, double v, Spell spell) {
        shield(playerEntity);
    }
}
