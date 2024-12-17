package org.magicmaker.alternismundimagicae.uniqueclasses;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;


public abstract class CastingItem extends Item {
    public CastingItem(Settings settings) {
        super(settings);
    }

    public abstract void castSpell(ItemStack stack, World world, PlayerEntity player);

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient) {
            // Trigger spell casting logic
            castSpell(stack, world, player);
        }

        return TypedActionResult.success(stack);
    }
}
