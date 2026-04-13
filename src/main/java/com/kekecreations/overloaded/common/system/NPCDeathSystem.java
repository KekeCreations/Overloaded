package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathSystems;
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
                    if (store.getArchetype(entitySource.getRef()).contains(Player.getComponentType())) {
                        GoldAndKillsComponent goldData = store.getComponent(entitySource.getRef(), GoldAndKillsComponent.getComponentType());
                        RoundComponent roundComponent = store.getComponent(entitySource.getRef(), RoundComponent.getComponentType());
                        Player player = store.getComponent(entitySource.getRef(), Player.getComponentType());

                        int goldReward = (int) (Math.random() * 5);
                        if (goldData != null) {
                            goldData.setKills(goldData.getKills() + 1);

                            if (Objects.equals(npc.getNPCTypeId(), "Skeleton_Burnt_Archer")) {
                                goldData.setGold(goldData.getGold() + 5);
                                player.sendMessage(Message.raw("5 gold coins reward received"));
                            }
                            if (Objects.equals(npc.getNPCTypeId(), "Skeleton_Burnt_Lancer")) {
                                goldData.setGold(goldData.getGold() + 6);
                                player.sendMessage(Message.raw("6 gold coins reward received"));
                            }
                            if (Objects.equals(npc.getNPCTypeId(), "Skeleton_Burnt_Knight")) {
                                goldData.setGold(goldData.getGold() + 8);
                                player.sendMessage(Message.raw("8 gold coins reward received"));
                            }
                            if (Objects.equals(npc.getNPCTypeId(), "Skeleton_Burnt_Gunner")) {
                                goldData.setGold(goldData.getGold() + 5);
                                player.sendMessage(Message.raw("5 gold coins reward received"));
                            }
                            if (Objects.equals(npc.getNPCTypeId(), "Skeleton_Burnt_Alchemist")) {
                                goldData.setGold(goldData.getGold() + 5);
                                player.sendMessage(Message.raw("5 gold coins reward received"));
                            }
                            if (Objects.equals(npc.getNPCTypeId(), "Skeleton_Burnt_Praetorian")) {
                                goldData.setGold(goldData.getGold() + 10);
                                player.sendMessage(Message.raw("10 gold coins reward received"));
                            }




                            if (roundComponent != null) {
                                if (roundComponent.isDoubleGoldMode()) {
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

    @Override
    public @Nullable Query<EntityStore> getQuery() {
        return Query.any();
    }
}
