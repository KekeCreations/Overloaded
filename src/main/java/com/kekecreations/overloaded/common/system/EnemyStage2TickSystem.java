package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;

import javax.annotation.Nonnull;
import java.util.Objects;

public class EnemyStage2TickSystem extends DelayedEntitySystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;


    public EnemyStage2TickSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super(2.5F);
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
        PlayerRef playerRef = Objects.requireNonNull(store.getComponent(ref, PlayerRef.getComponentType()));


        if (store.getComponent(ref, roundStats) != null) {
            RoundComponent roundData = store.getComponent(ref, roundStats);
            //Purely so Intellij doesn't annoy me
            if (roundData != null && roundData.getRoundType() == "classic" || roundData.getRoundType() == "quick" || roundData.getRoundType() == "rounds") {
                if (roundData.getRoundTimer() > 0 && !roundData.isTimerFrozen()) {
                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                        if (roundData.getRoundCount() >= 10) {
                            int enemy = (int) (Math.random() * 15);
                            switch (enemy) {
                                case 0, 1 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                }
                                case 2, 3 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                }
                                case 4, 5 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                }
                                case 6, 7 -> {
                                    if (roundData.isArachnophobiaMode()) {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                    } else {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider_Cave");
                                    }
                                }
                                case 8 -> {
                                    if (roundData.isArachnophobiaMode()) {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                    } else {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider");
                                    }
                                }
                                case 9 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Thief");
                                }
                                case 10 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Scrapper");
                                }
                                case 11, 12 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Crawler_Void");
                                }
                                case 13, 14 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spawn_Void");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
