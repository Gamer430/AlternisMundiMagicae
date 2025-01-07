package org.magicmaker.alternismundimagicae.cca;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.util.Identifier;
import org.magicmaker.alternismundimagicae.Alternismundimagicae;
import org.magicmaker.alternismundimagicae.cca.entitycca.PlayerDataImpl;
import org.magicmaker.alternismundimagicae.cca.entitycca.interfaces.PlayerData;

public class PlayerEntityCCARegistry implements EntityComponentInitializer {
    public static final ComponentKey<PlayerData> PLAYER_DATA = ComponentRegistryV3.INSTANCE.getOrCreate(
        new Identifier(Alternismundimagicae.MODID, "playedata"),
        PlayerData.class
    );

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(PLAYER_DATA, player -> new PlayerDataImpl(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
