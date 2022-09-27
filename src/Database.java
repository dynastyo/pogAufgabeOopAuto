import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Database {
    private final Auto[] carpark;
    private int amountCars = 0;

    // Array für 50 Auto Objekte
    public Database() {
        carpark = new Auto[50];
    }

    public boolean carparkFull() {
        return amountCars >= 50;
    }

    public boolean idFree(String id) {
        for (int i = 0; i < amountCars; i++) {
            if (carpark[i].getId().equals(id)) return false;
        }
        return true;
    }

    public void addCar(Auto newCar) {
        carpark[amountCars] = newCar;
        amountCars++;
    }

    public Auto[] returnUsedCars() {
        Auto[] usedCarpark = new Auto[amountCars];
        System.arraycopy(carpark, 0, usedCarpark, 0, amountCars);
        return usedCarpark;
    }

    // suche nach einem String in den Auto Objekten
    public void sucheAuto(String suchbegriff) {
        for (int i = 0; i < amountCars; i++) {
            if (suchbegriff.equals(carpark[i].getBrand().toLowerCase()) || suchbegriff.equals(carpark[i].getId().toLowerCase())) {
                System.out.println("hiho");
            }
        }
    }

    public boolean deleteCar(String idToDelete) {
        for (int i = 0; i < amountCars; i++) {
            if (idToDelete.equals(carpark[i].getId().toLowerCase())) {
                carpark[i] = carpark[amountCars - 1];
                carpark[amountCars - 1] = null;
                amountCars--;
                return true;
            }
        }
        return false;
    }

    public void carparkSort() {
        Arrays.sort(carpark, Comparator.comparing(Auto::getBrand));
    }

    public void dummyDaten(int amountOfCars) {
        String[] brands = new String[]{"BMW", "Audi", "VW", "Opel", "Dacia", "Suzuki"};
        String[] models = new String[]{"500", "A7", "Corsa", "3", "Tiguan", "F240", "Diabolo"};
        Random rand = new Random();
        for (int i = 0; i < amountOfCars; i++) {
            int brand = rand.nextInt(brands.length - 1);
            int model = rand.nextInt(models.length - 1);
            double value = Math.round(rand.nextDouble(500, 100000) *100)/100.00;
            int topspeed = rand.nextInt(80, 270);
            boolean unUsed = rand.nextBoolean();
            carpark[i] = new Auto(brands[brand],models[model],String.valueOf(i + 1),value, topspeed, unUsed );
            amountCars++;
        }
    }
}


