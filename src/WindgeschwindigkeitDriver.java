
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WindgeschwindigkeitDriver {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException {

        List<Windgeschwindigkeit> windSpeedList = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        Path path = Paths.get("output\\windspeedObjekte1.txt");
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
        if (auswahlUser() == 1) {
            windSpeedList.addAll(readAllLines(path));


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

    public static List<Windgeschwindigkeit> readAllLines(Path path) throws IOException {

        BufferedReader reader;
        List<Windgeschwindigkeit> allLines = new ArrayList<>();

        if (Files.size(path) < 1) {
            System.out.println("return null");
            return null;
        } else {
            System.out.println("L76");

            try {
                reader = new BufferedReader(new FileReader("output\\windspeedObjekte1.txt"));
                String line = reader.readLine();
                while (line != null) {
                    String[] ausgeleseneZeile = line.split(";");
                    //ID
                    int idf = Integer.parseInt(ausgeleseneZeile[0]);
                    //TIMESTAMP
//                    Pattern p = Pattern.compile("[\\d]*[^\\d]+([\\d]+)");
                    String ldtf = ausgeleseneZeile[1];
//                    String.valueOf(p.matcher(line));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(ldtf, formatter);
                    //SPEED
//                  Pattern p1 = Pattern.compile("[\\d]*[^\\d]+[\\d]*[^\\d]+([\\d]+)");
                    double speedf = Double.parseDouble(ausgeleseneZeile[2]);
                    Windgeschwindigkeit object = new Windgeschwindigkeit(idf, dateTime, speedf);
                    allLines.add(object);
                    System.out.println(line);
                    // read next line
                    line = reader.readLine();
                }
                System.out.println("Werte der Datei wurden in ArrayList 'allLines' geschrieben");
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return allLines;

    }

//    public static void auslesen() {
//        System.out.println();
//        System.out.println("Auslesen der bestehenden Datei:");
//        System.out.println();
//        BufferedReader reader;
//
//        try {
//            reader = new BufferedReader(new FileReader("output\\windspeedObjekte1.txt"));
//            String line = reader.readLine();
//            while (line != null) {
//                System.out.println(line);
//                // read next line
//                line = reader.readLine();
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static int auswahlUser() {        //BUG doppelte Abfrage ??

        System.out.println("Was möchtest du tun? Wähle 1 für das Auslesen der Werte aus der Datei oder 2 um neue Messwerte hinzuzufügen: ");
        int auswahl = scanner.nextInt();
        scanner.nextLine();
        return auswahl;
    }

    public static void prints() {
        System.out.println("Datensätze in Datei windspeedObjekte1.txt geschrieben!");
        System.out.println("------------------------------------------------------");
        System.out.println();
        System.out.println("Abfrage und Ausgabe für aktuelle Werte:");
        System.out.println();
    }
}


