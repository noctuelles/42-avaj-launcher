package com.plouvel.avajlauncher.aircraft;

import com.plouvel.avajlauncher.Flyable;
import com.plouvel.avajlauncher.WeatherType;
import com.plouvel.avajlauncher.parser.Coordinate;

public abstract class Aircraft extends Flyable {
    protected long id;
    protected String name;
    protected Coordinate coordinate;

    protected Aircraft(long id, String name, Coordinate coordinate) {
        this.id = id;
        this.name = name;
        this.coordinate = coordinate;
    }

    public final String getIdentification() {
        return this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + ")";
    }

    @Override
    public final void updateConditions() {
        WeatherType weatherType = this.weatherTower.getWeather(this.coordinate);

        switch (weatherType) {
        case FOG:
            this.onFoggyWeather();
            break;
        case RAIN:
            this.onRainyWeather();
            break;
        case SNOW:
            this.onSnowyWeather();
            break;
        case SUN:
            this.onSunnyWeather();
            break;
        }

        if (this.coordinate.getHeight() <= 0) {
            this.weatherTower.broadcastMessageFrom(this, "landing.");
            this.weatherTower.unregister(this);
        }
    }

    protected abstract void onSnowyWeather();

    protected abstract void onRainyWeather();

    protected abstract void onFoggyWeather();

    protected abstract void onSunnyWeather();
}