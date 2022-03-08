package com.company;


import java.io.IOException;

public class BattleThread implements Runnable{

    Battle battle = new Battle();
    Pokemon pokemon1;
    Pokemon pokemon2;
    StringBuilder stringBuilder;

    public BattleThread(Pokemon pokemon1, Pokemon pokemon2) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
    }

    @Override
    public void run() {
        try {
            battle.battleType1(pokemon1, pokemon2);
            this.stringBuilder = battle.getPrint();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
