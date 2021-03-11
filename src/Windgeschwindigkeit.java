import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Windgeschwindigkeit extends WindDaten implements Serializable {

    private final int id;
    String uuid = UUID.randomUUID().toString();
    private final double stundenKilometer;


    // Konstruktor
    public Windgeschwindigkeit(int id, LocalDateTime localDateTime, double stundenKilometer) {
        super(localDateTime);
        this.stundenKilometer = stundenKilometer;
        this.id = id;
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

    private String convert() {
        String convertedString =
                id +
                ";" +
                this.getZeitpunkt() +
                ";" +
                this.stundenKilometer+
                "\n";
        return convertedString;
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
        List<String> lines = Files.readAllLines(path);
        //get last entry
        String line = lines.get(lines.size() - 1);
        //6;11.03.2021 10:59:46;4.44
        String[] lastEntry = line.split(";");
        //cconvert to int
        String idFromFile = lastEntry[0];
        return Integer.parseInt(idFromFile);
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

