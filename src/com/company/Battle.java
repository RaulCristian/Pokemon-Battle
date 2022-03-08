package com.company;

import java.io.IOException;
import java.util.logging.Level;

import static com.company.Main.logger;

public class Battle{

    Event event = new Event();
    StringBuilder print = new StringBuilder();

    public StringBuilder getPrint() {
        return print;
    }

    //  Battle between Pokemon and Neutrel1/Neutrel2
    public void battleType1 (Pokemon pokemon, Pokemon neutrel) throws IOException {

        print = new StringBuilder(pokemon.getName() + " vs " + neutrel.getName());

        Integer init_Hp = pokemon.getHp();
        Integer init_Def = pokemon.getDefense();
        Integer init_SpecDef = pokemon.getSpecial_defense();
        Integer init_Attack = null;
        Integer init_SpecAttack = null;

        if (pokemon.getAttack() != null){
            init_Attack = pokemon.getAttack();
        }
        else {
            init_SpecAttack = pokemon.getSpecial_attack();
        }

        int nr_rounds = 1;

        //  Setting before battle about HP and Attack values
        int attackType = pokemon.getTypeAttack();
        Integer attackValue;
        String attackRoar;
        if (attackType == 1) {                                      //  Normal Attack
            attackValue = pokemon.getAttack();
            neutrel.setHp(neutrel.getHp() + neutrel.getDefense());
            attackRoar = " atac normal";
        }
        else {                                                      //  Special Attack
            attackValue = pokemon.getSpecial_attack();
            neutrel.setHp(neutrel.getHp() + neutrel.getSpecial_defense());
            attackRoar = " atac special";
        }
        pokemon.setHp(pokemon.getHp() + pokemon.getDefense());


        int ability_used1 = 0;
        int ability_used2 = 0;
        int cooldown1 = pokemon.getAbilities().get(0).getCd();
        int cooldown2 = pokemon.getAbilities().get(1).getCd();
        int isStuned = 10;
        String stun = " si este stuned";

        while (neutrel.getHp() > 0) {

            //  1 - Attack      2 - Ability1        3 - Ability2
            int type_command = event.getRandomNumber(1, 3, ability_used1, ability_used2);

            String cooldown_string1 = " abilitate 1 cooldown " + cooldown1;
            String cooldown_string2 = " abilitate 2 cooldown " + cooldown2;

            if (type_command == 1) {
                String info1 = "";
                String info2 = "";
                String roar;

                if (isStuned != 0) {
                    pokemon.setHp(pokemon.getHp() - neutrel.getAttack());
                    roar = " atac normal";
                }
                else
                    roar = " nimic";

                neutrel.setHp(neutrel.getHp() - attackValue);

                if (cooldown1 != pokemon.getAbilities().get(0).getCd())
                    info1 = cooldown_string1;
                if (cooldown2 != pokemon.getAbilities().get(1).getCd())
                    info2 = cooldown_string2;


                print.append("\n\n").append(nr_rounds).append(". ").append(pokemon.getName()).append(attackRoar).append(" / ").append(neutrel.getName()).append(roar).append("\n")
                        .append(pokemon.getName()).append(" HP ").append(pokemon.getHp()).append(info1).append(" ").append(info2).append("\n")
                        .append(neutrel.getName()).append(" HP ").append(neutrel.getHp());
            }


            if (type_command == 2) {
                String dodge = "";
                String roar;
                String info2 = "";
                String info3 = "";

                ability_used1 = 2;

                // Check if Pokemon Ability1 have Dodge
                if (pokemon.getAbilities().get(0).getDodge() == Boolean.FALSE && isStuned != 0) {
                    pokemon.setHp(pokemon.getHp() - neutrel.getAttack());
                }
                if (pokemon.getAbilities().get(0).getDodge() == Boolean.TRUE) {
                    dodge = " (abilitatea 1 are dodge), ";
                }

                if (isStuned != 0) {
                    roar = " atac normal";
                }
                else
                    roar = " nimic";

                neutrel.setHp(neutrel.getHp() - pokemon.getAbilities().get(0).getDmg());


                // Check if Pokemon Ability1 have Stun
                if (pokemon.getAbilities().get(0).getStun() == Boolean.TRUE){
                    isStuned = -1;
                    info3 = stun;
                }


                if (cooldown2 != pokemon.getAbilities().get(1).getCd())
                    info2 = cooldown_string2;


                print.append("\n\n").append(nr_rounds).append(". ").append(pokemon.getName()).append(" abilitate 1 / ").append(neutrel.getName()).append(roar).append("\n")
                        .append(pokemon.getName()).append(" HP ").append(pokemon.getHp()).append(dodge).append(cooldown_string1).append(info2).append("\n")
                        .append(neutrel.getName()).append(" HP ").append(neutrel.getHp()).append(info3);

            }

            if (type_command == 3) {
                ability_used2 = 3;

                String dodge = "";
                String roar;
                String info1 = "";
                String info3 = "";

                // Check if Pokemon Ability2 have Dodge
                if (pokemon.getAbilities().get(1).getDodge() == Boolean.FALSE && isStuned != 0) {
                    pokemon.setHp(pokemon.getHp() - neutrel.getAttack());
                }
                if (pokemon.getAbilities().get(1).getDodge() == Boolean.TRUE){
                    dodge = " (abilitatea 2 are dodge), ";
                }

                if (isStuned != 0) {
                    roar = " atac normal";
                }
                else
                    roar = " nimic";

                neutrel.setHp(neutrel.getHp() - pokemon.getAbilities().get(1).getDmg());


                // Check if Pokemon Ability2 have Stun
                if (pokemon.getAbilities().get(1).getStun() == Boolean.TRUE){
                    isStuned = -1;
                    info3 = stun;
                }

                if (cooldown1 != pokemon.getAbilities().get(0).getCd())
                    info1 = cooldown_string1;


                print.append("\n\n").append(nr_rounds).append(". ").append(pokemon.getName()).append(" abilitate 2 / ").append(neutrel.getName()).append(roar).append("\n")
                        .append(pokemon.getName()).append(" HP ").append(pokemon.getHp()).append(dodge).append(info1).append(cooldown_string2).append("\n")
                        .append(neutrel.getName()).append(" HP ").append(neutrel.getHp()).append(info3);

            }


            if (cooldown1 == 0) {
                ability_used1 = 0;
                cooldown1 = pokemon.getAbilities().get(0).getCd();
            }

            if (cooldown2 == 0) {
                ability_used2 = 0;
                cooldown2 = pokemon.getAbilities().get(1).getCd();
            }

            if (ability_used1 == 2) {
                cooldown1--;
            }

            if (ability_used2 == 3) {
                cooldown2--;
            }

            isStuned++;
            nr_rounds++;
        }


        if (neutrel.getHp() <= 0){
            pokemon.setHp(init_Hp + 1);
            pokemon.setDefense(init_Def + 1);
            pokemon.setSpecial_defense(init_SpecDef + 1);

            if (init_Attack != null){
                pokemon.setAttack(init_Attack + 1);
            }
            if (init_SpecAttack != null){
                pokemon.setSpecial_attack(init_SpecAttack + 1);
            }

            print.append("\n").append(pokemon.winBattle()).append("\n").append(pokemon);
            logger.log(Level.INFO, String.valueOf(print));

        }

    }


    //  Battle between 2 Pokemons
    public void battleType2 (Pokemon pokemon1, Pokemon pokemon2){

        logger.log(Level.INFO, pokemon1.getName() + " vs " + pokemon2.getName());

        Integer init_Hp1 = pokemon1.getHp();
        Integer init_Def1 = pokemon1.getDefense();
        Integer init_SpecDef1 = pokemon1.getSpecial_defense();
        Integer init_Attack1 = null;
        Integer init_SpecAttack1 = null;

        if (pokemon1.getAttack() != null){
            init_Attack1 = pokemon1.getAttack();
        }
        else {
            init_SpecAttack1 = pokemon1.getSpecial_attack();
        }

        Integer init_Hp2 = pokemon2.getHp();
        Integer init_Def2 = pokemon2.getDefense();
        Integer init_SpecDef2 = pokemon2.getSpecial_defense();
        Integer init_Attack2 = null;
        Integer init_SpecAttack2 = null;

        if (pokemon2.getAttack() != null){
            init_Attack2 = pokemon2.getAttack();
        }
        else {
            init_SpecAttack2 = pokemon2.getSpecial_attack();
        }

        int nr_rounds = 1;

        //  Setting before battle about HP and Attack values
        int attackType1 = pokemon1.getTypeAttack();
        Integer attackValue1;
        String attackRoar1;
        if (attackType1 == 1) {                                      //  Normal Attack
            attackValue1 = pokemon1.getAttack();
            pokemon2.setHp(pokemon2.getHp() + pokemon2.getDefense());
            attackRoar1 = " atac normal";
        }
        else {                                                      //  Special Attack
            attackValue1 = pokemon1.getSpecial_attack();
            pokemon2.setHp(pokemon2.getHp() + pokemon2.getSpecial_defense());
            attackRoar1 = " atac special";
        }

        int attackType2 = pokemon2.getTypeAttack();
        Integer attackValue2;
        String attackRoar2;
        if (attackType2 == 1) {                                      //  Normal Attack
            attackValue2 = pokemon2.getAttack();
            pokemon1.setHp(pokemon1.getHp() + pokemon1.getDefense());
            attackRoar2 = " atac normal";
        }
        else {                                                      //  Special Attack
            attackValue2 = pokemon2.getSpecial_attack();
            pokemon1.setHp(pokemon1.getHp() + pokemon1.getSpecial_defense());
            attackRoar2 = " atac special";
        }

        int ability_used11 = 0;
        int ability_used12 = 0;
        int ability_used21 = 0;
        int ability_used22 = 0;
        int cooldown11 = pokemon1.getAbilities().get(0).getCd();
        int cooldown12 = pokemon1.getAbilities().get(1).getCd();
        int cooldown21 = pokemon2.getAbilities().get(0).getCd();
        int cooldown22 = pokemon2.getAbilities().get(1).getCd();
        int isStuned1 = 10;
        int isStuned2 = 10;
        String stun = " si este stuned";


        while (pokemon1.getHp() > 0 && pokemon2.getHp() > 0) {
            isStuned1++;
            isStuned2++;
            //  1 - Attack      2 - Ability1        3 - Ability2
            int type_command1 = event.getRandomNumber(1, 3, ability_used11, ability_used12);
            int type_command2 = event.getRandomNumber(1, 3, ability_used21, ability_used22);


            String cooldown_string11 = " abilitate 1->cooldown: " + cooldown11;
            String cooldown_string12 = " abilitate 2->cooldown: " + cooldown12;
            String cooldown_string21 = " abilitate 1->cooldown: " + cooldown21;
            String cooldown_string22 = " abilitate 2->cooldown: " + cooldown22;

            if (type_command1 == 1 && type_command2 == 1) {     //  Pokemon1 attack vs Pokemon2 attack
                String info11 = "";
                String info12 = "";
                String info21 = "";
                String info22 = "";
                String roar1;
                String roar2;

                if (isStuned1 != 0) {
                    pokemon2.setHp(pokemon2.getHp() - attackValue1);
                    roar1 = attackRoar1;
                }
                else
                    roar1 = " nimic";

                if (isStuned2 != 0) {
                    pokemon1.setHp(pokemon1.getHp() - attackValue2);
                    roar2 = attackRoar2;
                }
                else
                    roar2 = " nimic";

                if (cooldown11 != pokemon1.getAbilities().get(0).getCd())
                    info11 = cooldown_string11;
                if (cooldown12 != pokemon1.getAbilities().get(1).getCd())
                    info12 = cooldown_string12;

                if (cooldown21 != pokemon2.getAbilities().get(0).getCd())
                    info21 = cooldown_string21;
                if (cooldown22 != pokemon2.getAbilities().get(1).getCd())
                    info22 = cooldown_string22;


                logger.log(Level.INFO, "\n" +nr_rounds + ". " + pokemon1.getName() + roar1 + " / " + pokemon2.getName() + roar2
                        + "\n" + pokemon1.getName() + " HP " + pokemon1.getHp() + info11 + " " + info12
                        + "\n" + pokemon2.getName() + " HP " + pokemon2.getHp() + info21 + " " + info22);

            }

            if (type_command1 == 1 && type_command2 == 2) {     //  Pokemon1 attack vs Pokemon2 ability1
                String dodge = "";
                String info11 = "";
                String info12 = "";
                String info22 = "";
                String info3 = "";
                String roar1;
                String roar2;


                // Check if Pokemon2 Ability1 have Dodge
                if (pokemon2.getAbilities().get(0).getDodge() == Boolean.FALSE && isStuned1 != 0) {
                    pokemon2.setHp(pokemon2.getHp() - attackValue1);
                }
                if (pokemon2.getAbilities().get(0).getDodge() == Boolean.TRUE) {
                    dodge = " (abilitatea 1 are dodge), ";
                }

                if (isStuned1 != 0)
                    roar1 = attackRoar1;
                else
                    roar1 = " nimic";

                if (isStuned2 != 0) {
                    pokemon1.setHp(pokemon1.getHp() - pokemon2.getAbilities().get(0).getDmg());
                    roar2 = " abilitate 1";
                    ability_used21 = 2;
                }
                else {
                    roar2 = " nimic";
                    dodge = "";
                    cooldown_string21 = "";
                }
                // Check if Pokemon2 Ability1 have Stun
                if (pokemon2.getAbilities().get(0).getStun() == Boolean.TRUE && isStuned2 != 0) {
                    isStuned1 = -1;
                    info3 = stun;
                }

                if (cooldown22 != pokemon2.getAbilities().get(1).getCd())
                    info22 = cooldown_string22;

                if (cooldown11 != pokemon1.getAbilities().get(0).getCd())
                    info11 = cooldown_string11;
                if (cooldown12 != pokemon1.getAbilities().get(1).getCd())
                    info12 = cooldown_string12;


                logger.log(Level.INFO, "\n" +nr_rounds + ". " + pokemon1.getName() + roar1 + " / " + pokemon2.getName() + roar2
                        + "\n" + pokemon1.getName() + " HP " + pokemon1.getHp() + info3 + " " + info11 + " " + info12
                        + "\n" + pokemon2.getName() + " HP " + pokemon2.getHp() + dodge +  cooldown_string21 + " " + info22);

            }

            if (type_command1 == 1 && type_command2 == 3) {     //  Pokemon1 attack vs Pokemon2 ability2
                String dodge = "";
                String info11 = "";
                String info12 = "";
                String info21 = "";
                String info3 = "";
                String roar1;
                String roar2;

                // Check if Pokemon2 Ability2 have Dodge
                if (pokemon2.getAbilities().get(1).getDodge() == Boolean.FALSE && isStuned1 != 0) {
                    pokemon2.setHp(pokemon2.getHp() - attackValue1);
                }
                if (pokemon2.getAbilities().get(1).getDodge() == Boolean.TRUE) {
                    dodge = " (abilitatea 2 are dodge),";
                }

                if (isStuned1 != 0)
                    roar1 = attackRoar1;
                else {
                    roar1 = " nimic";
                }
                if (isStuned2 != 0) {
                    pokemon1.setHp(pokemon1.getHp() - pokemon2.getAbilities().get(1).getDmg());
                    roar2 = " abilitate 2";
                    ability_used22 = 3;
                }
                else {
                    roar2 = " nimic";
                    dodge = "";
                    cooldown_string22 = "";
                }
                // Check if Pokemon2 Ability2 have Stun
                if (pokemon2.getAbilities().get(1).getStun() == Boolean.TRUE && isStuned2 != 0){
                    isStuned1 = -1;
                    info3 = stun;
                }

                if (cooldown21 != pokemon2.getAbilities().get(0).getCd())
                    info21 = cooldown_string21;

                if (cooldown11 != pokemon1.getAbilities().get(0).getCd())
                    info11 = cooldown_string11;
                if (cooldown12 != pokemon1.getAbilities().get(1).getCd())
                    info12 = cooldown_string12;

                logger.log(Level.INFO, "\n" +nr_rounds + ". " + pokemon1.getName() + roar1 + " / " + pokemon2.getName() + roar2
                        + "\n" + pokemon1.getName() + " HP " + pokemon1.getHp() + info3 + " " + info11 + " " + info12
                        + "\n" + pokemon2.getName() + " HP " + pokemon2.getHp() + dodge + " " + info21 + " " + cooldown_string22);

            }

            if (type_command1 == 2 && type_command2 == 1) {     //  Pokemon1 ability1 vs Pokemon2 attack
                String dodge = "";
                String info12 = "";
                String info21 = "";
                String info22 = "";
                String info3 = "";
                String roar1;
                String roar2;

                // Check if Pokemon1 Ability1 have Dodge
                if (pokemon1.getAbilities().get(0).getDodge() == Boolean.FALSE && isStuned2 != 0) {
                    pokemon1.setHp(pokemon1.getHp() - attackValue2);
                }
                if (pokemon1.getAbilities().get(0).getDodge() == Boolean.TRUE) {
                    dodge = " (abilitatea 1 are dodge), ";
                }


                if (isStuned1 != 0) {
                    pokemon2.setHp(pokemon2.getHp() - pokemon1.getAbilities().get(0).getDmg());
                    roar1 = " abilitate 1";
                    ability_used11 = 2;
                }
                else {
                    //pokemon1.setHp(pokemon1.getHp() - attackValue2);
                    roar1 = " nimic";
                    dodge = "";
                    cooldown_string11 = "";
                }
                if (isStuned2 != 0)
                    roar2 = attackRoar2;
                else {
                    roar2 = " nimic";
                }
                // Check if Pokemon1 Ability1 have Stun
                if (pokemon1.getAbilities().get(0).getStun() == Boolean.TRUE && isStuned1 != 0){
                    isStuned2 = -1;
                    info3 = stun;
                }

                if (cooldown12 != pokemon1.getAbilities().get(1).getCd())
                    info12 = cooldown_string12;

                if (cooldown21 != pokemon2.getAbilities().get(0).getCd())
                    info21 = cooldown_string21;
                if (cooldown22 != pokemon2.getAbilities().get(1).getCd())
                    info22 = cooldown_string22;

                logger.log(Level.INFO, "\n" +nr_rounds + ". " + pokemon1.getName() + roar1 + " / " + pokemon2.getName() + roar2
                        + "\n" + pokemon1.getName() + " HP " + pokemon1.getHp() + dodge +  cooldown_string11 + " " + info12
                        + "\n" + pokemon2.getName() + " HP " + pokemon2.getHp() + info3 + " " + info21 + " " + info22);

            }

            if (type_command1 == 2 && type_command2 == 2) {     //  Pokemon1 ability1 vs Pokemon2 ability1

                String dodge1 = "";
                String dodge2 = "";
                String info3 = "";
                String info4 = "";
                String info12 = "";
                String info22 = "";
                String roar1;
                String roar2;

                // Check if Pokemon1 Ability1 have Dodge
                if (pokemon1.getAbilities().get(0).getDodge() == Boolean.TRUE) {
                    dodge1 = " (abilitatea 1 are dodge), ";
                }
                if (pokemon1.getAbilities().get(0).getDodge() == Boolean.FALSE && isStuned2 != 0) {
                    pokemon1.setHp(pokemon1.getHp() - pokemon2.getAbilities().get(0).getDmg());
                }

                // Check if Pokemon2 Ability1 have Dodge
                if (pokemon2.getAbilities().get(0).getDodge() == Boolean.TRUE) {
                    dodge2 = " (abilitatea 1 are dodge), ";
                }
                if (pokemon2.getAbilities().get(0).getDodge() == Boolean.FALSE && isStuned1 != 0) {
                    pokemon2.setHp(pokemon2.getHp() - pokemon1.getAbilities().get(0).getDmg());
                }

                if (isStuned1 != 0) {
                    roar1 = " abilitate 1";
                    ability_used11 = 2;
                }
                else {
                    //pokemon1.setHp(pokemon1.getHp() - pokemon2.getAbilities().get(0).getDmg());
                    roar1 = " nimic";
                    dodge1 = "";
                    cooldown_string11 = "";
                }

                if (isStuned2 != 0) {
                    roar2 = " abilitate 1";
                    ability_used21 = 2;
                }
                else {
                    //pokemon2.setHp(pokemon2.getHp() - pokemon1.getAbilities().get(0).getDmg());
                    roar2 = " nimic";
                    dodge2 = "";
                    cooldown_string21 = "";
                }

                // Check if Pokemon1 Ability1 have Stun
                if (pokemon1.getAbilities().get(0).getStun() == Boolean.TRUE && isStuned1 != 0
                        && pokemon2.getAbilities().get(0).getDodge() == Boolean.FALSE){
                    isStuned2 = -1;
                    info3 = stun;
                }

                // Check if Pokemon2 Ability1 have Stun
                if (pokemon2.getAbilities().get(0).getStun() == Boolean.TRUE && isStuned2 != 0
                        && pokemon1.getAbilities().get(0).getDodge() == Boolean.FALSE){
                    isStuned1 = -1;
                    info4 = stun;
                }

                if (cooldown12 != pokemon1.getAbilities().get(1).getCd())
                    info12 = cooldown_string12;

                if (cooldown22 != pokemon2.getAbilities().get(1).getCd())
                    info22 = cooldown_string22;

                logger.log(Level.INFO, "\n" +nr_rounds + ". " + pokemon1.getName() + roar1 + " / " + pokemon2.getName() + roar2
                        + "\n" + pokemon1.getName() + " HP " + pokemon1.getHp() + info4 + " " + dodge1 + " " + cooldown_string11 + " " + info12
                        + "\n" + pokemon2.getName() + " HP " + pokemon2.getHp() + info3 + " " + dodge2 + " " + cooldown_string21 + " " + info22);

            }

            if (type_command1 == 2 && type_command2 == 3) {     //  Pokemon1 ability1 vs Pokemon2 ability2

                String dodge1 = "";
                String dodge2 = "";
                String info3 = "";
                String info4 = "";
                String info12 = "";
                String info21 = "";
                String roar1;
                String roar2;


                // Check if Pokemon1 Ability1 have Dodge
                if (pokemon1.getAbilities().get(0).getDodge() == Boolean.TRUE) {
                    dodge1 = " (abilitatea 1 are dodge),";
                }
                if (pokemon1.getAbilities().get(0).getDodge() == Boolean.FALSE && isStuned2 != 0) {
                    pokemon1.setHp(pokemon1.getHp() - pokemon2.getAbilities().get(1).getDmg());
                }

                // Check if Pokemon2 Ability2 have Dodge
                if (pokemon2.getAbilities().get(1).getDodge() == Boolean.TRUE) {
                    dodge2 = " (abilitatea 2 are dodge),";
                }
                if (pokemon2.getAbilities().get(1).getDodge() == Boolean.FALSE && isStuned1 != 0) {
                    pokemon2.setHp(pokemon2.getHp() - pokemon1.getAbilities().get(0).getDmg());
                }

                if (isStuned1 != 0) {
                    roar1 = " abilitate 1";
                    ability_used11 = 2;
                }
                else {
                    //pokemon1.setHp(pokemon1.getHp() - pokemon2.getAbilities().get(1).getDmg());
                    roar1 = " nimic";
                    dodge1 = "";
                    cooldown_string11 = "";
                }
                if (isStuned2 != 0) {
                    roar2 = " abilitate 2";
                    ability_used22 = 3;
                }
                else {
                    //pokemon2.setHp(pokemon2.getHp() - pokemon1.getAbilities().get(0).getDmg());
                    roar2 = " nimic";
                    dodge2 = "";
                    cooldown_string22 = "";
                }

                // Check if Pokemon1 Ability1 have Stun
                if (pokemon1.getAbilities().get(0).getStun() == Boolean.TRUE && isStuned1 != 0
                        && pokemon2.getAbilities().get(1).getDodge() == Boolean.FALSE){
                    isStuned2 = -1;
                    info3 = stun;
                }

                // Check if Pokemon2 Ability2 have Stun
                if (pokemon2.getAbilities().get(1).getStun() == Boolean.TRUE && isStuned2 != 0
                        && pokemon1.getAbilities().get(0).getDodge() == Boolean.FALSE){
                    isStuned1 = -1;
                    info4 = stun;
                }

                if (cooldown12 != pokemon1.getAbilities().get(1).getCd())
                    info12 = cooldown_string12;

                if (cooldown21 != pokemon2.getAbilities().get(0).getCd())
                    info21 = cooldown_string21;

                logger.log(Level.INFO, "\n" +nr_rounds + ". " + pokemon1.getName() + roar1 + " / " + pokemon2.getName() + roar2
                        + "\n" + pokemon1.getName() + " HP " + pokemon1.getHp() + info4 + " " + dodge1 + " " + cooldown_string11 + " " + info12
                        + "\n" + pokemon2.getName() + " HP " + pokemon2.getHp() + info3 + " " + dodge2 + " " + info21 + " " + cooldown_string22);


            }

            if (type_command1 == 3 && type_command2 == 1) {     //  Pokemon1 ability2 vs Pokemon2 attack
                String dodge = "";
                String info11 = "";
                String info21 = "";
                String info22 = "";
                String info3 = "";
                String roar1;
                String roar2;


                // Check if Pokemon1 Ability2 have Dodge
                if (pokemon1.getAbilities().get(1).getDodge() == Boolean.FALSE && isStuned2 != 0) {
                    pokemon1.setHp(pokemon1.getHp() - attackValue2);
                }
                if (pokemon1.getAbilities().get(1).getDodge() == Boolean.TRUE) {
                    dodge = " (abilitatea 2 are dodge), ";
                }

                if (isStuned2 != 0)
                    roar2 = attackRoar2;
                else {
                    roar2 = " nimic";
                    dodge = "";
                }
                if (isStuned1 != 0) {
                    pokemon2.setHp(pokemon2.getHp() - pokemon1.getAbilities().get(1).getDmg());
                    roar1 = " abilitate 2";
                    ability_used12 = 3;
                }
                else {
                    roar1 = " nimic";
                    dodge = "";
                    cooldown_string12 = "";
                }
                // Check if Pokemon1 Ability2 have Stun
                if (pokemon1.getAbilities().get(1).getStun() == Boolean.TRUE  && isStuned1 != 0){
                    isStuned2 = -1;
                    info3 = stun;
                }

                if (cooldown11 != pokemon1.getAbilities().get(0).getCd())
                    info11 = cooldown_string11;

                if (cooldown21 != pokemon2.getAbilities().get(0).getCd())
                    info21 = cooldown_string21;
                if (cooldown22 != pokemon2.getAbilities().get(1).getCd())
                    info22 = cooldown_string22;

                logger.log(Level.INFO, "\n" +nr_rounds + ". " + pokemon1.getName() + roar1 + " / " + pokemon2.getName() + roar2
                        + "\n" + pokemon1.getName() + " HP " + pokemon1.getHp() + dodge +  info11 + " " + cooldown_string12
                        + "\n" + pokemon2.getName() + " HP " + pokemon2.getHp() + info3 + " " + info21 + " " + info22);

            }

            if (type_command1 == 3 && type_command2 == 2) {     //  Pokemon1 ability2 vs Pokemon2 ability1

                String dodge1 = "";
                String dodge2 = "";
                String info3 = "";
                String info4 = "";
                String info11 = "";
                String info22 = "";
                String roar1;
                String roar2;

                // Check if Pokemon1 Ability2 have Dodge
                if (pokemon1.getAbilities().get(1).getDodge() == Boolean.TRUE) {
                    dodge1 = " (abilitatea 2 are dodge),";
                }
                if (pokemon1.getAbilities().get(1).getDodge() == Boolean.FALSE && isStuned2 != 0) {
                    pokemon1.setHp(pokemon1.getHp() - pokemon2.getAbilities().get(0).getDmg());
                }

                // Check if Pokemon2 Ability1 have Dodge
                if (pokemon2.getAbilities().get(0).getDodge() == Boolean.TRUE) {
                    dodge2 = " (abilitatea 1 are dodge),";
                }
                if (pokemon2.getAbilities().get(0).getDodge() == Boolean.FALSE && isStuned1 != 0) {
                    pokemon2.setHp(pokemon2.getHp() - pokemon1.getAbilities().get(1).getDmg());
                }

                if (isStuned1 != 0) {
                    roar1 = " abilitate 2";
                    ability_used12 = 3;
                }
                else {
                    //pokemon1.setHp(pokemon1.getHp() - pokemon2.getAbilities().get(0).getDmg());
                    roar1 = " nimic";
                    dodge1 = "";
                    cooldown_string12 = "";
                }
                if (isStuned2 != 0) {
                    roar2 = " abilitate 1";
                    ability_used21 = 2;
                }
                else {
                    //pokemon2.setHp(pokemon2.getHp() - pokemon1.getAbilities().get(1).getDmg());
                    roar2 = " nimic";
                    dodge2 = "";
                    cooldown_string21 = "";
                }

                // Check if Pokemon1 Ability2 have Stun
                if (pokemon1.getAbilities().get(1).getStun() == Boolean.TRUE && isStuned1 != 0
                        && pokemon2.getAbilities().get(0).getDodge() == Boolean.FALSE){
                    isStuned2 = -1;
                    info3 = stun;
                }

                // Check if Pokemon2 Ability1 have Stun
                if (pokemon2.getAbilities().get(0).getStun() == Boolean.TRUE && isStuned2 != 0
                        && pokemon1.getAbilities().get(1).getDodge() == Boolean.FALSE){
                    isStuned1 = -1;
                    info4 = stun;
                }

                if (cooldown11 != pokemon1.getAbilities().get(0).getCd())
                    info11 = cooldown_string11;

                if (cooldown22 != pokemon2.getAbilities().get(1).getCd())
                    info22 = cooldown_string22;

                logger.log(Level.INFO, "\n" +nr_rounds + ". " + pokemon1.getName() + roar1 + " / " + pokemon2.getName() + roar2
                        + "\n" + pokemon1.getName() + " HP " + pokemon1.getHp() + info4 + " " + dodge1 + " " + info11 + " " +  cooldown_string12
                        + "\n" + pokemon2.getName() + " HP " + pokemon2.getHp() + info3 + " " + dodge2 + " " + cooldown_string21 + " " + info22);


            }

            if (type_command1 == 3 && type_command2 == 3) {     //  Pokemon1 ability2 vs Pokemon2 ability2

                String dodge1 = "";
                String dodge2 = "";
                String info3 = "";
                String info4 = "";
                String info11 = "";
                String info21 = "";
                String roar1;
                String roar2;


                // Check if Pokemon1 Ability2 have Dodge
                if (pokemon1.getAbilities().get(1).getDodge() == Boolean.TRUE) {
                    dodge1 = " (abilitatea 2 are dodge),";
                }
                if (pokemon1.getAbilities().get(1).getDodge() == Boolean.FALSE && isStuned2 != 0) {
                    pokemon1.setHp(pokemon1.getHp() - pokemon2.getAbilities().get(1).getDmg());
                }

                // Check if Pokemon2 Ability2 have Dodge
                if (pokemon2.getAbilities().get(1).getDodge() == Boolean.TRUE) {
                    dodge2 = " (abilitatea 2 are dodge),";
                }
                if (pokemon2.getAbilities().get(1).getDodge() == Boolean.FALSE && isStuned1 != 0) {
                    pokemon2.setHp(pokemon2.getHp() - pokemon1.getAbilities().get(1).getDmg());
                }

                if (isStuned1 != 0) {
                    roar1 = " abilitate 2";
                    ability_used12 = 3;
                }
                else {
                    //pokemon1.setHp(pokemon1.getHp() - pokemon2.getAbilities().get(1).getDmg());
                    roar1 = " nimic";
                    dodge1 = "";
                    cooldown_string12 = "";
                }
                if (isStuned2 != 0) {
                    roar2 = " abilitate 2";
                    ability_used22 = 3;
                }
                else {
                    //pokemon2.setHp(pokemon2.getHp() - pokemon1.getAbilities().get(1).getDmg());
                    roar2 = " nimic";
                    dodge2 = "";
                    cooldown_string22 = "";
                }

                // Check if Pokemon1 Ability2 have Stun
                if (pokemon1.getAbilities().get(1).getStun() == Boolean.TRUE && isStuned1 != 0      // Daca Pokemon1 are abilitatea2 cu stun
                        && pokemon2.getAbilities().get(1).getDodge() == Boolean.FALSE){             // si nu este blocat si Pokemon2 cu abilitatea2
                    isStuned2 = -1;                                                                 // nu are dodge atunci Pokemon2 va fi blocat
                    info3 = stun;                                                                   // pentru urmatoarea runda
                }

                // Check if Pokemon2 Ability2 have Stun
                if (pokemon2.getAbilities().get(1).getStun() == Boolean.TRUE && isStuned2 != 0
                        && pokemon1.getAbilities().get(1).getDodge() == Boolean.FALSE){
                    isStuned1 = -1;
                    info4 = stun;
                }

                if (cooldown11 != pokemon1.getAbilities().get(0).getCd())
                    info11 = cooldown_string11;

                if (cooldown21 != pokemon2.getAbilities().get(0).getCd())
                    info21 = cooldown_string21;

                logger.log(Level.INFO, "\n" +nr_rounds + ". " + pokemon1.getName() + roar1 + " / " + pokemon2.getName() + roar2
                        + "\n" + pokemon1.getName() + " HP " + pokemon1.getHp() + info4 + " " + dodge1 + " " + info11 + " " + cooldown_string12
                        + "\n" + pokemon2.getName() + " HP " + pokemon2.getHp() + info3 + " " + dodge2 + " " + info21 + " " + cooldown_string22);

            }


            if (cooldown11 == 0) {
                ability_used11 = 0;
                cooldown11 = pokemon1.getAbilities().get(0).getCd();
            }

            if (ability_used11 == 2) {
                cooldown11--;
            }

            if (cooldown12 == 0) {
                ability_used12 = 0;
                cooldown12 = pokemon1.getAbilities().get(1).getCd();
            }

            if (ability_used12 == 3) {
                cooldown12--;
            }

            if (cooldown21 == 0) {
                ability_used21 = 0;
                cooldown21 = pokemon2.getAbilities().get(0).getCd();
            }

            if (ability_used21 == 2) {
               cooldown21--;
            }

            if (cooldown22 == 0) {
                ability_used22 = 0;
                cooldown22 = pokemon2.getAbilities().get(1).getCd();
            }

            if (ability_used22 == 3) {
                cooldown22--;
            }

            nr_rounds++;
        }


        if(pokemon1.getHp() > pokemon2.getHp()) {
            logger.log(Level.INFO, "\n" + pokemon1.winBattle());           //  Pokemon 1 wins

            //  Regeneration Pokemon1
            pokemon1.setHp(init_Hp1 + 1);
            pokemon1.setDefense(init_Def1 + 1);
            pokemon1.setSpecial_defense(init_SpecDef1 + 1);

            if (init_Attack1 != null){
                pokemon1.setAttack(init_Attack1 + 1);
            }
            if (init_SpecAttack1 != null){
                pokemon1.setSpecial_attack(init_SpecAttack1 + 1);
            }

            //  Regeneration Pokemon2
            pokemon2.setHp(init_Hp2);
            pokemon2.setDefense(init_Def2);
            pokemon2.setSpecial_defense(init_SpecDef2);

            if (init_Attack2 != null){
                pokemon2.setAttack(init_Attack2);
            }
            if (init_SpecAttack2 != null){
                pokemon2.setSpecial_attack(init_SpecAttack2);
            }
            logger.log(Level.INFO, pokemon1.toString());

        }
        else {                                                      //  Pokemon 2 wins
            logger.log(Level.INFO, "\n" + pokemon2.winBattle());

            //  Regeneration Pokemon1
            pokemon1.setHp(init_Hp1);
            pokemon1.setDefense(init_Def1);
            pokemon1.setSpecial_defense(init_SpecDef1);

            if (init_Attack1 != null){
                pokemon1.setAttack(init_Attack1);
            }
            if (init_SpecAttack1 != null){
                pokemon1.setSpecial_attack(init_SpecAttack1);
            }

            //  Regeneration Pokemon2
            pokemon2.setHp(init_Hp2 + 1);
            pokemon2.setDefense(init_Def2 + 1);
            pokemon2.setSpecial_defense(init_SpecDef2 + 1);

            if (init_Attack2 != null){
                pokemon2.setAttack(init_Attack2 + 1);
            }
            if (init_SpecAttack2 != null){
                pokemon2.setSpecial_attack(init_SpecAttack2 + 1);
            }
            logger.log(Level.INFO, pokemon2.toString());
        }
    }
}
