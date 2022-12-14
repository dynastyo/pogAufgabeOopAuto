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
        String choice = sc.nextLine();
        switch (choice) {
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
            String id = addAttribute("Enter ID:", String.class);
            if (!data.idFree(id)) {
                System.out.println("ID already taken.");
                menuAddCar();
            }
            String brand = addAttribute("Enter brand:", String.class);
            String model = addAttribute("Enter model:", String.class);
            // added replace -> , and . can be used as a seperator
            double value = Double.parseDouble(addAttribute("Enter value in USD:", Double.class).replace(",", "."));
            int topSpeed = Integer.parseInt(addAttribute("Enter topSpeed in MPH:", Integer.class));
            boolean unUsed = Boolean.parseBoolean(addAttribute("Is the car new? true/false:", Boolean.class));
            Auto newCar = new Auto(brand, model, id, value, topSpeed, unUsed);
            data.addCar(newCar);
            backToMenu("Car added.");
        }
    }


    public static void menuPrintCarpark() {
        Auto[] cars = data.returnCarArray();
        for (Auto car : cars) {
            printCar(car);
        }
        backToMenu("Those are all " + cars.length + " Cars.");
    }

    public static void menuSearchCar() {
        System.out.println("Search for...");
        System.out.println("1 - ID");
        System.out.println("2 - Brand");
        System.out.println("3 - Model");
        System.out.println("4 - Value");
        System.out.println("5 - Top Speed");
        System.out.println("6 - List all New/Used cars");
        String choice = sc.nextLine();
        switch (choice) {
            case "1" -> menuSearchString("ID");
            case "2" -> menuSearchString("Brand");
            case "3" -> menuSearchString("Model");
            case "4" -> menuSearchInt("Value");
            case "5" -> menuSearchInt("Top Speed");
            case "6" -> menuSearchNewUsed();
            default -> {
                System.out.println("Wrong input, try again!");
                menuSearchCar();
            }
        }
    }


    public static void menuSearchString(String category) {
        String searchString = addAttribute("Enter " + category + " to look for:", String.class).toLowerCase();
        Auto[] cars = data.returnSearchStringArray(category, searchString);
        int foundCars = printCarArray(cars);
        if (foundCars == 0) {
            backToMenu("No cars found");
        } else {
            backToMenu("Those are all " + foundCars + " Cars. I could find with " + searchString + " in " + category + "s");
        }
    }

    public static void menuSearchInt(String category) {
        int maxInt;
        int minInt = Integer.parseInt(addAttribute("Enter minimum:", Integer.class));
        do {
            maxInt = Integer.parseInt(addAttribute("Enter maximum:", Integer.class));
            if (maxInt <= minInt){
                System.out.println("The maximum needs to be bigger than the minimum (" + minInt + ")");
            }
        } while (maxInt <= minInt);
        Auto[] cars = data.returnSearchIntArray(category, minInt, maxInt);
        int foundCars = printCarArray(cars);
        if (foundCars == 0) {
            backToMenu("No cars found");
        } else {
            backToMenu("Those are all " + foundCars + " Cars with a " + category + " between " + minInt + " and " + maxInt + ".");
        }
    }

    public static void menuSearchNewUsed() {
        System.out.println("List new or used cars? N/U");
        String input = sc.nextLine().toLowerCase();
        if (!"n".equals(input) && !"u".equals(input)) {
            System.out.println("Wrong input, try again!");
            menuSearchNewUsed();
        }
        Auto[] cars = data.returnSearchBoolArray(input);
        int foundCars = printCarArray(cars);
        if (foundCars == 0) {
            backToMenu("No cars found");
        } else if ("n".equals(input)) {
            backToMenu("Those are all " + foundCars + " new Cars.");
        } else if ("u".equals(input)) {
            backToMenu("Those are all " + foundCars + " used Cars.");
        }
    }


    public static void menuDeleteCar() {
        System.out.println("Enter ID of car to delete!");
        String idToDelete = UserInterface.sc.nextLine().toLowerCase();
        if (data.deleteCar(idToDelete)) backToMenu("The car with ID \"" + idToDelete + "\" got deleted!");
        else backToMenu("ID not found, could not delete the car!");
    }

    public static void menuSortCarpark() {
        data.carparkSort();
        backToMenu("Cars sorted by brand!");
    }

    public static void printCar(Auto car) {
        System.out.printf("""
                        -------------
                        ID:\t\t\t%s
                        Brand:\t\t%s
                        Model:\t\t%s
                        Value:\t\t%.2f USD
                        Top Speed:\t%d MPH
                        New:\t\t%b%n""",
                        car.getId(), car.getBrand(), car.getModel(), car.getValue(), car.getTopSpeed(), car.isUnUsed());
    }

    public static int printCarArray(Auto[] cars) {
        int foundCars = 0;
        for (Auto car : cars) {
            if (car != null) {
                printCar(car);
                foundCars++;
            }
        }
        return foundCars;
    }

    public static void backToMenu(String info) {
        System.out.println("-------------\n" + info + "\n- - - - - - -\nGoing back to Main Menu\n-------------");
        menuMain();
    }

    public static String addAttribute(String soutBegin, Class<?> type) {
        System.out.println(soutBegin);
        String value = sc.nextLine();
        while (!checkDatatype(type, value)) {
            System.out.println("Value must be a " + type.getSimpleName() + ". Try again!");
            value = sc.nextLine();
        }
        return value;
    }

    public static boolean checkDatatype(Class<?> type, String value) {
        Scanner checkscan = new Scanner(value);
        boolean result = false;
        if (type.equals(Integer.class)) {
            result = checkscan.hasNextInt();
        } else if (type.equals(Boolean.class)) {
            result = checkscan.hasNextBoolean();
        } else if (type.equals(Double.class)) {
            result = checkscan.hasNextDouble();
        } else if (type.equals(String.class)) {
            result = !value.isEmpty();
        }
        checkscan.close();
        return result;
    }
}