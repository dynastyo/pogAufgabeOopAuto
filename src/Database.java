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

    // Rückgabe aller Datensätze
    public Auto[] returnUsedCars() {
        Auto[] usedCarpark = new Auto[amountCars];
        System.arraycopy(carpark, 0, usedCarpark, 0, amountCars);
        return usedCarpark;
    }

    // Hinzufügen eines Autos
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

    public void deleteCar(String idToDelete) {

        // find the Spot in carpark Array of the Car to delete and replace it with the last filled spot
        for (int i = 0; i < amountCars; i++) {
            if (carpark[i] != null) {
                if (idToDelete.equals(carpark[i].getId().toLowerCase())) {
                    carpark[i] = carpark[amountCars - 1];
                    carpark[amountCars - 1] = null;
                    amountCars--;
                }
            }
        }
    }


    // suche nach einem String in den Auto Objekten
    public void sucheAuto(String suchbegriff) {
        for (int i = 0; i < amountCars; i++) {
            if (suchbegriff.equals(carpark[i].getBrand().toLowerCase()) || suchbegriff.equals(carpark[i].getId().toLowerCase())) {
                printCar(i);
            }
        }
    }

    public void printCar(int arraySpot) {
        System.out.println("------------");
        System.out.println("ID " + carpark[arraySpot].getId());
        System.out.println("Brand " + carpark[arraySpot].getBrand());
    }

    public void carparkSort() {
        Arrays.sort(carpark, Comparator.comparing(Auto::getBrand));
        UserInterface.menuMain();
    }

    public void dummyDaten() {
        String[] brands = new String[]{"BMW", "Audi", "VW", "Opel", "Dacia", "Suzuki"};
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            int brand = rand.nextInt(brands.length - 1);
            carpark[i] = new Auto(String.valueOf(i + 1), brands[brand]);
            amountCars++;
        }
    }
}


