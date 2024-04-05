
package com.plouvel.avajlauncher.aircraft;

import com.plouvel.avajlauncher.Coordinate;

public class JetPlane extends Aircraft {

    public JetPlane(long id, String name, Coordinate coordinate) {
        super(id, name, coordinate);
    }

    @Override
    protected void onSnowyWeather() {
        this.coordinate.setHeight(this.coordinate.getHeight() - 7);

        this.weatherTower.broadcastMessageFrom(this, "Snow ? Pff. My supersonic reactor will take care of it.");
    }

    @Override
    protected void onRainyWeather() {
        this.coordinate.setLatitude(this.coordinate.getLatitude() + 5);

        this.weatherTower.broadcastMessageFrom(this, "I can't even see rain on my windshield, i'm too fast !");
    }

    @Override
    protected void onFoggyWeather() {
        this.coordinate.setLatitude(this.coordinate.getLatitude() + 1);

        this.weatherTower.broadcastMessageFrom(this, "Yes.. fog is the only thing that bothers me..");
    }

    @Override
    protected void onSunnyWeather() {
        this.coordinate.setLatitude(this.coordinate.getLatitude() + 10);
        this.coordinate.setHeight(this.coordinate.getLatitude() + 2);

        this.weatherTower.broadcastMessageFrom(this, "Marvelous, clear sky ahead !");
    }
}