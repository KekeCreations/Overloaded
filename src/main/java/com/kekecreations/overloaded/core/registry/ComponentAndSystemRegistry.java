package com.kekecreations.overloaded.core.registry;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;
import com.kekecreations.overloaded.common.system.PlayerUiTickSystem;

public class ComponentAndSystemRegistry {

    private static ComponentType<EntityStore, RoundComponent> roundComponent;

    //private static ComponentType<EntityStore, OtherPlayerRoundComponent> otherRoundComponent;


    public static void registerComponents(JavaPlugin javaPlugin) {
        var registry = javaPlugin.getEntityStoreRegistry();
        roundComponent = registry.registerComponent(
                RoundComponent.class,
                "RoundStats",
                RoundComponent.CODEC
        );
        //Allows to call component from component class
        RoundComponent.setComponentType(roundComponent);

        /*
        otherRoundComponent = registry.registerComponent(
                OtherPlayerRoundComponent.class,
                "OtherRoundData",
                OtherPlayerRoundComponent.CODEC
        );
        OtherPlayerRoundComponent.setComponentType(otherRoundComponent);

         */

        registry.registerSystem(new PlayerUiTickSystem(roundComponent));
       // registry.registerSystem(new OtherPlayerTickSystem(otherRoundComponent));
        //registry.registerSystem(new PlayerDeathSystem());
       // registry.registerSystem(new NPCDeathSystem());
    }

}
