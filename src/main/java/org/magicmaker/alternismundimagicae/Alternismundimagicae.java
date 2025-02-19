package org.magicmaker.alternismundimagicae;

import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import org.magicmaker.alternismundimagicae.classes.TaskScheduler;
import org.magicmaker.alternismundimagicae.spelleffects.SpellRegistryInit;

public class Alternismundimagicae implements ModInitializer {
    public static String MODID = "alternismundimagicae";
    @Override
    public void onInitialize() {
        SpellRegistryInit.initSpells();
        ServerTickEvents.END_SERVER_TICK.register(this::onServerTick);
    }

    private void onServerTick(MinecraftServer server) {
        TaskScheduler.tick(server);
    }
}
