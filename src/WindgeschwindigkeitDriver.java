
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class WindgeschwindigkeitDriver {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException {

        List<Double> speedsFromFile = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        Path path = Paths.get("output\\windspeedObjekte5.txt");
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
        List<Windgeschwindigkeit> windSpeedList = new ArrayList<>(Objects.requireNonNull(readAllLines(path)));
        switch (auswahlUser()) {
            case 1 -> {

                System.out.println("Ausgabe der Werte aus Datei:");
                for (Windgeschwindigkeit windgeschwindigkeit : windSpeedList) {
                    System.out.println(windgeschwindigkeit.toString());
                }
                System.out.println("Keine weiteren Datensätze gespeichert.");
                System.out.println();
                System.out.println("--- Ende der Ausgabe erreicht! ---");
            }
            case 2 -> {
                for (Windgeschwindigkeit windgeschwindigkeit : windSpeedList) {
                    speedsFromFile.add(windgeschwindigkeit.getStundenKilometer());
                }
                ausgabeSortiert(speedsFromFile);
                System.out.println();
                System.out.println("--- Ende der Ausgabe erreicht! ---");
                System.exit(0);

            }
            case 3 -> {
                for (Windgeschwindigkeit windgeschwindigkeit : windSpeedList) {
                    ausgabeWindstill(windgeschwindigkeit, speedsFromFile);
                }
                System.out.println();
                System.out.println("--- Ende der Ausgabe erreicht! ---");
                System.exit(0);
            }
            case 4 -> {
                System.out.println();
                System.out.println("Wie viele Geschwindigkeiten wollen Sie erfassen: ");
                int anzahl = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                int id = Windgeschwindigkeit.getLastId(path);
                for (int i = 0; i < anzahl; i++) {
                    System.out.println("Bitte die " + (i + 1) + ". Windgeschwindigkeit eingeben: ");
                    String speedInput = scanner.nextLine();             // unsauber noch
                    double stundenKilometer = Double.parseDouble(speedInput);
                    id++;
                    Windgeschwindigkeit windgeschwindigkeit = new Windgeschwindigkeit(id, localDateTime, stundenKilometer);
                    windSpeedList.add(windgeschwindigkeit);
                    windgeschwindigkeit.writeToFile(path);
                    System.out.println();
                }
                prints(anzahl, windSpeedList, path);
            }
            default -> throw new IllegalStateException("Unexpected value: " + auswahlUser() + ". Bitte Zahl von 1 - 4 eingeben!");

        }
    }

    private static void ausgabeWindstill(Windgeschwindigkeit windgeschwindigkeit, List<Double> windSpeedList) {

        if (windgeschwindigkeit.isWindstill()) {
            System.out.println();
            System.out.println(windgeschwindigkeit.stringyfy());
        }
    }

    private static void ausgabeSortiert(List<Double> listname) {
        System.out.println();
        Collections.sort(listname);
        System.out.println();
        System.out.println("Ausgelesene Windgeschwindigkeiten aufsteigend sortiert:");
        System.out.println();
        System.out.println(listname);
        System.out.println();
        listname.sort(Collections.reverseOrder());
        System.out.println("Ausgelesene Windgeschwindigkeiten absteigend sortiert:");
        System.out.println();
        System.out.println(listname);
        System.out.println();
        System.out.println("-------------");
    }

    public static List<Windgeschwindigkeit> readAllLines(Path path) throws IOException {

        BufferedReader reader;
        List<Windgeschwindigkeit> allLines = new ArrayList<>();

        if (Files.size(path) < 1) {
            System.out.println("return null");
            return null;
        } else {

            try {
                reader = new BufferedReader(new FileReader(String.valueOf(path)));
                String line = reader.readLine();
                while (line != null) {
                    String[] ausgeleseneZeile = line.split(";");
                    //ID
                    int idf = Integer.parseInt(ausgeleseneZeile[0]);
                    //TIMESTAMP
                    String ldtf = ausgeleseneZeile[1];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(ldtf, formatter);
                    //SPEED
                    double speedf = Double.parseDouble(ausgeleseneZeile[2]);
                    Windgeschwindigkeit object = new Windgeschwindigkeit(idf, dateTime, speedf);
                    allLines.add(object);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return allLines;
    }

    public static int auswahlUser() {

        System.out.println();
        System.out.println("Programm zur Verwaltung von gemessenen Windgeschwindigkeiten: Was möchtest du tun?");
        System.out.println();
        System.out.println("1 für Auslesen der Werte aus der Datei, 2 um Werte sortiert auszugeben, 3 zur Ausgabe der Werte 'windstill', oder 4 um neue Werte zu erfassen: ");
        int auswahl = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return auswahl;
    }

    public static void prints(int anzahl, List<Windgeschwindigkeit> windSpeedList, Path path) {
        System.out.println("Datensätze wurden in Datei ..\\" + path + " geschrieben!");
        System.out.println();
        System.out.println("Abfrage und Ausgabe für aktuelle Werte:");
        System.out.println();
        for (int j = windSpeedList.size() - anzahl; j < windSpeedList.size(); j++) {
            System.out.println(windSpeedList.get(j));
        }
    }
}


