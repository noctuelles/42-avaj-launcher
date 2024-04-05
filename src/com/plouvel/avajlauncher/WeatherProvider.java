package com.plouvel.avajlauncher;

import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider instance = null;
    private Random random;

    private WeatherProvider() {
        this.random = new Random();
    }

    public static WeatherProvider getInstance() {
        if (instance == null) {
            instance = new WeatherProvider();
        }

        return instance;
    }

    public WeatherType getCurrentWeather(Coordinate coord) {
        this.random
                .setSeed(coord.getLatitude() * coord.getLongitude() * coord.getHeight() * System.currentTimeMillis());
        return WeatherType.values()[this.random.nextInt(WeatherType.values().length)];
    }
}
