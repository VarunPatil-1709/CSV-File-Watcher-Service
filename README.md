# CSV File Watcher Service

## Overview

CSV File Watcher Service is a Spring Boot backend application that monitors a configured directory for newly added CSV files.  
When a CSV file is detected, the system parses its content and inserts the data into a MySQL database.

After processing:
- Successfully processed files are moved to the `processed` folder.
- Failed files are moved to the `error` folder.

This project is designed for single-server, low-load environments.

---

## How It Works

1. Application starts.
2. Java NIO WatchService monitors the import directory.
3. When a `.csv` file is created:
   - The file is read using `BufferedReader`.
   - Data is parsed using Apache Commons CSV.
   - Each row is mapped to a DTO.
   - DTO is converted to Entity.
   - Data is batch inserted using Spring Data JPA.
4. The file is moved to:
   - `processed/` if successful
   - `error/` if an exception occurs

---

## Technology Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- Apache Commons CSV
- Java NIO WatchService
- Maven

---

## Project Structure

com.FileWatcherService
│
├── entity
├── repository
├── service
├── watcher
└── FileWatcherServiceApplication.java

---

## Configuration

Update `application.properties`:

spring.datasource.url=jdbc:mysql://localhost:3306/testdb
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

file.import.path=D:/FileWatcher/import

---

## Required Folder Structure

Create the following directories:

D:/FileWatcher/import  
D:/FileWatcher/import/processed  
D:/FileWatcher/import/error  

Place CSV files directly inside:

D:/FileWatcher/import

---

## CSV Format (Required Header)

The CSV file must include the following header row:

name,email,phone

Example:

name,email,phone  
Varun,varun@gmail.com,9876543210  
Rahul,rahul@gmail.com,9999999999  

Note:
- Headers are case-sensitive.
- File must have `.csv` extension.

---

## How to Run

1. Ensure MySQL is running.
2. Create database `testdb`.
3. Run the application:

   mvn clean install  
   mvn spring-boot:run  

4. Drop a CSV file into the import folder.
5. Check console logs and database entries.

---

## Features

- Event-driven file monitoring
- Automatic CSV parsing
- Batch database insertion
- Transactional processing
- Automatic file lifecycle management
- Configurable import path

---

## Limitations

- Designed for single-server deployment
- Not distributed-safe
- Does not monitor subdirectories (non-recursive)
- Large files may require chunk-based processing optimization

---

## Future Improvements

- Multithreaded processing using ExecutorService
- Retry mechanism for failed processing
- Logging with SLF4J/Logback
- Distributed lock for clustered deployment
- Cloud-based ingestion (S3 + event-driven architecture)

---

## Author

Backend system design practice project using Spring Boot and Java NIO.
