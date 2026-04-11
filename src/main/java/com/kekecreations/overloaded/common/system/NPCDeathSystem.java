package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
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

                        int goldReward = (int) (Math.random() * 5);
                        if (goldData != null) {
                            goldData.setKills(goldData.getKills() + 1);
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
