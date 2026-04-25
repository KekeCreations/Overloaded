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
                    .append(new KeyedCodec<>("Pause", Codec.BOOLEAN),
                            (c, f) -> c.pause = f, c -> c.pause)
                    .add()
                    .build();


    private int roundTimer = 0;

    private int roundCount = 0;

    private String roundType = "null";

    private String roundMenu = "null";

    private boolean freezeTimer = false;

    private boolean arachnophobiaMode = false;

    private boolean doubleGoldMode = false;

    private boolean pause = false;

    private int sandboxSection1 = 0;
    private int sandboxSection2 = 0;
    private int sandboxSection3 = 0;
    private int sandboxSection4 = 0;
    private int sandboxSection5 = 0;
    private int sandboxSection6 = 0;
    private int sandboxSection7 = 0;
    private int sandboxSection8 = 0;
    private int sandboxSection9 = 0;
    private int sandboxSection10 = 0;

    private int sandboxStartingPet = 0;
    private int sandboxStartingArmour = 0;
    private int sandboxStartingGold = 0;
    private int sandboxRoundLength = 0;
    private boolean sandboxChaosMode = false;

    public RoundComponent() {}

    public RoundComponent(int roundTimer, int roundCount, String roundType, boolean freezeTimer, String roundMenu,
                          boolean arachnophobiaMode, boolean doubleGoldMode, boolean pause, int sandboxSection1,
                          int sandboxSection2, int sandboxSection3, int sandboxSection4, int sandboxSection5,
                          int sandboxSection6, int sandboxSection7, int sandboxSection8, int sandboxSection9,
                          int sandboxSection10, int sandboxStartingPet, int sandboxStartingArmour, boolean sandboxChaosMode,
                          int sandboxStartingGold, int sandboxRoundLength) {
        this.roundTimer = roundTimer;
        this.roundCount = roundCount;
        this.roundType = roundType;
        this.freezeTimer = freezeTimer;
        this.roundMenu = roundMenu;
        this.arachnophobiaMode = arachnophobiaMode;
        this.doubleGoldMode = doubleGoldMode;
        this.pause = pause;
        this.sandboxSection1 = sandboxSection1;
        this.sandboxSection2 = sandboxSection2;
        this.sandboxSection3 = sandboxSection3;
        this.sandboxSection4 = sandboxSection4;
        this.sandboxSection5 = sandboxSection5;
        this.sandboxSection6 = sandboxSection6;
        this.sandboxSection7 = sandboxSection7;
        this.sandboxSection8 = sandboxSection8;
        this.sandboxSection9 = sandboxSection9;
        this.sandboxSection10 = sandboxSection10;
        this.sandboxStartingPet = sandboxStartingPet;
        this.sandboxStartingArmour = sandboxStartingArmour;
        this.sandboxChaosMode = sandboxChaosMode;
        this.sandboxStartingGold = sandboxStartingGold;
        this.sandboxRoundLength = sandboxRoundLength;
    }

    @Override
    public Component<EntityStore> clone() {
        RoundComponent copy = new RoundComponent(roundTimer, roundCount, roundType, freezeTimer, roundMenu, arachnophobiaMode, doubleGoldMode, pause,
                sandboxSection1, sandboxSection2, sandboxSection3, sandboxSection4, sandboxSection5, sandboxSection6,
                sandboxSection7, sandboxSection8, sandboxSection9, sandboxSection10, sandboxStartingPet, sandboxStartingArmour,
                sandboxChaosMode, sandboxStartingGold, sandboxRoundLength);
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

    public boolean isPaused() {
        return this.pause;
    }

    public String getRoundMenu() {
        return this.roundMenu;
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

    public void setPausee(boolean bool) {
        this.pause = bool;
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


    //sandbox
    public int getSandboxSection1() {
        return this.sandboxSection1;
    }
    public int getSandboxSection2() {
        return this.sandboxSection2;
    }
    public int getSandboxSection3() {
        return this.sandboxSection3;
    }
    public int getSandboxSection4() {
        return this.sandboxSection4;
    }
    public int getSandboxSection5() {
        return this.sandboxSection5;
    }
    public int getSandboxSection6() {
        return this.sandboxSection6;
    }
    public int getSandboxSection7() {
        return this.sandboxSection7;
    }
    public int getSandboxSection8() {
        return this.sandboxSection8;
    }
    public int getSandboxSection9() {
        return this.sandboxSection9;
    }
    public int getSandboxSection10() {
        return this.sandboxSection10;
    }
    public int getSandboxStartingPet() {
        return this.sandboxStartingPet;
    }
    public int getSandboxStartingArmour() {
        return this.sandboxStartingArmour;
    }
    public int getSandboxStartingGold() {
        return this.sandboxStartingGold;
    }
    public int getSandboxRoundLength() {
        return this.sandboxRoundLength;
    }

    public boolean getSandboxChaosMode() {
        return this.sandboxChaosMode;
    }

    public void setSandboxSection1(int value) {
        this.sandboxSection1 = value;
    }
    public void setSandboxSection2(int value) {
        this.sandboxSection2 = value;
    }
    public void setSandboxSection3(int value) {
        this.sandboxSection3 = value;
    }
    public void setSandboxSection4(int value) {
        this.sandboxSection4 = value;
    }
    public void setSandboxSection5(int value) {
        this.sandboxSection5 = value;
    }
    public void setSandboxSection6(int value) {
        this.sandboxSection6 = value;
    }
    public void setSandboxSection7(int value) {
        this.sandboxSection7 = value;
    }
    public void setSandboxSection8(int value) {
        this.sandboxSection8 = value;
    }
    public void setSandboxSection9(int value) {
        this.sandboxSection9 = value;
    }
    public void setSandboxSection10(int value) {
        this.sandboxSection10 = value;
    }

    public void setSandboxStartingPet(int value) {
        this.sandboxStartingPet = value;
    }
    public void setSandboxStartingArmour(int value) {
        this.sandboxStartingArmour = value;
    }
    public void setSandboxStartingGold(int value) {
        this.sandboxStartingGold = value;
    }
    public void setSandboxRoundLength(int value) {
        this.sandboxRoundLength = value;
    }

    public void setSandboxChaosMode(boolean value) {
        this.sandboxChaosMode = value;
    }
}
