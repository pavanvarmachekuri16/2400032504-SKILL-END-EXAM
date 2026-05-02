# FSAD End Exam - Hibernate Vehicle Project

## Project Overview
This is a Maven-based Hibernate project that demonstrates CRUD operations on a Vehicle entity. The project implements:
- **Insert Operation**: Add new vehicle records to the database
- **Update Operation**: Modify vehicle name and status by ID
- **Retrieve Operation**: Fetch vehicle details by ID

## Project Structure
```
├── src/
│   ├── main/
│   │   ├── java/com/klef/fsad/exam/
│   │   │   ├── Vehicle.java
│   │   │   └── ClientDemo.java
│   │   └── resources/
│   │       ├── hibernate.cfg.xml
│   │       └── log4j.properties
│   └── test/java/
├── pom.xml
└── README.md
```

## Classes

### 1. Vehicle Entity Class (Vehicle.java)
A persistent entity class representing a vehicle record in the database.

**Attributes:**
- `vehicleId` - Auto-generated primary key (Integer)
- `name` - Vehicle name (String, not null)
- `description` - Vehicle description (String)
- `date` - Date of entry (java.util.Date)
- `status` - Vehicle status (Available, Sold, In Service, etc.)
- `manufacturer` - Vehicle manufacturer name (String)
- `model` - Vehicle model name (String)
- `year` - Manufacturing year (Integer)
- `price` - Vehicle price (Double)

**Features:**
- Uses JPA annotations for mapping
- Auto-generated ID using `GenerationType.IDENTITY`
- Implements Serializable interface
- Includes toString() method for easy debugging

### 2. ClientDemo Class (ClientDemo.java)
A client class demonstrating Hibernate operations on the Vehicle entity.

**Key Methods:**
- `insertVehicle(Vehicle vehicle)` - Inserts a new vehicle record
- `updateVehicle(Integer vehicleId, String name, String status)` - Updates vehicle name and status
- `getVehicleById(Integer vehicleId)` - Retrieves vehicle by ID
- Interactive menu-driven main() method

## Setup Instructions

### Prerequisites
- Java 11 or higher
- MySQL Server
- Maven

### Database Setup
1. Create a MySQL database named `fsadendexam`:
```sql
CREATE DATABASE fsadendexam;
USE fsadendexam;
```

2. Update the Hibernate configuration in `src/main/resources/hibernate.cfg.xml`:
```xml
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/fsadendexam</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">root</property>
```

### Build and Run

1. **Build the project:**
```bash
mvn clean install
```

2. **Run the application:**
```bash
mvn exec:java -Dexec.mainClass="com.klef.fsad.exam.ClientDemo"
```

Alternatively, you can run it directly if you have compiled the classes:
```bash
java -cp target/classes:target/dependency/* com.klef.fsad.exam.ClientDemo
```

## Usage

The application provides an interactive menu:

1. **Insert a new Vehicle** - Enter vehicle details and it will be saved to the database
2. **Update Vehicle** - Update the name and status of an existing vehicle by ID
3. **Retrieve Vehicle by ID** - Fetch and display vehicle details
4. **Exit** - Close the application

### Example Operations

**Insert:**
```
Enter Vehicle Name: Toyota Camry
Enter Description: Sedan
Enter Manufacturer: Toyota
Enter Model: Camry 2023
Enter Year: 2023
Enter Price: 25000.00
Enter Status: Available
Vehicle inserted successfully with ID: 1
```

**Update:**
```
Enter Vehicle ID to update: 1
Enter new Vehicle Name: Toyota Camry SE
Enter new Status: Sold
Vehicle updated successfully!
```

## Database Configuration

The Hibernate configuration (`hibernate.cfg.xml`) is set to:
- Dialect: MySQL8Dialect
- Connection pooling: 5 connections
- Auto schema creation: create-drop (creates and drops on each session)
- SQL logging: Enabled for debugging

To change this to `update` (preserve data between runs), modify:
```xml
<property name="hibernate.hbm2ddl.auto">update</property>
```

## Dependencies

- **Hibernate Core** 5.6.14.Final
- **MySQL Connector** 8.0.33
- **SLF4J** 1.7.36
- **Log4j** 1.2.17
- **JUnit** 4.13.2

## Notes

- All database operations are wrapped in transactions
- Proper exception handling is implemented
- The application uses thread-bound session context
- SQL logging is enabled for monitoring queries
- The project uses automatic ID generation (AUTO_INCREMENT)

## Package
- `com.klef.fsad.exam` - Contains all classes

## Database Name
- `fsadendexam` - MySQL database for this project
