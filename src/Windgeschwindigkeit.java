import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Windgeschwindigkeit extends WindDaten {

    private double stundenKilometer = 0.02f;
    private double knoten;
    private int beaufort;

    private boolean orkan;
    private boolean windstill;
    private Scanner scanner = new Scanner(System.in);


    // Konstruktor
    public Windgeschwindigkeit(int id, LocalDateTime localDateTime, double stundenKilometer) {
        super(localDateTime);
        this.stundenKilometer = stundenKilometer;
    }

    public static void main(String[] args) {
        List<Windgeschwindigkeit> windSpeedList = new ArrayList<>();
        Scanner scanner1 = new Scanner(System.in);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("Wie viele Geschwindigkeiten wollen Sie erfassen: ");
        int anzahl = scanner1.nextInt();
        for (int i = 0; i < anzahl; i++) {
            System.out.println("Bitte die Windgeschwindigkeit eingeben: ");
            double speed = scanner1.nextDouble();
            windSpeedList.add(new Windgeschwindigkeit(i, localDateTime, speed));
        }
        for (int j = 0; j < anzahl; j++) {
            System.out.println(windSpeedList.get(j));
        }
    }

    private double readSpeed() {

        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Falsche Eingabe! Bitte Integer oder Double eingeben:");
                scanner.nextLine();
            }
        }
    }

    // Getter
    public double getStundenKilometer() {
        return stundenKilometer;
    }

    public double getKnoten() {

        double knoten = Math.round((((stundenKilometer / 1.852)) * 100.00) / 100);

        return knoten;
    }

    public int getBeaufort() {
        beaufort = (int) (Math.pow(getStundenKilometer() / 3.01, 0.6666) + 0.5);
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
        return " Zeitpunkt der Messung: " + getZeitpunkt() + ". Geschwindigkeit in verschiedenen Einheiten = " + getStundenKilometer() + " km/h || " + getKnoten() + " Knoten. Das bedeutet Wert " + getBeaufort() + " auf der Beaufort Skala. Somit ergibt sich für 'ist windstill' = " + isWindstill() + ", und für 'ist ein Orkan' = " + isOrkan();
    }


    // Overrides
    @Override
    public void addWindgeschwindigkeit(Windgeschwindigkeit windgeschwindigkeit) {
        super.addWindgeschwindigkeit(windgeschwindigkeit);
    }

    @Override
    public ArrayList<Windgeschwindigkeit> getWindSpeedValuesList() {
        return super.getWindSpeedValuesList();
    }

    @Override
    public void dummyDaten() {
        super.dummyDaten();
    }

    @Override
    public String getReadableTimestamp() {
        return super.getReadableTimestamp();
    }

}

