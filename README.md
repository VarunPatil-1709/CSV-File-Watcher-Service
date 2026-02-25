CSV FILE WATCHER SERVICE
Spring Boot Event-Driven CSV Ingestion System

------------------------------------------------------------

PROJECT OVERVIEW

This project is a Spring Boot-based backend service that automatically monitors a configured directory for newly added CSV files.

When a CSV file is detected, the system:

1. Reads the file using Java NIO BufferedReader
2. Parses the CSV using Apache Commons CSV
3. Maps each row to a DTO object
4. Converts DTO to Entity
5. Inserts records into a MySQL database using JPA batch processing
6. Moves the file to a "processed" folder if successful
7. Moves the file to an "error" folder if processing fails

This solution is designed for single-server, low-load environments.

------------------------------------------------------------

SYSTEM ARCHITECTURE FLOW

File Drop (CSV)
        ↓
WatchService (Java NIO)
        ↓
CSV Parser
        ↓
DTO Mapping
        ↓
JPA Batch Insert
        ↓
Move File (processed / error)

------------------------------------------------------------

TECHNOLOGY STACK

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- Apache Commons CSV
- Java NIO WatchService
- Maven

------------------------------------------------------------

PROJECT STRUCTURE

com.FileWatcherService
│
├── entity

├── repository

├── service

├── watcher

└── FileWatcherServiceApplication.java

------------------------------------------------------------

CONFIGURATION

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/testdb

spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

file.import.path=D:/FileWatcher/import

------------------------------------------------------------

REQUIRED FOLDER STRUCTURE

Create the following directories:

D:/FileWatcher/import
D:/FileWatcher/import/processed
D:/FileWatcher/import/error

Place CSV files directly inside:

D:/FileWatcher/import

------------------------------------------------------------

CSV FORMAT (MANDATORY HEADER)

name,email,phone
Varun,varun@gmail.com,9876543210
Rahul,rahul@gmail.com,9999999999

Headers are case-sensitive.

------------------------------------------------------------

HOW TO RUN

1. Ensure MySQL is running
2. Create database: testdb
3. Run:

   mvn clean install
   mvn spring-boot:run

4. Drop a CSV file into the import folder
5. Observe logs and database entries

------------------------------------------------------------

FEATURES

- Event-driven file monitoring
- Automatic CSV parsing
- Batch database insertion
- Transactional processing
- Automatic file lifecycle handling
- Configurable import path

------------------------------------------------------------

LIMITATIONS

- Designed for single-server deployment
- Not distributed-safe
- Not recursive (does not monitor subdirectories)
- Large files may require chunk processing optimization

------------------------------------------------------------

FUTURE IMPROVEMENTS

- Add multithreaded processing (ExecutorService)
- Add retry mechanism
- Add logging framework (SLF4J / Logback)
- Add distributed lock for clustered deployment
- Integrate with cloud storage (S3-based ingestion)

------------------------------------------------------------

AUTHOR

Developed as a backend system design practice project using Spring Boot and Java NIO.
