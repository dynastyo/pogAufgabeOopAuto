
import java.util.Scanner;

public class UserInterface {
    static Database data = new Database();
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        data.dummyDaten(40);

       menuMain();

    }

    public static void menuMain() {
        System.out.println("1 - Add car to carpark");
        System.out.println("2 - List all cars");
        System.out.println("3 - Search for a car");
        System.out.println("4 - Delete a car");
        System.out.println("5 - Sort cars by brand");
        System.out.println("Your choice?");
        String auswahl = sc.nextLine();
        switch (auswahl) {
            case "1" -> menuAddCar();
            case "2" -> menuPrintCarpark();
            case "3" -> menuSearchCar();
            case "4" -> menuDeleteCar();
            case "5" -> menuSortCarpark();
            default -> {
                System.out.println("Wrong input, try again!");
                menuMain();
            }
        }
    }

    public static void menuAddCar() {
        if (data.carparkFull()) {
            backToMenu("Carpark is Full!");
        } else {
            System.out.println("Enter ID:");
            String id = sc.nextLine();
            if (!data.idFree(id)) {
                System.out.println("ID already taken.");
                menuAddCar();
            }
            System.out.println("Enter brand:");
            String brand = sc.nextLine();
            System.out.println("Enter model:");
            String model = sc.nextLine();
            double value = Double.parseDouble(addCarAttribute("value","Enter value:", double.class).replace(",","."));
            int topSpeed = Integer.parseInt(addCarAttribute("topSpeed","Enter topSpeed:", int.class));
            boolean unUsed = Boolean.parseBoolean(addCarAttribute("unUsed","Is the car new? true/false:", boolean.class));
            Auto newCar = new Auto(brand, model, id, value, topSpeed, unUsed);
            data.addCar(newCar);
            backToMenu("Car added.");
        }
    }

    public static String addCarAttribute(String attributeName,String syso1, Class<?> type){
        System.out.println(syso1);
        attributeName = sc.nextLine();
        if (type.equals(double.class)) attributeName = attributeName.replace(".",",");
        while (!checkDatatype(type, attributeName)){
            System.out.println("Value must be a " + type +". Try again!"); //edit text
            attributeName = sc.nextLine();
        }
        return attributeName;
    }

    public static boolean checkDatatype(Class<?> type, String value){
        Scanner checkscan = new Scanner(value);
        boolean result = false;
        if (type.equals(int .class)) {
            result = checkscan.hasNextInt();
        } else if (type.equals(boolean.class)) {
            result = checkscan.hasNextBoolean();
        } else if (type.equals(double.class)) {
            result = checkscan.hasNextDouble();
        }
        checkscan.close();
        return result;
    }

    public static void menuPrintCarpark() {
        Auto[] usedCars = data.returnUsedCars();
        for (Auto usedCar : usedCars) {
            System.out.printf("-------------\nID:\t\t\t%s\nBrand:\t\t%s\nModel:\t\t%s\nValue:\t\t%.2f USD\nTop Speed:\t%d MPH\nNew:\t\t%b%n", usedCar.getId(),usedCar.getBrand(),usedCar.getModel(), usedCar.getValue(), usedCar.getTopSpeed(), usedCar.isUsed());

        }
        backToMenu("Those are all " +usedCars.length + " Cars.");
    }

    public static void menuSearchCar() {
        System.out.println("Suchbegriff Eingeben");
        String suchbegriff = UserInterface.sc.nextLine().toLowerCase();
        data.sucheAuto(suchbegriff);
        backToMenu("Found your Car?");
    }

    public static void menuDeleteCar() {
        System.out.println("Enter ID of car to delete!");
        String idToDelete = UserInterface.sc.nextLine().toLowerCase();
        if (data.deleteCar(idToDelete)) backToMenu("The car with ID \""+ idToDelete+"\" got deleted!");
        else backToMenu("ID not found, could not delete the car!");
    }

    public static void menuSortCarpark() {
        data.carparkSort();
        backToMenu("Cars sorted by brand!");
    }

    public static void backToMenu(String info) {
        System.out.println("-------------\n" + info + "\n- - - - - - -\nGoing back to Main Menu\n-------------");
        menuMain();
    }
}