package com.plouvel.avajlauncher;

import com.plouvel.avajlauncher.parser.ParserResult;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

import com.plouvel.avajlauncher.exception.ParsingException;
import com.plouvel.avajlauncher.parser.AircraftLineParsingStrategy;
import com.plouvel.avajlauncher.parser.FileParser;
import java.util.List;

public class App {
    public static String outFilename = "simulation.txt";

    public static void printUsage() {
        System.err.println("Usage : <scenario_filename>");
    }

    public static void main(String[] args) {
        int returnCode = 0;

        if (args.length != 1) {
            printUsage();
            System.exit(1);
        }

        try (FileParser parser = new FileParser(args[0], new AircraftLineParsingStrategy());
                PrintStream printStream = new PrintStream(new FileOutputStream(outFilename, false))) {
            WeatherTower weatherTower = new WeatherTower(printStream);
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
            System.err.println("An error occured opening file \"" + args[0] + "\" : " + e.getMessage());
            returnCode = 1;
        } catch (ParsingException e) {
            System.err.println(
                    "An error occured during the parsing of the file \"" + args[0] + "\":\n\n" + e.getMessage());
            returnCode = 1;
        } catch (IOException e) {
            System.err.println("An error I/O error occured \"" + args[0] + "\" : " + e.getMessage());
            returnCode = 1;
        }

        if (returnCode != 0) {
            System.exit(returnCode);
        }
    }
}
