
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;


public class Windgeschwindigkeit extends WindDaten {

    private final int id;
    private final double stundenKilometer;

    // Konstruktor
    public Windgeschwindigkeit(int id, LocalDateTime localDateTime, double stundenKilometer) {
        super(localDateTime);
        this.stundenKilometer = stundenKilometer;
        this.id = id;
    }

    private String convert() {
        return id +
                ";" +
                this.getZeitpunkt() +
                ";" +
                this.stundenKilometer +
                "\n";
    }


    public void writeToFile(Path path) throws IOException {
        String object = convert();

        if (Files.notExists(path)) {
            Files.createFile(path);
        }

        Files.write(
                path,
                object.getBytes(),
                StandardOpenOption.APPEND);
    }


    public static int getLastId(Path path) throws IOException {

        if (Files.size(path) < 1) {
            return 0;
        } else {
            List<String> lines = Files.readAllLines(path);
            //letzten Eintrag holen
            String line = lines.get(lines.size() - 1);
            //Elemente der Zeile in ein String Array
            String[] lastEntry = line.split(";");
            //in int parsen
            return Integer.parseInt(lastEntry[0]);
        }

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
        return ">>  Zeitpunkt der Messung: " + getZeitpunkt() + ". Geschwindigkeit in verschiedenen Einheiten = "
                + getStundenKilometer() + " km/h || " + getKnoten() + " Knoten. Das bedeutet Wert "
                + getBeaufort() + " auf der Beaufort Skala. Somit ergibt sich für 'ist windstill' = "
                + isWindstill() + ", und für 'ist ein Orkan' = " + isOrkan() + "  <<";
    }

}