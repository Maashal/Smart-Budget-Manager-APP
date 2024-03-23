package cs619.project.vu.pbmstp.model;

/**
 * Created by Maria on 8/4/2018.
 */

public class Category {
    private int id;
    private String name;

    public Category(String name) {
        this.name = name;
        this.id=0;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return this.name;
    }
}
