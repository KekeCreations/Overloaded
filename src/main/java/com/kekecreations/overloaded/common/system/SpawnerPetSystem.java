package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.math.vector.Transform;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.inventory.transaction.ItemStackTransaction;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatMap;
import com.hypixel.hytale.server.core.modules.entitystats.asset.DefaultEntityStatTypes;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.TargetUtil;
import com.kekecreations.overloaded.common.component.GoldAndKillsComponent;
import com.kekecreations.overloaded.common.component.RoundComponent;
import com.kekecreations.overloaded.common.util.ProjectileSpawner;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.atomic.AtomicInteger;

public class SpawnerPetSystem extends DelayedEntitySystem<EntityStore> {


    public SpawnerPetSystem() {
        super(15.0F);
    }

    @Override
    public void tick(float v, int i, @NonNull ArchetypeChunk<EntityStore> chunk, @NonNull Store<EntityStore> store, @NonNull CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = chunk.getReferenceTo(i);
        RoundComponent roundData = store.getComponent(ref, RoundComponent.getComponentType());


        if (roundData != null) {
            for (PlayerRef oPlayerRef : Universe.get().getPlayers()) {
                if (oPlayerRef.getReference() != null) {
                    InventoryComponent storageComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-2));
                    ItemContainer storage = storageComponent.getInventory();



                    storage.forEach((slot, itemStack) -> {
                        if (itemStack.isValid()) {
                            if (itemStack.equals(new ItemStack("Baby_Skeleton_Pet"))) {
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Skeleton");
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Skeleton");
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Skeleton");
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Skeleton");
                            }
                            if (itemStack.equals(new ItemStack("Baby_Burnt_Skeleton_Pet"))) {
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Burnt_Skeleton");
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Burnt_Skeleton");
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Burnt_Skeleton");
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Burnt_Skeleton");
                            }
                            if (itemStack.equals(new ItemStack("Baby_Zombie_Pet"))) {
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Zombie");
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Zombie");
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Zombie");
                            }
                            if (itemStack.equals(new ItemStack("Baby_Cold_Zombie_Pet"))) {
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Cold_Zombie");
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Cold_Zombie");
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Cold_Zombie");
                            }
                            if (itemStack.equals(new ItemStack("Baby_Spider_Pet"))) {
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Spider");
                                CommandManager.get().handleCommand(oPlayerRef, "spawn_friendly Friendly_Baby_Spider");
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public @Nullable Query<EntityStore> getQuery() {
        return Archetype.of(PlayerRef.getComponentType());
    }
}
