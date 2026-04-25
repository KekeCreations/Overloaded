package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.RemoveReason;
import com.hypixel.hytale.component.Store;
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
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.entities.NPCEntity;
import com.kekecreations.overloaded.common.component.GoldAndKillsComponent;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class SandboxGui extends InteractiveCustomUIPage<StartMenuGuiData> {

    private static final String PLAY_NORMAL_BUTTON_ID = "PLAYCLASSIC";
    private static final String PLAY_QUICK_BUTTON_ID = "PLAYQUICK";
    private static final String PLAY_ROUNDS_BUTTON_ID = "PLAYROUNDS";
    private static final String PLAY_CHAOS_BUTTON_ID = "PLAYCHAOS";
    private static final String QUIT_BUTTON_ID = "QUIT";

    private static final String SETTINGS = "SETTINGS";
    private static final String HOW_TO_PLAY = "HOWTOPLAY";

    RoundComponent roundComponent;

    public SandboxGui(@Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime, RoundComponent roundComponent) {
        super(playerRef, lifetime, StartMenuGuiData.CODEC);
        this.roundComponent = roundComponent;
    }


    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/sandbox.ui");
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull StartMenuGuiData data) {
        super.handleDataEvent(ref, store, data);
        boolean changed = false;

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));

        if (changed) {
            this.playerRef.sendMessage(Message.raw("Changes processed."));
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
            roundComponent.setRoundMenu("start");
        }
    }
}