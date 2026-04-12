package com.kekecreations.overloaded.common.component;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class GoldAndKillsComponent implements Component<EntityStore> {

    public static final BuilderCodec<GoldAndKillsComponent> CODEC =
            BuilderCodec.builder(GoldAndKillsComponent.class, GoldAndKillsComponent::new)
                    .build();



    private int kills = 0;

    private int gold = 0;

    private int itemCost1 = 0;
    private int itemCost2 = 0;
    private int itemCost3 = 0;
    private int itemCost4 = 0;
    private int itemCost5 = 0;

    public GoldAndKillsComponent() {}

    public GoldAndKillsComponent(int kills, int gold, int itemCost1, int itemCost2,
                                 int itemCost3, int itemCost4, int itemCost5) {
        this.kills = kills;
        this.gold = gold;
        this.itemCost1 = itemCost1;
        this.itemCost2 = itemCost2;
        this.itemCost3 = itemCost3;
        this.itemCost4 = itemCost4;
        this.itemCost5 = itemCost5;
    }

    @Override
    public Component<EntityStore> clone() {
        GoldAndKillsComponent copy = new GoldAndKillsComponent(kills, gold, itemCost1, itemCost2, itemCost3, itemCost4, itemCost5);
        return copy;
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

    public int getItemCost4() {
        return this.itemCost4;
    }

    public int getItemCost5() {
        return this.itemCost5;
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

    public void setItemCost4(int value) {
        this.itemCost4 = value;
    }

    public void setItemCost5(int value) {
        this.itemCost5 = value;
    }


    private static ComponentType<EntityStore, GoldAndKillsComponent> type;

    public static ComponentType<EntityStore, GoldAndKillsComponent> getComponentType(){
        return type;
    }

    public static void setComponentType(ComponentType<EntityStore, GoldAndKillsComponent> type2){
        type = type2;
    }
}
