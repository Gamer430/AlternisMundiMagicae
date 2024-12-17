package org.magicmaker.alternismundimagicae.uniqueclasses;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.magicmaker.alternismundimagicae.cca.entityRegistry;
import org.magicmaker.alternismundimagicae.cca.impl.PlayerCooldownComponentImpl;
import org.magicmaker.alternismundimagicae.cca.interfaces.PlayerCooldownComponent;
import org.magicmaker.alternismundimagicae.data.SpellEffect;
import org.magicmaker.alternismundimagicae.extraLogic.SpellTM;
import org.manadependants.manalib.components.player.interfaces.ManaComponent;

public class Spell {
    private final String id;
    private final String name;
    private final String description;
    private final float manaCost;
    private final int cooldownTicks;
    private final int castingTimeTicks;
    private final SpellEffect effect;


    public Spell(String id, String name, String description, float manaCost, int cooldownTicks, int castingTimeTicks, SpellEffect effect) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
        this.cooldownTicks = cooldownTicks;
        this.castingTimeTicks = castingTimeTicks;
        this.effect = effect;
    }

    public void cast(PlayerEntity player) {
        PlayerCooldownComponent cooldownComponent = getCooldownComponent(player);

        if (cooldownComponent.isOnCooldown(id)) {
            player.sendMessage(Text.literal("Spell is on cooldown!"), true);
            return;
        }
        player.sendMessage(Text.literal("Casting: " + name), true);
        if (player instanceof ManaComponent mana && mana.getTotalMana() >= manaCost) {
            mana.setTotalMana(mana.getTotalMana() - manaCost);
            // Apply the casting delay
            SpellTM.addTask(player, new SpellTM.Task("cast", castingTimeTicks, () -> {
                effect.apply(player);
                cooldownComponent.setCooldown(id, cooldownTicks);
                player.sendMessage(Text.literal("Casted " + name + "!"), true);
            }));
        }
    }

    public String getName(){
        return this.name;
    }

    private PlayerCooldownComponent getCooldownComponent(PlayerEntity player) {
        return player.getComponent(entityRegistry.COOLDOWN_COMP);
    }
}
