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

public class ClassicEnemyTickSystem extends DelayedEntitySystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;


    public ClassicEnemyTickSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super(4.0F);
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
            if (roundData != null && roundData.getRoundType() == "classic" || roundData.getRoundType() == "quick") {
                if (roundData.getRoundTimer() > 0 && !roundData.isTimerFrozen()) {
                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                        if (roundData.getRoundCount() == 1) {
                            CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                        }
                        if (roundData.getRoundCount() == 2) {
                            CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                        }
                        if (roundData.getRoundCount() == 3) {
                            CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                        }
                        if (roundData.getRoundCount() == 4) {
                            int enemy = (int) (Math.random() * 3);
                            switch (enemy) {
                                case 0 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                }
                                case 1 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                }
                                case 2 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                }
                            }
                        }
                        if (roundData.getRoundCount() == 5) {
                            if (roundData.getRoundTimer() == 40) {
                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Skeleton_Fighter");
                            } else {
                                int enemy = (int) (Math.random() * 3);
                                switch (enemy) {
                                    case 0 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Archer");
                                    }
                                    case 1 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                    }
                                    case 2 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() == 6) {
                            if (roundData.getRoundTimer() == 40) {
                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Skeleton_Burnt");
                            } else {
                                int enemy = (int) (Math.random() * 3);
                                switch (enemy) {
                                    case 0 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Archer");
                                    }
                                    case 1 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                    }
                                    case 2 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
