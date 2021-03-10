import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Windgeschwindigkeit extends WindDaten {

    private final double stundenKilometer;


    // Konstruktor
    public Windgeschwindigkeit(int id, LocalDateTime localDateTime, double stundenKilometer) {
        super(localDateTime);
        this.stundenKilometer = stundenKilometer;
    }


    // Getter
    public double getStundenKilometer() {
        return stundenKilometer;
    }

    public double getKnoten() {

        return (double) Math.round((((stundenKilometer / 1.852)) * 100.00) / 100);
    }

    public int getBeaufort() {
        int beaufort = (int) (Math.pow(getStundenKilometer() / 3.01, 0.6666) + 0.5);
        if (beaufort > 12) {
            beaufort = 12;
        }
        return beaufort;
    }

    public boolean isOrkan() {
        return getStundenKilometer() >= 120.0;
    }

    public boolean isWindstill() {
        return getStundenKilometer() < 2.0;
    }

    // ToString
    @Override
    public String toString() {
        return "Zeitpunkt der Messung: " + getZeitpunkt() + ". Geschwindigkeit in verschiedenen Einheiten = "
                + getStundenKilometer() + " km/h || " + getKnoten() + " Knoten. Das bedeutet Wert "
                + getBeaufort() + " auf der Beaufort Skala. Somit ergibt sich für 'ist windstill' = "
                + isWindstill() + ", und für 'ist ein Orkan' = " + isOrkan();
    }


}

