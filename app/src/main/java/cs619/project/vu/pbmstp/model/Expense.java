package cs619.project.vu.pbmstp.model;

/**
 * Created by Maria on 8/4/2018.
 */

public class Expense {
    private int id;
    private String category;
    private String name;
    private double amount;
    private String date;
    private String remarks;

    public Expense(int id, String category, String name, double amount, String date, String remarks) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.remarks = remarks;
    }

    public Expense(String category, String name, double amount, String date, String remarks) {
        this.category = category;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
