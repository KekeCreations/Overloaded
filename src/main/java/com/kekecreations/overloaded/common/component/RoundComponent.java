package com.kekecreations.overloaded.common.component;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class RoundComponent implements Component<EntityStore> {

    public static final BuilderCodec<RoundComponent> CODEC =
            BuilderCodec.builder(RoundComponent.class, RoundComponent::new)
                    .append(new KeyedCodec<>("RoundTimer", Codec.INTEGER),
                            (c, f) -> c.roundTimer = f, c -> c.roundTimer)
                    .add()
                    .append(new KeyedCodec<>("RoundCount", Codec.INTEGER),
                            (c, f) -> c.roundCount = f, c -> c.roundCount)
                    .add()
                    .append(new KeyedCodec<>("RoundType", Codec.STRING),
                            (c, f) -> c.roundType = f, c -> c.roundType)
                    .add()
                    .build();


    private int roundTimer = 0;

    private int roundCount = 0;

    private String roundType = "null";

    public RoundComponent() {}

    public RoundComponent(int roundTimer, int roundCount, String roundType) {
        this.roundTimer = roundTimer;
        this.roundCount = roundCount;
        this.roundType = roundType;
    }

    @Override
    public Component<EntityStore> clone() {
        RoundComponent copy = new RoundComponent(roundTimer, roundCount, roundType);
        copy.roundTimer = this.roundTimer;
        copy.roundCount = this.roundCount;
        copy.roundType = this.roundType;
        return copy;
    }

    public int getRoundTimer() {
        return this.roundTimer;
    }

    public int getRoundCount() {
        return this.roundCount;
    }

    public String getRoundType() {
        return this.roundType;
    }

    public void setRoundType(String newRoundType) {
        this.roundType = newRoundType;
    }

    public void setRoundTimer(int newRoundTimer) {
        this.roundTimer = newRoundTimer;
    }

    public void setRoundCount(int newRoundCount) {
        this.roundCount = newRoundCount;
    }

    public void incrementRoundCount(int incrementAmount) {
        setRoundCount(getRoundTimer() + incrementAmount);
    }

    private static ComponentType<EntityStore, RoundComponent> type;

    public static ComponentType<EntityStore, RoundComponent> getComponentType(){
        return type;
    }

    public static void setComponentType(ComponentType<EntityStore, RoundComponent> type2){
        type = type2;
    }
}
