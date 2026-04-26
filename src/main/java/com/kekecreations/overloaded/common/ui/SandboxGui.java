package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.protocol.packets.interface_.Page;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.GoldAndKillsComponent;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class SandboxGui extends InteractiveCustomUIPage<SandboxGui.Data> {

    private static final String PLAY = "PLAYBUTTON";
    private static final String BACK = "BACKBUTTON";
    private static final String CHAOS = "CHAOSBUTTON";
    private static final String ARMOUR = "CYCLEARMOUR";
    private static final String ITEM = "CYCLEITEM";

    RoundComponent roundComponent;

    public SandboxGui(@Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime, RoundComponent roundComponent) {
        super(playerRef, lifetime, SandboxGui.Data.CODEC);
        this.roundComponent = roundComponent;
    }


    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/sandbox.ui");

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYBUTTON", EventData.of("OnButtonClicked", PLAY), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BACKBUTTON", EventData.of("OnButtonClicked", BACK), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CHAOSBUTTON", EventData.of("OnButtonClicked", CHAOS), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLEITEM", EventData.of("OnButtonClicked", ITEM), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLEARMOUR", EventData.of("OnButtonClicked", ARMOUR), false);

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged, "#ROUNDLENGTH", EventData.of("@ROUNDLENGTH", "#ROUNDLENGTH.Value"), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.ValueChanged, "#GOLD", EventData.of("@GOLD", "#GOLD.Value"), false);

        if (roundComponent.getSandboxChaosMode()) {
            uiCommandBuilder.set("#CHAOSBUTTON.TextSpans", Message.raw("CHAOS MODE: ON"));
        } else {
            uiCommandBuilder.set("#CHAOSBUTTON.TextSpans", Message.raw("CHAOS MODE: OFF"));
        }

        if (roundComponent.getSandboxStartingPet() == 0) {
            uiCommandBuilder.set("#ITEM.TextSpans", Message.raw("FIREBALL PET"));
            uiCommandBuilder.set("#STARTINGITEM.AssetPath", "Icons/ItemsGenerated/Fireball_Pet.png");
        }
        if (roundComponent.getSandboxStartingPet() == 1) {
            uiCommandBuilder.set("#ITEM.TextSpans", Message.raw("ICE BALL PET"));
            uiCommandBuilder.set("#STARTINGITEM.AssetPath", "Icons/ItemsGenerated/Ice_Ball_Pet.png");
        }
        if (roundComponent.getSandboxStartingPet() == 2) {
            uiCommandBuilder.set("#ITEM.TextSpans", Message.raw("ACID ORB PET"));
            uiCommandBuilder.set("#STARTINGITEM.AssetPath", "Icons/ItemsGenerated/Acid_Orb_Pet.png");
        }
        if (roundComponent.getSandboxStartingPet() == 3) {
            uiCommandBuilder.set("#ITEM.TextSpans", Message.raw("ANVIL PET"));
            uiCommandBuilder.set("#STARTINGITEM.AssetPath", "Icons/ItemsGenerated/Anvil_Pet.png");
        }
        if (roundComponent.getSandboxStartingPet() == 4) {
            uiCommandBuilder.set("#ITEM.TextSpans", Message.raw("BABY SKELETON"));
            uiCommandBuilder.set("#STARTINGITEM.AssetPath", "Icons/ItemsGenerated/Baby_Skeleton_Pet.png");
        }
        if (roundComponent.getSandboxStartingPet() == 5) {
            uiCommandBuilder.set("#ITEM.TextSpans", Message.raw("BABY ZOMBIE"));
            uiCommandBuilder.set("#STARTINGITEM.AssetPath", "Icons/ItemsGenerated/Baby_Zombie_Pet.png");
        }
        if (roundComponent.getSandboxStartingPet() == 6) {
            uiCommandBuilder.set("#ITEM.TextSpans", Message.raw("BABY SPIDER"));
            uiCommandBuilder.set("#STARTINGITEM.AssetPath", "Icons/ItemsGenerated/Baby_Spider_Pet.png");
        }
        if (roundComponent.getSandboxStartingPet() == 7) {
            uiCommandBuilder.set("#ITEM.TextSpans", Message.raw("DICE"));
            uiCommandBuilder.set("#STARTINGITEM.AssetPath", "Icons/ItemsGenerated/Dice.png");
        }
        if (roundComponent.getSandboxStartingPet() == 8) {
            uiCommandBuilder.set("#ITEM.TextSpans", Message.raw("CRIMSON DICE"));
            uiCommandBuilder.set("#STARTINGITEM.AssetPath", "Icons/ItemsGenerated/Crimson_Dice.png");
        }
        if (roundComponent.getSandboxStartingPet() == 9) {
            uiCommandBuilder.set("#ITEM.TextSpans", Message.raw("LUCKY DICE"));
            uiCommandBuilder.set("#STARTINGITEM.AssetPath", "Icons/ItemsGenerated/Lucky_Dice.png");
        }
        if (roundComponent.getSandboxStartingPet() == 10) {
            uiCommandBuilder.set("#ITEM.TextSpans", Message.raw("UNBREAKABLE BATTLEAXE"));
            uiCommandBuilder.set("#STARTINGITEM.AssetPath", "Icons/ItemsGenerated/Weapon_Custom_Battleaxe_Mithril.png");
        }
        if (roundComponent.getSandboxStartingPet() == 11) {
            uiCommandBuilder.set("#ITEM.TextSpans", Message.raw("3X HEALTH POTION"));
            uiCommandBuilder.set("#STARTINGITEM.AssetPath", "Icons/ItemsGenerated/Potion_Health.png");
        }

        if (roundComponent.getSandboxStartingArmour() == 0) {
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("COPPER ARMOUR"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Copper_Head.png");
        }
        if (roundComponent.getSandboxStartingArmour() == 1) {
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("IRON ARMOUR"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Iron_Head.png");
        }
        if (roundComponent.getSandboxStartingArmour() == 2) {
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("BRONZE ARMOUR"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Bronze_Head.png");
        }
        if (roundComponent.getSandboxStartingArmour() == 3) {
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("ANCIENT STEEL ARMOUR"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Steel_Ancient_Head.png");
        }
        if (roundComponent.getSandboxStartingArmour() == 4) {
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("THORIUM ARMOUR"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Thorium_Head.png");
        }
        if (roundComponent.getSandboxStartingArmour() == 5) {
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("COBALT ARMOUR"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Cobalt_Head.png");
        }
        if (roundComponent.getSandboxStartingArmour() == 6) {
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("ONYXIUM ARMOUR"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Onyxium_Head.png");
        }
        if (roundComponent.getSandboxStartingArmour() == 7) {
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("MITHRIL ARMOUR"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Mithril_Head.png");
        }
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull SandboxGui.Data data) {
        super.handleDataEvent(ref, store, data);
        boolean changed = false;

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));


        if (data.ROUNDLENGTH >= 1) {
            roundData.setSandboxRoundLength((int) data.ROUNDLENGTH);
            changed = true;
        }
        if (data.GOLD >= 0) {
            roundData.setSandboxStartingGold((int) data.GOLD);
            changed = true;
        }
        if (CHAOS.equals(data.buttonClicked)) {
            roundData.setSandboxChaosMode(!roundData.getSandboxChaosMode());
            player.getPageManager().openCustomPage(ref, store, new SandboxGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundData));
        }
        if (ITEM.equals(data.buttonClicked)) {
            if (roundData.getSandboxStartingPet() == 11) {
                roundData.setSandboxStartingPet(0);
            } else {
                roundData.setSandboxStartingPet(roundData.getSandboxStartingPet() + 1);
            }
            player.getPageManager().setPage(ref, store, Page.None);
        }

        if (ARMOUR.equals(data.buttonClicked)) {
            if (roundData.getSandboxStartingArmour() == 7) {
                roundData.setSandboxStartingArmour(0);
            } else {
                roundData.setSandboxStartingArmour(roundData.getSandboxStartingArmour() + 1);
            }
            player.getPageManager().setPage(ref, store, Page.None);
        }

        if (changed) {
            this.playerRef.sendMessage(Message.raw("Changes processed."));
            player.sendMessage(Message.raw("DATA CHANGED"));
        }
    }

    @Override
    public void onDismiss(@NonNull Ref<EntityStore> ref, @NonNull Store<EntityStore> store) {
        super.onDismiss(ref, store);

        for (PlayerRef oPlayerRef : Universe.get().getPlayers()) {
            if (oPlayerRef.isValid() && oPlayerRef.getReference() != null) {
                InventoryComponent hotbarComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-1));
                InventoryComponent armourComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-3));
                InventoryComponent utilityComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-5));
                InventoryComponent backpackComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-9));
                InventoryComponent storageComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-2));
                InventoryComponent toolComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-8));

                ItemContainer hotbar = hotbarComponent.getInventory();
                ItemContainer armour = armourComponent.getInventory();
                ItemContainer utility = utilityComponent.getInventory();
                ItemContainer backpack = backpackComponent.getInventory();
                ItemContainer storage = storageComponent.getInventory();
                ItemContainer tool = toolComponent.getInventory();

                GoldAndKillsComponent goldData = store.getComponent(oPlayerRef.getReference(), GoldAndKillsComponent.getComponentType());

                if (goldData != null) {
                    goldData.setGold(0);
                    goldData.setKills(0);
                }

                if (hotbar != null && armour != null && utility != null && backpack != null && storage != null && tool != null) {
                    hotbar.clear();
                    armour.clear();
                    utility.clear();
                    backpack.clear();
                    storage.clear();
                    tool.clear();
                    hotbar.setItemStackForSlot((short) 0, new ItemStack("Remote"));
                    hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Battleaxe_Copper"));
                    hotbar.setItemStackForSlot((short) 2, new ItemStack("Potion_Health", 3));

                    armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Copper_Head"));
                    armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Copper_Chest"));
                    armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Copper_Hands"));
                    armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Copper_Legs"));
                }
            }
        }
        if (Objects.equals(roundComponent.getRoundType(), "null")) {
            roundComponent.setRoundMenu("sandbox");
        }
    }

    public static class Data {

        public String buttonClicked;

        public float ROUNDLENGTH;
        public float GOLD;

        public static final BuilderCodec<Data> CODEC = BuilderCodec.builder(SandboxGui.Data.class, SandboxGui.Data::new)
                .append(new KeyedCodec<>("OnButtonClicked", Codec.STRING), (menuData, s) -> menuData.buttonClicked = s, choicePageEventData -> choicePageEventData.buttonClicked)
                .add()
                .append(new KeyedCodec<>("@ROUNDLENGTH", Codec.FLOAT), (menuData, s) -> menuData.ROUNDLENGTH = s, menuData -> menuData.ROUNDLENGTH)
                .add()
                .append(new KeyedCodec<>("@GOLD", Codec.FLOAT), (menuData, s) -> menuData.GOLD = s, menuData -> menuData.GOLD)
                .add()
                .build();
    }
}