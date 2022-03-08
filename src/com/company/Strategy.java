package com.company;

import java.io.IOException;

public interface Strategy {

    void fight(Pokemon pokemon1, Pokemon pokemon2) throws IOException;
}
