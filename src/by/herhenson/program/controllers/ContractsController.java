package by.herhenson.program.controllers;

import by.herhenson.program.logic.ContractsFunc;
import by.herhenson.program.logic.EmployeesFunc;
import by.herhenson.program.tables.*;
import by.herhenson.program.utils.TypeOfMenu;

import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ContractsController extends Controller {

    public ContractsController() {
    }

    @Override
    public TypeOfMenu loop(Scanner scan, Connection conn) {
        var escape = false;
        do {
            System.out.print(
                    "\n-----------------------------------------" +
                            "\n<CONTRACTS>:" +
                            "\n | 1 - Show all Contracts." +
                            "\n | 2 - Add a new Contract." +
                            "\n | 0 - Back." +
                            "\n-----------------------------------------\n"
            );
            int num = scan.nextInt();
            switch (num){
                case 0:
                    escape = true;
                    break;
                case 1:
                    ArrayList<String> names = ContractsFunc.showContracts(conn);
                    int i = 1;
                    for (String e:
                            names) {
                        System.out.println("\t" + i + ". - " + e);
                        i++;
                    }
                    showChosenContract(scan, conn, names);
                    break;
                case 2:
                    Contract contract = addingContract(scan, conn);
                    if (contract == null) break;
                    int num2;
                    do {
                        System.out.print(
                                "\n-----------------------------------------" +
                                        "\n<CONTRACTS/ADD CONTRACT>:" +
                                        "\n | 1 - Add Shipment." +
                                        "\n | 0 - Back." +
                                        "\n-----------------------------------------\n"
                        );
                        num2 = scan.nextInt();
                        if (num2 == 1) {
                            Shipment shipment = addingShipment(scan, conn, contract.getName());
                            ArrayList<Vehicle> vehicles = ContractsFunc.showVehicles(conn);
                            int num3;
                            do {
                                int j = 2;
                                for (Vehicle v:
                                        vehicles) {
                                    System.out.println("\t" + j + ". - " + v);
                                    j++;
                                }
                                System.out.print(
                                        "\n-----------------------------------------" +
                                                "\n<CONTRACTS/ADD CONTRACT/ADD SHIPMENT/ADD VEHICLE>:" +
                                                "\n | 1 - Add new Vehicle." +
                                                "\n | 0 - Back." +
                                                "\n | Type in Vehicle number to add:\n"
                                );
                                num3 = scan.nextInt();
                                Vehicle vehicle = new Vehicle();
                                if (num3 == 1){
                                    vehicle = addingVehicle(scan, conn);
                                } else if (num3 > 1 && num3 <= vehicles.size()) {
                                    vehicle = vehicles.get(num3 -2);
                                } else if (num3 == 0) {
                                    break;
                                }
                                ArrayList<Employee> employees = EmployeesFunc.showShippers(conn);
                                Employee employee;
                                int num4;
                                do {
                                    int k = 1;
                                    for (Employee e:
                                            employees) {
                                        System.out.println("\t" + k + ". - " + e);
                                        k++;
                                    }
                                    System.out.print(
                                            "\n-----------------------------------------" +
                                                    "\n<CONTRACTS/ADD CONTRACT/ADD SHIPMENT/ADD SHIPMENT/ADD SHIPPER>:" +
                                                    "\n | 0 - Back." +
                                                    "\n | Type in Employee number to add:\n"
                                    );
                                    num4 = scan.nextInt();
                                    if (num4 == 0) {
                                        break;
                                    }
                                    if (num4 > 0 && num4 <= employees.size()) {
                                        employee = employees.get(num4 - 1);
                                        ContractsFunc.addSVS(conn, EmployeesFunc.getShipperID(conn, employee.getId()),
                                                shipment.getName(), vehicle.getName());
                                        break;
                                    }
                                } while(true);

                                if (vehicle == null) break;
                            } while(true);
                        }
                    } while (num2 != 0);
                    break;

                default:
                    break;
            }
        } while(!escape);

        return TypeOfMenu.MAIN;
    }

    public void showChosenContract(Scanner scan, Connection conn, ArrayList<String> names) {
        var escape = false;
        while(!escape) {
            System.out.print(
                    "\n-----------------------------------------" +
                            "\n<CONTRACTS/ALL CONTRACTS>:" +
                            "\n | 0 - Back." +
                            "\n | Type in Contract number to see details:\n"
            );
            int num = scan.nextInt();
            switch (num){
                case 0:
                    escape = true;
                    break;
                default:
                    if (num > 0 || num <= names.size()) {
                        showContractDetails(conn, names.get(num - 1));
                        escape = true;
                        System.out.print(
                                "\n-----------------------------------------" +
                                        "\n<CONTRACTS/ALL CONTRACTS/DETAILS>:" +
                                        "\n | 0 - Back." +
                                        "\n-----------------------------------------\n"
                        );
                        while (scan.nextInt() != 0) {};
                    }
                    break;
            }
        }
    }

    public void showContractDetails(Connection conn, String name) {
        Contract contract = ContractsFunc.getContract(conn, name);
        System.out.println(contract.toString());
        System.out.println("MANAGER: " + EmployeesFunc.getManager(conn, contract.getManager_id()).toString());
        System.out.println(ContractsFunc.getHirer(conn, contract.getHirer_id()).toString());
        for (Shipment s:
             ContractsFunc.getShipment(conn, name)) {
            System.out.println(s.toString());
            for (SVS svs :
                    ContractsFunc.getSVS(conn, s.getName())) {
                System.out.println(ContractsFunc.getVehicle(conn, svs.getVehicle_id()).toString());
                System.out.println("        SHIPPER: "
                        + EmployeesFunc.getShipper(conn, svs.getShipper_id()).toString());
            }

        }
    }

    enum ContractsStrings{
        Name,
        Status,
        Total,
        Validity,
        HirerID,
        ManagerID,
        Ready
    }

    public Contract addingContract(Scanner scan, Connection conn) {
        String name = "";
        String status = "";
        Double total = 0.;
        Date dataTime = new Date(0, 0, 0);
        int hirer_id = -1;
        int manager_id = -1;
        ContractsStrings count = ContractsStrings.Name;
        var quit = false;
        scan.nextLine();
        while(!quit && count != ContractsStrings.Ready) {
            System.out.printf(
                    "\n-----------------------------------------" +
                            "\n<CONTRACTS/ADD CONTRACT>:" +
                            "\n | 0 - Back." +
                            "\n | Type in %s:\n",
                    count.name()
            );
            switch (count.ordinal()) {
                case 0:
                    name = scan.nextLine();
                    if (name.equals("0")) quit = true;
                    count = ContractsStrings.Status;
                    break;
                case 1:
                    status = scan.nextLine();
                    if (status.equals("0")) quit = true;
                    count = ContractsStrings.Total;
                    break;
                case 2:
                    total = scan.nextDouble();
                    if (total.toString().equals("0")) quit = true;
                    count = ContractsStrings.Validity;
                    break;
                case 3:
                    var date = scan.nextLine();
                    if (date.equals("0")) quit = true;
                    else {
                        try {
                            java.util.Date validity = new SimpleDateFormat("dd.MM.yyyy").parse(date);
                            dataTime = new Date(validity.getTime());
                        }
                        catch (ParseException e) {
                            break;
                        }
                    }
                    count = ContractsStrings.HirerID;
                    break;
                case 4:
                    while(true) {
                        int j = 2;
                        ArrayList<Hirer> hirers = ContractsFunc.showHirers(conn);
                        for (Hirer h :
                                hirers) {
                            System.out.println("\t" + j + ". - " + h);
                            j++;
                        }
                        System.out.print(
                                "\n-----------------------------------------" +
                                        "\n<CONTRACTS/ADD CONTRACT/ADD HIRER>:" +
                                        "\n | 1 - Add new Hirer." +
                                        "\n | 0 - Back." +
                                        "\n | Type in Hirer number to add:\n"
                        );
                        int num = scan.nextInt();
                        Hirer hirer = new Hirer();
                        if (num == 1) {
                            hirer = addingHirer(scan, conn);
                        } else if (num > 1 && num <= hirers.size()) {
                            hirer = hirers.get(num - 2);
                        } else if (num == 0) {
                            break;
                        }
                        if (hirer == null) {
                            quit = true;
                            break;
                        } else {
                            hirer_id = hirer.getId();
                            count = ContractsStrings.ManagerID;
                            break;
                        }
                    }
                    break;
                default:
                    ArrayList<Employee> employees = EmployeesFunc.showManagers(conn);
                    Employee employee = new Employee();
                    int num;
                    do {
                        int k = 1;
                        for (Employee e:
                                employees) {
                            System.out.println("\t" + k + ". - " + e);
                            k++;
                        }
                        System.out.print(
                                "\n-----------------------------------------" +
                                        "\n<CONTRACTS/ADD CONTRACT/ADD MANAGER>:" +
                                        "\n | 0 - Back." +
                                        "\n | Type in Employee number to add:\n"
                        );
                        num = scan.nextInt();
                        if (num == 0) {
                            break;
                        }
                        if (num > 0 && num <= employees.size()) {
                            employee = employees.get(num - 1);
                            break;
                        }
                    } while(true);
                    manager_id = employee.getId();
                    if (manager_id == 0) quit = true;
                    count = ContractsStrings.Ready;
                    break;
            }
        }

        return (quit)?null:ContractsFunc.addContract(conn, name, status, total, dataTime,
                hirer_id, EmployeesFunc.getManagerID(conn,manager_id));
    }

    enum ShipmentsStrings{
        Name,
        DeparturePoint,
        DestinationPoint,
        ShippingDate,
        ArrivalDate,
        Status,
        Ready
    }

    public Shipment addingShipment(Scanner scan, Connection conn, String contract_id) {
        String name = "";
        String departure_point = "";
        String destination_point = "";
        Date shippingDataTime = new Date(0, 0, 0);
        Date arrivalDataTime = new Date(0, 0, 0);
        String status = "";
        ShipmentsStrings count = ShipmentsStrings.Name;
        var quit = false;
        scan.nextLine();
        while(!quit && count != ShipmentsStrings.Ready) {
            System.out.printf(
                    "\n-----------------------------------------" +
                            "\n<CONTRACTS/ADD CONTRACT/ADD SHIPMENT>:" +
                            "\n | 0 - Back." +
                            "\n | Type in %s:\n",
                    count.name()
            );
            switch (count.ordinal()) {
                case 0:
                    name = scan.nextLine();
                    if (name.equals("0")) quit = true;
                    count = ShipmentsStrings.DeparturePoint;
                    break;
                case 1:
                    departure_point = scan.nextLine();
                    if (name.equals("0")) quit = true;
                    count = ShipmentsStrings.DestinationPoint;
                    ContractsFunc.addPlace(conn, departure_point);
                    break;
                case 2:
                    destination_point = scan.nextLine();
                    if (name.equals("0")) quit = true;
                    count = ShipmentsStrings.ShippingDate;
                    ContractsFunc.addPlace(conn, destination_point);
                    break;
                case 3:
                    var date = scan.nextLine();
                    if (date.equals("0")) quit = true;
                    else {
                        try {
                            java.util.Date validity = new SimpleDateFormat("dd.MM.yyyy").parse(date);
                            shippingDataTime = new java.sql.Date(validity.getTime());
                        }
                        catch (ParseException e) {
                            break;
                        }
                    }
                    count = ShipmentsStrings.ArrivalDate;
                    break;
                case 4:
                    date = scan.nextLine();
                    if (date.equals("0")) quit = true;
                    else {
                        try {
                            java.util.Date validity = new SimpleDateFormat("dd.MM.yyyy").parse(date);
                            arrivalDataTime = new java.sql.Date(validity.getTime());
                        }
                        catch (ParseException e) {
                            break;
                        }
                    }
                    count = ShipmentsStrings.Status;
                    break;
                default:
                    status = scan.nextLine();
                    if (status.equals("0")) quit = true;
                    count = ShipmentsStrings.Ready;
                    break;
            }
        }

        return (quit)?null:ContractsFunc.addShipment(conn, name, contract_id, departure_point, destination_point,
                shippingDataTime, arrivalDataTime, status);
    }

    enum VehicleStrings{
        Name,
        Description,
        Status,
        Ready
    }

    public Vehicle addingVehicle(Scanner scan, Connection conn) {
        String name = "";
        String description = "";
        String status = "";
        VehicleStrings count = VehicleStrings.Name;
        var quit = false;
        scan.nextLine();
        while(!quit && count != VehicleStrings.Ready) {
            System.out.printf(
                    "\n-----------------------------------------" +
                            "\n<CONTRACTS/ADD CONTRACT/ADD SHIPMENT/ADD VEHICLE>:" +
                            "\n | 0 - Back." +
                            "\n | Type in %s:\n",
                    count.name()
            );
            switch (count.ordinal()) {
                case 0:
                    name = scan.nextLine();
                    if (name.equals("0")) quit = true;
                    count = VehicleStrings.Description;
                    break;
                case 1:
                    status = scan.nextLine();
                    if (status.equals("0")) quit = true;
                    count = VehicleStrings.Status;
                    break;
                default:
                    name = scan.nextLine();
                    if (name.equals("0")) quit = true;
                    count = VehicleStrings.Ready;
                    break;
            }
        }

        return (quit)?null:ContractsFunc.addVehicle(conn, name, description, status);
    }

    public String getStringHirer(int count) {
        String output = "";

        switch (count) {
            case 0:
                output = "Fullname";
                break;
            case 1:
                output = "Passport Data";
                break;
            case 2:
                output = "Contact Data";
                break;
            default:
                break;
        }

        return output;
    }

    public Hirer addingHirer(Scanner scan, Connection conn) {
        ArrayList<String> data = new ArrayList<>();
        var string = "";
        int count = 0;
        var quit = false;
        scan.nextLine();
        while(!quit && count < 3) {
            System.out.printf(
                    "\n-----------------------------------------" +
                            "\n<CONTRACTS/ADD CONTRACT/ADD HIRER>:" +
                            "\n | 0 - Back." +
                            "\n | Type in %s:\n",
                    getStringHirer(count)
            );
            do {
                string = scan.nextLine();
                if (string.equals("0")) {
                    quit = true;
                    break;
                }
            } while (count == 0 && string.isEmpty());
            if (!quit) data.add(string);
            count++;
        }

        return (quit)?null:ContractsFunc.addHirer(conn, data.get(0), data.get(1), data.get(1));
    }
}
