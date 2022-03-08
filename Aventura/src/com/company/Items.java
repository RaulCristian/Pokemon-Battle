package com.company;

public class Items {

    private final String name;
    private final Integer hp;
    private final Integer attack;
    private final Integer special_attack;
    private final Integer defense;
    private final Integer special_defense;

    //  Design Pattern 3 - Builder
    Items(ItemsBuilder builder) {
        this.name = builder.name;
        this.hp = builder.hp;
        this.attack = builder.attack;
        this.special_attack = builder.special_attack;
        this.defense = builder.defense;
        this.special_defense = builder.special_defense;
    }

    public String getName() {
        return name;
    }
    public Integer getHp() {
        return hp;
    }
    public Integer getAttack() {
        return attack;
    }
    public Integer getSpecial_attack() {
        return special_attack;
    }
    public Integer getDefense() {
        return defense;
    }
    public Integer getSpecial_defense() {
        return special_defense;
    }


    public static class ItemsFactory{

        private static ItemsFactory instantaUnica;

        private ItemsFactory() {}

        // Design pattern 1 - Singleton
        public static ItemsFactory Instanta() {
            if (instantaUnica == null)
                instantaUnica = new ItemsFactory();
            return instantaUnica;
        }

        // Design pattern 2 - Factory
        public Items createItems(String tip){
            return switch (tip){
                case "scut"             -> new ItemsBuilder("Scut").defense(2).special_defense(2).build();
                case "vesta"            -> new ItemsBuilder("Vesta").hp(10).build();
                case "sabiuta"          -> new ItemsBuilder("Sabiuta").attack(3).build();
                case "bagheta_magica"   -> new ItemsBuilder("Bagheta Magica").special_attack(3).build();
                case "vitamine"         -> new ItemsBuilder("Vitamine").hp(2).attack(2).special_attack(2).build();
                case "brad_de_craciun"  -> new ItemsBuilder("Brad de Craciun").attack(3).defense(1).build();
                case "pelerina"         -> new ItemsBuilder("Pelerina").special_defense(3).build();
                default -> null;
            };
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

}










