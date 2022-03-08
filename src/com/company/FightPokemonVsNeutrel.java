package com.company;

public class FightPokemonVsNeutrel implements Strategy {
    @Override
    public void fight(Pokemon pokemon1, Pokemon pokemon2) {
        BattleThread thread = new BattleThread(pokemon1, pokemon2);
        thread.run();
    }
}
