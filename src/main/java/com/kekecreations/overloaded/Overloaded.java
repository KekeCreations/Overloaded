package com.kekecreations.overloaded;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;
import com.kekecreations.overloaded.common.system.PlayerTickSystem;
import com.kekecreations.overloaded.core.registry.CommandRegistry;
import com.kekecreations.overloaded.core.registry.EventRegistry;

public class Overloaded extends JavaPlugin {
    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    private ComponentType<EntityStore, RoundComponent> roundComponent;

    public Overloaded(JavaPluginInit init) {
        super(init);
        LOGGER.atInfo().log("Hello from %s version %s", this.getName(), this.getManifest().getVersion().toString());
    }

    @Override
    protected void setup() {
        var registry = getEntityStoreRegistry();
        CommandRegistry.registerCommmands(this);
        EventRegistry.registerEvents(this);


        this.roundComponent = registry.registerComponent(
                RoundComponent.class,
                "RoundData",
                RoundComponent.CODEC
        );
        //Allows to call component from component class
        RoundComponent.setComponentType(this.roundComponent);

        //Systems
        registry.registerSystem(new PlayerTickSystem(this.roundComponent));
    }
}
