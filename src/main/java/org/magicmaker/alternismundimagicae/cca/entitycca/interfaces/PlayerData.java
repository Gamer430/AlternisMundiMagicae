package org.magicmaker.alternismundimagicae.cca.entitycca.interfaces;

import dev.onyxstudios.cca.api.v3.component.Component;

import java.util.UUID;

public interface PlayerData extends Component {
    int getMarkValue();
    void setMarkValue(int amount);

    boolean getTeleportable();
    void setTeleportable(boolean isTeleportable);

    boolean getBlacklisted();
    void setBlacklisted(boolean isBlacklisted);

    void updateMarkedMap(UUID uuid, int value);
}
