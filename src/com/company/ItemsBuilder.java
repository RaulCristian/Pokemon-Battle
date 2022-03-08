package com.company;

public class ItemsBuilder {

    public String name;
    public Integer hp = null;
    public Integer attack = null;
    public Integer special_attack = null;
    public Integer defense = null;
    public Integer special_defense = null;

    public ItemsBuilder(String name) {
        this.name = name;
    }

    public ItemsBuilder hp(Integer hp) {
        this.hp = hp;
        return this;
    }

    public ItemsBuilder attack(Integer attack) {
        this.attack = attack;
        return this;
    }

    public ItemsBuilder special_attack(Integer special_attack) {
        this.special_attack = special_attack;
        return this;
    }

    public ItemsBuilder defense(Integer defense) {
        this.defense = defense;
        return this;
    }

    public ItemsBuilder special_defense(Integer special_defense) {
        this.special_defense = special_defense;
        return this;
    }

    public Items build() {
        return new Items(this);
    }


}
