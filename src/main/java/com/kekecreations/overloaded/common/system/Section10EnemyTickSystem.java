package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;

import javax.annotation.Nonnull;

public class Section10EnemyTickSystem extends DelayedEntitySystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;


    public Section10EnemyTickSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super(1.0F);
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


        if (store.getComponent(ref, roundStats) != null) {
            RoundComponent roundData = store.getComponent(ref, roundStats);
            //Purely so Intellij doesn't annoy me
            if (roundData != null && roundData.getRoundType() == "classic" || roundData.getRoundType() == "quick" || roundData.getRoundType() == "rounds") {
                if (roundData.getRoundCount() >= 91 && roundData.getRoundCount() <= 100) {
                    if (roundData.getRoundTimer() > 0 && !roundData.isTimerFrozen()) {
                        for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                            int enemy = (int) (Math.random() * 10);
                            switch (enemy) {
                                case 0 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                }
                                case 1 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Shadow_Knight");
                                }
                                case 2 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hedera");
                                }
                                case 3 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Cow_Undead");
                                }
                                case 4 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Pig_Undead");
                                }
                                case 5 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hound_Bleached");
                                }
                                case 6 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Chicken_Undead");
                                }
                                case 7 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Werewolf");
                                }
                                case 8 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wraith");
                                }
                                case 9 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Yeti");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
