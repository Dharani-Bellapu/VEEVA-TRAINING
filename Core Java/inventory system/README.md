# Inventory Management System

A menu-driven Java application for managing product information and inventory
quantities. Product records are stored in memory for the duration of the
application; no file or database persistence is used.

## Product attributes

- Product ID
- Product name
- Quantity
- Price

## Operations

1. Add a product
2. Remove a product by ID
3. Check product details by ID
4. Display all products
5. Exit

Product IDs are case-insensitive, and duplicate IDs are not allowed. Quantity
and price must be non-negative.

## Requirements

- Java 17 or later

## Run

From this directory, compile the source files and start the application:

```powershell
javac Product.java Inventory.java InventoryManagement.java
java InventoryManagement
```

## Project structure

- `Product.java` — Product model and attributes.
- `Inventory.java` — In-memory product collection and inventory operations.
- `InventoryManagement.java` — Console menu and user interaction.
