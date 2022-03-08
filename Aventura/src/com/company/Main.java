package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.*;

public class Main{

    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {

        Pokemon.Factory factory = Pokemon.Factory.Instanta();
        Items.ItemsFactory itemsFactory = Items.ItemsFactory.Instanta();


        String [] data = new String[10];

        File test0 = new File("tests/test0.txt");
        File test1 = new File("tests/test1.txt");
        File test2 = new File("tests/test2.txt");
        File test3 = new File("tests/test3.txt");
        File test4 = new File("tests/test4.txt");
        File test5 = new File("tests/test5.txt");
        File test6 = new File("tests/test6.txt");
        File test7 = new File("tests/test7.txt");
        File test8 = new File("tests/test8.txt");
        File test9 = new File("tests/test9.txt");

        File out0 = new File("outputs/out0.txt");
        File out1 = new File("outputs/out1.txt");
        File out2 = new File("outputs/out2.txt");
        File out3 = new File("outputs/out3.txt");
        File out4 = new File("outputs/out4.txt");
        File out5 = new File("outputs/out5.txt");
        File out6 = new File("outputs/out6.txt");
        File out7 = new File("outputs/out7.txt");
        File out8 = new File("outputs/out8.txt");
        File out9 = new File("outputs/out9.txt");

        Aventura aventura = new Aventura();
        aventura.setLogger(out2);

        try (BufferedReader br = new BufferedReader(new FileReader(test2))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                data[i] = line;
                i++;
            }
        }


        /*-------------------------ANTRENOR    1-------------------------*/
        Antrenor antrenor1 = new Antrenor();
        String [] antrenor_detalis1;
        antrenor_detalis1 = data[0].split(" ");
        antrenor1.setName(antrenor_detalis1[0]);                    //  Nume
        antrenor1.setAge(Integer.parseInt(antrenor_detalis1[1]));   //  Varsta


        /*      Pokemon 1      */
        String [] pokemon_details1;
        pokemon_details1 = data[1].split(" ");
        Pokemon pokemon1_antr1 = factory.createPokemon(pokemon_details1[0]);

        ArrayList<Items> items1 = new ArrayList<>();                            // Items pokemon 1
        for(int i = 1; i < pokemon_details1.length; i++){
            Items items = itemsFactory.createItems(pokemon_details1[i]);
            items1.add(items);
        }
        pokemon1_antr1.setItems(items1);


        /*      Pokemon 2      */
        String [] pokemon_details2;
        pokemon_details2 = data[2].split(" ");
        Pokemon pokemon2_antr1 = factory.createPokemon(pokemon_details2[0]);

        ArrayList<Items> items2 = new ArrayList<>();                            // Items pokemon 2
        for(int i = 1; i < pokemon_details2.length; i++){
            Items items = itemsFactory.createItems(pokemon_details2[i]);
            items2.add(items);
        }
        pokemon2_antr1.setItems(items2);


        /*      Pokemon 3     */
        String [] pokemon_details3;
        pokemon_details3 = data[3].split(" ");
        Pokemon pokemon3_antr1 = factory.createPokemon(pokemon_details3[0]);

        ArrayList<Items> items3 = new ArrayList<>();                            // Items pokemon 1
        for(int i = 1; i < pokemon_details3.length; i++){
            Items items = itemsFactory.createItems(pokemon_details3[i]);
            items3.add(items);
        }
        pokemon3_antr1.setItems(items3);

        // Atribuim antrenorului 1 pokemonii
        ArrayList<Pokemon> pokemons1 = new ArrayList<>();
        pokemons1.add(pokemon1_antr1);
        pokemons1.add(pokemon2_antr1);
        pokemons1.add(pokemon3_antr1);
        antrenor1.setPokemons(pokemons1);

/*-----------------------------------------------------------------------------------------------------------------------*/

        /*-------------------------ANTRENOR    2-------------------------*/
        Antrenor antrenor2 = new Antrenor();
        String [] antrenor_detalis2;
        antrenor_detalis2 = data[5].split(" ");
        antrenor2.setName(antrenor_detalis2[0]);                    //  Nume
        antrenor2.setAge(Integer.parseInt(antrenor_detalis2[1]));   //  Varsta

        /*      Pokemon 1       */
        String [] pokemon_details4;
        pokemon_details4 = data[6].split(" ");
        Pokemon pokemon1_antr2 = factory.createPokemon(pokemon_details4[0]);

        ArrayList<Items> items4 = new ArrayList<>();                            // Items pokemon 1
        for(int i = 1; i < pokemon_details4.length; i++){
            Items items = itemsFactory.createItems(pokemon_details4[i]);
            items4.add(items);
        }
        pokemon1_antr2.setItems(items4);


        /*      Pokemon 2       */
        String [] pokemon_details5;
        pokemon_details5 = data[7].split(" ");
        Pokemon pokemon2_antr2 = factory.createPokemon(pokemon_details5[0]);

        ArrayList<Items> items5 = new ArrayList<>();                            // Items pokemon 2
        for(int i = 1; i < pokemon_details5.length; i++){
            Items items = itemsFactory.createItems(pokemon_details5[i]);
            items5.add(items);
        }
        pokemon2_antr2.setItems(items5);


        /*      Pokemon 3       */
        String [] pokemon_details6;
        pokemon_details6 = data[8].split(" ");
        Pokemon pokemon3_antr2 = factory.createPokemon(pokemon_details6[0]);

        ArrayList<Items> items6 = new ArrayList<>();                            // Items pokemon 3
        for(int i = 1; i < pokemon_details6.length; i++){
            Items items = itemsFactory.createItems(pokemon_details6[i]);
            items6.add(items);
        }
        pokemon3_antr2.setItems(items6);

        // Atribuim antrenorului 2 pokemonii
        ArrayList<Pokemon> pokemons2 = new ArrayList<>();
        pokemons2.add(pokemon1_antr2);
        pokemons2.add(pokemon2_antr2);
        pokemons2.add(pokemon3_antr2);
        antrenor2.setPokemons(pokemons2);



        /*----------------------------------TESTE----------------------------------*/

        logger.log(Level.INFO, "Au intrat in arena antrenorii: " + antrenor1 + " si " + antrenor2);

        for (int i = 0, j; i < 4; i++) {
            if (i < 3) {
                j = i + 1;
                logger.log(Level.INFO, "DUEL " + j);
                aventura.Combat(antrenor1.getPokemons().get(i), antrenor2.getPokemons().get(i));
                logger.log(Level.INFO, "DUELUL " + j + " s-a terminat!\n");
            }
            if (i == 3) {

                int pos1 = antrenor1.getBestPokemonPosition();
                int pos2 = antrenor2.getBestPokemonPosition();
                logger.log(Level.INFO, "\nBatalia suprema se da intre: " + antrenor1.getPokemons().get(pos1).getName()
                        + " si " + antrenor2.getPokemons().get(pos2).getName());
                aventura.Combat(antrenor1.getPokemons().get(pos1), antrenor2.getPokemons().get(pos2));
            }
        }
    }
}















