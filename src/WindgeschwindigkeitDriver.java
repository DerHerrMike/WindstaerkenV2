
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

        List<Windgeschwindigkeit> windSpeedList = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        Path path = Paths.get("output\\windspeedObjekte5.txt");
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
        if (auswahlUser() == 1) {
            windSpeedList.addAll(readAllLines(path));
            System.out.println();
            for (Windgeschwindigkeit windgeschwindigkeit : windSpeedList) {
                System.out.println(windgeschwindigkeit.toString());
            }
            System.out.println("Keine weiteren Datensätze gespeichert.");
            //hier weiter: alle werte auslesen, in Liste schreiben, sortieren, ausgeben
            List<Double> speedsFromFile = new ArrayList<>();
            for (Windgeschwindigkeit windgeschwindigkeit : windSpeedList) {
                speedsFromFile.add(windgeschwindigkeit.getStundenKilometer());
            }
            System.out.println();
            Collections.sort(speedsFromFile);
            System.out.println();
            System.out.println("Ausgelesene Windgeschwindigkeiten aufsteigend sortiert:");
            System.out.println();
            System.out.println(speedsFromFile);
            System.out.println();
            Collections.sort(speedsFromFile, Collections.reverseOrder());
            System.out.println("Ausgelesene Windgeschwindigkeiten absteigend sortiert:");
            System.out.println();
            System.out.println(speedsFromFile);
            System.out.println();
            System.out.println("--------------------------------------");

            for (Windgeschwindigkeit windgeschwindigkeit : windSpeedList){
                if(windgeschwindigkeit.isWindstill()){
                    System.out.println();
                    System.out.println(windgeschwindigkeit.stringyfy());
                }
            }



            System.exit(0);
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Wie viele Geschwindigkeiten wollen Sie erfassen: ");
        int anzahl = scanner.nextInt();
        scanner.nextLine();
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
        prints();
        // aktuell erfasste Datensätze aus Liste holen
        for (int j = 0; j < anzahl; j++) {
            System.out.println(windSpeedList.get(j));
            System.out.println();
        }
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
//                    System.out.println(line);     // Gibt Daten in Rohfassung aus
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
        System.out.println("Was möchtest du tun? Wähle 1 für das Auslesen der Werte aus der Datei oder 2 um neue Messwerte hinzuzufügen: ");
        int auswahl = scanner.nextInt();
        scanner.nextLine();
        return auswahl;
    }

    public static void prints() {
        System.out.println("Datensätze in Datei geschrieben!");
        System.out.println("------------------------------------------------------");
        System.out.println();
        System.out.println("Abfrage und Ausgabe für aktuelle Werte:");
        System.out.println();
    }



}


