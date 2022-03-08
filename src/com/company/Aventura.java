package com.company;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

import static com.company.Main.logger;

public class Aventura {

    Pokemon.Factory factory = Pokemon.Factory.Instanta();

    public void setLogger(File file){
        logger.setLevel(Level.FINE);
        logger.addHandler(new MyLogger.MyHandler());

        try {
            //FileHandler file name with max size and number of log files limit
            Handler fileHandler = new FileHandler(file.getAbsolutePath(), 30000, 1);
            fileHandler.setFormatter(new MyLogger.MyFormatter());
            //setting custom filter for FileHandler
            fileHandler.setFilter(new MyLogger.MyFilter());
            logger.addHandler(fileHandler);
            logger.log(Level.CONFIG, "Config data");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public void Combat(Pokemon pokemon1, Pokemon pokemon2) throws IOException {
        Event event = new Event();
        Arena trainingArena1 = new Arena(new FightPokemonVsNeutrel());
        Arena trainingArena2 = new Arena(new FightPokemonVsNeutrel());
        Arena duelArena = new Arena(new FightPokemonVsPokemon());

        boolean finalBattle = Boolean.FALSE;

        logger.log(Level.INFO, "Se dueleaza pokemonii:\n" + pokemon1.toString() + pokemon2.toString());

        while (finalBattle == Boolean.FALSE) {

            Pokemon neutrel11 = factory.createPokemon("Neutrel1");
            Pokemon neutrel12 = factory.createPokemon("Neutrel2");
            Pokemon neutrel21 = factory.createPokemon("Neutrel1");
            Pokemon neutrel22 = factory.createPokemon("Neutrel2");

               //  1 - vs Neutrel1     2 - vs Neutrel2     3 - Pokemon1 vs Pokemon2
            int type_battle = event.getRandomNumber(1, 3, 0, 0);

            if (type_battle == 3) {
                finalBattle = Boolean.TRUE;
                duelArena.executeFight(pokemon1, pokemon2);
            }

            if (type_battle == 2) {
                trainingArena1.executeFight(pokemon1, neutrel12);
                trainingArena2.executeFight(pokemon2, neutrel22);

            }

            if (type_battle == 1) {
                trainingArena1.executeFight(pokemon1, neutrel11);
                trainingArena2.executeFight(pokemon2, neutrel21);

            }
        }
    }
}
