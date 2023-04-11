package by.herhenson.program.controllers;

import by.herhenson.program.tables.Employee;
import by.herhenson.program.utils.TypeOfMenu;
import by.herhenson.program.logic.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeesController extends Controller {

    public EmployeesController() {
    }

    @Override
    public TypeOfMenu loop(Scanner scan, Connection conn) {
        TypeOfMenu page = TypeOfMenu.EMPLOYEES;
        ArrayList<Employee> employees = new ArrayList<>();
        var escape = false;
        do {
            System.out.print(
                    "\n-----------------------------------------" +
                    "\n<EMPLOYEES>:" +
                    "\n | 1 - Show all employees." +
                    "\n | 2 - Show managers." +
                    "\n | 3 - Show shippers." +
                    "\n | 4 - Add new employee." +
                    "\n | 0 - Back." +
                    "\n-----------------------------------------\n"
            );
            int num = scan.nextInt();
            int i = 1;
            switch (num){
                case 0:
                    escape = true;
                    break;
                case 1:
                    employees = EmployeesFunc.showEmployees(conn);
                    for (Employee e:
                         employees) {
                        System.out.println(i + ". " + e.toString());
                        i++;
                    }
                    System.out.print(
                            "\n-----------------------------------------" +
                            "\n<EMPLOYEES/ALL EMPLOYEES>:" +
                            "\n | 0 - Back." +
                            "\n-----------------------------------------\n"
                    );
                    while (scan.nextInt() != 0) {};
                    break;
                case 2:
                    employees = EmployeesFunc.showManagers(conn);
                    for (Employee e:
                            employees) {
                        System.out.println(i + ". " + e.toString());
                        i++;
                    }
                    System.out.print(
                            "\n-----------------------------------------" +
                                    "\n<EMPLOYEES/MANAGERS>:" +
                                    "\n | 0 - Back." +
                                    "\n-----------------------------------------\n"
                    );
                    while (scan.nextInt() != 0) {};
                    break;
                case 3:
                    employees = EmployeesFunc.showShippers(conn);

                    for (Employee e:
                            employees) {
                        System.out.println(i + ". " + e.toString());
                        i++;
                    }
                    System.out.print(
                            "\n-----------------------------------------" +
                                    "\n<EMPLOYEES/SHIPPERS>:" +
                                    "\n | 0 - Back." +
                                    "\n-----------------------------------------\n"
                    );
                    while (scan.nextInt() != 0) {};
                    break;
                case 4:
                    Employee employee = addingEmployee(scan, conn);
                    if (employee == null) break;
                    System.out.printf(
                            "\n-----------------------------------------" +
                                    "\n<EMPLOYEES/ADD EMPLOYEE/ADD ROLE>:" +
                                    "\n | 1 - Manager." +
                                    "\n | 2 - Shipper." +
                                    "\n | 0 - Back." +
                                    "\n-----------------------------------------\n"
                    );
                    var escape2 = false;
                    while(!escape2) {
                        switch (scan.nextInt()) {
                            case 0:
                                escape2 = true;
                                break;
                            case 1:
                                escape2 = true;
                                EmployeesFunc.addManager(conn, employee);
                                break;
                            case 2:
                                escape2 = true;
                                EmployeesFunc.addShipper(conn, employee);
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                default:
                    break;
            }
        } while(!escape);

        return TypeOfMenu.MAIN;
    }

    public String getStringEmployee(int count) {
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

    public Employee addingEmployee(Scanner scan, Connection conn) {
        ArrayList<String> data = new ArrayList<>();
        var string = "";
        int count = 0;
        var quit = false;
        while(!quit && count < 3) {
            System.out.printf(
                    "\n-----------------------------------------" +
                            "\n<EMPLOYEES/ADD EMPLOYEE>:" +
                            "\n | 0 - Back." +
                            "\n | Type in %s:\n",
                    getStringEmployee(count)
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

        return (quit)?null:EmployeesFunc.addEmployee(conn, data.get(0), data.get(1), data.get(1));
    }
}
