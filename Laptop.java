package com.day20;

public class Laptop {
    private int id;
    private String name;
    private String brand;
    private double price;

    public Laptop(int id, String name, String brand, double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

       public Laptop() {
       }

    void display() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Laptop id: ");
        stringBuilder.append(this.id);
        stringBuilder.append("\n");
        stringBuilder.append("Laptop Name: ");
        stringBuilder.append(this.name);
        stringBuilder.append("\n");
        stringBuilder.append("Laptop Brand: ");
        stringBuilder.append(this.brand);
        stringBuilder.append("\n");
        stringBuilder.append("Laptop Price: ");
        stringBuilder.append(this.price);
        System.out.println(stringBuilder.toString());
    }
}
//Ask user to input the id of laptop and then delete that row
//• Ask user to input the id of laptop, change all the values and then update the
//values.