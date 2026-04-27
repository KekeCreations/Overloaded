package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatMap;
import com.hypixel.hytale.server.core.modules.entitystats.asset.DefaultEntityStatTypes;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public class ChaosModeTickSystem extends DelayedEntitySystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;

    public ChaosModeTickSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super(1.0F);
        this.roundStats = roundStats;
    }

    @Override
    public void tick(float v, int i, @NonNull ArchetypeChunk<EntityStore> archetypeChunk, @NonNull Store<EntityStore> store, @NonNull CommandBuffer<EntityStore> commandBuffer) {

        if (roundStats != null) {
            Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
            PlayerRef playerRef = Objects.requireNonNull(store.getComponent(ref, PlayerRef.getComponentType()));
            RoundComponent roundData = store.getComponent(ref, roundStats);

            InventoryComponent hotbarComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-1));
            ItemContainer hotbar = hotbarComponent.getInventory();

            if (roundData != null) {
                if (Objects.equals(roundData.getRoundType(), "chaos") || (Objects.equals(roundData.getRoundType(), "sandbox") && roundData.getSandboxChaosMode())) {
                    EntityStatMap entityStat = store.getComponent(ref, EntityStatMap.getComponentType());
                    if (entityStat != null) {
                        entityStat.addStatValue(DefaultEntityStatTypes.getHealth(), 10.0F);
                    }
                }
                if (roundData.getRoundCount() == 1 && roundData.getRoundTimer() == 58 && roundData.getRoundType() == "chaos") {
                    hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Custom_Battleaxe_Mithril", 1));
                    hotbar.setItemStackForSlot((short) 3, new ItemStack("Kunai_Pack", 1));
                    hotbar.setItemStackForSlot((short) 4, new ItemStack("Acid_Orb_Pet", 1));
                    hotbar.setItemStackForSlot((short) 5, new ItemStack("Ice_Ball_Pet", 1));
                }
            }

        }

    }
    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Archetype.of(PlayerRef.getComponentType());
    }
}
