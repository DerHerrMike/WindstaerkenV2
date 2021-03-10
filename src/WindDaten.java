import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public abstract class WindDaten {

    protected LocalDateTime localDateTime = LocalDateTime.now();
    protected ArrayList<Windgeschwindigkeit> windSpeedValuesList = new ArrayList<>();


    // Konstruktor

    public WindDaten(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }


    // Methoden

    public void addWindgeschwindigkeit(Windgeschwindigkeit windgeschwindigkeit) {
        this.windSpeedValuesList.add(windgeschwindigkeit);
    }

    public ArrayList<Windgeschwindigkeit> getWindSpeedValuesList() {
        return windSpeedValuesList;
    }

    public LocalDateTime getZeitpunkt() {
        return localDateTime;
    }

    public void dummyDaten() {
        createDummyDaten();
    }

    private void createDummyDaten() {
        Instant instant;
        ZoneId zone;
        LocalDateTime local = LocalDateTime.now();
    }

    public String getReadableTimestamp() {

        LocalDateTime windTime = LocalDateTime.now();
        System.out.println(windTime.toLocalDate());

        var format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String timeStamp = windTime.format(format);
        System.out.println(timeStamp);

        return timeStamp;
    }
}


