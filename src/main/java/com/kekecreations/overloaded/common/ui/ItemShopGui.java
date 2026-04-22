package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.*;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.protocol.packets.interface_.Page;
import com.hypixel.hytale.server.core.Message;
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
import com.kekecreations.overloaded.common.component.GoldAndKillsComponent;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;

public class ItemShopGui extends InteractiveCustomUIPage<MenuWithButtonsData> {

    GoldAndKillsComponent goldComponent;

    public ItemShopGui(@Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime, GoldAndKillsComponent goldComponent) {
        super(playerRef, lifetime, MenuWithButtonsData.CODEC);
        this.goldComponent = goldComponent;
    }

    private static final String BUY1 = "BUY1";
    private static final String BUY2 = "BUY2";
    private static final String BUY3 = "BUY3";
    private static final String BUY4 = "BUY4";
    private static final String BUY5 = "BUY5";
    private static final String CONTINUE = "CONTINUE";

    //Armour
    int itemChance = (int)(Math.random() * 16);
    //Weapons
    int itemChance2 = (int)(Math.random() * 51);
    //Throwables & Shields
    int itemChance3 = (int)(Math.random() * 26);
    //Healing & Totems
    int itemChance4 = (int)(Math.random() * 20);
    //Pets and Custom Items
    int itemChance5 = (int)(Math.random() * 40);

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/item_shop.ui");

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BUY1", EventData.of("OnButtonClicked", BUY1), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BUY2", EventData.of("OnButtonClicked", BUY2), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BUY3", EventData.of("OnButtonClicked", BUY3), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BUY4", EventData.of("OnButtonClicked", BUY4), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BUY5", EventData.of("OnButtonClicked", BUY5), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CONTINUE", EventData.of("OnButtonClicked", CONTINUE), false);

        switch(itemChance) {
            case 0, 9 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans",
                        new Message(new FormattedMessage("COMMON", null, null, null, null,
                                "#FFFFF", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("COPPER ARMOUR"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Armor_Copper_Head.png");
                goldComponent.setItemCost1(5);
            }
            case 1, 2, 3 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans",
                        new Message(new FormattedMessage("UNCOMMON", null, null, null, null,
                                "#41DE28", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("IRON ARMOUR"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Armor_Iron_Head.png");
                goldComponent.setItemCost1(12);
            }
            case 4, 5, 6 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans",
                        new Message(new FormattedMessage("UNCOMMON", null, null, null, null,
                                "#41DE28", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("BRONZE ARMOUR"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Armor_Bronze_Head.png");
                goldComponent.setItemCost1(15);
            }
            case 7, 8 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("ANCIENT STEEL ARMOUR"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Armor_Steel_Ancient_Head.png");
                goldComponent.setItemCost1(20);
            }
            case 10, 11 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("THORIUM ARMOUR"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Armor_Thorium_Head.png");
                goldComponent.setItemCost1(25);
            }
            case 12, 13 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("COBALT ARMOUR"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Armor_Cobalt_Head.png");
                goldComponent.setItemCost1(30);
            }
            case 14 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("ONYXIUM ARMOUR"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Armor_Onyxium_Head.png");
                goldComponent.setItemCost1(40);
            }
            case 15 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("MITHRIL ARMOUR"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Armor_Mithril_Head.png");
                goldComponent.setItemCost1(45);
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
                goldComponent.setItemCost2(50);
            }
            case 1, 2, 3, 4 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("COMMON", null, null, null, null,
                                "#FFFFF", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("COPPER SWORD"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Sword_Copper.png");
                goldComponent.setItemCost2(8);
            }
            case 5, 6, 7, 8 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("COMMON", null, null, null, null,
                                "#FFFFF", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("COPPER LONGSWORD"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Longsword_Copper.png");
                goldComponent.setItemCost2(7);
            }
            case 9, 10, 11, 12 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("COMMON", null, null, null, null,
                                "#FFFFF", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("COPPER BATTLEAXE"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Battleaxe_Copper.png");
                goldComponent.setItemCost2(9);
            }
            case 13, 14, 15, 16 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("COMMON", null, null, null, null,
                                "#FFFFF", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("COPPER MACE"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Mace_Copper.png");
                goldComponent.setItemCost2(10);
            }
            case 17, 18, 19, 20 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("COMMON", null, null, null, null,
                                "#FFFFF", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("COPPER DAGGERS"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Daggers_Copper.png");
                goldComponent.setItemCost2(3);
            }
            case 21, 22, 23 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("UNCOMMON", null, null, null, null,
                                "#41DE28", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("IRON SWORD"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Sword_Iron.png");
                goldComponent.setItemCost2(12);
            }
            case 24, 25, 26 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("UNCOMMON", null, null, null, null,
                                "#41DE28", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("IRON LONGSWORD"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Longsword_Iron.png");
                goldComponent.setItemCost2(11);
            }
            case 27, 28, 29 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("UNCOMMON", null, null, null, null,
                                "#41DE28", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("IRON BATTLEAXE"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Battleaxe_Iron.png");
                goldComponent.setItemCost2(13);
            }
            case 30, 31, 32 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("UNCOMMON", null, null, null, null,
                                "#41DE28", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("IRON MACE"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Mace_Iron.png");
                goldComponent.setItemCost2(14);
            }
            case 33, 34, 35 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("UNCOMMON", null, null, null, null,
                                "#41DE28", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("IRON DAGGERS"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Daggers_Iron.png");
                goldComponent.setItemCost2(4);
            }
            case 36, 37 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("THORIUM SWORD"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Sword_Thorium.png");
                goldComponent.setItemCost2(16);
            }
            case 38, 39 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("THORIUM LONGSWORD"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Longsword_Thorium.png");
                goldComponent.setItemCost2(15);
            }
            case 40, 41 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("THORIUM BATTLEAXE"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Battleaxe_Thorium.png");
                goldComponent.setItemCost2(17);
            }
            case 42, 43 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("THORIUM MACE"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Mace_Thorium.png");
                goldComponent.setItemCost2(18);
            }
            case 44, 45 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("THORIUM DAGGERS"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Daggers_Thorium.png");
                goldComponent.setItemCost2(5);
            }
            case 46 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("ONYXIUM SWORD"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Sword_Onyxium.png");
                goldComponent.setItemCost2(21);
            }
            case 47 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("ONYXIUM LONGSWORD"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Longsword_Onyxium.png");
                goldComponent.setItemCost2(20);
            }
            case 48 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("ONYXIUM BATTLEAXE"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Battleaxe_Onyxium.png");
                goldComponent.setItemCost2(22);
            }
            case 49 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("ONYXIUM MACE"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Mace_Onyxium.png");
                goldComponent.setItemCost2(23);
            }
            case 50 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("ONYXIUM DAGGERS"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Weapon_Daggers_Onyxium.png");
                goldComponent.setItemCost2(6);
            }
        }
        //Shields and throwables
        switch(itemChance3) {
            case 0, 1, 2, 3 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans",
                        new Message(new FormattedMessage("COMMON", null, null, null, null,
                                "#FFFFF", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("COPPER SHIELD"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Weapon_Shield_Copper.png");
                goldComponent.setItemCost3(10);
            }
            case 4, 5, 6 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans",
                        new Message(new FormattedMessage("UNCOMMON", null, null, null, null,
                                "#41DE28", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("IRON SHIELD"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Weapon_Shield_Iron.png");
                goldComponent.setItemCost3(15);
            }
            case 7, 8 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("THORIUM SHIELD"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Weapon_Shield_Thorium.png");
                goldComponent.setItemCost3(20);
            }
            case 9, 10 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("COBALT SHIELD"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Weapon_Shield_Cobalt.png");
                goldComponent.setItemCost3(25);
            }
            case 11, 12 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans",
                        new Message(new FormattedMessage("COMMON", null, null, null, null,
                                "#FFFFF", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("BROKEN SHIELD"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Broken_Shield.png");
                goldComponent.setItemCost3(5);
            }
            case 13, 14 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans",
                        new Message(new FormattedMessage("UNCOMMON", null, null, null, null,
                                "#41DE28", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("KING SHIELD"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/King_Shield.png");
                goldComponent.setItemCost3(11);
            }
            case 15, 16, 17 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans",
                        new Message(new FormattedMessage("UNCOMMON", null, null, null, null,
                                "#41DE28", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("3X BOMBS"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Weapon_Bomb_Fire_Goblin.png");
                goldComponent.setItemCost3(12);
            }
            case 18, 19 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("3X POISON BOMBS"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Weapon_Bomb_Potion_Poison.png");
                goldComponent.setItemCost3(16);
            }
            case 20, 21, 22 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans",
                        new Message(new FormattedMessage("UNCOMMON", null, null, null, null,
                                "#41DE28", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("IRON SPEAR"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Overloaded_Iron_Spear.png");
                goldComponent.setItemCost3(13);
            }
            case 23, 24 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("COBALT SPEAR"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Overloaded_Cobalt_Spear.png");
                goldComponent.setItemCost3(18);
            }
            case 25 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("FLAME SPEAR"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Overloaded_Flame_Spear.png");
                goldComponent.setItemCost3(35);
            }
        }
        //Healing and totems
        switch(itemChance4) {
            case 0, 1, 2, 3 -> {
                uiCommandBuilder.set("#RARITY4.TextSpans",
                        new Message(new FormattedMessage("COMMON", null, null, null, null,
                                "#FFFFF", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC4.TextSpans", Message.raw("CRUDE BANDAGE"));
                uiCommandBuilder.set("#IMAGE4.AssetPath", "Icons/ItemsGenerated/Bandage_Crude.png");
                goldComponent.setItemCost4(5);
            }
            case 4, 5 -> {
                uiCommandBuilder.set("#RARITY4.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC4.TextSpans", Message.raw("SLOWING TOTEM"));
                uiCommandBuilder.set("#IMAGE4.AssetPath", "Icons/ItemsGenerated/Furniture_Temple_Emerald_Statue.png");
                goldComponent.setItemCost4(10);
            }
            case 6, 7 -> {
                uiCommandBuilder.set("#RARITY4.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC4.TextSpans", Message.raw("HEALING TOTEM"));
                uiCommandBuilder.set("#IMAGE4.AssetPath", "Icons/ItemsGenerated/Healing_Totem.png");
                goldComponent.setItemCost4(15);
            }
            case 8, 9 -> {
                uiCommandBuilder.set("#RARITY4.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC4.TextSpans", Message.raw("SHIMMER VIAL"));
                uiCommandBuilder.set("#IMAGE4.AssetPath", "Icons/ItemsGenerated/Shimmer_Vial.png");
                goldComponent.setItemCost4(24);
            }
            case 10, 11 -> {
                uiCommandBuilder.set("#RARITY4.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC4.TextSpans", Message.raw("SLAP VIAL"));
                uiCommandBuilder.set("#IMAGE4.AssetPath", "Icons/ItemsGenerated/Slap_Vial.png");
                goldComponent.setItemCost4(20);
            }
            case 12, 13 -> {
                uiCommandBuilder.set("#RARITY4.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC4.TextSpans", Message.raw("HEX VIAL"));
                uiCommandBuilder.set("#IMAGE4.AssetPath", "Icons/ItemsGenerated/Hex_Vial.png");
                goldComponent.setItemCost4(22);
            }
            case 14, 15 -> {
                uiCommandBuilder.set("#RARITY4.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC4.TextSpans", Message.raw("HEALTH POTION"));
                uiCommandBuilder.set("#IMAGE4.AssetPath", "Icons/ItemsGenerated/Potion_Health.png");
                goldComponent.setItemCost4(8);
            }
            case 16, 17 -> {
                uiCommandBuilder.set("#RARITY4.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC4.TextSpans", Message.raw("VAMPIRE FANG"));
                uiCommandBuilder.set("#IMAGE4.AssetPath", "Icons/ItemsGenerated/Vampire_Fang.png");
                goldComponent.setItemCost4(14);
            }
            case 18, 19 -> {
                uiCommandBuilder.set("#RARITY4.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC4.TextSpans", Message.raw("GOLD VAMPIRE FANG"));
                uiCommandBuilder.set("#IMAGE4.AssetPath", "Icons/ItemsGenerated/Gold_Vampire_Fang.png");
                goldComponent.setItemCost4(18);
            }
        }
        //Pets
        switch(itemChance5) {
            case 0, 1, 2 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("COMMON", null, null, null, null,
                                "#FFFFF", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("ANVIL PET"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Anvil_Pet.png");
                goldComponent.setItemCost5(15);
            }
            case 4, 5, 6, 7 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("COMMON", null, null, null, null,
                                "#FFFFF", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("TRASH CAN"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Trash_Can.png");
                goldComponent.setItemCost5(8);
            }
            case 8, 9, 10, 11 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("COMMON", null, null, null, null,
                                "#FFFFF", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("CRIMSON_DICE"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Crimson_Dice.png");
                goldComponent.setItemCost5(10);
            }
            case 12, 13 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("KUNAI PACK"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Kunai_Pack.png");
                goldComponent.setItemCost5(12);
            }
            case 14 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("ACID ORB PET"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Acid_Orb_Pet.png");
                goldComponent.setItemCost5(22);
            }
            case 15, 16 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("METEOR PET"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Meteor_Pet.png");
                goldComponent.setItemCost5(25);
            }
            case 17, 18 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("THE CORE"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/The_Core.png");
                goldComponent.setItemCost5(18);
            }
            case 19, 20 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("HEART PET"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Heart_Pet.png");
                goldComponent.setItemCost5(13);
            }
            case 21 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("ICE BALL PET"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Ice_Ball_Pet.png");
                goldComponent.setItemCost5(20);
            }
            case 22, 3 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("FIREBALL PET"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Fireball_Pet.png");
                goldComponent.setItemCost5(35);
            }
            case 23 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("LEGENDARY", null, null, null, null,
                                "#CFB30E", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("LUCKY DICE"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Lucky_Dice.png");
                goldComponent.setItemCost5(150);
            }
            case 24 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("LEGENDARY", null, null, null, null,
                                "#CFB30E", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("DICE"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Dice.png");
                goldComponent.setItemCost5(50);
            }
            case 25, 26 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("SPEAR HEAD PET"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Spear_Head_Pet.png");
                goldComponent.setItemCost5(9);
            }
            case 27, 28 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("WAR SPEAR HEAD PET"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/War_Ready_Spear_Head_Pet.png");
                goldComponent.setItemCost5(21);
            }
            case 29, 30 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("GOLD SPEAR HEAD PET"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Gold_Spear_Head_Pet.png");
                goldComponent.setItemCost5(23);
            }
            case 31, 32 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("BABY SKELETON"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Baby_Skeleton_Pet.png");
                goldComponent.setItemCost5(26);
            }
            case 33, 34 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("BABY ZOMBIE"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Baby_Zombie_Pet.png");
                goldComponent.setItemCost5(27);
            }
            case 35, 36 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("BABY SPIDER"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Baby_Spider_Pet.png");
                goldComponent.setItemCost5(28);
            }
            case 37 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("BABY COLD ZOMBIE"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Baby_Cold_Zombie_Pet.png");
                goldComponent.setItemCost5(32);
            }
            case 38 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("BABY BURNT SKELETON"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Baby_Burnt_Skeleton_Pet.png");
                goldComponent.setItemCost5(34);
            }
            case 39 -> {
                uiCommandBuilder.set("#RARITY5.TextSpans",
                        new Message(new FormattedMessage("LEGENDARY", null, null, null, null,
                                "#CFB30E", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC5.TextSpans", Message.raw("BABY ZOMBIE BRUTE"));
                uiCommandBuilder.set("#IMAGE5.AssetPath", "Icons/ItemsGenerated/Baby_Zombie_Brute_Pet.png");
                goldComponent.setItemCost5(42);
            }
        }
        uiCommandBuilder.set("#PRICE1.TextSpans", Message.raw("GOLD: " + goldComponent.getItemCost1()));
        uiCommandBuilder.set("#PRICE2.TextSpans", Message.raw("GOLD: " + goldComponent.getItemCost2()));
        uiCommandBuilder.set("#PRICE3.TextSpans", Message.raw("GOLD: " + goldComponent.getItemCost3()));
        uiCommandBuilder.set("#PRICE4.TextSpans", Message.raw("GOLD: " + goldComponent.getItemCost4()));
        uiCommandBuilder.set("#PRICE5.TextSpans", Message.raw("GOLD: " + goldComponent.getItemCost5()));
        uiCommandBuilder.set("#TOTALCOINS.TextSpans", Message.raw("TOTAL GOLD: " + goldComponent.getGold()));
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
                    if (goldComponent.getGold() >= goldComponent.getItemCost1()) {
                        goldComponent.setGold(goldComponent.getGold() - goldComponent.getItemCost1());
                        switch (goldComponent.getItemCost1()) {
                            case 5 -> {
                                armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Copper_Head"));
                                armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Copper_Chest"));
                                armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Copper_Hands"));
                                armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Copper_Legs"));
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Copper Armour Set"));
                            }
                            case 12 -> {
                                armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Iron_Head"));
                                armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Iron_Chest"));
                                armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Iron_Hands"));
                                armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Iron_Legs"));
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Iron Armour Set"));
                            }
                            case 15 -> {
                                armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Bronze_Head"));
                                armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Bronze_Chest"));
                                armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Bronze_Hands"));
                                armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Bronze_Legs"));
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Bronze Armour Set"));
                            }
                            case 20 -> {
                                armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Steel_Ancient_Head"));
                                armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Steel_Ancient_Chest"));
                                armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Steel_Ancient_Hands"));
                                armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Steel_Ancient_Legs"));
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Ancient Steel Armour Set"));
                            }
                            case 25 -> {
                                armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Thorium_Head"));
                                armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Thorium_Chest"));
                                armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Thorium_Hands"));
                                armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Thorium_Legs"));
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Thorium Armour Set"));
                            }
                            case 30 -> {
                                armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Cobalt_Head"));
                                armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Cobalt_Chest"));
                                armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Cobalt_Hands"));
                                armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Cobalt_Legs"));
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Cobalt Armour Set"));
                            }
                            case 40 -> {
                                armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Onyxium_Head"));
                                armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Onyxium_Chest"));
                                armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Onyxium_Hands"));
                                armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Onyxium_Legs"));
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Onyxium Armour Set"));
                            }
                            case 45 -> {
                                armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Mithril_Head"));
                                armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Mithril_Chest"));
                                armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Mithril_Hands"));
                                armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Mithril_Legs"));
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Mithril Armour Set"));
                            }
                        }
                    } else {
                        player.sendMessage(Message.raw(player.getDisplayName() + " NOT ENOUGH GOLD FOR PURCHASE"));
                    }
                }
                if (BUY2.equals(data.buttonClicked)) {
                    if (goldComponent.getGold() >= goldComponent.getItemCost2()) {
                        ItemStack itemStack;

                        switch (goldComponent.getItemCost2()) {
                            case 50 -> {
                                itemStack = new ItemStack("Flamethrower_Goblin", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Flamethrower"));
                            }
                            case 3 -> {
                                itemStack = new ItemStack("Weapon_Daggers_Copper", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Copper Daggers"));
                            }
                            case 7 -> {
                                itemStack = new ItemStack("Weapon_Longsword_Copper", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Copper Longsword"));
                            }
                            case 8 -> {
                                itemStack = new ItemStack("Weapon_Sword_Copper", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Copper Sword"));
                            }
                            case 9 -> {
                                itemStack = new ItemStack("Weapon_Battleaxe_Copper", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Copper Battleaxe"));
                            }
                            case 10 -> {
                                itemStack = new ItemStack("Weapon_Mace_Copper", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Copper Mace"));
                            }
                            case 4 -> {
                                itemStack = new ItemStack("Weapon_Daggers_Iron", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Iron Daggers"));
                            }
                            case 11 -> {
                                itemStack = new ItemStack("Weapon_Longsword_Iron", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Iron Longsword"));
                            }
                            case 12 -> {
                                itemStack = new ItemStack("Weapon_Sword_Iron", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Iron Sword"));
                            }
                            case 13 -> {
                                itemStack = new ItemStack("Weapon_Battleaxe_Iron", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Iron Battleaxe"));
                            }
                            case 14 -> {
                                itemStack = new ItemStack("Weapon_Mace_Iron", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Iron Mace"));
                            }
                            case 5 -> {
                                itemStack = new ItemStack("Weapon_Daggers_Thorium", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Thorium Daggers"));
                            }
                            case 15 -> {
                                itemStack = new ItemStack("Weapon_Longsword_Thorium", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Thorium Longsword"));
                            }
                            case 16 -> {
                                itemStack = new ItemStack("Weapon_Sword_Thorium", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Thorium Sword"));
                            }
                            case 17 -> {
                                itemStack = new ItemStack("Weapon_Battleaxe_Thorium", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Thorium Battleaxe"));
                            }
                            case 18 -> {
                                itemStack = new ItemStack("Weapon_Mace_Thorium", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() +  " Bought 1x Thorium Mace"));
                            }
                            case 6 -> {
                                itemStack = new ItemStack("Weapon_Daggers_Onyxium", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Onyxium Daggers"));
                            }
                            case 20 -> {
                                itemStack = new ItemStack("Weapon_Longsword_Onyxium", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Onyxium Longsword"));
                            }
                            case 21 -> {
                                itemStack = new ItemStack("Weapon_Sword_Onyxium", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Onyxium Sword"));
                            }
                            case 22 -> {
                                itemStack = new ItemStack("Weapon_Battleaxe_Onyxium", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Onyxium Battleaxe"));
                            }
                            case 23 -> {
                                itemStack = new ItemStack("Weapon_Mace_Onyxium", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Onyxium Mace"));
                            }
                            default -> {
                                itemStack = new ItemStack("Bandage_Crude", 1);
                            }
                        }

                        ItemStackTransaction itemStackTransaction = player.giveItem(itemStack, ref, store);
                        ItemStack remainder = itemStackTransaction.getRemainder();

                        if (remainder != null && !remainder.isEmpty()) {
                            player.sendMessage(Message.raw(player.getDisplayName() + " NO INVENTORY SPACE"));
                        } else {
                            goldComponent.setGold(goldComponent.getGold() - goldComponent.getItemCost2());
                        }

                    } else {
                        player.sendMessage(Message.raw(player.getDisplayName() + " NOT ENOUGH GOLD FOR PURCHASE"));
                    }
                }
                if (BUY3.equals(data.buttonClicked)) {
                    if (goldComponent.getGold() >= goldComponent.getItemCost3()) {
                        ItemStack itemStack;
                        switch (goldComponent.getItemCost3()) {
                            case 10 -> {
                                itemStack = new ItemStack("Weapon_Shield_Copper", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Copper Shield"));
                            }
                            case 15 -> {
                                itemStack = new ItemStack("Weapon_Shield_Iron", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Iron Shield"));
                            }
                            case 20 -> {
                                itemStack = new ItemStack("Weapon_Shield_Thorium", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Thorium Shield"));
                            }
                            case 25 -> {
                                itemStack = new ItemStack("Weapon_Shield_Cobalt", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Cobalt Shield"));
                            }
                            case 5 -> {
                                itemStack = new ItemStack("Broken_Shield", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Broken Shield"));
                            }
                            case 11 -> {
                                itemStack = new ItemStack("King_Shield", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x King Shield"));
                            }
                            case 12 -> {
                                itemStack = new ItemStack("Weapon_Bomb", 3);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 3x Bombs"));
                            }
                            case 16 -> {
                                itemStack = new ItemStack("Weapon_Bomb_Potion_Poison", 3);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 3x Poison Bombs"));
                            }
                            case 13 -> {
                                itemStack = new ItemStack("Overloaded_Iron_Spear", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Iron Spear"));
                            }
                            case 18 -> {
                                itemStack = new ItemStack("Overloaded_Cobalt_Spear", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Cobalt Spear"));
                            }
                            case 35 -> {
                                itemStack = new ItemStack("Overloaded_Flame_Spear", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Flame Spear"));
                            }
                            default -> {
                                itemStack = new ItemStack("Bandage_Crude", 1);
                            }
                        }
                        ItemStackTransaction itemStackTransaction = player.giveItem(itemStack, ref, store);
                        ItemStack remainder = itemStackTransaction.getRemainder();

                        if (remainder != null && !remainder.isEmpty()) {
                            player.sendMessage(Message.raw(player.getDisplayName() + " NO INVENTORY SPACE"));
                        } else {
                            goldComponent.setGold(goldComponent.getGold() - goldComponent.getItemCost3());
                        }
                    } else {
                        player.sendMessage(Message.raw(player.getDisplayName() + " NOT ENOUGH GOLD FOR PURCHASE"));
                    }
                }
                if (BUY4.equals(data.buttonClicked)) {
                    if (goldComponent.getGold() >= goldComponent.getItemCost4()) {
                        ItemStack itemStack;
                        switch (goldComponent.getItemCost4()) {
                            case 5 -> {
                                itemStack = new ItemStack("Bandage_Crude", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Crude Bandage"));
                            }
                            case 10 -> {
                                itemStack = new ItemStack("Weapon_Deployable_Slowness_Totem", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Slowness Totem"));
                            }
                            case 15 -> {
                                itemStack = new ItemStack("Weapon_Deployable_Healing_Totem", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Healing Totem"));
                            }
                            case 8 -> {
                                itemStack = new ItemStack("Potion_Health", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Health Potion"));
                            }
                            case 22 -> {
                                itemStack = new ItemStack("Hex_Vial", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Hex Vial"));
                            }
                            case 20 -> {
                                itemStack = new ItemStack("Slap_Vial", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Slap Vial"));
                            }
                            case 24 -> {
                                itemStack = new ItemStack("Shimmer_Vial", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Shimmer Vial"));
                            }
                            case 14 -> {
                                itemStack = new ItemStack("Vampire_Fang", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Vampire Fang"));
                            }
                            case 18 -> {
                                itemStack = new ItemStack("Gold_Vampire_Fang", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Gold_Vampire Fang"));
                            }
                            default -> {
                                itemStack = new ItemStack("Bandage_Crude", 1);
                            }
                        }
                        ItemStackTransaction itemStackTransaction = player.giveItem(itemStack, ref, store);
                        ItemStack remainder = itemStackTransaction.getRemainder();

                        if (remainder != null && !remainder.isEmpty()) {
                            player.sendMessage(Message.raw(player.getDisplayName() + " NO INVENTORY SPACE"));
                        } else {
                            goldComponent.setGold(goldComponent.getGold() - goldComponent.getItemCost4());
                        }
                    } else {
                        player.sendMessage(Message.raw(player.getDisplayName() + " NOT ENOUGH GOLD FOR PURCHASE"));
                    }
                }
                if (BUY5.equals(data.buttonClicked)) {
                    if (goldComponent.getGold() >= goldComponent.getItemCost5()) {
                        ItemStack itemStack;
                        switch (goldComponent.getItemCost5()) {
                            case 15 -> {
                                itemStack = new ItemStack("Anvil_Pet", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Anvil Pet"));
                            }
                            case 8 -> {
                                itemStack = new ItemStack("Trash_Can", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Trash Can"));
                            }
                            case 10 -> {
                                itemStack = new ItemStack("Crimson_Dice", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Crimson Dice"));
                            }
                            case 12 -> {
                                itemStack = new ItemStack("Kunai_Pack", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Kunai Pack"));
                            }
                            case 22 -> {
                                itemStack = new ItemStack("Acid_Orb_Pet", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Acid_Orb_Pet"));
                            }
                            case 25 -> {
                                itemStack = new ItemStack("Meteor_Pet", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Meteor Pet"));
                            }
                            case 18 -> {
                                itemStack = new ItemStack("The_Core", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x The Core"));
                            }
                            case 13 -> {
                                itemStack = new ItemStack("Heart_Pet", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Heart Pet"));
                            }
                            case 20 -> {
                                itemStack = new ItemStack("Ice_Ball_Pet", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Ice Ball Pet"));
                            }
                            case 35 -> {
                                itemStack = new ItemStack("Fireball_Pet", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Fireball Pet"));
                            }
                            case 150 -> {
                                itemStack = new ItemStack("Lucky_Dice", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Lucky Dice"));
                            }
                            case 50 -> {
                                itemStack = new ItemStack("Dice", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Dice"));
                            }
                            case 9 -> {
                                itemStack = new ItemStack("Spear_Head_Pet", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Spear Head Pet"));
                            }
                            case 21 -> {
                                itemStack = new ItemStack("War_Ready_Spear_Head_Pet", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x War Ready Spear Head Pet"));
                            }
                            case 23 -> {
                                itemStack = new ItemStack("Gold_Spear_Head_Pet", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Gold Spear Head Pet"));
                            }
                            case 26 -> {
                                itemStack = new ItemStack("Baby_Skeleton", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Baby Skeleton"));
                            }
                            case 27 -> {
                                itemStack = new ItemStack("Baby_Zombie", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Baby Zombie"));
                            }
                            case 28 -> {
                                itemStack = new ItemStack("Baby_Spider", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Baby Spider"));
                            }
                            case 32 -> {
                                itemStack = new ItemStack("Baby_Cold_Zombie", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Baby Cold Zombie"));
                            }
                            case 34 -> {
                                itemStack = new ItemStack("Baby_Burnt_Skeleton", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Baby Burnt Skeleton"));
                            }
                            case 42 -> {
                                itemStack = new ItemStack("Baby_Zombie_Brute", 1);
                                player.sendMessage(Message.raw(player.getDisplayName() + " Bought 1x Baby Zombie Brute"));
                            }
                            default -> {
                                itemStack = new ItemStack("Bandage_Crude", 1);
                            }
                        }
                        ItemStackTransaction itemStackTransaction = player.giveItem(itemStack, ref, store);
                        ItemStack remainder = itemStackTransaction.getRemainder();

                        if (remainder != null && !remainder.isEmpty()) {
                            player.sendMessage(Message.raw(player.getDisplayName() + " NO INVENTORY SPACE"));
                        } else {
                            goldComponent.setGold(goldComponent.getGold() - goldComponent.getItemCost5());
                        }
                    } else {
                        player.sendMessage(Message.raw(player.getDisplayName() + " NOT ENOUGH GOLD FOR PURCHASE"));
                    }
                }
            }
            if (CONTINUE.equals(data.buttonClicked)) {
                player.getPageManager().setPage(ref, store, Page.None);
                RoundComponent roundComponent = store.getComponent(ref, RoundComponent.getComponentType());
                if (roundComponent != null) {
                    roundComponent.setRoundMenu("null");
                }
            }
        }

        if (changed) {
            this.playerRef.sendMessage(Message.raw("Changes processed."));
        }
    }

    @Override
    public void onDismiss(@NonNull Ref<EntityStore> ref, @NonNull Store<EntityStore> store) {
        super.onDismiss(ref, store);
        RoundComponent roundComponent = store.getComponent(ref, RoundComponent.getComponentType());
        if (roundComponent != null) {
            roundComponent.setRoundCount(roundComponent.getRoundCount() + 1);
            roundComponent.freezeRoundTimer(false);
            roundComponent.setRoundMenu("null");
            if (roundComponent.getRoundType() == "classic" || roundComponent.getRoundType() == "quick" || roundComponent.getRoundType() == "chaos") {
                World.setTimeDilation(1F, store);
            }
            if (roundComponent.getRoundType() == "classic" || roundComponent.getRoundType() == "rounds") {
                roundComponent.setRoundTimer(45);
            }
            if (roundComponent.getRoundType() == "quick") {
                roundComponent.setRoundTimer(25);
            }
            if (roundComponent.getRoundType() == "chaos") {
                roundComponent.setRoundTimer(60);
            }
        }
    }
}