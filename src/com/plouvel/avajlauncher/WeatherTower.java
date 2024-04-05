package com.plouvel.avajlauncher;

import java.io.PrintStream;

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