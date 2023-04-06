package by.herhenson.program.tables;

public class SVS implements Table {
    private int shipper_id;
    private String shipment_id;
    private int vehicle_id;

    public SVS() {
    }

    public SVS(int shipper_id, String shipment_id, int vehicle_id) {
        this.shipper_id = shipper_id;
        this.shipment_id = shipment_id;
        this.vehicle_id = vehicle_id;
    }

    public int getShipper_id() {
        return shipper_id;
    }

    public void setShipper_id(int shipper_id) {
        this.shipper_id = shipper_id;
    }

    public String getShipment_id() {
        return shipment_id;
    }

    public void setShipment_id(String shipment_id) {
        this.shipment_id = shipment_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    @Override
    public String toString() {
        return "SVS{" +
                "shipper_id=" + shipper_id +
                ", shipment_id='" + shipment_id + '\'' +
                ", vehicle_id=" + vehicle_id +
                '}';
    }
}
