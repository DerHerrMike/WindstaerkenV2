import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

        int id = Windgeschwindigkeit.getLastId(path);

        System.out.println("Wie viele Geschwindigkeiten wollen Sie erfassen: ");
        int anzahl = scanner.nextInt();
        scanner.nextLine(); //muss IMMER nach einem nextInt() etc gemacht werden. sonst kann es zu komische nebenwirkungen kommen.

        for (int i = 0; i < anzahl; i++) {
            System.out.println("Bitte die Windgeschwindigkeit eingeben: ");
            //double speed = scanner.nextDouble();
            String speedInput = scanner.nextLine();
            double stundenKilometer = Double.parseDouble(speedInput);
            id++;
            Windgeschwindigkeit windgeschwindigkeit = new Windgeschwindigkeit(id, localDateTime, stundenKilometer);
            windSpeedList.add(windgeschwindigkeit);
            windgeschwindigkeit.writeToFile(path);
        }

    }


}
