
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class WindgeschwindigkeitDriver {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException {

        List<Windgeschwindigkeit> windSpeedList = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        Path path = Paths.get("output\\windspeedObjekte1.txt");
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
        auswahlUser();
        if (!auswahlUser()) {
            auslesen();
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
            String speedInput = scanner.nextLine();
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

    public static boolean auswahlUser() {        //BUG doppelte Abfrage ??

        System.out.println("Was möchtest du tun? Wähle 1 für das Auslesen der Werte aus der Datei oder 2 um neue Messwerte hinzuzufügen: ");
        int auswahl = scanner.nextInt();
        scanner.nextLine();
        return auswahl != 1;
    }

    public static void auslesen() {
        System.out.println();
        System.out.println("Auslesen der bestehenden Datei:");
        System.out.println();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("output\\windspeedObjekte1.txt"));
            String line = reader.readLine();
            while (line != null) {

                System.out.println(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void prints (){
        System.out.println("Datensätze in Datei windspeedObjekte1.txt geschrieben!");
        System.out.println("------------------------------------------------------");
        System.out.println();
        System.out.println("Abfrage und Ausgabe für aktuelle Werte:");
        System.out.println();
    }
}


