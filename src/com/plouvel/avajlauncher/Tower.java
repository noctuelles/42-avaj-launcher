package com.plouvel.avajlauncher;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Tower {
    private List<Flyable> observers;
    private List<Flyable> observersToRemove;
    private Boolean isIteratingOverObservers;

    protected PrintStream printStream;

    Tower(PrintStream printStream) {
        this.printStream = printStream;
        this.observers = new ArrayList<>();
        this.observersToRemove = new ArrayList<>();
        this.isIteratingOverObservers = false;
    }

    public final void broadcastMessageFrom(Flyable flyable, String message) {
        this.printStream.println(String.format("%s: %s", flyable.getIdentification(), message));
    }

    public void register(Flyable flyable) {
        this.observers.add(flyable);

        printStream.println("Tower says: " + flyable.getIdentification() + " registered to weather tower.");
    }

    public void unregister(Flyable flyable) {
        if (this.isIteratingOverObservers) {
            this.observersToRemove.add(flyable);
        } else {
            this.observers.remove(flyable);
        }

        printStream.println("Tower says: " + flyable.getIdentification() + " unregistered from weather tower.");
    }

    protected void conditionChanged() {
        this.isIteratingOverObservers = true;

        for (Flyable observer : this.observers) {
            observer.updateConditions();
        }

        this.isIteratingOverObservers = false;

        /*
         * Avoid ConcurrentModificationException : if we delete an observer during the
         * for loop above, an exception of this kind is raised. We're delaying the
         * removal.
         */

        if (this.observersToRemove.isEmpty()) {
            return;
        }

        for (Flyable observerToRemove : this.observersToRemove) {
            this.observers.remove(observerToRemove);
        }

        this.observersToRemove.clear();
    }
}