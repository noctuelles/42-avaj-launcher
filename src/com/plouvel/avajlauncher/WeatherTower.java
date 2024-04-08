package com.plouvel.avajlauncher;

import java.io.PrintStream;

import com.plouvel.avajlauncher.parser.Coordinate;

public class WeatherTower extends Tower {
    WeatherTower(PrintStream printStream) {
        super(printStream);
    }

    public WeatherType getWeather(Coordinate coordinate) {
        return WeatherProvider.getInstance().getCurrentWeather(coordinate);
    }

    public void changeWeather() {
        this.conditionChanged();
    }
}