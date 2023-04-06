package by.herhenson.program.tables;

public class Good {
    private int id;
    private String name;
    private int amount;
    private String unit_id;
    private String contract_id;

    public Good() {
    }

    public Good(int id, String name, int amount, String unit_id, String contract_id) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.unit_id = unit_id;
        this.contract_id = contract_id;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

    public String getContract_id() {
        return contract_id;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", unit_id='" + unit_id + '\'' +
                ", contract_id='" + contract_id + '\'' +
                '}';
    }
}
