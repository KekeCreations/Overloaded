package com.kekecreations.overloaded.core.registry;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.GoldAndKillsComponent;
import com.kekecreations.overloaded.common.component.RoundComponent;
import com.kekecreations.overloaded.common.system.*;

public class ComponentAndSystemRegistry {

    private static ComponentType<EntityStore, RoundComponent> roundComponent;

    private static ComponentType<EntityStore, GoldAndKillsComponent> goldComponent;



    public static void registerComponents(JavaPlugin javaPlugin) {
        var registry = javaPlugin.getEntityStoreRegistry();
        roundComponent = registry.registerComponent(
                RoundComponent.class,
                "RoundStats",
                RoundComponent.CODEC
        );
        goldComponent = registry.registerComponent(
                GoldAndKillsComponent.class,
                "GoldStats",
                GoldAndKillsComponent.CODEC
        );
        //Allows to call component from component class
        RoundComponent.setComponentType(roundComponent);
        GoldAndKillsComponent.setComponentType(goldComponent);


        registry.registerSystem(new InstantTickSystem(roundComponent));
        registry.registerSystem(new ClassicEnemyTickSystem(roundComponent));
        registry.registerSystem(new RoundsEnemyTickSystem(roundComponent));
        registry.registerSystem(new RoundTickSystem(roundComponent));
        registry.registerSystem(new UiTickSystem(roundComponent));
        registry.registerSystem(new ChestTickSystem(roundComponent));
        registry.registerSystem(new ChestDespawnSystem());
        registry.registerSystem(new PetSystem());
        registry.registerSystem(new NPCDeathSystem());
        registry.registerSystem(new PlayerDeathSystem());
        registry.registerSystem(new OtherPlayerTickSystem());
    }

}
