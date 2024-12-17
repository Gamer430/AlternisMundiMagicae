package org.magicmaker.alternismundimagicae.items;

import io.github.ladysnake.pal.AbilitySource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.magicmaker.alternismundimagicae.extraLogic.spellRegistry;
import org.magicmaker.alternismundimagicae.uniqueclasses.CastingItem;
import org.magicmaker.alternismundimagicae.uniqueclasses.Spell;

public class trueMageStaff extends CastingItem {
    public trueMageStaff(Settings settings){
        super(settings);
    }

    @Override
    public void castSpell(ItemStack stack, World world, PlayerEntity player) {
        // Check if the player has selected a spell
        Spell selectedSpell = getSelectedSpell(player);
        if (selectedSpell == null) {
            player.sendMessage(Text.literal("No spell selected!"), true);
            return;
        }

        // Cast the spell
        selectedSpell.cast(player);
        player.sendMessage(Text.literal("Casted: " + selectedSpell.getName()), true);

    }

    private Spell getSelectedSpell(PlayerEntity player) {
        // Placeholder for spell selection logic
        return spellRegistry.get("fireball"); // Default spell for testing
    }

}
