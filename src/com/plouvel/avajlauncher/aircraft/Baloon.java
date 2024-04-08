package com.plouvel.avajlauncher.aircraft;

import com.plouvel.avajlauncher.Coordinate;

public class Baloon extends Aircraft {

    public Baloon(long id, String name, Coordinate coordinate) {
        super(id, name, coordinate);
    }

    @Override
    protected void onSnowyWeather() {
        this.coordinate.setHeight(this.coordinate.getHeight() - 15);

        this.weatherTower.broadcastMessageFrom(this, "At least it's not rain, let's appreciate the white landscape.");
    }

    @Override
    protected void onRainyWeather() {
        this.coordinate.setHeight(this.coordinate.getHeight() - 5);

        this.weatherTower.broadcastMessageFrom(this, "Rain coming everytime at the worst moment!");
    }

    @Override
    protected void onFoggyWeather() {
        this.coordinate.setHeight(this.coordinate.getHeight() - 3);

        this.weatherTower.broadcastMessageFrom(this, "Can't see shit out there !");
    }

    @Override
    protected void onSunnyWeather() {
        this.coordinate.setLongitude(this.coordinate.getLongitude() + 2);
        this.coordinate.setHeight(this.coordinate.getHeight() + 4);

        this.weatherTower.broadcastMessageFrom(this,
                "Damn the sun is shining hard baby ! I hope it doesn't deflate me.");
    }
}