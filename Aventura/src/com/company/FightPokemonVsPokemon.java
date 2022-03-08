package com.company;

public class FightPokemonVsPokemon implements Strategy {
    @Override
    public void fight(Pokemon pokemon1, Pokemon pokemon2) {
        Battle battle = new Battle();
        battle.battleType2(pokemon1,pokemon2);
    }
}
