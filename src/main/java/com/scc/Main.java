package com.scc;


import com.scc.dao.UserDao;
import com.scc.model.user;
import com.scc.ui.AdminMenu;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final UserDao userDao = new UserDao();

    public static void main(String[] args) {

        System.out.println("\n=======================");
        System.out.println("STUDENT RECORDS MANAGEMENT");
        System.out.println("=======================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        String choice = sc.nextLine();

        switch(choice){
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
                String role;
                switch (roleChoice){
                    case 1:
                        role = "CUSTODIAN";
                        break;
                    case 2:
                        role = "STUDENT";
                        break;
                    default:
                        role = null;

                }
                if(role != null){
                    userDao.registerUser(user, pass, role);

                }else{
                    System.out.println("Invalid role Selection!");
                }

                break;



        }
    }

    private static void handleLogin(){
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password");
        String password = sc.nextLine();

        user user = userDao.login(username, password);

        if(user != null){
            System.out.println("\n -> Welcome "+ user.username()+" | ROLE : " +user.roles()+ "");
            switch(user.roles()){
                case "ADMIN" -> new AdminMenu(userDao, sc).display(user);
                default -> System.out.println("Account roles not found!");

            }
        }
    }
}