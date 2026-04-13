package com.kekecreations.overloaded.core.registry;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.*;
import com.hypixel.hytale.protocol.packets.camera.SetServerCamera;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.GoldAndKillsComponent;
import com.kekecreations.overloaded.common.component.RoundComponent;
import com.kekecreations.overloaded.common.ui.StartMenuGui;

public class EventRegistry {

    private static final ServerCameraSettings cameraSettings = new ServerCameraSettings();

    public static void registerEvents(JavaPlugin javaPlugin) {
        cameraSettings.positionLerpSpeed = 0.2F;
        cameraSettings.rotationLerpSpeed = 0.2F;
        cameraSettings.distance = 10.0F;
        cameraSettings.displayCursor = true;
        cameraSettings.isFirstPerson = false;
        cameraSettings.movementForceRotationType = MovementForceRotationType.Custom;
        cameraSettings.eyeOffset = true;
        cameraSettings.positionDistanceOffsetType = PositionDistanceOffsetType.DistanceOffset;
        cameraSettings.rotationType = RotationType.Custom;
        cameraSettings.rotation = new Direction(0F, (-(float) Math.PI / 2F - 5.8F), 0F);
        cameraSettings.mouseInputType = MouseInputType.LookAtPlane;
        cameraSettings.planeNormal = new Vector3f(0.0F, 1.0F, 0.0F);

        javaPlugin.getEventRegistry().registerGlobal(PlayerReadyEvent.class, event -> {
            Player player = event.getPlayer();
            Ref<EntityStore> playerRef = event.getPlayerRef();
            Store<EntityStore> store = playerRef.getStore();
            PlayerRef ref = store.getComponent(playerRef, PlayerRef.getComponentType());
            if (ref != null) {
                if (Universe.get().getPlayerCount() == 1) {
                    RoundComponent roundComponent = store.ensureAndGetComponent(playerRef, RoundComponent.getComponentType());
                    roundComponent.freezeRoundTimer(true);
                    roundComponent.setRoundTimer(9999);
                    roundComponent.setRoundType("null");
                    player.getPageManager().openCustomPage(playerRef, store, new StartMenuGui(ref, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundComponent));
                } else {
                    if (store.getComponent(playerRef, RoundComponent.getComponentType()) != null) {
                        store.removeComponent(playerRef, RoundComponent.getComponentType());
                    }
                }
                World.setTimeDilation(1F, store);
                GoldAndKillsComponent goldComponent = store.ensureAndGetComponent(playerRef, GoldAndKillsComponent.getComponentType());
                goldComponent.setGold(0);
                goldComponent.setKills(0);
                CommandManager.get().handleCommand(ref, "start_tp");
                ref.getPacketHandler().writeNoCache(new SetServerCamera(ClientCameraView.Custom, true, cameraSettings));


                InventoryComponent hotbarComponent = store.getComponent(playerRef, InventoryComponent.getComponentTypeById(-1));
                InventoryComponent armourComponent = store.getComponent(playerRef, InventoryComponent.getComponentTypeById(-3));
                InventoryComponent utilityComponent = store.getComponent(playerRef, InventoryComponent.getComponentTypeById(-5));
                InventoryComponent backpackComponent = store.getComponent(playerRef, InventoryComponent.getComponentTypeById(-9));
                InventoryComponent storageComponent = store.getComponent(playerRef, InventoryComponent.getComponentTypeById(-2));
                InventoryComponent toolComponent = store.getComponent(playerRef, InventoryComponent.getComponentTypeById(-8));

                ItemContainer hotbar = hotbarComponent.getInventory();
                ItemContainer armour = armourComponent.getInventory();
                ItemContainer utility = utilityComponent.getInventory();
                ItemContainer backpack = backpackComponent.getInventory();
                ItemContainer storage = storageComponent.getInventory();
                ItemContainer tool = toolComponent.getInventory();

                if (hotbar != null && armour != null && utility != null && backpack != null && storage != null && tool != null) {
                    hotbar.clear();
                    armour.clear();
                    utility.clear();
                    backpack.clear();
                    storage.clear();
                    tool.clear();
                    hotbar.setItemStackForSlot((short) 1, new ItemStack("Weapon_Battleaxe_Copper"));
                    hotbar.setItemStackForSlot((short) 2, new ItemStack("Potion_Health", 3));

                    armour.setItemStackForSlot((short) 0, new ItemStack("Armor_Copper_Head"));
                    armour.setItemStackForSlot((short) 1, new ItemStack("Armor_Copper_Chest"));
                    armour.setItemStackForSlot((short) 2, new ItemStack("Armor_Copper_Hands"));
                    armour.setItemStackForSlot((short) 3, new ItemStack("Armor_Copper_Legs"));
                }
            }
        });
    }
}
