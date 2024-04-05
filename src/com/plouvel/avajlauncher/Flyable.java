package com.plouvel.avajlauncher;

public abstract class Flyable {
    protected WeatherTower weatherTower;

    public abstract void updateConditions();

    public abstract String getIdentification();

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }
}