package org.magicmaker.alternismundimagicae.cca;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.util.Identifier;
import org.magicmaker.alternismundimagicae.Alternismundimagicae;
import org.magicmaker.alternismundimagicae.cca.impl.PlayerCooldownComponentImpl;
import org.magicmaker.alternismundimagicae.cca.interfaces.PlayerCooldownComponent;

public class entityRegistry implements EntityComponentInitializer {
    public static ComponentKey<PlayerCooldownComponent> COOLDOWN_COMP = ComponentRegistryV3.INSTANCE.getOrCreate(
            new Identifier(Alternismundimagicae.MODID, "mana"),
            PlayerCooldownComponent.class
    );


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(COOLDOWN_COMP, player -> new PlayerCooldownComponentImpl(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
