package org.magicmaker.alternismundimagicae.classes;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.magicmaker.alternismundimagicae.classes.particles.ArcaneParticleType;

public class InstanceRenders {
    public static ArcaneParticleType ARCANE = new ArcaneParticleType();

    public static Identifier id(String string) {
        return new Identifier("magicmod", string);
    }

    public static void initClientSideEffects(){
        ParticleFactoryRegistry.getInstance().register(ARCANE, ArcaneParticleType.Factory::new);
        ARCANE = Registry.register(Registries.PARTICLE_TYPE, InstanceRenders.id("arcane"), ARCANE);

    }

}
