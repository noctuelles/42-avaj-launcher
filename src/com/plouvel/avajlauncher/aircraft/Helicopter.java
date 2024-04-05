package com.plouvel.avajlauncher.aircraft;

import com.plouvel.avajlauncher.Coordinate;

public class Helicopter extends Aircraft {

    public Helicopter(long id, String name, Coordinate coordinate) {
        super(id, name, coordinate);
    }

    @Override
    protected void onSnowyWeather() {
        this.coordinate.setHeight(this.coordinate.getHeight() - 12);

        this.weatherTower.broadcastMessageFrom(this, "Ah snow... my favorite weather. Too bad i can see nothing.");
    }

    @Override
    protected void onRainyWeather() {
        this.coordinate.setLongitude(this.coordinate.getLongitude() + 5);

        this.weatherTower.broadcastMessageFrom(this, "Well rain can't touch me because of my propeller, ahah !");
    }

    @Override
    protected void onFoggyWeather() {
        this.coordinate.setLongitude(this.coordinate.getLongitude() + 1);

        this.weatherTower.broadcastMessageFrom(this,
                "Woof, i must be carefull avoiding the mountain in front of me with this fog.");
    }

    @Override
    protected void onSunnyWeather() {
        this.coordinate.setLongitude(this.coordinate.getLongitude() + 10);
        this.coordinate.setHeight(this.coordinate.getHeight() + 2);

        this.weatherTower.broadcastMessageFrom(this, "Magnificent sun. Can't wait to land and bike around.");
    }
}