package by.herhenson.program.controllers;

import by.herhenson.program.utils.TypeOfMenu;

import java.sql.Connection;
import java.util.Scanner;

public class MainController extends Controller{

    public MainController() {
    }

    @Override
    public TypeOfMenu loop(Scanner scan, Connection conn) {
        TypeOfMenu page = TypeOfMenu.MAIN;
        var escape = false;
        do {
            System.out.print(
                    "\n-----------------------------------------" +
                    "\n<Command list>:" +
                    "\n | 1 - Manage employees." +
                    "\n | 2 - Fill contract." +
                    "\n | 0 - Exit." +
                    "\n-----------------------------------------\n"
            );
            int num = scan.nextInt();
            switch (num){
                case 0:
                    page = TypeOfMenu.EXIT;
                    escape = true;
                    break;
                case 1:
                    page = TypeOfMenu.EMPLOYEES;
                    escape = true;
                    break;
                case 2:
                    page = TypeOfMenu.CONTRACTS;
                    escape = true;
                    break;
                default:
                    break;
            }
        } while(!escape);

        return page;
    }
}
