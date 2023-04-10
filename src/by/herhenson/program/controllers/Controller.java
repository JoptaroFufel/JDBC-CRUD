package by.herhenson.program.controllers;

import by.herhenson.program.utils.TypeOfMenu;

import java.sql.Connection;
import java.util.Scanner;

public abstract  class Controller {

    public abstract TypeOfMenu loop(Scanner scan, Connection conn);
}
