package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;

import javax.annotation.Nonnull;
import java.util.Objects;

public class PlayerTickSystem extends EntityTickingSystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;


    public PlayerTickSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super();
        this.roundStats = roundStats;
    }

    @Nonnull
    @Override
    public Query<EntityStore> getQuery() {
        return Archetype.of(PlayerRef.getComponentType());
    }

    @Override
    public void tick(float dt, int index, ArchetypeChunk<EntityStore> chunk, Store<EntityStore> store, CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = chunk.getReferenceTo(index);
        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));


        if (store.getComponent(ref, roundStats) != null) {
            RoundComponent roundData = store.getComponent(ref, roundStats);
            player.sendMessage(Message.raw(roundData.getRoundType()));
            player.sendMessage(Message.raw("PLAYER GOT C"));
        }

        player.sendMessage(Message.raw("PLAYER"));

    }
}
