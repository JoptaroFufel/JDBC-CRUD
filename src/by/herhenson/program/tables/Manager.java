package by.herhenson.program.tables;

public class Manager implements Table {
    private int id;
    private int employee_id;

    public Manager() {
    }

    public Manager(int id, int employee_id) {
        this.id = id;
        this.employee_id = employee_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", employee_id=" + employee_id +
                '}';
    }
}
