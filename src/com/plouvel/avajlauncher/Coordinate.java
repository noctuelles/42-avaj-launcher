package com.plouvel.avajlauncher;

public class Coordinate {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinate(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }

    public void setLongitude(int longitude) {
        if (longitude < 0) {
            this.longitude = 0;
        } else {
            this.longitude = longitude;
        }
    }

    public void setLatitude(int latitude) {
        if (latitude < 0) {
            this.latitude = 0;
        } else {
            this.latitude = latitude;
        }
    }

    public void setHeight(int height) {
        if (height < 0) {
            this.height = 0;
        } else {
            if (height > 100) {
                this.height = 100;
            } else {
                this.height = height;
            }
        }
    }
}