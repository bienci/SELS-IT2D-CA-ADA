package com.scc.ui;

import com.scc.dao.UserDao;
import com.scc.model.user;
import java.util.Scanner;

public class Authentication {

    private final UserDao userDao;
    private final Scanner sc;

    public Authentication(UserDao userDao, Scanner sc) {
        this.userDao = userDao;
        this.sc = sc;
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n=======================");
            System.out.println("SCHOOL SPORTS EQUIPMENT LENDING SYSTEM");
            System.out.println("=======================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    handleLogin();
                    break;
                case "2":
                    System.out.print("Enter new username: ");
                    String user = sc.nextLine();
                    System.out.print("Enter password: ");
                    String pass = sc.nextLine();

                    System.out.println("Select role [1] CUSTODIAN |  [2] ADMIN: ");
                    int roleChoice = sc.nextInt();
                    sc.nextLine(); // consume leftover newline so next sc.nextLine() works cleanly
                    String role;
                    switch (roleChoice) {
                        case 1:
                            role = "CUSTODIAN";
                            break;
                        case 2:
                            role = "ADMIN";
                            break;
                        default:
                            role = null;
                    }
                    if (role != null) {
                        userDao.registerUser(user, pass, role);
                    } else {
                        System.out.println("Invalid role Selection!");
                    }
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void handleLogin() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        user user = userDao.login(username, password);

        if (user != null) {
            System.out.println("\n -> Welcome " + user.username() + " | ROLE : " + user.roles() + "");
            switch (user.roles()) {
                case "ADMIN" -> new AdminMenu(userDao, sc).display(user);
                case "STUDENT" -> new StudentMenu(sc).display(user);
                case "CUSTODIAN" -> new CustodianMenu(sc).display(user);
                default -> System.out.println("Account roles not found!");
            }
        } else {
            System.out.println("Invalid account. Please try again.");
        }
    }
}