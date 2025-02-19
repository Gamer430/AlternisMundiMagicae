package org.magicmaker.alternismundimagicae.spelleffects.spelleffects;

import net.minecraft.entity.player.PlayerEntity;
import org.magicmaker.alternismundimagicae.cca.PlayerEntityCCARegistry;
import org.magicmaker.alternismundimagicae.cca.entitycca.interfaces.PlayerData;
import org.magicmaker.alternismundimagicae.classes.TaskScheduler;
import org.manadependants.manalib.classes.Spell;
import org.manadependants.manalib.data.SpellEffect;

public class Invisibility implements SpellEffect {
    public void goInvisible(PlayerEntity player, double time){
        long secondsOfInvisibility = (long) time/10;
        PlayerData playerData = PlayerEntityCCARegistry.PLAYER_DATA.get(player);
        if(!playerData.getInvisible()){
            playerData.setInvisible(true);
            TaskScheduler.schedule(() -> {
                playerData.setInvisible(false);
            },secondsOfInvisibility);
        }
    }

    @Override
    public void apply(PlayerEntity playerEntity, double v, Spell spell) {

    }
}
