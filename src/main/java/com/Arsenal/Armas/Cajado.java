package com.Arsenal.Armas;

public class Cajado extends Arma {
    private double cura;

    public Cajado(String n, double a, double d, double c) {
        super(n, a, d);
        this.cura = c;
    }

    public double getCura() {
        return this.cura;
    }
}
