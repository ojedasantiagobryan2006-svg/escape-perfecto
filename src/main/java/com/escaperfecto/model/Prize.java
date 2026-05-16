package com.escaperfecto.model;

public class Prize {
    private final int id;
    private final String name;
    private final int value;
    private final int secondsToTake;
    private final boolean safeEscape;

    public Prize(int id, String name, int value, int secondsToTake, boolean safeEscape) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.secondsToTake = secondsToTake;
        this.safeEscape = safeEscape;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getSecondsToTake() {
        return secondsToTake;
    }

    public boolean isSafeEscape() {
        return safeEscape;
    }

    @Override
    public String toString() {
        return name + " ($" + value + ") - " + secondsToTake + "s";
    }
}

