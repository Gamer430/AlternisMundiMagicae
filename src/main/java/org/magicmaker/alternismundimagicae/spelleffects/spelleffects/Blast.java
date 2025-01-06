package org.magicmaker.alternismundimagicae.spelleffects.spelleffects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.magicmaker.alternismundimagicae.classes.InstanceRenders;
import org.manadependants.manalib.classes.Spell;
import org.manadependants.manalib.data.SpellEffect;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;

import java.awt.*;
import java.util.List;

public class Blast implements SpellEffect {
    private void raytraceBlast(World world, PlayerEntity player, double chargeTime){
        if(chargeTime < 10) return;
        // Scale speed and damage based on charge time
        // Scale speed and damage based on charge time
        float maxSpeed = 10.0f; // Blocks per second
        float maxDamage = 80.0f;
        int maxChargeTime = 200; // Adjust based on how long charging can take

        float chargeFactor = Math.min((float) chargeTime / (float) maxChargeTime, 1.0f);
        float speed = chargeFactor * maxSpeed;
        float damage = chargeFactor * maxDamage;

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
                if (blockHitResult instanceof BlockHitResult blockHit) {
                    world.breakBlock(blockHit.getBlockPos(), true); // Example: Break the block
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
                        livingEntity.damage(livingEntity.getDamageSources().magic(), damage);
                        break; // Stop after hitting one entity
                    }
                }
                break; // Stop the raycast after hitting an entity
            }

            // Spawn particles for visual feedback
            spawnParticlesAlongRay(world, currentPosition, nextPosition, chargeFactor);

            // Update current position
            currentPosition = nextPosition;
        }
    }

    public static void spawnParticlesAlongRay(World world, Vec3d start, Vec3d end, float intensity) {
        if (world.isClient) {
            double particleStep = 0.2; // Distance between particles
            double totalDistance = start.distanceTo(end);
            Vec3d direction = end.subtract(start).normalize();

            for (double i = 0; i < totalDistance; i += particleStep) {
                Vec3d particlePos = start.add(direction.multiply(i));

                // Fetch world data and randomize
                float scale = 0.5f + (float) world.random.nextGaussian() * 0.6f;
                float spin = (float) (world.random.nextGaussian() / 5f);
                float randomOffsetX = (float) (world.random.nextGaussian() / 20f);
                float randomOffsetY = (float) (world.random.nextGaussian() / 20f);
                float randomOffsetZ = (float) (world.random.nextGaussian() / 20f);

                // Calculate particle position with slight offset
                double x = particlePos.x + randomOffsetX;
                double y = particlePos.y + randomOffsetY;
                double z = particlePos.z + randomOffsetZ;

                // Spawn the particle
                WorldParticleBuilder.create(InstanceRenders.ARCANE)
                        .enableForcedSpawn()
                        .setSpinData(SpinParticleData.create(spin).build())
                        .setScaleData(GenericParticleData.create(scale, 0f)
                                .setEasing(Easing.CIRC_OUT)
                                .build())
                        .setTransparencyData(GenericParticleData.create(1f).build())
                        .setColorData(ColorParticleData.create(
                                        new Color(0xD400FF), // Start color
                                        new Color(0xFF00A4)  // End color
                                )
                                .setEasing(Easing.CIRC_OUT)
                                .build())
                        .enableNoClip()
                        .setLifetime(20) // Particle lifetime
                        .spawn(world, x, y, z);
            }
        }
    }



    @Override
    public void apply(PlayerEntity playerEntity, double v, Spell spell) {
        raytraceBlast(playerEntity.getWorld(), playerEntity, v);
    }
}
