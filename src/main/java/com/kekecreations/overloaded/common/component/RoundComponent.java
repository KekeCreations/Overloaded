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
                    .append(new KeyedCodec<>("ArachnophobiaMode", Codec.BOOLEAN),
                            (c, f) -> c.arachnophobiaMode = f, c -> c.arachnophobiaMode)
                    .add()
                    .append(new KeyedCodec<>("DoubleGoldMode", Codec.BOOLEAN),
                            (c, f) -> c.doubleGoldMode = f, c -> c.doubleGoldMode)
                    .add()
                    .build();


    private int roundTimer = 0;

    private int roundCount = 0;

    private String roundType = "null";

    private String roundMenu = "null";

    private boolean freezeTimer = false;

    private boolean arachnophobiaMode = false;

    private boolean doubleGoldMode = false;

    private int kills = 0;

    private int gold = 0;

    private int itemCost1 = 0;
    private int itemCost2 = 0;
    private int itemCost3 = 0;

    public RoundComponent() {}

    public RoundComponent(int roundTimer, int roundCount, String roundType, boolean freezeTimer, String roundMenu,
                          boolean arachnophobiaMode, boolean doubleGoldMode, int kills, int gold, int itemCost1, int itemCost2,
                          int itemCost3) {
        this.roundTimer = roundTimer;
        this.roundCount = roundCount;
        this.roundType = roundType;
        this.freezeTimer = freezeTimer;
        this.roundMenu = roundMenu;
        this.arachnophobiaMode = arachnophobiaMode;
        this.doubleGoldMode = doubleGoldMode;
        this.kills = kills;
        this.gold = gold;
        this.itemCost1 = itemCost1;
        this.itemCost2 = itemCost2;
        this.itemCost3 = itemCost3;
    }

    @Override
    public Component<EntityStore> clone() {
        RoundComponent copy = new RoundComponent(roundTimer, roundCount, roundType, freezeTimer, roundMenu, arachnophobiaMode, doubleGoldMode, kills, gold, itemCost1, itemCost2, itemCost3);
        copy.roundTimer = this.roundTimer;
        copy.roundCount = this.roundCount;
        copy.roundType = this.roundType;
        copy.freezeTimer = this.freezeTimer;
        copy.arachnophobiaMode = this.arachnophobiaMode;
        copy.doubleGoldMode = this.doubleGoldMode;
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

    public boolean isTimerFrozen() {
        return this.freezeTimer;
    }

    public boolean isArachnophobiaMode() {
        return this.arachnophobiaMode;
    }

    public boolean isDoubleGoldMode() {
        return this.doubleGoldMode;
    }

    public String getRoundMenu() {
        return this.roundMenu;
    }

    public int getKills() {
        return this.kills;
    }

    public int getGold() {
        return this.gold;
    }

    public int getItemCost1() {
        return this.itemCost1;
    }

    public int getItemCost2() {
        return this.itemCost2;
    }

    public int getItemCost3() {
        return this.itemCost3;
    }

    public void setRoundType(String newRoundType) {
        this.roundType = newRoundType;
    }

    public void setRoundMenu(String newRoundMenu) {
        this.roundMenu = newRoundMenu;
    }

    public void setRoundTimer(int newRoundTimer) {
        this.roundTimer = newRoundTimer;
    }

    public void setRoundCount(int newRoundCount) {
        this.roundCount = newRoundCount;
    }

    public void freezeRoundTimer(boolean freezeTimer) {
        this.freezeTimer = freezeTimer;
    }

    public void setArachnophobiaMode(boolean bool) {
        this.arachnophobiaMode = bool;
    }

    public void setDoubleGoldMode(boolean bool) {
        this.doubleGoldMode = bool;
    }

    public void setKills(int value) {
        this.kills = value;
    }

    public void setGold(int value) {
        this.gold = value;
    }

    public void setItemCost1(int value) {
        this.itemCost1 = value;
    }

    public void setItemCost2(int value) {
        this.itemCost2 = value;
    }

    public void setItemCost3(int value) {
        this.itemCost3 = value;
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
