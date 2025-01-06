package org.magicmaker.alternismundimagicae.spelleffects.spelleffects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.manadependants.manalib.classes.Spell;
import org.manadependants.manalib.data.SpellEffect;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;

import java.awt.*;
import java.util.List;

public class Mark implements SpellEffect {
    private void raytraceMark(World world, PlayerEntity player, String markType){
        float speed = 10;

        Vec3d start = player.getCameraPosVec(1.0F); // Start at player's eye position
        Vec3d direction = player.getRotationVec(1.0F).normalize(); // Unit direction vector
        double maxStep = 0.5; // Distance between each step
        double maxDistance = 1000; // Safety cap for infinite raycasting
        double traveledDistance = 0.0;

        Vec3d currentPosition = start;


        while (traveledDistance < maxDistance) {
            Vec3d nextPosition = currentPosition.add(direction.multiply(maxStep));
            traveledDistance += maxStep;

            // Block Raycast
            HitResult blockHitResult = world.raycast(new RaycastContext(
                    currentPosition,
                    nextPosition,
                    RaycastContext.ShapeType.OUTLINE,
                    RaycastContext.FluidHandling.NONE,
                    player
            ));

            if (blockHitResult.getType() != HitResult.Type.MISS) {
                // Handle block hit
                if (blockHitResult instanceof BlockHitResult blockHit) {// Example: Break the block
                    break;
                }
            }

            // Entity Detection
            Box entityBox = new Box(
                    currentPosition.subtract(maxStep / 2, maxStep / 2, maxStep / 2),
                    currentPosition.add(maxStep / 2, maxStep / 2, maxStep / 2)
            );

            List<Entity> hitEntities = world.getOtherEntities(
                    player, entityBox,
                    entity -> entity instanceof LivingEntity && entity.isAlive()
            );

            if (!hitEntities.isEmpty()) {
                for (Entity hitEntity : hitEntities) {
                    if (hitEntity instanceof LivingEntity livingEntity) {
                        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 10));
                        break; // Stop after hitting one entity
                    }
                }
                break; // Stop the raycast after hitting an entity
            }
            // Update current position
            currentPosition = nextPosition;
        }
    }



    @Override
    public void apply(PlayerEntity playerEntity, double v, Spell spell) {

    }
}
