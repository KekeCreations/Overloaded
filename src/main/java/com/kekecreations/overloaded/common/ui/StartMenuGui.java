package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.RemoveReason;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.*;
import com.hypixel.hytale.protocol.packets.connection.DisconnectType;
import com.hypixel.hytale.protocol.packets.connection.ServerDisconnect;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.protocol.packets.interface_.Page;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatMap;
import com.hypixel.hytale.server.core.modules.entitystats.asset.DefaultEntityStatTypes;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.entities.NPCEntity;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class StartMenuGui extends InteractiveCustomUIPage<StartMenuGuiData> {

    private static final String PLAY_NORMAL_BUTTON_ID = "PLAYCLASSIC";
    private static final String PLAY_QUICK_BUTTON_ID = "PLAYQUICK";
    private static final String QUIT_BUTTON_ID = "QUIT";

    private static final String SETTINGS = "SETTINGS";

    RoundComponent roundComponent;

    public StartMenuGui(@Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime, RoundComponent roundComponent) {
        super(playerRef, lifetime, StartMenuGuiData.CODEC);
        this.roundComponent = roundComponent;
    }


    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/start_menu.ui");

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#SETTINGS", EventData.of("OnButtonClicked", SETTINGS), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYCLASSIC", EventData.of("OnButtonClicked", PLAY_NORMAL_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYQUICK", EventData.of("OnButtonClicked", PLAY_QUICK_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#QUIT", EventData.of("OnButtonClicked", QUIT_BUTTON_ID), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull StartMenuGuiData data) {
        super.handleDataEvent(ref, store, data);
        boolean changed = false;

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));
        EntityStatMap entityStat = store.getComponent(ref, EntityStatMap.getComponentType());

        InventoryComponent hotbarComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-1));
        InventoryComponent armourComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-3));
        InventoryComponent utilityComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-5));
        InventoryComponent backpackComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-9));
        InventoryComponent storageComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-2));
        InventoryComponent toolComponent = store.getComponent(ref, InventoryComponent.getComponentTypeById(-8));

        ItemContainer hotbar = hotbarComponent.getInventory();
        ItemContainer armour = armourComponent.getInventory();
        ItemContainer utility = utilityComponent.getInventory();
        ItemContainer backpack = backpackComponent.getInventory();
        ItemContainer storage = storageComponent.getInventory();
        ItemContainer tool = toolComponent.getInventory();

        if (PLAY_NORMAL_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundType("classic");
            roundData.setRoundTimer(60);
            roundData.setRoundCount(1);
            roundData.freezeRoundTimer(false);

            if (hotbar != null && armour != null && utility != null && backpack != null && storage != null && tool != null) {
                hotbar.clear();
                armour.clear();
                utility.clear();
                backpack.clear();
                storage.clear();
                tool.clear();
                if (entityStat != null) {
                    entityStat.resetStatValue(DefaultEntityStatTypes.getHealth());
                    entityStat.resetStatValue(DefaultEntityStatTypes.getStamina());
                    entityStat.resetStatValue(DefaultEntityStatTypes.getMana());
                }

                hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Battleaxe_Copper"));
                hotbar.setItemStackForSlot((short) 2, new ItemStack("Potion_Health", 3));

                armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Copper_Head"));
                armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Copper_Chest"));
                armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Copper_Hands"));
                armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Copper_Legs"));
            }
        }
        else if (PLAY_QUICK_BUTTON_ID.equals(data.buttonClicked)) {
        }
        else if (SETTINGS.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundMenu("settings");
        }
        else if (QUIT_BUTTON_ID.equals(data.buttonClicked)) {
            playerRef.getPacketHandler().writeNoCache(new ServerDisconnect(null, DisconnectType.Disconnect));
        }

        if (PLAY_NORMAL_BUTTON_ID.equals(data.buttonClicked) || SETTINGS.equals(data.buttonClicked) || PLAY_QUICK_BUTTON_ID.equals(data.buttonClicked)) {
            store.forEachEntityParallel(NPCEntity.getComponentType(), (index, archetypeChunk, commandBuffer) -> commandBuffer.removeEntity(archetypeChunk.getReferenceTo(index), RemoveReason.REMOVE));
        }

        if (changed) {
            this.playerRef.sendMessage(Message.raw("Changes processed."));
        }
    }

    @Override
    public void onDismiss(@NonNull Ref<EntityStore> ref, @NonNull Store<EntityStore> store) {
        super.onDismiss(ref, store);
        if (Objects.equals(roundComponent.getRoundType(), "null")) {
            roundComponent.setRoundMenu("start");
        }
    }
}