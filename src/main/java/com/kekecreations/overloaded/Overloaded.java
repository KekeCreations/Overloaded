package com.kekecreations.overloaded;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.kekecreations.overloaded.core.registry.CommandRegistry;
import com.kekecreations.overloaded.core.registry.ComponentAndSystemRegistry;
import com.kekecreations.overloaded.core.registry.EventRegistry;

public class Overloaded extends JavaPlugin {
    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();


    public Overloaded(JavaPluginInit init) {
        super(init);
        LOGGER.atInfo().log("Hello from %s version %s", this.getName(), this.getManifest().getVersion().toString());
    }

    @Override
    protected void setup() {
        CommandRegistry.registerCommmands(this);
        EventRegistry.registerEvents(this);
        ComponentAndSystemRegistry.registerComponents(this);
    }
}
