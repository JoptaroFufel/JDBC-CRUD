package by.herhenson.program;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import by.herhenson.program.controllers.*;
import by.herhenson.program.logic.*;
import by.herhenson.program.tables.Employee;
import by.herhenson.program.utils.TypeOfMenu;

public class Main {

    private static final String url = "jdbc:postgresql://localhost/postgres";
    private static final String user = "postgres";
    private static final String password = "1234";

    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        TypeOfMenu typeOfMenu = TypeOfMenu.MAIN;
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            ArrayList<Controller> controllers = new ArrayList<>();
            controllers.add(new MainController());
            controllers.add(new EmployeesController());
            controllers.add(new ContractsController());
            System.out.println("Connected to the PostgreSQL server successfully.");
            while (typeOfMenu != TypeOfMenu.EXIT) {
                typeOfMenu = controllers.get(typeOfMenu.ordinal()).loop(scan, conn);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
