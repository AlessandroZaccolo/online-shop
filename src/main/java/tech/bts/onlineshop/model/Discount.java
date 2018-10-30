package tech.bts.onlineshop.model;

public class Discount {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String name;
    private double amount;
    private boolean percentage;

    public Discount (String name, double amount, boolean percentage){
        this.id = "";
        this.name = name;
        this.amount = amount;
        this.percentage = percentage;
    }

    public String getName(){
        return name;
    }

    public double getAmount(){
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPercentage(boolean percentage) {
        this.percentage = percentage;
    }
    public boolean isPercentage() {
        return percentage;
    }

}
