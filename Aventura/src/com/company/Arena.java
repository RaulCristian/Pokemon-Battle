package com.company;

import java.io.IOException;

//  Design Pattern 4 - Strategy
public class Arena{
    private final Strategy strategy;

    public Arena(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeFight(Pokemon pokemon1, Pokemon pokemon2) throws IOException {
        strategy.fight(pokemon1, pokemon2);
    }

}
