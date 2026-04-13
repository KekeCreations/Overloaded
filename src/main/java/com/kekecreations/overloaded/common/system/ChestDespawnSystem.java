package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.entities.NPCEntity;

import javax.annotation.Nonnull;
import java.util.Objects;

public class ChestDespawnSystem extends DelayedEntitySystem<EntityStore> {



    public ChestDespawnSystem() {
        super(8F);
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
            if (Objects.equals(npc.getNPCTypeId(), "Chest")) {
                npc.setDespawning(true);
                npc.remove();

            }
        }
    }
}
