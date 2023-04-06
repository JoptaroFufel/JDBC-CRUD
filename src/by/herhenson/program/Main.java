package by.herhenson.program;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String url = "jdbc:postgresql://localhost/postgres";
    private static final String user = "postgres";
    private static final String password = "1234";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        var EOP = false;
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String menu = "main";
            while (!EOP) {
                switch (menu) {
                    case "main":
                        break;
                    case "employees":
                        break;
                    case "contracts":
                        break;
                    default:
                        EOP = true;
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
