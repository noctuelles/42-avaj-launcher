package com.plouvel.avajlauncher;

import com.plouvel.avajlauncher.parser.ParserResult;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;

import com.plouvel.avajlauncher.exception.ParsingException;
import com.plouvel.avajlauncher.parser.AircraftLineParsingStrategy;
import com.plouvel.avajlauncher.parser.FileParser;

public class App {
    public static void printUsage() {
        System.err.println("""
                Usage : <scenario_file>
                Please refer to the subject document for the file structure.""");
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            printUsage();
            System.exit(1);
        }

        try (FileParser parser = new FileParser(args[0], new AircraftLineParsingStrategy())) {

            WeatherTower weatherTower = new WeatherTower(System.out);
            ParserResult parserResult = parser.parse();

            Collections.shuffle(parserResult.flyables());

            for (Flyable flyable : parserResult.flyables()) {
                weatherTower.register(flyable);
                flyable.registerTower(weatherTower);
            }

            for (int i = 0; i < parserResult.nbrSimulation(); i++) {
                weatherTower.conditionChanged();
            }

        } catch (FileNotFoundException e) {
            System.err.println("Cannot open file : " + e.getMessage());
            System.exit(1);
        } catch (ParsingException e) {

        }
    }
}
