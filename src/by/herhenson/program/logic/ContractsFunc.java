package by.herhenson.program.logic;

import java.sql.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Scanner;

import by.herhenson.program.tables.*;

public class ContractsFunc {
    public static Contract addContract(Connection conn,
                                   String name,
                                   String status,
                                   Double total,
                                   Date validity,
                                   Integer hirer_id,
                                   Integer manager_id) {
        Contract contract;
        try {
            CallableStatement cs = conn.prepareCall("INSERT INTO contracts\n" +
                    "\t(name, status, total, validity, hirer_id, manager_id)\n" +
                    "\tVALUES(?, ?, ?, ?, ?, ?);"
            );
            cs.setString(1, name);
            cs.setString(2, status);
            cs.setDouble(3, total);
            cs.setDate(4, validity);
            cs.setInt(5, hirer_id);
            cs.setInt(6, manager_id);
            cs.execute();
            contract = new Contract(name, status, total, validity, hirer_id, manager_id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return contract;
    }

    public static Shipment addShipment(Connection conn,
                                   String name,
                                   String contract_id,
                                   String departure_point,
                                   String destination_point,
                                   Date shipping_date,
                                   Date arrival_date,
                                   String status) {
        Shipment shipment;
        try {
            CallableStatement cs = conn.prepareCall("INSERT INTO shipments\n" +
                    "\t(name, contract_id, departure_point, destination_point, shipping_date, arrival_date, status)\n" +
                    "\tVALUES(?, ?, ?, ?, ?, ?, ?);"
            );
            cs.setString(1, name);
            cs.setString(2, contract_id);
            cs.setString(3, departure_point);
            cs.setString(4, destination_point);
            cs.setDate(5, shipping_date);
            cs.setDate(6, arrival_date);
            cs.setString(7, status);
            cs.execute();
            shipment = new Shipment(name, contract_id, departure_point, destination_point, shipping_date, arrival_date, status);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return shipment;
    }

    public static Hirer addHirer(Connection conn, String fullname, String passport_data, String contact_data){
        Hirer hirer;
        try {
            CallableStatement cs = conn.prepareCall("INSERT INTO hirers\n" +
                    "\t(fullname, passport_data, contact_data)\n" +
                    "\tVALUES(?, ?, ?)\n" +
                    "\tRETURNING id;"
            );
            cs.setString(1, fullname);
            cs.setString(2, passport_data);
            cs.setString(3, contact_data);
            ResultSet rs = cs.executeQuery();
            rs.next();
            hirer = new Hirer(rs.getInt("id"), fullname, passport_data, contact_data);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return hirer;
    }

    public static ArrayList<Hirer> showHirers(Connection conn) {
        ArrayList<Hirer> hirers = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall("SELECT * FROM hirers;");
            ResultSet results = cs.executeQuery();
            while (results.next()) {
                int id = results.getInt("id");
                String fullname = results.getString("fullname");
                String passport_data = results.getString("passport_data");
                String contact_data = results.getString("contact_data");
                hirers.add(new Hirer(id, fullname, passport_data, contact_data));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return hirers;
    }

    public static Place addPlace(Connection conn, String address) {
        Place place;
        try {
            CallableStatement cs = conn.prepareCall("INSERT INTO places\n" +
                    "\t(address)\n" +
                    "\tVALUES(?);"
            );
            cs.setString(1, address);
            cs.execute();
            place = new Place(address);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return place;
    }

    public static ArrayList<Place> showPlaces(Connection conn) {
        ArrayList<Place> places = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall("SELECT * FROM places;");
            ResultSet results = cs.executeQuery();
            while (results.next()) {
                String address = results.getString("address");
                places.add(new Place(address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return places;
    }

    public static Vehicle addVehicle(Connection conn,
                                   String name,
                                   String description,
                                   String status) {
        Vehicle vehicle;
        try {
            CallableStatement cs = conn.prepareCall("INSERT INTO vehicles\n" +
                    "\t(name, description, status)\n" +
                    "\tVALUES(?, ?, ?);"
            );
            cs.setString(1, name);
            cs.setString(2, description);
            cs.setString(3, status);
            cs.execute();
            vehicle = new Vehicle(name, description, status);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return vehicle;
    }

    public static ArrayList<Vehicle> showVehicles(Connection conn) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall("SELECT * FROM vehicles;");
            ResultSet results = cs.executeQuery();
            while (results.next()) {
                String name = results.getString("name");
                String description = results.getString("description");
                String status = results.getString("status");
                vehicles.add(new Vehicle(name, description, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return vehicles;
    }

    public static SVS addSVS(Connection conn,
                             Integer shipper_id,
                             String shipment_id,
                             String vehicle_id) {
        SVS svs;
        try {
            CallableStatement cs = conn.prepareCall("INSERT INTO shippers_vehicles_shipments\n" +
                    "\t(shipper_id, shipment_id, vehicle_id)\n" +
                    "\tVALUES(?, ?, ?);"
            );
            cs.setInt(1, shipper_id);
            cs.setString(2, shipment_id);
            cs.setString(3, vehicle_id);
            cs.execute();
            svs = new SVS(shipper_id, shipment_id, vehicle_id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return svs;
    }

    public static ArrayList<SVS> getSVS(Connection conn, String shipment_id) {
        ArrayList<SVS> svs = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall("SELECT * FROM shippers_vehicles_shipments svs\n" +
                    "\tWHERE svs.shipment_id = ?;");
            cs.setString(1, shipment_id);
            ResultSet results = cs.executeQuery();
            while (results.next()) {
                Integer shipper_id = results.getInt("shipper_id");
                String vehicle_id = results.getString("vehicle_id");
                svs.add(new SVS(shipper_id, shipment_id, vehicle_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return svs;
    }

    public static Vehicle getVehicle(Connection conn, String name) {
        Vehicle vehicle;
        try {
            CallableStatement cs = conn.prepareCall("SELECT * FROM vehicles v\n" +
                    "   WHERE v.name = ?;");
            cs.setString(1, name);
            ResultSet results = cs.executeQuery();
            results.next();
            String description = results.getString("description");
            String status = results.getString("status");
            vehicle = new Vehicle(name, description, status);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return vehicle;
    }

    public static Hirer getHirer(Connection conn, Integer id) {
        Hirer hirer;
        try {
            CallableStatement cs = conn.prepareCall("SELECT * FROM hirers h\n" +
                    "\tWHERE h.id = ?;");
            cs.setInt(1, id);
            ResultSet results = cs.executeQuery();
            results.next();
            String fullname = results.getString("fullname");
            String passport_data = results.getString("passport_data");
            String contact_data = results.getString("contact_data");
            hirer = new Hirer(id, fullname, passport_data, contact_data);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return hirer;
    }

    public static ArrayList<Shipment> getShipment(Connection conn, String contract_id) {
        ArrayList<Shipment> shipments = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall("SELECT * FROM shipments s\n" +
                    "   WHERE s.contract_id = ?;");
            cs.setString(1, contract_id);
            ResultSet results = cs.executeQuery();
            while (results.next()) {
                var name = results.getString("name");
                var departure_point = results.getString("departure_point");
                var destination_point = results.getString("destination_point");
                var shipment_date = results.getDate("shipping_date");
                var arrival_date = results.getDate("arrival_date");
                var status = results.getString("status");
                shipments.add(new Shipment(name, contract_id, departure_point,
                        destination_point, shipment_date, arrival_date, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return shipments;
    }

    public static ArrayList<String> showContracts(Connection conn) {
        ArrayList<String> names = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall("SELECT name FROM contracts svs\n");
            ResultSet results = cs.executeQuery();
            while (results.next()) {
                names.add(results.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

    public static Contract getContract(Connection conn, String name) {
        Contract contract;
        try {
            CallableStatement cs = conn.prepareCall("SELECT * FROM contracts h\n" +
                    "\tWHERE h.name = ?;");
            cs.setString(1, name);
            ResultSet results = cs.executeQuery();
            results.next();
            var status = results.getString("status");
            var total = results.getDouble("total");
            var validity = results.getDate("validity");
            var hirer_id = results.getInt("hirer_id");
            var manager_id = results.getInt("manager_id");
            contract = new Contract(name, status, total, validity, hirer_id, manager_id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return contract;
    }
}
