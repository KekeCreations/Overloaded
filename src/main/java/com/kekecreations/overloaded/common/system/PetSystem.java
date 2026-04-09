package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public class PetSystem extends DelayedEntitySystem<EntityStore> {
    public PetSystem(float intervalSec) {
        super(intervalSec);
    }

    @Override
    public void tick(float v, int i, @NonNull ArchetypeChunk<EntityStore> chunk, @NonNull Store<EntityStore> store, @NonNull CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = chunk.getReferenceTo(i);
        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        PlayerRef playerRef = Objects.requireNonNull(store.getComponent(ref, PlayerRef.getComponentType()));
        RoundComponent roundData = store.getComponent(ref, RoundComponent.getComponentType());

        InventoryComponent hotbarComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-1));
        InventoryComponent armourComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-3));
        InventoryComponent utilityComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-5));
        InventoryComponent backpackComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-9));
        InventoryComponent storageComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-2));
        InventoryComponent toolComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-8));
        ItemContainer hotbar = hotbarComponent.getInventory();
        ItemContainer armour = armourComponent.getInventory();
        ItemContainer utility = utilityComponent.getInventory();
        ItemContainer backpack = backpackComponent.getInventory();
        ItemContainer storage = storageComponent.getInventory();
        ItemContainer tool = toolComponent.getInventory();
    }

    @Override
    public @Nullable Query<EntityStore> getQuery() {
        return Archetype.of(PlayerRef.getComponentType());
    }
}
