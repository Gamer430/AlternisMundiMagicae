package org.magicmaker.alternismundimagicae.classes;

import de.maxhenkel.voicechat.api.Player;
import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatClientApi;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import net.minecraft.entity.player.PlayerEntity;
import org.magicmaker.alternismundimagicae.Alternismundimagicae;
import org.magicmaker.alternismundimagicae.mixins.PlayerMixin;

public class VoiceChatIntegration implements VoicechatPlugin {
    @Override
    public String getPluginId() {
        return Alternismundimagicae.MODID;
    }

    @Override
    public void initialize(VoicechatApi api) {
        VoicechatPlugin.super.initialize(api);
    }

    @Override
    public void registerEvents(EventRegistration registration) {
        VoicechatPlugin.super.registerEvents(registration);
    }

    public static void mutePlayer(PlayerEntity player){
    }
}
