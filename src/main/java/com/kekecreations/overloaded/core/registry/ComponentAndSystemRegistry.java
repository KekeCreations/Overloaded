package com.kekecreations.overloaded.core.registry;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;
import com.kekecreations.overloaded.common.system.*;

public class ComponentAndSystemRegistry {

    private static ComponentType<EntityStore, RoundComponent> roundComponent;



    public static void registerComponents(JavaPlugin javaPlugin) {
        var registry = javaPlugin.getEntityStoreRegistry();
        roundComponent = registry.registerComponent(
                RoundComponent.class,
                "RoundStats",
                RoundComponent.CODEC
        );
        //Allows to call component from component class
        RoundComponent.setComponentType(roundComponent);


        registry.registerSystem(new PlayerUiTickSystem(roundComponent));
        registry.registerSystem(new ClassicEnemyTickSystem(roundComponent));
        registry.registerSystem(new RoundTickSystem(roundComponent));
        registry.registerSystem(new UiTickSystem(roundComponent));
        registry.registerSystem(new NPCDeathSystem());
        registry.registerSystem(new PlayerDeathSystem());
    }

}
