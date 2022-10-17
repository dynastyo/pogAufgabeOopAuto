import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Database {
    // declaring the carPark array
    private final Auto[] carPark;
    // int value to keep track of the amount of cars in the carPark array
    private int amountCars = 0;

    // constructor for the carpark array with a length of 50
    public Database() {
        carPark = new Auto[50];
    }
    //test if the carpark array is full
    public boolean carparkFull() {
        return amountCars >= 50;
    }

    //check if an ID for an Auto object is free/taken
    public boolean idFree(String id) {
        for (int i = 0; i < amountCars; i++) {
            if (carPark[i].getId().equals(id)) return false;
        }
        return true;
    }

    //add a new Auto object to the carPark array at the first spot that is null
    public void addCar(Auto newCar) {
        carPark[amountCars] = newCar;
        amountCars++;
    }

    //return an array of Auto objects containing all Auto objects of carPark that are not null
    public Auto[] returnCarArray() {
        Auto[] cars = new Auto[amountCars];
        System.arraycopy(carPark, 0, cars, 0, amountCars);
        return cars;
    }

    public Auto[] returnSearchIntArray(String category, int minInt, int maxInt){
        Auto[] foundCars = new Auto[amountCars];
        for (int i = 0; i < amountCars; i++) {
            if(carPark[i].getValue() >= minInt && carPark[i].getValue() <= maxInt && category.equals("Value")){
                foundCars[i] = carPark[i];
            } else if(carPark[i].getTopSpeed() >= minInt && carPark[i].getTopSpeed() <= maxInt && category.equals("Top Speed")){
                foundCars[i] = carPark[i];
            }
        }
        return foundCars;
    }
    public Auto[] returnSearchStringArray(String category, String searchString){
        Auto[] foundCars = new Auto[amountCars];
        for (int i = 0; i < amountCars; i++) {
            if (carPark[i].getBrand().toLowerCase().contains(searchString) && category.equals("Brand")) {
                foundCars[i] = carPark[i];
            } else if (carPark[i].getModel().toLowerCase().contains(searchString) && category.equals("Model")) {
                foundCars[i] = carPark[i];
            } else if (carPark[i].getId().toLowerCase().contains(searchString) && category.equals("ID")) {
                foundCars[i] = carPark[i];
            }
        }
        return foundCars;
    }

    public Auto[] returnSearchBoolArray(String newUsed){
        Auto[] foundCars = new Auto[amountCars];
        for (int i = 0; i < amountCars; i++) {
            if (carPark[i].isUnUsed() && newUsed.equals("n")) {
                foundCars[i] = carPark[i];
            } else if (carPark[i].isUnUsed() && newUsed.equals("u")) {
                foundCars[i] = carPark[i];
            }
        }
        return foundCars;
    }

    //remove a specific Auto object by overwriting it with the last object that is not null.
    //the auto object is chosen by ID value, if it does not exist the method will return false
    public boolean deleteCar(String idToDelete) {
        for (int i = 0; i < amountCars; i++) {
            if (idToDelete.equals(carPark[i].getId().toLowerCase())) {
                carPark[i] = carPark[amountCars - 1];
                carPark[amountCars - 1] = null;
                amountCars--;
                return true;
            }
        }
        return false;
    }

    //sort the carPark array by the brand key
    public void carparkSort() {
        Arrays.sort(carPark,0,amountCars, Comparator.comparing(Auto::getBrand));
    }

    //fill the carpark array with randomized Auto objects
    public void dummyDaten(int amountOfCars) {
        String[] brands = new String[]{"BMW", "Audi", "VW", "Opel", "Dacia", "Subaru", "Suzuki"};
        String[] models = new String[]{"500", "A7", "Corsa", "3", "Tiguan", "F240", "Diabolo"};
        Random rand = new Random();
        for (int i = 0; i < amountOfCars; i++) {
            int brand = rand.nextInt(brands.length);
            int model = rand.nextInt(models.length);
            double value = Math.round(rand.nextDouble(500, 100000) *100)/100.00;
            int topspeed = rand.nextInt(80, 270);
            boolean unUsed = rand.nextBoolean();
            carPark[i] = new Auto(brands[brand],models[model],String.valueOf(i + 1),value, topspeed, unUsed );
            amountCars++;
        }
    }
}


