
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
        Path path = Paths.get("output\\windspeedObjekte.txt");
        if (Files.notExists(path)) {

            var p = Files.createFile(path);
        }


        System.out.println("Wie viele Geschwindigkeiten wollen Sie erfassen: ");
        int anzahl = scanner.nextInt();
        scanner.nextLine();
        int id = Windgeschwindigkeit.getLastId(path);
        for (int i = 0; i < anzahl; i++) {

            System.out.println("Bitte die Windgeschwindigkeit eingeben: ");
            String speedInput = scanner.nextLine();
            double stundenKilometer = Double.parseDouble(speedInput);
            id++;
            Windgeschwindigkeit windgeschwindigkeit = new Windgeschwindigkeit(id, localDateTime, stundenKilometer);
            windSpeedList.add(windgeschwindigkeit);
            windgeschwindigkeit.writeToFile(path);
            System.out.println();
        }
        System.out.println("Datensätze in Datei windspeedObjekte.txt geschrieben!");
        System.out.println();
        for (int j = 0; j < anzahl; j++) {

            String werteDieserEingabe = windSpeedList.get(j).toString();
            System.out.println(windSpeedList.get(j));
            System.out.println();
        }
    }
}