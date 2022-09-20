public class Auto {
private String brand, id;
//model, id
//private double value;
//private int topSpeed;
//private boolean used;

    public Auto() {
    }

//    public Auto(String brand, String model, String id, boolean used) {
//        this.brand = brand;
//        this.model = model;
//        this.id = id;
//        this.used = used;
//    }
    public Auto(String id, String brand) {
        this.brand = brand;
        this.id = id;
    }

    //    public Auto(String brand, String model, String id, double value, int topSpeed, boolean used) {
//        this.brand = brand;
//        this.model = model;
//        this.id = id;
//        this.value = value;
//        this.topSpeed = topSpeed;
//        this.used = used;
//    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public double getValue() {
//        return value;
//    }
//
//    public void setValue(double value) {
//        this.value = value;
//    }
//
//    public int getTopSpeed() {
//        return topSpeed;
//    }
//
//    public void setTopSpeed(int topSpeed) {
//        this.topSpeed = topSpeed;
//    }
//
//    public boolean isUsed() {
//        return used;
//    }
//
//    public void setUsed(boolean used) {
//        this.used = used;
//    }
    @Override
    public String toString() {
        return this.id + " + " + this.brand + " peter";
    }
}
