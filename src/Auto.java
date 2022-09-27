
public class Auto {
private String brand, id, model;
private double value;
private int topSpeed;
private boolean unUsed;


    public Auto() {
    }

    public Auto(String id, String brand) {
        this.brand = brand;
        this.id = id;
    }

        public Auto(String brand, String model, String id, double value, int topSpeed, boolean unUsed) {
        this.brand = brand;
        this.model = model;
        this.id = id;
        this.value = value;
        this.topSpeed = topSpeed;
        this.unUsed = unUsed;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public boolean isUsed() {
        return unUsed;
    }

    public void setUsed(boolean used) {
        this.unUsed = used;
    }

    public void pirntCar(){
        System.out.printf("%.2f\n", this.value);
    }
    //            return "-------------\nID:\t \t\t" + this.id+ "\nBrand:\t\t" + this.brand+ "\nModel: \t\t" + this.model + "\nValue: \t\t%.2f USD\nTop Speed: \t" + this.topSpeed + " KMH\nNew: \t\t" + this.unUsed, this.value;


}
