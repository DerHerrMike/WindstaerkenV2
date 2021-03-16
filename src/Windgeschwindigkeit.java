
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class Windgeschwindigkeit extends WindDaten implements List<Windgeschwindigkeit> {

    final int id;
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

    public String stringyfy() {
        return "Zeitpunkt der Messung " + getId() + ": " + getZeitpunkt() + System.getProperty("line.separator") +
                getStundenKilometer() + " km/h. Die Bedingung 'windstill' (< 2 km/h) ist erfüllt.";
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

    public int getId() {
        return id;
    }

    // ToString

    @Override
    public String toString() {
        return "Zeitpunkt der Messung " + getId() + ": " + getZeitpunkt() + System.getProperty("line.separator") +
                getStundenKilometer() + " km/h || " + getKnoten() + " Knoten. " +
                "Das bedeutet Wert " + getBeaufort() + " auf der Beaufort Skala. Dies ergibt für 'ist windstill' = "
                + isWindstill() + ", & für 'ist ein Orkan' = " + isOrkan() +
                System.getProperty("line.separator");

    }


    //  Overrides
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @NotNull
    @Override
    public Iterator<Windgeschwindigkeit> iterator() {
        return null;
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return null;
    }

    @Override
    public boolean add(Windgeschwindigkeit windgeschwindigkeit) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends Windgeschwindigkeit> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends Windgeschwindigkeit> c) {
        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Windgeschwindigkeit get(int index) {
        return null;
    }

    @Override
    public Windgeschwindigkeit set(int index, Windgeschwindigkeit element) {
        return null;
    }

    @Override
    public void add(int index, Windgeschwindigkeit element) {

    }

    @Override
    public Windgeschwindigkeit remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @NotNull
    @Override
    public ListIterator<Windgeschwindigkeit> listIterator() {
        return null;
    }

    @NotNull
    @Override
    public ListIterator<Windgeschwindigkeit> listIterator(int index) {
        return null;
    }

    @NotNull
    @Override
    public List<Windgeschwindigkeit> subList(int fromIndex, int toIndex) {
        return null;
    }
}