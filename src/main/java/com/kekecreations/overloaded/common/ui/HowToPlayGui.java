package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.RemoveReason;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.protocol.packets.interface_.Page;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.entities.NPCEntity;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;


public class HowToPlayGui extends InteractiveCustomUIPage<MenuWithButtonsData> {

    private static final String BACKBUTTON = "BACKBUTTON";

    RoundComponent roundData;

    public HowToPlayGui(@Nonnull PlayerRef playerRef, RoundComponent roundComponent, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, MenuWithButtonsData.CODEC);
        this.roundData = roundComponent;
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/how_to_play.ui");


        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BACKBUTTON", EventData.of("OnButtonClicked", BACKBUTTON), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));
        if (store.getComponent(ref, RoundComponent.getComponentType()) == null) {
            store.addComponent(ref, RoundComponent.getComponentType());
        }

        if (BACKBUTTON.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundMenu("start");
        }
    }

    void wipeRound(Store<EntityStore> store) {
        store.forEachEntityParallel(NPCEntity.getComponentType(), (index, archetypeChunk, commandBuffer) -> commandBuffer.removeEntity(archetypeChunk.getReferenceTo(index), RemoveReason.REMOVE));
    }

    @Override
    public void onDismiss(@NotNull Ref<EntityStore> ref, @NotNull Store<EntityStore> store) {
        super.onDismiss(ref, store);
        wipeRound(store);
        roundData.setRoundMenu("start");
    }
}