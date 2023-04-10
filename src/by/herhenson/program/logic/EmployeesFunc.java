package by.herhenson.program.logic;

import java.sql.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Scanner;

import by.herhenson.program.tables.Employee;

public class EmployeesFunc {
    public static Employee addEmployee(Connection conn, String fullname, String passport_data, String contact_data){
        Employee employee;
        try {
            CallableStatement cs = conn.prepareCall("INSERT INTO employees\n" +
                    "\t(fullname, passport_data, contact_data)\n" +
                    "\tVALUES(?, ?, ?)\n" +
                    "\tRETURNING id;"
            );
            cs.setString(1, fullname);
            cs.setString(2, passport_data);
            cs.setString(3, contact_data);
            ResultSet rs = cs.executeQuery();
            rs.next();
            employee = new Employee(rs.getInt("id"), fullname, passport_data, contact_data);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return employee;
    }

    public static ArrayList<Employee> showEmployees(Connection conn) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall("SELECT * FROM employees;");
            ResultSet results = cs.executeQuery();
            while (results.next()) {
                int id = results.getInt("id");
                String fullname = results.getString("fullname");
                String passport_data = results.getString("passport_data");
                String contact_data = results.getString("contact_data");
                employees.add(new Employee(id, fullname, passport_data, contact_data));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static void addManager(Connection conn, Employee employee) {
        try {
            CallableStatement cs = conn.prepareCall("INSERT INTO managers \n" +
                    "\t(employee_id)\n" +
                    "\tVALUES(?);");
            cs.setInt(1, employee.getId());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addShipper(Connection conn, Employee employee) {
        try {
            CallableStatement cs = conn.prepareCall("INSERT INTO shippers \n" +
                    "\t(employee_id)\n" +
                    "\tVALUES(?);");
            cs.setInt(1, employee.getId());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Employee> showManagers(Connection conn) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall("SELECT * \n" +
                    "    FROM employees e\n" +
                    "        INNER JOIN managers mng ON e.id = mng.employee_id\n" +
                    "\tORDER BY e.id;");
            ResultSet results = cs.executeQuery();
            while (results.next()) {
                int id = results.getInt("employee_id");
                String fullname = results.getString("fullname");
                String passport_data = results.getString("passport_data");
                String contact_data = results.getString("contact_data");
                employees.add(new Employee(id, fullname, passport_data, contact_data));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static ArrayList<Employee> showShippers(Connection conn) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall("SELECT * \n" +
                    "    FROM employees e\n" +
                    "        INNER JOIN shippers mng ON e.id = mng.employee_id\n" +
                    "\tORDER BY e.id;");
            ResultSet results = cs.executeQuery();
            while (results.next()) {
                int id = results.getInt("employee_id");
                String fullname = results.getString("fullname");
                String passport_data = results.getString("passport_data");
                String contact_data = results.getString("contact_data");
                employees.add(new Employee(id, fullname, passport_data, contact_data));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static Employee getManager(Connection conn, Integer id) {
        Employee employee;
        try {
            CallableStatement cs = conn.prepareCall("SELECT * FROM employees e\n" +
                    "\tINNER JOIN managers mng ON e.id = mng.employee_id\n" +
                    "\tWHERE mng.id = ?;");
            cs.setInt(1, id);
            ResultSet results = cs.executeQuery();
            results.next();
            int employee_id = results.getInt("employee_id");
            String fullname = results.getString("fullname");
            String passport_data = results.getString("passport_data");
            String contact_data = results.getString("contact_data");
            employee = new Employee(employee_id, fullname, passport_data, contact_data);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return employee;
    }

    public static Employee getShipper(Connection conn, Integer id) {
        Employee employee;
        try {
            CallableStatement cs = conn.prepareCall("SELECT * FROM employees e\n" +
                    "\tINNER JOIN shippers mng ON e.id = mng.employee_id\n" +
                    "\tWHERE mng.id = ?;");
            cs.setInt(1, id);
            ResultSet results = cs.executeQuery();
            results.next();
            int employee_id = results.getInt("employee_id");
            String fullname = results.getString("fullname");
            String passport_data = results.getString("passport_data");
            String contact_data = results.getString("contact_data");
            employee = new Employee(employee_id, fullname, passport_data, contact_data);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return employee;
    }

    public static int getShipperID(Connection conn, Integer id) {
        try {
            CallableStatement cs = conn.prepareCall("SELECT s.id FROM shippers s\n" +
                    "\tINNER JOIN employees e ON s.employee_id = e.id\n" +
                    "\tWHERE e.id = ?;");
            cs.setInt(1, id);
            ResultSet results = cs.executeQuery();
            results.next();
            return results.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getManagerID(Connection conn, Integer id) {
        try {
            CallableStatement cs = conn.prepareCall("SELECT s.id FROM managers s\n" +
                    "\tINNER JOIN employees e ON s.employee_id = e.id\n" +
                    "\tWHERE e.id = ?;");
            cs.setInt(1, id);
            ResultSet results = cs.executeQuery();
            results.next();
            return results.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
