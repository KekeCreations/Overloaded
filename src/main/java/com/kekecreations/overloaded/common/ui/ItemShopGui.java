package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.*;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.protocol.packets.interface_.Page;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.inventory.transaction.ItemStackTransaction;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;

public class ItemShopGui extends InteractiveCustomUIPage<MenuWithButtonsData> {

    RoundComponent roundComponent;

    public ItemShopGui(@Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime, RoundComponent roundComponent) {
        super(playerRef, lifetime, MenuWithButtonsData.CODEC);
        this.roundComponent = roundComponent;
    }

    private static final String BUY1 = "BUY1";
    private static final String BUY2 = "BUY2";
    private static final String BUY3 = "BUY3";
    private static final String CONTINUE = "CONTINUE";

    int itemChance = (int)(Math.random() * 8);
    int itemChance2 = (int)(Math.random() * 6);
    int itemChance3 = (int)(Math.random() * 6);

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/item_shop.ui");

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BUY1", EventData.of("OnButtonClicked", BUY1), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BUY2", EventData.of("OnButtonClicked", BUY2), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BUY3", EventData.of("OnButtonClicked", BUY3), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CONTINUE", EventData.of("OnButtonClicked", CONTINUE), false);

        switch(itemChance) {
            case 0 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans",
                        new Message(new FormattedMessage("COMMON", null, null, null, null,
                                "#FFFFF", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("COPPER ARMOUR"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Armor_Copper_Head.png");
                roundComponent.setItemCost1(5);
            }
            case 1, 2 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans",
                        new Message(new FormattedMessage("COMMON", null, null, null, null,
                                "#FFFFF", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("IRON ARMOUR"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Armor_Iron_Head.png");
                roundComponent.setItemCost1(12);
            }
            case 3, 4, 5 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans",
                        new Message(new FormattedMessage("UNCOMMON", null, null, null, null,
                                "#41DE28", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("BRONZE ARMOUR"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Armor_Bronze_Head.png");
                roundComponent.setItemCost1(15);
            }
            case 6, 7 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("COBALT ARMOUR"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Armor_Cobalt_Head.png");
                roundComponent.setItemCost1(30);
            }
        }
        switch(itemChance2) {
            case 0 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("FLAMETHROWER"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Flamethrower_Goblin.png");
            }
            case 1, 2, 3, 4, 5 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("SLOWING TOTEM"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Furniture_Temple_Emerald_Statue.png");
            }
        }
        switch(itemChance3) {
            case 0, 1, 2, 3 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans",
                        new Message(new FormattedMessage("COMMON", null, null, null, null,
                                "#FFFFF", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("CRUDE BANDAGE"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Bandage_Crude.png");
                roundComponent.setItemCost3(5);
            }
            case 4, 5 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("SLOWING TOTEM"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Furniture_Temple_Emerald_Statue.png");
                roundComponent.setItemCost3(10);
            }
        }
        uiCommandBuilder.set("#PRICE1.TextSpans", Message.raw("GOLD: " + roundComponent.getItemCost1()));
        uiCommandBuilder.set("#PRICE2.TextSpans", Message.raw("GOLD: " + roundComponent.getItemCost2()));
        uiCommandBuilder.set("#PRICE3.TextSpans", Message.raw("GOLD: " + roundComponent.getItemCost3()));
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);
        boolean changed = false;

        Player player = store.getComponent(ref, Player.getComponentType());
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

        if (player != null) {
            if (hotbar != null && armour != null && utility != null && backpack != null && storage != null && tool != null) {
                if (BUY1.equals(data.buttonClicked)) {
                    if (roundComponent.getGold() >= roundComponent.getItemCost1()) {
                        roundComponent.setGold(roundComponent.getGold() - roundComponent.getItemCost1());
                        switch (roundComponent.getItemCost1()) {
                            case 5 -> {
                                armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Copper_Head"));
                                armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Copper_Chest"));
                                armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Copper_Hands"));
                                armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Copper_Legs"));
                                CommandManager.get().handleCommand(playerRef, "say Bought 1x Copper Armour Set");
                            }
                            case 12 -> {
                                armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Iron_Head"));
                                armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Iron_Chest"));
                                armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Iron_Hands"));
                                armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Iron_Legs"));
                                CommandManager.get().handleCommand(playerRef, "say Bought 1x Iron Armour Set");
                            }
                            case 15 -> {
                                armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Bronze_Head"));
                                armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Bronze_Chest"));
                                armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Bronze_Hands"));
                                armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Bronze_Legs"));
                                CommandManager.get().handleCommand(playerRef, "say Bought 1x Bronze Armour Set");
                            }
                            case 30 -> {
                                armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Cobalt_Head"));
                                armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Cobalt_Chest"));
                                armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Cobalt_Hands"));
                                armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Cobalt_Legs"));
                                CommandManager.get().handleCommand(playerRef, "say Bought 1x Cobalt Armour Set");
                            }
                        }
                    } else {
                        CommandManager.get().handleCommand(playerRef, "say NOT ENOUGH GOLD FOR PURCHASE");
                    }
                }
                if (BUY3.equals(data.buttonClicked)) {
                    if (roundComponent.getGold() >= roundComponent.getItemCost3()) {
                        roundComponent.setGold(roundComponent.getGold() - roundComponent.getItemCost3());
                        ItemStack itemStack;
                        switch (roundComponent.getItemCost3()) {
                            case 5 -> {
                                itemStack = new ItemStack("Bandage_Crude", 1);

                                CommandManager.get().handleCommand(playerRef, "say Bought 1x Crude Bandage");
                            }
                            default -> {
                                itemStack = new ItemStack("Bandage_Crude", 1);
                            }
                        }
                        ItemStackTransaction itemStackTransaction = player.giveItem(itemStack, ref, store);
                        ItemStack remainder = itemStackTransaction.getRemainder();

                        if (remainder != null && !remainder.isEmpty()) {
                            CommandManager.get().handleCommand(playerRef, "say NO INVENTORY SPACE");
                        }
                    } else {
                        CommandManager.get().handleCommand(playerRef, "say NOT ENOUGH GOLD FOR PURCHASE");
                    }
                }
            }
            if (CONTINUE.equals(data.buttonClicked)) {
                player.getPageManager().setPage(ref, store, Page.None);
                if (roundComponent.getRoundType() == "classic") {
                    World.setTimeDilation(1F, store);
                }
                roundComponent.setRoundMenu("null");
            }
        }

        if (changed) {
            this.playerRef.sendMessage(Message.raw("Changes processed."));
        }
    }

    @Override
    public void onDismiss(@NonNull Ref<EntityStore> ref, @NonNull Store<EntityStore> store) {
        super.onDismiss(ref, store);
        roundComponent.setRoundCount(roundComponent.getRoundCount() + 1);
        roundComponent.setRoundTimer(45);
        roundComponent.freezeRoundTimer(false);
        roundComponent.setRoundMenu("null");
        if (roundComponent.getRoundType() == "classic") {
            World.setTimeDilation(1F, store);
        }
    }
}