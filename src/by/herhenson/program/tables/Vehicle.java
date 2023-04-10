package by.herhenson.program.tables;

public class Vehicle implements Table {
    private String name;
    private String description;
    private String status;

    public Vehicle() {
    }

    public Vehicle(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "    VEHICLE: " + name +
                "\n        DESCRIPTION: " + description +
                "\n        STATUS:      " + status;
    }
}
