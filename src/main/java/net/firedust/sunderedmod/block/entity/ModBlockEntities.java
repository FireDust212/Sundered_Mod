package net.firedust.sunderedmod.block.entity;

import net.firedust.sunderedmod.SunderedMod;
import net.firedust.sunderedmod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SunderedMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<PitCoreBlockEntity>> PIT_CORE_BE =
            BLOCK_ENTITIES.register("pit_core_be",
                    () -> BlockEntityType.Builder.of(PitCoreBlockEntity::new,
                            ModBlocks.PIT_CORE_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<PitBlockEntity>> PIT_BLOCK_BE =
            BLOCK_ENTITIES.register("pit_block_be",
                    () -> BlockEntityType.Builder.of(PitBlockEntity::new,
                            ModBlocks.PIT_BLOCK.get()).build(null));

    public static void register(IEventBus bus){
        BLOCK_ENTITIES.register(bus);
    }
}
