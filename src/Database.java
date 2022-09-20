import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Database {
    private final Auto[] carpark;

    // Array für 50 Auto Objekte
    public Database()  {
        carpark = new Auto[50];
    }

    // Rückgabe aller Datensätze
    public Auto[] rueckgabeAutoliste() {
        int usedCarparkLength = carpark.length;
        for (int i = 0; i < 50; i++) {
            if (carpark[i] == null){
                usedCarparkLength = i;
                break;
            }
        }
        Auto[] usedCarpark = new Auto[usedCarparkLength];
        System.arraycopy(carpark,0, usedCarpark,0,usedCarparkLength);
        return usedCarpark;
    }

    // Hinzufügen eines Autos
    public void hinzufuegenAuto(Auto newCar) {
        for (int i = 0; i < 50; i++) {
            if (carpark[i] == null) {
                carpark[i] = newCar;
                break;
            }
        }
    }

    public void deleteCar(String idToDelete){
        int lastFilledSpot = carpark.length - 1;
        int spotToDelete;
        // find last Filled Spot in carpark Array
        for (int i = 0; i < 50; i++) {
            if (carpark[i] == null){
                lastFilledSpot = i - 1;
                break;
            }
        }
        // find the Spot in carpark Array of the Car to delete and replace it with the last filled spot
        for (int i = 0; i < 50; i++) {
            if (carpark[i] != null) {
                if (idToDelete.equals(carpark[i].getId().toLowerCase())) {
                    carpark[i] = carpark[lastFilledSpot];
                    carpark[lastFilledSpot] = null;
                }
            }
        }
    }

    // Prüfen, ob die id beim erstellen schon vergeben ist
    public boolean idFree(String id) {
        for (int i = 0; i < 50; i++) {
            if (carpark[i] != null) {
                if (carpark[i].getId().equals(id)) {
                    return false;
                }
            }
        }
        return true;
    }

    // suche nach einem String in den Auto Objekten
    public void sucheAuto(String suchbegriff) {
        for (int i = 0; i < 50; i++) {
            if (carpark[i] != null) {
                // String Check
                if (suchbegriff.equals(carpark[i].getBrand().toLowerCase()) || suchbegriff.equals(carpark[i].getId().toLowerCase())) {
                    printCar(i);
                }
            }
        }
    }

    public void printCar(int arraySpot){
        System.out.println("------------");
        System.out.println("ID " + carpark[arraySpot].getId());
        System.out.println("Brand " + carpark[arraySpot].getBrand());
    }

    public void carparkSort(){
        Arrays.sort(carpark, new Comparator<Auto>() {
            @Override
            public int compare(Auto o1, Auto o2) {
                return o1.getBrand().compareTo(o2.getBrand());
            }
        });
        UserInterface.menuMain();
    }

    public void dummyDaten(){
        String brands[] = new String[]{ "BMW","Audi", "VW", "Opel", "Dacia", "Suzuki" };
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            int brand = rand.nextInt(brands.length -1);
            carpark[i] = new Auto(String.valueOf(i), brands[brand]);
        }
    }
}


