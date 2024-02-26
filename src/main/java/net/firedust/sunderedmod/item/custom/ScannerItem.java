package net.firedust.sunderedmod.item.custom;

import net.firedust.sunderedmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpyglassItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ScannerItem extends SpyglassItem {
    public ScannerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        // Super
        InteractionResultHolder<ItemStack> resultHolder = super.use(pLevel, pPlayer, pUsedHand);

        if (!pLevel.isClientSide){
            // Get the block the player is on
            BlockPos target = new BlockPos(pPlayer.getBlockX(), pPlayer.getBlockY(), pPlayer.getBlockZ());

            // Loop through all blocks within 5 blocks of player
            for (int x = target.getX()-5; x < target.getX()+5; x++){
                for (int y = target.getY()-5; y < target.getY()+5; y++){
                    for (int z = target.getZ()-5; z < target.getZ()+5; z++) {
                        // Get the state of the block
                        BlockState state = pLevel.getBlockState(new BlockPos(x, y, z));

                        // If it is a pit block
                        if (state.is(ModBlocks.PIT_BLOCK.get())){
                            // For now just tell the coordinates and break from the loop
                            pPlayer.sendSystemMessage(Component.literal("Watch your step!"));
                            x = target.getX()+5;
                            y = target.getY()+5;
                            z = target.getZ()+5;
                        }
                    }
                }
            }
            // Give it a delay of 1 minute (60 * 20 ticks)
            ((Player)pPlayer).getCooldowns().addCooldown(this, 1200);
        }
        return resultHolder;
    }
}
