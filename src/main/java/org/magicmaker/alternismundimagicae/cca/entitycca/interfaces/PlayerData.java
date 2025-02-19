package org.magicmaker.alternismundimagicae.cca.entitycca.interfaces;

import dev.onyxstudios.cca.api.v3.component.Component;

import java.util.Map;
import java.util.UUID;

public interface PlayerData extends Component {
    int getMarkValue();
    void setMarkValue(int amount);
    void cycleMarkValue();

    int getHealValue();
    void setHealValue(int amount);
    void cycleHealValue();

    int getBarrierMastery();
    void setBarrierMastery(int mastery);
    void increaseBarrierMastery(int mastIncrease);

    boolean getShielded();
    void setShielded(boolean value);

    boolean getInvisible();
    void setInvisible(boolean value);

    Map<Integer, UUID> getMarkingsMap();

    boolean getTeleportable();
    void setTeleportable(boolean isTeleportable);

    boolean getBlacklisted();
    void setBlacklisted(boolean isBlacklisted);

    void updateMarkedMap(UUID uuid, int value);
}
