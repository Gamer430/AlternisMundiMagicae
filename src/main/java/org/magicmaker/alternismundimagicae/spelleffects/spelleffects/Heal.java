package org.magicmaker.alternismundimagicae.spelleffects.spelleffects;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import org.magicmaker.alternismundimagicae.cca.PlayerEntityCCARegistry;
import org.magicmaker.alternismundimagicae.cca.entitycca.interfaces.PlayerData;
import org.manadependants.manalib.classes.Spell;
import org.manadependants.manalib.data.SpellEffect;

public class Heal implements SpellEffect {

    public static void healSelf(PlayerEntity player, double chargeTime){
        float healthRegained = (player.getMaxHealth() / 10) * (float) chargeTime/20;
        player.heal(healthRegained);

    }
    public static void healOthers(PlayerEntity player, double chargeTime){
        if (player.getWorld() instanceof ServerWorld serverWorld) {
            // Get all players in a 30-block radius
            Box radius = new Box(
                    player.getX() - 30, player.getY() - 30, player.getZ() - 30,
                    player.getX() + 30, player.getY() + 30, player.getZ() + 30
            );

            serverWorld.getPlayers().stream()
                    .filter(otherPlayer -> otherPlayer != player) // Exclude the caster
                    .filter(otherPlayer -> otherPlayer.getBoundingBox().intersects(radius)) // Check within radius
                    .forEach(otherPlayer -> {
                        // Check if the marking map contains (3, playerUUID)
                        PlayerData otherPlayerData = otherPlayer.getComponent(PlayerEntityCCARegistry.PLAYER_DATA);
                        float healthRegained = (otherPlayer.getMaxHealth() / 10) * (float) chargeTime/20;// Replace with your component key
                        if (otherPlayerData.getMarkingsMap().containsKey(3) && otherPlayerData.getMarkingsMap().get(3).equals(player.getUuid())) {
                            // Heal the player
                            otherPlayer.heal(healthRegained); // Heals 5 HP
                        }
                    });
        }
    }

    @Override
    public void apply(PlayerEntity playerEntity, double v, Spell spell) {
        PlayerData playerData = PlayerEntityCCARegistry.PLAYER_DATA.get(playerEntity);
        switch(playerData.getHealValue()){
            case 0:
                healSelf(playerEntity, v);
                break;
            case 1:
                healOthers(playerEntity, v);
                break;
            default:
                break;
        }


    }
}