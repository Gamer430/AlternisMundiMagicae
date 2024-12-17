package org.magicmaker.alternismundimagicae.cca.interfaces;

import dev.onyxstudios.cca.api.v3.component.Component;

public interface PlayerCooldownComponent extends Component {
    void setCooldown(String spellId, int cooldownTicks);
    boolean isOnCooldown(String spellId);
    int getRemainingCooldown(String spellId);
}
