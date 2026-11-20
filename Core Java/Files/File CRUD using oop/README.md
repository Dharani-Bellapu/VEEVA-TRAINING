# Student Management System

A menu-driven Java application for managing student records with object-oriented design. Student objects are held in an `ArrayList<Student>` while the application is running and persisted to `student.txt`.

## Student details

Each record contains:

- Roll number
- Name
- Age
- Five marks (each from 0 to 100)
- Grade

## Features

1. Add a student
2. Display all students
3. Find a student by roll number
4. Find students by name
5. Update a student's details
6. Delete a student by roll number
7. Delete students by name
8. Exit

Roll numbers must be unique (case-insensitive). Names and grades cannot be empty, and fields cannot contain the `|` delimiter used in the data file.

## OOP structure

- `Student` models and validates an individual student and converts records to and from the file format.
- `StudentManager` owns the `ArrayList<Student>` and implements CRUD operations and file persistence.
- `StudentManagementApp` provides the console menu and reads user input.

## Run

From this directory, compile the Java files and start the application:

```text
javac Student.java StudentManager.java StudentManagementApp.java
java StudentManagementApp
```

The program creates `student.txt` in the current working directory when the first record is saved. Each line stores one record in this format:

```text
rollNumber|name|age|mark1|mark2|mark3|mark4|mark5|grade
```
