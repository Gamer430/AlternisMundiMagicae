package org.magicmaker.alternismundimagicae.mixins;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.magicmaker.alternismundimagicae.cca.PlayerEntityCCARegistry;
import org.magicmaker.alternismundimagicae.cca.entitycca.interfaces.PlayerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer<T extends LivingEntity> {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRender(T entity, float f, float g, MatrixStack matrixStack,
                          net.minecraft.client.render.VertexConsumerProvider vertexConsumerProvider,
                          int i, CallbackInfo ci) {
        if (entity instanceof PlayerEntity player) {
            // Check if the player should be invisible
            PlayerData playerData = PlayerEntityCCARegistry.PLAYER_DATA.get(player);
            if (playerData.getInvisible()) {
                ci.cancel(); // Prevent rendering
            }
        }
    }
}
