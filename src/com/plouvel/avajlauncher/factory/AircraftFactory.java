package com.plouvel.avajlauncher.factory;

import com.plouvel.avajlauncher.Flyable;
import com.plouvel.avajlauncher.aircraft.Baloon;
import com.plouvel.avajlauncher.aircraft.Helicopter;
import com.plouvel.avajlauncher.aircraft.JetPlane;
import com.plouvel.avajlauncher.exception.InvalidAircraftType;
import com.plouvel.avajlauncher.parser.Coordinate;

public class AircraftFactory {
    private static long idSeq = 0;

    public static Flyable newAircraft(String type, String name, Coordinate coordinate) throws InvalidAircraftType {
        Flyable flyable = null;

        switch (type) {
        case "Helicopter":
            flyable = new Helicopter(idSeq, name, coordinate);
            break;
        case "JetPlane":
            flyable = new JetPlane(idSeq, name, coordinate);
            break;
        case "Baloon":
            flyable = new Baloon(idSeq, name, coordinate);
            break;
        default:
            throw new InvalidAircraftType(type);
        }

        idSeq++;
        return flyable;
    }
}