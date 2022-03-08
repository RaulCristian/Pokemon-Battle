package com.company;

import java.util.ArrayList;

public class Antrenor {

    String name;
    Integer age;
    ArrayList<Pokemon> pokemons;

    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }


    // Getters
    public String getName() {
        return name;
    }
    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public Integer getBestPokemonPosition(){

        int maxScore = pokemons.get(0).getAllDetails();
        String maxName = null;
        int position = 0;
        for (int i = 1; i < 3; i++) {
            if (maxScore < pokemons.get(i).getAllDetails()) {
                maxScore = pokemons.get(i).getAllDetails();
                maxName = pokemons.get(i).getName();
                position = i;
            }
            if (maxScore == pokemons.get(i).getAllDetails()){
                assert maxName != null;
                if (maxName.compareTo(pokemons.get(i).getName()) > 0) {
                    maxName = pokemons.get(i).getName();
                    position = i;
                }
            }
        }
        return position;
    }

    @Override
    public String toString() {
        return  name + " " + age;
    }
}
