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
    private static final String CYCLE1 = "CYCLE1";
    private static final String CYCLE2 = "CYCLE2";
    private static final String CYCLE3 = "CYCLE3";
    private static final String CYCLE4 = "CYCLE4";
    private static final String CYCLE5 = "CYCLE5";
    private static final String CYCLE6 = "CYCLE6";
    private static final String CYCLE7 = "CYCLE7";
    private static final String CYCLE8 = "CYCLE8";
    private static final String CYCLE9 = "CYCLE9";
    private static final String CYCLE10 = "CYCLE10";


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
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLE1", EventData.of("OnButtonClicked", CYCLE1), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLE2", EventData.of("OnButtonClicked", CYCLE2), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLE3", EventData.of("OnButtonClicked", CYCLE3), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLE4", EventData.of("OnButtonClicked", CYCLE4), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLE5", EventData.of("OnButtonClicked", CYCLE5), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLE6", EventData.of("OnButtonClicked", CYCLE6), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLE7", EventData.of("OnButtonClicked", CYCLE7), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLE8", EventData.of("OnButtonClicked", CYCLE8), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLE9", EventData.of("OnButtonClicked", CYCLE9), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CYCLE10", EventData.of("OnButtonClicked", CYCLE10), false);

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
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("COPPER EQUIPMENT"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Copper_Head.png");
        }
        if (roundComponent.getSandboxStartingArmour() == 1) {
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("IRON EQUIPMENT"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Iron_Head.png");
        }
        if (roundComponent.getSandboxStartingArmour() == 2) {
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("BRONZE EQUIPMENT"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Bronze_Head.png");
        }
        if (roundComponent.getSandboxStartingArmour() == 3) {
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("ANCIENT STEEL EQUIPMENT"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Steel_Ancient_Head.png");
        }
        if (roundComponent.getSandboxStartingArmour() == 4) {
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("THORIUM EQUIPMENT"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Thorium_Head.png");
        }
        if (roundComponent.getSandboxStartingArmour() == 5) {
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("COBALT EQUIPMENT"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Cobalt_Head.png");
        }
        if (roundComponent.getSandboxStartingArmour() == 6) {
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("ONYXIUM EQUIPMENT"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Onyxium_Head.png");
        }
        if (roundComponent.getSandboxStartingArmour() == 7) {
            uiCommandBuilder.set("#ARMOUR.TextSpans", Message.raw("MITHRIL EQUIPMENT"));
            uiCommandBuilder.set("#STARTINGARMOUR.AssetPath", "Icons/ItemsGenerated/Armor_Mithril_Head.png");
        }

        //SECTIONS
        if (roundComponent.getSandboxSection1() == 0) {
            uiCommandBuilder.set("#SECTION1.TextSpans", Message.raw("THE UNDEAD RISES"));
        }
        if (roundComponent.getSandboxSection1() == 1) {
            uiCommandBuilder.set("#SECTION1.TextSpans", Message.raw("THE DEAD ZONE"));
        }
        if (roundComponent.getSandboxSection1() == 2) {
            uiCommandBuilder.set("#SECTION1.TextSpans", Message.raw("AHOY DEADLY"));
        }
        if (roundComponent.getSandboxSection1() == 3) {
            uiCommandBuilder.set("#SECTION1.TextSpans", Message.raw("WOLF PACK"));
        }
        if (roundComponent.getSandboxSection1() == 4) {
            uiCommandBuilder.set("#SECTION1.TextSpans", Message.raw("ARACHNIDS"));
        }
        if (roundComponent.getSandboxSection1() == 5) {
            uiCommandBuilder.set("#SECTION1.TextSpans", Message.raw("VOID UGLIES"));
        }
        if (roundComponent.getSandboxSection1() == 6) {
            uiCommandBuilder.set("#SECTION1.TextSpans", Message.raw("BUG SEASON"));
        }
        if (roundComponent.getSandboxSection1() == 7) {
            uiCommandBuilder.set("#SECTION1.TextSpans", Message.raw("GOLDEN LURE"));
        }
        if (roundComponent.getSandboxSection1() == 8) {
            uiCommandBuilder.set("#SECTION1.TextSpans", Message.raw("EMBER MIGHT"));
        }
        if (roundComponent.getSandboxSection1() == 9) {
            uiCommandBuilder.set("#SECTION1.TextSpans", Message.raw("FERAN RAID"));
        }
        if (roundComponent.getSandboxSection1() == 10) {
            uiCommandBuilder.set("#SECTION1.TextSpans", Message.raw("SUPERNATURAL"));
        }
        if (roundComponent.getSandboxSection1() == 11) {
            uiCommandBuilder.set("#SECTION1.TextSpans", Message.raw("OUTLANDERS"));
        }
        if (roundComponent.getSandboxSection1() == 12) {
            uiCommandBuilder.set("#SECTION1.TextSpans", Message.raw("10 ROUNDS TO LIVE"));
        }

        //2
        if (roundComponent.getSandboxSection2() == 0) {
            uiCommandBuilder.set("#SECTION2.TextSpans", Message.raw("THE UNDEAD RISES"));
        }
        if (roundComponent.getSandboxSection2() == 1) {
            uiCommandBuilder.set("#SECTION2.TextSpans", Message.raw("THE DEAD ZONE"));
        }
        if (roundComponent.getSandboxSection2() == 2) {
            uiCommandBuilder.set("#SECTION2.TextSpans", Message.raw("AHOY DEADLY"));
        }
        if (roundComponent.getSandboxSection2() == 3) {
            uiCommandBuilder.set("#SECTION2.TextSpans", Message.raw("WOLF PACK"));
        }
        if (roundComponent.getSandboxSection2() == 4) {
            uiCommandBuilder.set("#SECTION2.TextSpans", Message.raw("ARACHNIDS"));
        }
        if (roundComponent.getSandboxSection2() == 5) {
            uiCommandBuilder.set("#SECTION2.TextSpans", Message.raw("VOID UGLIES"));
        }
        if (roundComponent.getSandboxSection2() == 6) {
            uiCommandBuilder.set("#SECTION2.TextSpans", Message.raw("BUG SEASON"));
        }
        if (roundComponent.getSandboxSection2() == 7) {
            uiCommandBuilder.set("#SECTION2.TextSpans", Message.raw("GOLDEN LURE"));
        }
        if (roundComponent.getSandboxSection2() == 8) {
            uiCommandBuilder.set("#SECTION2.TextSpans", Message.raw("EMBER MIGHT"));
        }
        if (roundComponent.getSandboxSection2() == 9) {
            uiCommandBuilder.set("#SECTION2.TextSpans", Message.raw("FERAN RAID"));
        }
        if (roundComponent.getSandboxSection2() == 10) {
            uiCommandBuilder.set("#SECTION2.TextSpans", Message.raw("SUPERNATURAL"));
        }
        if (roundComponent.getSandboxSection2() == 11) {
            uiCommandBuilder.set("#SECTION1.TextSpans", Message.raw("OUTLANDERS"));
        }
        if (roundComponent.getSandboxSection2() == 12) {
            uiCommandBuilder.set("#SECTION1.TextSpans", Message.raw("10 ROUNDS TO LIVE"));
        }

        //3

        if (roundComponent.getSandboxSection3() == 0) {
            uiCommandBuilder.set("#SECTION3.TextSpans", Message.raw("THE UNDEAD RISES"));
        }
        if (roundComponent.getSandboxSection3() == 1) {
            uiCommandBuilder.set("#SECTION3.TextSpans", Message.raw("THE DEAD ZONE"));
        }
        if (roundComponent.getSandboxSection3() == 2) {
            uiCommandBuilder.set("#SECTION3.TextSpans", Message.raw("AHOY DEADLY"));
        }
        if (roundComponent.getSandboxSection3() == 3) {
            uiCommandBuilder.set("#SECTION3.TextSpans", Message.raw("WOLF PACK"));
        }
        if (roundComponent.getSandboxSection3() == 4) {
            uiCommandBuilder.set("#SECTION3.TextSpans", Message.raw("ARACHNIDS"));
        }
        if (roundComponent.getSandboxSection3() == 5) {
            uiCommandBuilder.set("#SECTION3.TextSpans", Message.raw("VOID UGLIES"));
        }
        if (roundComponent.getSandboxSection3() == 6) {
            uiCommandBuilder.set("#SECTION3.TextSpans", Message.raw("BUG SEASON"));
        }
        if (roundComponent.getSandboxSection3() == 7) {
            uiCommandBuilder.set("#SECTION3.TextSpans", Message.raw("GOLDEN LURE"));
        }
        if (roundComponent.getSandboxSection3() == 8) {
            uiCommandBuilder.set("#SECTION3.TextSpans", Message.raw("EMBER MIGHT"));
        }
        if (roundComponent.getSandboxSection3() == 9) {
            uiCommandBuilder.set("#SECTION3.TextSpans", Message.raw("FERAN RAID"));
        }
        if (roundComponent.getSandboxSection3() == 10) {
            uiCommandBuilder.set("#SECTION3.TextSpans", Message.raw("SUPERNATURAL"));
        }
        if (roundComponent.getSandboxSection3() == 11) {
            uiCommandBuilder.set("#SECTION3.TextSpans", Message.raw("OUTLANDERS"));
        }
        if (roundComponent.getSandboxSection3() == 12) {
            uiCommandBuilder.set("#SECTION3.TextSpans", Message.raw("10 ROUNDS TO LIVE"));
        }

        //4
        if (roundComponent.getSandboxSection4() == 0) {
            uiCommandBuilder.set("#SECTION4.TextSpans", Message.raw("THE UNDEAD RISES"));
        }
        if (roundComponent.getSandboxSection4() == 1) {
            uiCommandBuilder.set("#SECTION4.TextSpans", Message.raw("THE DEAD ZONE"));
        }
        if (roundComponent.getSandboxSection4() == 2) {
            uiCommandBuilder.set("#SECTION4.TextSpans", Message.raw("AHOY DEADLY"));
        }
        if (roundComponent.getSandboxSection4() == 3) {
            uiCommandBuilder.set("#SECTION4.TextSpans", Message.raw("WOLF PACK"));
        }
        if (roundComponent.getSandboxSection4() == 4) {
            uiCommandBuilder.set("#SECTION4.TextSpans", Message.raw("ARACHNIDS"));
        }
        if (roundComponent.getSandboxSection4() == 5) {
            uiCommandBuilder.set("#SECTION4.TextSpans", Message.raw("VOID UGLIES"));
        }
        if (roundComponent.getSandboxSection4() == 6) {
            uiCommandBuilder.set("#SECTION4.TextSpans", Message.raw("BUG SEASON"));
        }
        if (roundComponent.getSandboxSection4() == 7) {
            uiCommandBuilder.set("#SECTION4.TextSpans", Message.raw("GOLDEN LURE"));
        }
        if (roundComponent.getSandboxSection4() == 8) {
            uiCommandBuilder.set("#SECTION4.TextSpans", Message.raw("EMBER MIGHT"));
        }
        if (roundComponent.getSandboxSection4() == 9) {
            uiCommandBuilder.set("#SECTION4.TextSpans", Message.raw("FERAN RAID"));
        }
        if (roundComponent.getSandboxSection4() == 10) {
            uiCommandBuilder.set("#SECTION4.TextSpans", Message.raw("SUPERNATURAL"));
        }
        if (roundComponent.getSandboxSection4() == 11) {
            uiCommandBuilder.set("#SECTION4.TextSpans", Message.raw("OUTLANDERS"));
        }
        if (roundComponent.getSandboxSection4() == 12) {
            uiCommandBuilder.set("#SECTION4.TextSpans", Message.raw("10 ROUNDS TO LIVE"));
        }

        //5
        switch (roundComponent.getSandboxSection5()) {
            case 0 -> uiCommandBuilder.set("#SECTION5.TextSpans", Message.raw("THE UNDEAD RISES"));
            case 1 -> uiCommandBuilder.set("#SECTION5.TextSpans", Message.raw("THE DEAD ZONE"));
            case 2 -> uiCommandBuilder.set("#SECTION5.TextSpans", Message.raw("AHOY DEADLY"));
            case 3 -> uiCommandBuilder.set("#SECTION5.TextSpans", Message.raw("WOLF PACK"));
            case 4 -> uiCommandBuilder.set("#SECTION5.TextSpans", Message.raw("ARACHNIDS"));
            case 5 -> uiCommandBuilder.set("#SECTION5.TextSpans", Message.raw("VOID UGLIES"));
            case 6 -> uiCommandBuilder.set("#SECTION5.TextSpans", Message.raw("BUG SEASON"));
            case 7 -> uiCommandBuilder.set("#SECTION5.TextSpans", Message.raw("GOLDEN LURE"));
            case 8 -> uiCommandBuilder.set("#SECTION5.TextSpans", Message.raw("EMBER MIGHT"));
            case 9 -> uiCommandBuilder.set("#SECTION5.TextSpans", Message.raw("FERAN RAID"));
            case 10 -> uiCommandBuilder.set("#SECTION5.TextSpans", Message.raw("SUPERNATURAL"));
            case 11 -> uiCommandBuilder.set("#SECTION5.TextSpans", Message.raw("OUTLANDERS"));
            case 12 -> uiCommandBuilder.set("#SECTION5.TextSpans", Message.raw("10 ROUNDS TO LIVE"));
        }

        //6
        switch (roundComponent.getSandboxSection6()) {
            case 0 -> uiCommandBuilder.set("#SECTION6.TextSpans", Message.raw("THE UNDEAD RISES"));
            case 1 -> uiCommandBuilder.set("#SECTION6.TextSpans", Message.raw("THE DEAD ZONE"));
            case 2 -> uiCommandBuilder.set("#SECTION6.TextSpans", Message.raw("AHOY DEADLY"));
            case 3 -> uiCommandBuilder.set("#SECTION6.TextSpans", Message.raw("WOLF PACK"));
            case 4 -> uiCommandBuilder.set("#SECTION6.TextSpans", Message.raw("ARACHNIDS"));
            case 5 -> uiCommandBuilder.set("#SECTION6.TextSpans", Message.raw("VOID UGLIES"));
            case 6 -> uiCommandBuilder.set("#SECTION6.TextSpans", Message.raw("BUG SEASON"));
            case 7 -> uiCommandBuilder.set("#SECTION6.TextSpans", Message.raw("GOLDEN LURE"));
            case 8 -> uiCommandBuilder.set("#SECTION6.TextSpans", Message.raw("EMBER MIGHT"));
            case 9 -> uiCommandBuilder.set("#SECTION6.TextSpans", Message.raw("FERAN RAID"));
            case 10 -> uiCommandBuilder.set("#SECTION6.TextSpans", Message.raw("SUPERNATURAL"));
            case 11 -> uiCommandBuilder.set("#SECTION6.TextSpans", Message.raw("OUTLANDERS"));
            case 12 -> uiCommandBuilder.set("#SECTION6.TextSpans", Message.raw("10 ROUNDS TO LIVE"));
        }

        //7
        switch (roundComponent.getSandboxSection7()) {
            case 0 -> uiCommandBuilder.set("#SECTION7.TextSpans", Message.raw("THE UNDEAD RISES"));
            case 1 -> uiCommandBuilder.set("#SECTION7.TextSpans", Message.raw("THE DEAD ZONE"));
            case 2 -> uiCommandBuilder.set("#SECTION7.TextSpans", Message.raw("AHOY DEADLY"));
            case 3 -> uiCommandBuilder.set("#SECTION7.TextSpans", Message.raw("WOLF PACK"));
            case 4 -> uiCommandBuilder.set("#SECTION7.TextSpans", Message.raw("ARACHNIDS"));
            case 5 -> uiCommandBuilder.set("#SECTION7.TextSpans", Message.raw("VOID UGLIES"));
            case 6 -> uiCommandBuilder.set("#SECTION7.TextSpans", Message.raw("BUG SEASON"));
            case 7 -> uiCommandBuilder.set("#SECTION7.TextSpans", Message.raw("GOLDEN LURE"));
            case 8 -> uiCommandBuilder.set("#SECTION7.TextSpans", Message.raw("EMBER MIGHT"));
            case 9 -> uiCommandBuilder.set("#SECTION7.TextSpans", Message.raw("FERAN RAID"));
            case 10 -> uiCommandBuilder.set("#SECTION7.TextSpans", Message.raw("SUPERNATURAL"));
            case 11 -> uiCommandBuilder.set("#SECTION7.TextSpans", Message.raw("OUTLANDERS"));
            case 12 -> uiCommandBuilder.set("#SECTION7.TextSpans", Message.raw("10 ROUNDS TO LIVE"));
        }

        //8
        switch (roundComponent.getSandboxSection8()) {
            case 0 -> uiCommandBuilder.set("#SECTION8.TextSpans", Message.raw("THE UNDEAD RISES"));
            case 1 -> uiCommandBuilder.set("#SECTION8.TextSpans", Message.raw("THE DEAD ZONE"));
            case 2 -> uiCommandBuilder.set("#SECTION8.TextSpans", Message.raw("AHOY DEADLY"));
            case 3 -> uiCommandBuilder.set("#SECTION8.TextSpans", Message.raw("WOLF PACK"));
            case 4 -> uiCommandBuilder.set("#SECTION8.TextSpans", Message.raw("ARACHNIDS"));
            case 5 -> uiCommandBuilder.set("#SECTION8.TextSpans", Message.raw("VOID UGLIES"));
            case 6 -> uiCommandBuilder.set("#SECTION8.TextSpans", Message.raw("BUG SEASON"));
            case 7 -> uiCommandBuilder.set("#SECTION8.TextSpans", Message.raw("GOLDEN LURE"));
            case 8 -> uiCommandBuilder.set("#SECTION8.TextSpans", Message.raw("EMBER MIGHT"));
            case 9 -> uiCommandBuilder.set("#SECTION8.TextSpans", Message.raw("FERAN RAID"));
            case 10 -> uiCommandBuilder.set("#SECTION8.TextSpans", Message.raw("SUPERNATURAL"));
            case 11 -> uiCommandBuilder.set("#SECTION8.TextSpans", Message.raw("OUTLANDERS"));
            case 12 -> uiCommandBuilder.set("#SECTION8.TextSpans", Message.raw("10 ROUNDS TO LIVE"));
        }

        //9
        switch (roundComponent.getSandboxSection9()) {
            case 0 -> uiCommandBuilder.set("#SECTION9.TextSpans", Message.raw("THE UNDEAD RISES"));
            case 1 -> uiCommandBuilder.set("#SECTION9.TextSpans", Message.raw("THE DEAD ZONE"));
            case 2 -> uiCommandBuilder.set("#SECTION9.TextSpans", Message.raw("AHOY DEADLY"));
            case 3 -> uiCommandBuilder.set("#SECTION9.TextSpans", Message.raw("WOLF PACK"));
            case 4 -> uiCommandBuilder.set("#SECTION9.TextSpans", Message.raw("ARACHNIDS"));
            case 5 -> uiCommandBuilder.set("#SECTION9.TextSpans", Message.raw("VOID UGLIES"));
            case 6 -> uiCommandBuilder.set("#SECTION9.TextSpans", Message.raw("BUG SEASON"));
            case 7 -> uiCommandBuilder.set("#SECTION9.TextSpans", Message.raw("GOLDEN LURE"));
            case 8 -> uiCommandBuilder.set("#SECTION9.TextSpans", Message.raw("EMBER MIGHT"));
            case 9 -> uiCommandBuilder.set("#SECTION9.TextSpans", Message.raw("FERAN RAID"));
            case 10 -> uiCommandBuilder.set("#SECTION9.TextSpans", Message.raw("SUPERNATURAL"));
            case 11 -> uiCommandBuilder.set("#SECTION9.TextSpans", Message.raw("OUTLANDERS"));
            case 12 -> uiCommandBuilder.set("#SECTION9.TextSpans", Message.raw("10 ROUNDS TO LIVE"));
        }

        switch (roundComponent.getSandboxSection10()) {
            case 0 -> uiCommandBuilder.set("#SECTION10.TextSpans", Message.raw("THE UNDEAD RISES"));
            case 1 -> uiCommandBuilder.set("#SECTION10.TextSpans", Message.raw("THE DEAD ZONE"));
            case 2 -> uiCommandBuilder.set("#SECTION10.TextSpans", Message.raw("AHOY DEADLY"));
            case 3 -> uiCommandBuilder.set("#SECTION10.TextSpans", Message.raw("WOLF PACK"));
            case 4 -> uiCommandBuilder.set("#SECTION10.TextSpans", Message.raw("ARACHNIDS"));
            case 5 -> uiCommandBuilder.set("#SECTION10.TextSpans", Message.raw("VOID UGLIES"));
            case 6 -> uiCommandBuilder.set("#SECTION10.TextSpans", Message.raw("BUG SEASON"));
            case 7 -> uiCommandBuilder.set("#SECTION10.TextSpans", Message.raw("GOLDEN LURE"));
            case 8 -> uiCommandBuilder.set("#SECTION10.TextSpans", Message.raw("EMBER MIGHT"));
            case 9 -> uiCommandBuilder.set("#SECTION10.TextSpans", Message.raw("FERAN RAID"));
            case 10 -> uiCommandBuilder.set("#SECTION10.TextSpans", Message.raw("SUPERNATURAL"));
            case 11 -> uiCommandBuilder.set("#SECTION10.TextSpans", Message.raw("OUTLANDERS"));
            case 12 -> uiCommandBuilder.set("#SECTION10.TextSpans", Message.raw("10 ROUNDS TO LIVE"));
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
        if (data.GOLD >= 1) {
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
            player.getPageManager().openCustomPage(ref, store, new SandboxGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundData));
        }

        if (ARMOUR.equals(data.buttonClicked)) {
            if (roundData.getSandboxStartingArmour() == 7) {
                roundData.setSandboxStartingArmour(0);
            } else {
                roundData.setSandboxStartingArmour(roundData.getSandboxStartingArmour() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundData));
        }

        if (CYCLE1.equals(data.buttonClicked)) {
            if (roundData.getSandboxSection1() == 12) {
                roundData.setSandboxSection1(0);
            } else {
                roundData.setSandboxSection1(roundData.getSandboxSection1() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundData));
        }
        if (CYCLE2.equals(data.buttonClicked)) {
            if (roundData.getSandboxSection2() == 12) {
                roundData.setSandboxSection2(0);
            } else {
                roundData.setSandboxSection2(roundData.getSandboxSection2() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundData));
        }
        if (CYCLE3.equals(data.buttonClicked)) {
            if (roundData.getSandboxSection3() == 12) {
                roundData.setSandboxSection3(0);
            } else {
                roundData.setSandboxSection3(roundData.getSandboxSection3() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundData));
        }
        if (CYCLE4.equals(data.buttonClicked)) {
            if (roundData.getSandboxSection4() == 12) {
                roundData.setSandboxSection4(0);
            } else {
                roundData.setSandboxSection4(roundData.getSandboxSection4() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundData));
        }
        if (CYCLE5.equals(data.buttonClicked)) {
            if (roundData.getSandboxSection5() == 12) {
                roundData.setSandboxSection5(0);
            } else {
                roundData.setSandboxSection5(roundData.getSandboxSection5() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundData));
        }
        if (CYCLE6.equals(data.buttonClicked)) {
            if (roundData.getSandboxSection6() == 12) {
                roundData.setSandboxSection6(0);
            } else {
                roundData.setSandboxSection6(roundData.getSandboxSection6() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundData));
        }
        if (CYCLE7.equals(data.buttonClicked)) {
            if (roundData.getSandboxSection7() == 12) {
                roundData.setSandboxSection7(0);
            } else {
                roundData.setSandboxSection7(roundData.getSandboxSection7() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundData));
        }
        if (CYCLE8.equals(data.buttonClicked)) {
            if (roundData.getSandboxSection8() == 12) {
                roundData.setSandboxSection8(0);
            } else {
                roundData.setSandboxSection8(roundData.getSandboxSection8() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundData));
        }
        if (CYCLE9.equals(data.buttonClicked)) {
            if (roundData.getSandboxSection9() == 12) {
                roundData.setSandboxSection9(0);
            } else {
                roundData.setSandboxSection9(roundData.getSandboxSection9() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundData));
        }
        if (CYCLE10.equals(data.buttonClicked)) {
            if (roundData.getSandboxSection10() == 12) {
                roundData.setSandboxSection10(0);
            } else {
                roundData.setSandboxSection10(roundData.getSandboxSection10() + 1);
            }
            player.getPageManager().openCustomPage(ref, store, new SandboxGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundData));
        }

        if (BACK.equals(data.buttonClicked)) {
            roundData.setRoundMenu("start");
            player.getPageManager().setPage(ref, store, Page.None);
        }
        if (PLAY.equals(data.buttonClicked)) {
            GoldAndKillsComponent goldData = store.getComponent(ref, GoldAndKillsComponent.getComponentType());

            roundData.setRoundMenu("null");
            roundData.setRoundType("sandbox");
            roundData.setRoundCount(1);
            roundData.setRoundTimer(roundData.getSandboxRoundLength());
            roundData.freezeRoundTimer(false);
            player.getPageManager().setPage(ref, store, Page.None);
            if (goldData != null) {
                goldData.setGold(roundData.getSandboxStartingGold());
            }

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

                    GoldAndKillsComponent goldData2 = store.getComponent(oPlayerRef.getReference(), GoldAndKillsComponent.getComponentType());

                    if (goldData2 != null) {
                        goldData2.setKills(0);
                    }

                    if (hotbar != null && armour != null && utility != null && backpack != null && storage != null && tool != null) {
                        hotbar.clear();
                        armour.clear();
                        utility.clear();
                        backpack.clear();
                        storage.clear();
                        tool.clear();
                        hotbar.setItemStackForSlot((short) 0, new ItemStack("Remote"));
                        if (roundComponent.getSandboxStartingPet() == 0) {
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Fireball_Pet", 1));
                        }
                        if (roundComponent.getSandboxStartingPet() == 1) {
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Ice_Ball_Pet", 1));
                        }
                        if (roundComponent.getSandboxStartingPet() == 2) {
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Acid_Orb_Pet", 1));
                        }
                        if (roundComponent.getSandboxStartingPet() == 3) {
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Anvil_Pet", 1));
                        }
                        if (roundComponent.getSandboxStartingPet() == 4) {
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Baby_Skeleton", 1));
                        }
                        if (roundComponent.getSandboxStartingPet() == 5) {
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Baby_Zombie", 1));
                        }
                        if (roundComponent.getSandboxStartingPet() == 6) {
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Baby_Spider", 1));
                        }
                        if (roundComponent.getSandboxStartingPet() == 7) {
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Dice", 1));
                        }
                        if (roundComponent.getSandboxStartingPet() == 8) {
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Crimson_Dice", 1));
                        }
                        if (roundComponent.getSandboxStartingPet() == 9) {
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Lucky_Dice", 1));
                        }
                        if (roundComponent.getSandboxStartingPet() == 10) {
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Weapon_Custom_Battleaxe_Mithril", 1));
                        }
                        if (roundComponent.getSandboxStartingPet() == 11) {
                            hotbar.setItemStackForSlot((short) 2, new ItemStack("Potion_Health", 3));
                        }
                        if (roundComponent.getSandboxStartingArmour() == 0) {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Battleaxe_Copper"));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Copper_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Copper_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Copper_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Copper_Legs"));
                        }
                        if (roundComponent.getSandboxStartingArmour() == 1) {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Battleaxe_Iron"));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Iron_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Iron_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Iron_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Iron_Legs"));
                        }
                        if (roundComponent.getSandboxStartingArmour() == 2) {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Battleaxe_Iron"));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Bronze_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Bronze_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Bronze_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Bronze_Legs"));
                        }
                        if (roundComponent.getSandboxStartingArmour() == 3) {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Battleaxe_Iron"));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Steel_Ancient_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Steel_Ancient_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Steel_Ancient_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Steel_Ancient_Legs"));
                        }
                        if (roundComponent.getSandboxStartingArmour() == 4) {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Battleaxe_Thorium"));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Thorium_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Thorium_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Thorium_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Thorium_Legs"));
                        }
                        if (roundComponent.getSandboxStartingArmour() == 5) {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Battleaxe_Cobalt"));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Cobalt_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Cobalt_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Cobalt_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Cobalt_Legs"));
                        }
                        if (roundComponent.getSandboxStartingArmour() == 6) {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Battleaxe_Onyxium"));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Onyxium_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Onyxium_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Onyxium_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Onyxium_Legs"));
                        }
                        if (roundComponent.getSandboxStartingArmour() == 7) {
                            hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Battleaxe_Mithril"));
                            armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Mithril_Head"));
                            armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Mithril_Chest"));
                            armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Mithril_Hands"));
                            armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Mithril_Legs"));
                        }
                    }
                }
            }
        }

        if (changed) {
        }
    }

    @Override
    public void onDismiss(@NonNull Ref<EntityStore> ref, @NonNull Store<EntityStore> store) {
        super.onDismiss(ref, store);
        if (Objects.equals(roundComponent.getRoundType(), "null") && !Objects.equals(roundComponent.getRoundMenu(), "start")) {
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