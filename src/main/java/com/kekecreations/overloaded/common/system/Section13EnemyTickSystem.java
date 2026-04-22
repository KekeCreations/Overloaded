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

public class Section13EnemyTickSystem extends DelayedEntitySystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;


    public Section13EnemyTickSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super(0.75F);
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
                if (roundData.getRoundCount() >= 121) {
                    if (roundData.getRoundTimer() > 0 && !roundData.isTimerFrozen()) {
                        for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                            int enemy = (int) (Math.random() * 28);
                            switch (enemy) {
                                case 0, 1, 2 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant");
                                }
                                case 3 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                }
                                case 4 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                }
                                case 5 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                }
                                case 6 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                }
                                case 7 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                }
                                case 8 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                }
                                case 9 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Frost");
                                }
                                case 10 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                }
                                case 11 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Shadow_Knight");
                                }
                                case 12 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hedera");
                                }
                                case 13 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Cow_Undead");
                                }
                                case 14 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Pig_Undead");
                                }
                                case 15 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hound_Bleached");
                                }
                                case 16 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Chicken_Undead");
                                }
                                case 17 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Werewolf");
                                }
                                case 18 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wraith");
                                }
                                case 19 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Yeti");
                                }
                                case 20 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Duke");
                                }
                                case 21 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Hermit");
                                }
                                case 22 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Lobber");
                                }
                                case 23 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Miner");
                                }
                                case 24 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Ogre");
                                }
                                case 25 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Scavenger");
                                }
                                case 26 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Scrapper");
                                }
                                case 27 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Thief");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
