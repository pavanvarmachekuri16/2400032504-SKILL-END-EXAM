package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import java.util.Date;
import java.util.Scanner;

/**
 * ClientDemo Class
 * Demonstrates Insert and Update operations on Vehicle entity
 */
public class ClientDemo {

    private static SessionFactory sessionFactory;

    /**
     * Initialize SessionFactory
     */
    private static void initializeSessionFactory() {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Vehicle.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Error initializing SessionFactory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Insert a new Vehicle record into the database
     * 
     * @param vehicle Vehicle object to be inserted
     * @return vehicleId of the inserted record
     */
    public static Integer insertVehicle(Vehicle vehicle) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer vehicleId = null;

        try {
            transaction = session.beginTransaction();
            vehicleId = (Integer) session.save(vehicle);
            transaction.commit();
            System.out.println("Vehicle inserted successfully with ID: " + vehicleId);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error inserting vehicle: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }

        return vehicleId;
    }

    /**
     * Update Vehicle fields (Name and Status) by ID
     * 
     * @param vehicleId ID of the vehicle to update
     * @param name New name for the vehicle
     * @param status New status for the vehicle
     */
    public static void updateVehicle(Integer vehicleId, String name, String status) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Vehicle vehicle = session.get(Vehicle.class, vehicleId);

            if (vehicle != null) {
                vehicle.setName(name);
                vehicle.setStatus(status);
                session.update(vehicle);
                transaction.commit();
                System.out.println("Vehicle updated successfully!");
                System.out.println("Updated Vehicle Details: " + vehicle);
            } else {
                System.out.println("Vehicle with ID " + vehicleId + " not found!");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error updating vehicle: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Retrieve a Vehicle by ID
     * 
     * @param vehicleId ID of the vehicle to retrieve
     * @return Vehicle object or null if not found
     */
    public static Vehicle getVehicleById(Integer vehicleId) {
        Session session = sessionFactory.openSession();
        Vehicle vehicle = null;

        try {
            vehicle = session.get(Vehicle.class, vehicleId);
            if (vehicle != null) {
                System.out.println("Vehicle retrieved: " + vehicle);
            } else {
                System.out.println("Vehicle with ID " + vehicleId + " not found!");
            }
        } catch (Exception e) {
            System.err.println("Error retrieving vehicle: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }

        return vehicle;
    }

    /**
     * Close SessionFactory
     */
    private static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    /**
     * Main method to demonstrate Insert and Update operations
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Initialize SessionFactory
        initializeSessionFactory();

        try {
            while (true) {
                System.out.println("\n========== Vehicle Management System ==========");
                System.out.println("1. Insert a new Vehicle");
                System.out.println("2. Update Vehicle (Name and Status)");
                System.out.println("3. Retrieve Vehicle by ID");
                System.out.println("4. Exit");
                System.out.print("Enter your choice (1-4): ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        insertVehicleUI(scanner);
                        break;
                    case 2:
                        updateVehicleUI(scanner);
                        break;
                    case 3:
                        retrieveVehicleUI(scanner);
                        break;
                    case 4:
                        System.out.println("Exiting the application...");
                        return;
                    default:
                        System.out.println("Invalid choice! Please enter a number between 1 and 4.");
                }
            }
        } finally {
            scanner.close();
            closeSessionFactory();
        }
    }

    /**
     * UI for inserting a vehicle
     */
    private static void insertVehicleUI(Scanner scanner) {
        System.out.println("\n--- Insert New Vehicle ---");
        
        System.out.print("Enter Vehicle Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        
        System.out.print("Enter Manufacturer: ");
        String manufacturer = scanner.nextLine();
        
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        
        System.out.print("Enter Year: ");
        Integer year = scanner.nextInt();
        
        System.out.print("Enter Price: ");
        Double price = scanner.nextDouble();
        
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Status (e.g., Available, Sold, In Service): ");
        String status = scanner.nextLine();

        Vehicle vehicle = new Vehicle(name, description, new Date(), status, manufacturer, model, year, price);
        insertVehicle(vehicle);
    }

    /**
     * UI for updating a vehicle
     */
    private static void updateVehicleUI(Scanner scanner) {
        System.out.println("\n--- Update Vehicle ---");
        
        System.out.print("Enter Vehicle ID to update: ");
        Integer vehicleId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter new Vehicle Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter new Status: ");
        String status = scanner.nextLine();

        updateVehicle(vehicleId, name, status);
    }

    /**
     * UI for retrieving a vehicle
     */
    private static void retrieveVehicleUI(Scanner scanner) {
        System.out.println("\n--- Retrieve Vehicle ---");
        
        System.out.print("Enter Vehicle ID: ");
        Integer vehicleId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        getVehicleById(vehicleId);
    }
}
