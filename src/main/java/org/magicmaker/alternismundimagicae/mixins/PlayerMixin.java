package org.magicmaker.alternismundimagicae.mixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import org.magicmaker.alternismundimagicae.cca.PlayerEntityCCARegistry;
import org.magicmaker.alternismundimagicae.cca.entitycca.interfaces.PlayerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class PlayerMixin {
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onEntityDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        // Convert the entity instance to LivingEntity
        LivingEntity entity = (LivingEntity) (Object) this;

        if(entity instanceof PlayerEntity player){
            PlayerData playerData = PlayerEntityCCARegistry.PLAYER_DATA.get(player);

            // Check if the player is shielded
            if (playerData.getShielded()) {
                // Reduce the damage by 80%
                float reducedDamage = amount * 0.2f;

                // Apply the reduced damage to the player
                boolean damageApplied = player.damage(source, reducedDamage);

                // If damage was successfully applied, cancel further processing
                if (damageApplied) {
                    cir.setReturnValue(true);
                } else {
                    cir.setReturnValue(false);
                }
            }
        }
    }

}
