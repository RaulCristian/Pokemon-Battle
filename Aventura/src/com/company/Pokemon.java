package com.company;

import java.util.ArrayList;

public abstract class Pokemon {

    String name = null;
    Integer hp = null;
    Integer attack = null;
    Integer special_attack = null;
    Integer defense = null;
    Integer special_defense = null;
    ArrayList<Ability> abilities = new ArrayList<>(2);
    ArrayList<Items> items = new ArrayList<>(3);

    // Setters
    public void setAttack(Integer attack) {
        this.attack = attack;
    }
    public void setDefense(Integer defense) {
        this.defense = defense;
    }
    public void setHp(Integer hp) {
        this.hp = hp;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSpecial_attack(Integer special_attack) {
        this.special_attack = special_attack;
    }
    public void setSpecial_defense(Integer special_defense) {
        this.special_defense = special_defense;
    }
    public void setAbilities(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }
    public void setItems(ArrayList<Items> items) {
        this.items = items;

        for (Items item : items) {
            if(item.getName().equals("Scut")){
                this.defense += item.getDefense();
                this.special_defense += item.getSpecial_defense();
            }
            if (item.getName().equals("Vesta")) {
                this.hp += item.getHp();
            }
            if (item.getName().equals("Sabiuta")) {
                if(this.attack != null) {
                    this.attack += item.getAttack();
                }
            }
            if (item.getName().equals("Bagheta Magica")) {
                if(this.special_attack != null) {
                    this.special_attack += item.getSpecial_attack();
                }
            }
            if (item.getName().equals("Vitamine")) {
                this.hp += item.getHp();
                if(this.attack != null) {
                    this.attack += item.getAttack();
                }
                if(this.special_attack != null) {
                    this.special_attack += item.getSpecial_attack();
                }
            }
            if(item.getName().equals("Brad de Craciun")) {
                if(this.attack != null) {
                    this.attack += item.getAttack();
                    this.defense += item.getDefense();
                }

            }
            if(item.getName().equals("Pelerina")) {
                this.special_defense += item.getSpecial_defense();
            }
        }
    }

    // Getters
    public Integer getAttack() {
        return attack;
    }
    public String getName() {
        return name;
    }
    public Integer getDefense() {
        return defense;
    }
    public Integer getHp() {
        return hp;
    }
    public Integer getSpecial_attack() {
        return special_attack;
    }
    public Integer getSpecial_defense() {
        return special_defense;
    }
    public ArrayList<Ability> getAbilities() {
        return abilities;
    }
    public ArrayList<Items> getItems() {
        return items;
    }

    // Special Getters
    public Integer getTypeAttack() {
        int attack;
        if(this.getAttack() != null) {
            attack = 1;
        }
        else {
            attack = 2;
        }
        return attack;
    }
    public Integer getAllDetails() {
        Integer score;

        score = this.getHp() + this.getDefense() + this.getSpecial_defense();
        if (this.getAttack() != null){
            score += this.getAttack();
        }
        else {
            score += this.getSpecial_attack();
        }

        return score;
    }


    @Override
    public String toString() {
        return  "name:" + name +
                " hp:" + hp +
                " attack:" + attack +
                " special_attack:" + special_attack +
                " defense:" + defense +
                " special_defense:" + special_defense +
                " abilities:" + abilities.toString() +
                " items:" + items.toString() + "\n";
    }

    public static class Factory{

        private static Factory instantaUnica;

        private Factory(){}

        // Design pattern 1 - Singleton
        public static Factory Instanta (){
            if (instantaUnica == null)
                instantaUnica = new Factory();
            return instantaUnica;
        }

        // Design pattern 2 - Factory
        public Pokemon createPokemon(String tip){
            return switch (tip) {
                case "Neutrel1"     -> new Neutrel1();
                case "Neutrel2"     -> new Neutrel2();
                case "Pikachu"      -> new Pikachu();
                case "Bulbasaur"    -> new Bulbasaur();
                case "Charmander"   -> new Charmander();
                case "Squirtle"     -> new Squirtle();
                case "Snorlax"      -> new Snorlax();
                case "Vulpix"       -> new Vulpix();
                case "Eevee"        -> new Eevee();
                case "Jigglypuff"   -> new Jigglypuff();
                case "Meowth"       -> new Meowth();
                case "Psyduck"      -> new Psyduck();
                default -> null;
            };
        }

    }

    static class Neutrel1 extends Pokemon{

        public Neutrel1() {
            this.setName("Neutrel1");
            this.setHp(10);
            this.setAttack(3);
            this.setDefense(1);
            this.setSpecial_defense(1);
        }
    }
    static class Neutrel2 extends Pokemon{

        public Neutrel2() {
            this.setName("Neutrel2");
            this.setHp(20);
            this.setAttack(4);
            this.setDefense(1);
            this.setSpecial_defense(1);
        }
    }
    static class Pikachu extends Pokemon{

        public Pikachu() {
            this.setName("Pikachu");
            this.setHp(35);
            this.setSpecial_attack(4);
            this.setDefense(2);
            this.setSpecial_defense(3);

            ArrayList<Ability> abilities = new ArrayList<>();

            Ability ability1 = new Ability();
            ability1.setDmg(6);
            ability1.setStun(Boolean.FALSE);
            ability1.setDodge(Boolean.FALSE);
            ability1.setCd(4);

            Ability ability2 = new Ability();
            ability2.setDmg(4);
            ability2.setStun(Boolean.TRUE);
            ability2.setDodge(Boolean.TRUE);
            ability2.setCd(5);

            abilities.add(ability1);
            abilities.add(ability2);
            this.setAbilities(abilities);
        }
    }
    static class Bulbasaur extends Pokemon{

        public Bulbasaur() {
            this.setName("Bulbasaur");
            this.setHp(42);
            this.setSpecial_attack(5);
            this.setDefense(3);
            this.setSpecial_defense(1);
            ArrayList<Ability> abilities = new ArrayList<>();

            Ability ability1 = new Ability();
            ability1.setDmg(6);
            ability1.setStun(Boolean.FALSE);
            ability1.setDodge(Boolean.FALSE);
            ability1.setCd(4);

            Ability ability2 = new Ability();
            ability2.setDmg(5);
            ability2.setStun(Boolean.FALSE);
            ability2.setDodge(Boolean.FALSE);
            ability2.setCd(3);

            abilities.add(ability1);
            abilities.add(ability2);
            this.setAbilities(abilities);

        }
    }
    static class Charmander extends Pokemon{

        public Charmander() {
            this.setName("Charmander");
            this.setHp(50);
            this.setAttack(4);
            this.setDefense(3);
            this.setSpecial_defense(2);
            ArrayList<Ability> abilities = new ArrayList<>();

            Ability ability1 = new Ability();
            ability1.setDmg(4);
            ability1.setStun(Boolean.TRUE);
            ability1.setDodge(Boolean.FALSE);
            ability1.setCd(4);

            Ability ability2 = new Ability();
            ability2.setDmg(7);
            ability2.setStun(Boolean.FALSE);
            ability2.setDodge(Boolean.FALSE);
            ability2.setCd(6);

            abilities.add(ability1);
            abilities.add(ability2);
            this.setAbilities(abilities);

        }
    }
    static class Squirtle extends Pokemon{

        public Squirtle() {
            this.setName("Squirtle");
            this.setHp(60);
            this.setSpecial_attack(3);
            this.setDefense(5);
            this.setSpecial_defense(5);

            ArrayList<Ability> abilities = new ArrayList<>();

            Ability ability1 = new Ability();
            ability1.setDmg(4);
            ability1.setStun(Boolean.FALSE);
            ability1.setDodge(Boolean.FALSE);
            ability1.setCd(3);

            Ability ability2 = new Ability();
            ability2.setDmg(2);
            ability2.setStun(Boolean.TRUE);
            ability2.setDodge(Boolean.FALSE);
            ability2.setCd(2);

            abilities.add(ability1);
            abilities.add(ability2);
            this.setAbilities(abilities);
        }
    }
    static class Snorlax extends Pokemon{

        public Snorlax() {
            this.setName("Snorlax");
            this.setHp(62);
            this.setAttack(3);
            this.setDefense(6);
            this.setSpecial_defense(4);
            ArrayList<Ability> abilities = new ArrayList<>();

            Ability ability1 = new Ability();
            ability1.setDmg(4);
            ability1.setStun(Boolean.TRUE);
            ability1.setDodge(Boolean.FALSE);
            ability1.setCd(5);

            Ability ability2 = new Ability();
            ability2.setDmg(0);
            ability2.setStun(Boolean.FALSE);
            ability2.setDodge(Boolean.TRUE);
            ability2.setCd(5);

            abilities.add(ability1);
            abilities.add(ability2);
            this.setAbilities(abilities);
        }
    }
    static class Vulpix extends Pokemon{

        public Vulpix() {
            this.setName("Vulpix");
            this.setHp(36);
            this.setAttack(5);
            this.setDefense(2);
            this.setSpecial_defense(4);
            ArrayList<Ability> abilities = new ArrayList<>();

            Ability ability1 = new Ability();
            ability1.setDmg(8);
            ability1.setStun(Boolean.TRUE);
            ability1.setDodge(Boolean.FALSE);
            ability1.setCd(6);

            Ability ability2 = new Ability();
            ability2.setDmg(2);
            ability2.setStun(Boolean.FALSE);
            ability2.setDodge(Boolean.TRUE);
            ability2.setCd(7);

            abilities.add(ability1);
            abilities.add(ability2);
            this.setAbilities(abilities);
        }
    }
    static class Eevee extends Pokemon{

        public Eevee() {
            this.setName("Eevee");
            this.setHp(39);
            this.setSpecial_attack(4);
            this.setDefense(3);
            this.setSpecial_defense(3);
            ArrayList<Ability> abilities = new ArrayList<>();

            Ability ability1 = new Ability();
            ability1.setDmg(5);
            ability1.setStun(Boolean.FALSE);
            ability1.setDodge(Boolean.FALSE);
            ability1.setCd(3);

            Ability ability2 = new Ability();
            ability2.setDmg(3);
            ability2.setStun(Boolean.TRUE);
            ability2.setDodge(Boolean.FALSE);
            ability2.setCd(3);

            abilities.add(ability1);
            abilities.add(ability2);
            this.setAbilities(abilities);
        }
    }
    static class Jigglypuff extends Pokemon{

        public Jigglypuff() {
            this.setName("Jigglypuff");
            this.setHp(34);
            this.setAttack(4);
            this.setDefense(2);
            this.setSpecial_defense(3);
            ArrayList<Ability> abilities = new ArrayList<>();

            Ability ability1 = new Ability();
            ability1.setDmg(4);
            ability1.setStun(Boolean.TRUE);
            ability1.setDodge(Boolean.FALSE);
            ability1.setCd(4);

            Ability ability2 = new Ability();
            ability2.setDmg(3);
            ability2.setStun(Boolean.TRUE);
            ability2.setDodge(Boolean.FALSE);
            ability2.setCd(4);

            abilities.add(ability1);
            abilities.add(ability2);
            this.setAbilities(abilities);
        }
    }
    static class Meowth extends Pokemon{

        public Meowth() {
            this.setName("Meowth");
            this.setHp(41);
            this.setAttack(3);
            this.setDefense(4);
            this.setSpecial_defense(2);
            ArrayList<Ability> abilities = new ArrayList<>();

            Ability ability1 = new Ability();
            ability1.setDmg(5);
            ability1.setStun(Boolean.FALSE);
            ability1.setDodge(Boolean.TRUE);
            ability1.setCd(4);

            Ability ability2 = new Ability();
            ability2.setDmg(1);
            ability2.setStun(Boolean.FALSE);
            ability2.setDodge(Boolean.TRUE);
            ability2.setCd(3);

            abilities.add(ability1);
            abilities.add(ability2);
            this.setAbilities(abilities);
        }
    }
    static class Psyduck extends Pokemon{

        public Psyduck() {
            this.setName("Psyduck");
            this.setHp(43);
            this.setAttack(3);
            this.setDefense(3);
            this.setSpecial_defense(3);
            ArrayList<Ability> abilities = new ArrayList<>();

            Ability ability1 = new Ability();
            ability1.setDmg(2);
            ability1.setStun(Boolean.FALSE);
            ability1.setDodge(Boolean.FALSE);
            ability1.setCd(4);

            Ability ability2 = new Ability();
            ability2.setDmg(2);
            ability2.setStun(Boolean.TRUE);
            ability2.setDodge(Boolean.FALSE);
            ability2.setCd(5);

            abilities.add(ability1);
            abilities.add(ability2);
            this.setAbilities(abilities);
        }
    }


    // Battle method
    public String winBattle(){
        return this.getName() + " a castigat!";
    }

}













