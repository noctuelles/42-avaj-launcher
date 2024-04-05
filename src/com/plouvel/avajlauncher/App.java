package com.plouvel.avajlauncher;

import com.plouvel.avajlauncher.parser.ParserResult;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.plouvel.avajlauncher.exception.ParsingException;
import com.plouvel.avajlauncher.parser.AircraftLineParsingStrategy;
import com.plouvel.avajlauncher.parser.FileParser;
import java.util.List;

public class App {
    public static void printUsage() {
        System.err.println("Usage : <scenario_file>");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            printUsage();
            System.exit(1);
        }

        try (FileParser parser = new FileParser(args[0], new AircraftLineParsingStrategy())) {
            WeatherTower weatherTower = new WeatherTower(System.out);
            ParserResult parserResult = parser.parse();
            List<Flyable> flyables = new ArrayList<>(parserResult.flyables());

            Collections.shuffle(flyables);

            for (Flyable flyable : flyables) {
                weatherTower.register(flyable);
                flyable.registerTower(weatherTower);
            }

            for (int i = 0; i < parserResult.nbrSimulation(); i++) {
                weatherTower.changeWeather();
            }
        } catch (FileNotFoundException e) {
            System.err.println("An error occured opening file \":" + args[0] + "\" : " + e.getMessage());
        } catch (ParsingException e) {
            System.err.println(
                    "An error occured during the parsing of the file \"" + args[0] + "\":\n\n" + e.getMessage());
        } catch (IOException e) {
            System.err.println("An error occured closing file \":" + args[0] + "\" : " + e.getMessage());
        }
    }
}
