import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Windgeschwindigkeit {

    private double stundenKilometer = 0.02f;
    double knoten;
    int beaufort;
    int id = 1;
    boolean isOrkan;
    boolean isWindstill;
    private List<Windgeschwindigkeit> gesammelteWerte = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    // Konstruktor
    public Windgeschwindigkeit(double stundenKilometer, int id) {
        this.stundenKilometer = stundenKilometer;
        this.id = id;

    }

    // Getter
    public double getStundenKilometer() {
        return stundenKilometer;
    }

    public double getKnoten() {
        return stundenKilometer / 1.852;
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

    @Override
    public String toString() {
        return "Windgeschwindigkeit" + getStundenKilometer() + " km/h, " + getKnoten() + " Knoten. Das bedeutet Wert " + getBeaufort() + " auf der Beaufort Skala. Somit 'ist windstill'="+isWindstill()+", und 'ist ein Orkan'="+isOrkan();
    }


    public void setStundenKilometer(double stundenKilometer) {
        this.stundenKilometer = stundenKilometer;
    }


    public static void main(String[] args) {
        double wert = 0;
        int idNr = 1;
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Wie viele Geschwindigkeiten wollen Sie erfassen: ");
        int anzahl = scanner1.nextInt();
        for (int i = 1; i <= anzahl + 1; i++) {
            System.out.println("Bitte die " + i + ". Windgeschwindigkeit eingeben: ");
            wert = scanner1.nextDouble();
            List<Windgeschwindigkeit> gesammelteWerte = new ArrayList<>();
            gesammelteWerte.add(new Windgeschwindigkeit(wert, i));
        }

        Windgeschwindigkeit wg1 = new Windgeschwindigkeit(wert, idNr);
        double kmh = wg1.getStundenKilometer();
        double beaufort = wg1.getBeaufort();
        float knoten = (float) wg1.getKnoten();
        boolean orkan = wg1.isOrkan();
        boolean windstill = wg1.isWindstill();
        System.out.println(wg1.toString());
        System.out.println("-----------------------");
        System.out.println("Geschwindigkeit " + idNr + ": in km/h: " + kmh);
        System.out.println("Geschwindigkeit " + idNr + ": in Beaufort: " + beaufort);
        System.out.println("Geschwindigkeit " + idNr + ": in Knoten: " + knoten);
        System.out.println("Geschwindigkeit " + idNr + ": ist ein Orkan: " + orkan);
        System.out.println("Geschwindigkeit " + idNr + ": ist windstill: " + windstill);

//        Windgeschwindigkeit rennen = new Windgeschwindigkeit(0,1);
//        rennen.anzahlWerte();
//        rennen.addWerte(rennen.anzahlWerte());
//        System.out.println(rennen.addWerte(rennen.anzahlWerte()));

    }

    private int anzahlWerte() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Wie viele Geschwindigkeiten wollen Sie erfassen: ");
        return scanner1.nextInt();
    }

    private List addWerte(int anzahl) {
        int i = 0;
        while (i < anzahl) {
            System.out.println("Bitte die Windgeschwindigkeit eingeben: ");
            double speed = readSpeed();
            gesammelteWerte.add(new Windgeschwindigkeit(readSpeed(), getId()));
            ++i;
        }
        return gesammelteWerte;
    }

    private double readSpeed() {

        while (true) {
            try {
                return scanner.nextFloat();
            } catch (InputMismatchException e) {
                System.out.println("Falsche Eingabe! Bitte Integer oder Double eingeben:");
                scanner.nextLine();
            }
        }
    }

    public int getId() {
        return id;
    }
}
