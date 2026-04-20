package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.entities.NPCEntity;

import javax.annotation.Nonnull;
import java.util.Objects;

public class PetDespawnSystem extends DelayedEntitySystem<EntityStore> {



    public PetDespawnSystem() {
        super(100F);
    }

    @Nonnull
    @Override
    public Query<EntityStore> getQuery() {
        return Query.any();
    }

    @Override
    public void tick(float dt, int index, ArchetypeChunk<EntityStore> chunk, Store<EntityStore> store, CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = chunk.getReferenceTo(index);
        NPCEntity npc = (store.getComponent(ref, NPCEntity.getComponentType()));


        if (npc != null) {
            if (Objects.equals(npc.getNPCTypeId(), "Friendly_Baby_Burnt_Skeleton")) {
                npc.setDespawning(true);
                npc.remove();
            }
            if (Objects.equals(npc.getNPCTypeId(), "Friendly_Baby_Cold_Zombie")) {
                npc.setDespawning(true);
                npc.remove();
            }
            if (Objects.equals(npc.getNPCTypeId(), "Friendly_Baby_Skeleton")) {
                npc.setDespawning(true);
                npc.remove();
            }
            if (Objects.equals(npc.getNPCTypeId(), "Friendly_Baby_Spider")) {
                npc.setDespawning(true);
                npc.remove();
            }
            if (Objects.equals(npc.getNPCTypeId(), "Friendly_Baby_Zombie")) {
                npc.setDespawning(true);
                npc.remove();
            }
            if (Objects.equals(npc.getNPCTypeId(), "Friendly_Baby_Zombie_Brute")) {
                npc.setDespawning(true);
                npc.remove();
            }
        }
    }
}
