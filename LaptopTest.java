package com.day20;

import java.sql.*;
import java.util.Scanner;

public class LaptopTest {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private static int option = 0;
    private static char doAgain = 'y';

    public LaptopTest() {
        try {
            DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
            connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb", "SA", "");
        } catch (Exception e) {
            System.out.println("Something is wrong during jdbc...");
        }
    }

    public static void main(String[] args) {
        LaptopTest laptopTest = new LaptopTest();
        Scanner scanner = new Scanner(System.in);

        while (doAgain == 'y' || doAgain == 'Y') {
            System.out.println("Please select your options:\nOption 1: Insert\nOption 2: Display All\n" +
                    "Option 3: Delete\nOption 4: Update\nOption 5: Exit\n");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    laptopTest.insert();
                    break;
                case 2:
                    laptopTest.displayAll();
                    break;
                case 3:
                    laptopTest.delete();
                    break;
                case 4:
                    laptopTest.update();
                    break;
                case 5:
                    System.out.println("Thank you for using this program, good bye...");
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    public void insert() {
        try {
            Scanner scanner = new Scanner(System.in);
            Laptop laptop = new Laptop();
            for (int i = 0; i < 2; i++) {
                System.out.println("Enter laptop id: ");
                laptop.setId(scanner.nextInt());
                scanner.nextLine();

                System.out.println("Enter laptop name: ");
                laptop.setName(scanner.nextLine());

                System.out.println("Enter laptop brand: ");
                laptop.setBrand(scanner.nextLine());

                System.out.println("Enter laptop price");
                laptop.setPrice(scanner.nextDouble());
                scanner.nextLine();

                preparedStatement = connection.prepareStatement("INSERT INTO LAPTOP VALUES (?,?,?,?)");

                preparedStatement.setInt(1, laptop.getId());
                preparedStatement.setString(2, laptop.getName());
                preparedStatement.setString(3, laptop.getBrand());
                preparedStatement.setDouble(4, laptop.getPrice());

                int rows = preparedStatement.executeUpdate();
                System.out.println("Rows inserted : " + rows);
                System.out.println("Do you want to continue with any query?\n: " +
                        "enter a \'y \' for another query\n " +
                        " enter a \'n\' to end the program\n");

                doAgain = scanner.nextLine().charAt(0);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void displayAll() {
        try {
            Scanner scanner = new Scanner(System.in);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM LAPTOP");

            //5th process the output if required
            while (resultSet.next()) {
                System.out.println("LAPTOP ID : " + resultSet.getInt(1));
                System.out.println("LAPTOP NAME : " + resultSet.getString(2));
                System.out.println("LAPTOP BRAND : " + resultSet.getString(3));
                System.out.println("LAPTOP PRICE : " + resultSet.getDouble(4));
                System.out.println("-----------------------------");
            }
            System.out.println("Do you want to continue with any query?\n " +
                    "Enter a \'y \' for another query\n " +
                    "Enter a \'n\' to end the program\n");

            doAgain = scanner.nextLine().charAt(0);
        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
    }

    public void delete() {
        try {
            Scanner scanner = new Scanner(System.in);
            preparedStatement = connection.prepareStatement("DELETE FROM LAPTOP  WHERE ID = ?");

            scanner = new Scanner(System.in);
            System.out.println("Laptop ID : ");
            int iD = scanner.nextInt();
            scanner.nextLine();

            preparedStatement.setInt(1, iD);  //data must come from UI /html form

            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows DELETED : " + rows);

            System.out.println("Do you want to continue with any query?\n: " +
                    "enter a \'y \' for another query\n " +
                    " enter a \'n\' to end the program\n");
            doAgain = scanner.nextLine().charAt(0);
        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
    }

    public void update() {
        try {
            Scanner scanner = new Scanner(System.in);
            Laptop laptop = new Laptop();
            preparedStatement = connection.prepareStatement("UPDATE LAPTOP SET NAME=?, BRAND=?,PRICE=? WHERE ID=?");

            System.out.println("Enter laptop id to update: ");
            laptop.setId(scanner.nextInt());
            scanner.nextLine();

            System.out.println("Enter new laptop name: ");
            laptop.setName(scanner.nextLine());

            System.out.println("Enter new laptop brand: ");
            laptop.setBrand(scanner.nextLine());

            System.out.println("Enter new laptop price");
            laptop.setPrice(scanner.nextDouble());
            scanner.nextLine();

            preparedStatement.setString(1, laptop.getName());
            preparedStatement.setString(2, laptop.getBrand());
            preparedStatement.setDouble(3, laptop.getPrice());
            preparedStatement.setInt(4, laptop.getId());

            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows updated : " + rows);
            System.out.println("Do you want to continue with any query?\n: " +
                    "enter a \'y \' for another query\n " +
                    " enter a \'n\' to end the program\n");
            doAgain = scanner.nextLine().charAt(0);
        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
    }
}