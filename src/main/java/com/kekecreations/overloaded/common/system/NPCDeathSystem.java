package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathSystems;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatMap;
import com.hypixel.hytale.server.core.modules.entitystats.asset.DefaultEntityStatTypes;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.entities.NPCEntity;
import com.kekecreations.overloaded.common.component.GoldAndKillsComponent;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class NPCDeathSystem extends DeathSystems.OnDeathSystem {


    public NPCDeathSystem() {
        super();
    }

    @Override
    public void onComponentAdded(@NotNull Ref<EntityStore> ref, @NotNull DeathComponent deathComponent, @NotNull Store<EntityStore> store, @NotNull CommandBuffer<EntityStore> commandBuffer) {
        NPCEntity npc = store.getComponent(ref, NPCEntity.getComponentType());
        if (npc != null && !npc.wasRemoved()) {
            Damage damage = deathComponent.getDeathInfo();
            if (damage != null) {
                if (damage.getSource() instanceof Damage.EntitySource entitySource) {
                    if (store.getArchetype(entitySource.getRef()).contains(NPCEntity.getComponentType())) {
                        NPCEntity pet = store.getComponent(entitySource.getRef(), NPCEntity.getComponentType());
                        if (pet != null) {
                            Universe.get().getPlayers().forEach((playerRef -> {
                                if (playerRef.getReference() != null) {
                                    GoldAndKillsComponent goldAndKillsComponent = store.getComponent(playerRef.getReference(), GoldAndKillsComponent.getComponentType());
                                    if (goldAndKillsComponent != null) {
                                        if (Objects.equals(pet.getNPCTypeId(), "Friendly_Baby_Skeleton")) {
                                            int goldReward = (int) (Math.random() * 2);
                                            goldAndKillsComponent.setGold(goldAndKillsComponent.getGold() + goldReward);
                                        } else if (Objects.equals(pet.getNPCTypeId(), "Friendly_Baby_Burnt_Skeleton")) {
                                            int goldReward = (int) (Math.random() * 2);
                                            goldAndKillsComponent.setGold(goldAndKillsComponent.getGold() + goldReward);
                                        } else if (Objects.equals(pet.getNPCTypeId(), "Friendly_Baby_Spider")) {
                                            int goldReward = (int) (Math.random() * 2);
                                            goldAndKillsComponent.setGold(goldAndKillsComponent.getGold() + goldReward);
                                        } else if (Objects.equals(pet.getNPCTypeId(), "Friendly_Baby_Zombie")) {
                                            int goldReward = (int) (Math.random() * 2);
                                            goldAndKillsComponent.setGold(goldAndKillsComponent.getGold() + goldReward);
                                        } else if (Objects.equals(pet.getNPCTypeId(), "Friendly_Baby_Cold_Zombie")) {
                                            int goldReward = (int) (Math.random() * 2);
                                            goldAndKillsComponent.setGold(goldAndKillsComponent.getGold() + goldReward);
                                        }
                                        else if (Objects.equals(pet.getNPCTypeId(), "Friendly_Baby_Zombie_Brute")) {
                                            int goldReward = (int) (Math.random() * 4);
                                            goldAndKillsComponent.setGold(goldAndKillsComponent.getGold() + goldReward);
                                        }
                                    }
                                }
                            }));
                        }


                    }
                    if (store.getArchetype(entitySource.getRef()).contains(Player.getComponentType())) {
                        GoldAndKillsComponent goldData = store.getComponent(entitySource.getRef(), GoldAndKillsComponent.getComponentType());
                        RoundComponent roundComponent = store.getComponent(entitySource.getRef(), RoundComponent.getComponentType());
                        Player player = store.getComponent(entitySource.getRef(), Player.getComponentType());
                        PlayerRef playerRef = store.getComponent(entitySource.getRef(), PlayerRef.getComponentType());
                        EntityStatMap statMap = store.getComponent(entitySource.getRef(), EntityStatMap.getComponentType());
                        InventoryComponent storageComponent = store.getComponent(entitySource.getRef(), InventoryComponent.getComponentTypeById(-2));
                        ItemContainer storage = storageComponent.getInventory();

                        int goldReward = (int) (Math.random() * 4);
                        if (goldData != null) {
                            goldData.setKills(goldData.getKills() + 1);

                            if (Objects.equals(npc.getNPCTypeId(), "Chest")) {
                                int chestReward = (int) (Math.random() * 5);
                                switch (chestReward) {
                                    case 0 -> {
                                        goldData.setGold(goldData.getGold() + 8);
                                        playerRef.sendMessage(Message.raw("8 gold found in the chest!"));
                                    }
                                    case 1 -> {
                                        goldData.setGold(goldData.getGold() + 6);
                                        playerRef.sendMessage(Message.raw("6 gold found in the chest!"));
                                    }
                                    case 2 -> {
                                        goldData.setGold(goldData.getGold() + 5);
                                        playerRef.sendMessage(Message.raw("5 gold found in the chest!"));
                                    }
                                    case 3 -> {
                                        goldData.setGold(goldData.getGold() + 7);
                                        playerRef.sendMessage(Message.raw("7 gold found in the chest!"));
                                    }
                                    case 4 -> {
                                        playerRef.sendMessage(Message.raw("Nothing found in the chest!"));
                                    }
                                }
                            } else {
                                if (statMap != null) {
                                    storage.forEach((slot, itemStack) -> {
                                        if (itemStack.isValid()) {
                                            if (itemStack.equals(new ItemStack("Vampire_Fang"))) {
                                                statMap.addStatValue(DefaultEntityStatTypes.getHealth(), 1F);
                                            }
                                            if (itemStack.equals(new ItemStack("Gold_Vampire_Fang"))) {
                                                statMap.addStatValue(DefaultEntityStatTypes.getHealth(), 2F);
                                            }
                                        }
                                    });
                                }
                            }

                            if (Objects.equals(npc.getNPCTypeId(), "Giant_Skeleton_Burnt")) {
                                goldData.setGold(goldData.getGold() + 5);
                            }
                            if (Objects.equals(npc.getNPCTypeId(), "Giant_Skeleton_Fighter")) {
                                goldData.setGold(goldData.getGold() + 5);
                            }
                            if (Objects.equals(npc.getNPCTypeId(), "Giant_Zombie_Burnt")) {
                                goldData.setGold(goldData.getGold() + 5);
                            }

                            if (!Objects.equals(npc.getNPCTypeId(), "Chest")) {
                                if (roundComponent != null) {
                                    if (roundComponent.isDoubleGoldMode()) {
                                        goldData.setGold(goldData.getGold() + (goldReward));
                                    }
                                    if (roundComponent.getRoundType() == "quick") {
                                        goldData.setGold(goldData.getGold() + (goldReward));
                                    }
                                }
                                goldData.setGold(goldData.getGold() + goldReward);
                                npc.remove();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public @Nullable Query<EntityStore> getQuery() {
        return Query.any();
    }
}
