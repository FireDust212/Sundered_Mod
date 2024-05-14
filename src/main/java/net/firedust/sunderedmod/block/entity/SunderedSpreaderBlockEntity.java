package net.firedust.sunderedmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class SunderedSpreaderBlockEntity extends BlockEntity {
    protected int SPREAD_TIMER = 19;//19
    private int tickTracker = 0;
    private int UP_CHANCE = 4;
    private int DOWN_CHANCE = 4;
    private int NORTH_CHANCE = 4;
    private int SOUTH_CHANCE = 4;
    private int EAST_CHANCE = 4;
    private int WEST_CHANCE = 4;


    public SunderedSpreaderBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    // Up: y + 1
    // Down: y - 1
    // North: z - 1
    // South: z + 1
    // East: x + 1
    // West: x - 1
    public void tick(Level pLevel, BlockPos pPos, BlockState pState){
        if (tickTracker >= SPREAD_TIMER) {
            if (new Random().nextInt(UP_CHANCE) == 0 && canSpreadUp(pLevel, pPos, pState)) {
                spreadUp(pLevel, pPos, pState);
            }
            if (new Random().nextInt(DOWN_CHANCE) == 0 && canSpreadDown(pLevel, pPos, pState)) {
                spreadDown(pLevel, pPos, pState);
            }
            if (new Random().nextInt(NORTH_CHANCE) == 0 && canSpreadNorth(pLevel, pPos, pState)) {
                spreadNorth(pLevel, pPos, pState);
            }
            if (new Random().nextInt(SOUTH_CHANCE) == 0 && canSpreadSouth(pLevel, pPos, pState)) {
                spreadSouth(pLevel, pPos, pState);
            }
            if (new Random().nextInt(EAST_CHANCE) == 0 && canSpreadEast(pLevel, pPos, pState)) {
                spreadEast(pLevel, pPos, pState);
            }
            if (new Random().nextInt(WEST_CHANCE) == 0 && canSpreadWest(pLevel, pPos, pState)) {
                spreadWest(pLevel, pPos, pState);
            }
            tickTracker = 0;
        }
        tickTracker++;
    }

    // Helper methods
    protected boolean canSpreadUp(Level pLevel, BlockPos pPos, BlockState pState) {return false;}
    protected boolean canSpreadDown(Level pLevel, BlockPos pPos, BlockState pState) {return false;}
    protected boolean canSpreadNorth(Level pLevel, BlockPos pPos, BlockState pState) {return false;}
    protected boolean canSpreadSouth(Level pLevel, BlockPos pPos, BlockState pState) {return false;}
    protected boolean canSpreadEast(Level pLevel, BlockPos pPos, BlockState pState) {return false;}
    protected boolean canSpreadWest(Level pLevel, BlockPos pPos, BlockState pState) {return false;}

    protected void spreadUp(Level pLevel, BlockPos pPos, BlockState pState){}
    protected void spreadDown(Level pLevel, BlockPos pPos, BlockState pState){}
    protected void spreadNorth(Level pLevel, BlockPos pPos, BlockState pState){}
    protected void spreadSouth(Level pLevel, BlockPos pPos, BlockState pState){}
    protected void spreadEast(Level pLevel, BlockPos pPos, BlockState pState){}
    protected void spreadWest(Level pLevel, BlockPos pPos, BlockState pState){}
}
