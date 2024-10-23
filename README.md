# INVNTREE Product Service

## Building and Running the Application

To build and run the application using Maven, use the following command:
```
./mvnw clean package

./mvnw spring-boot:run -Dspring-boot.run.arguments="--db_host=localhost --db_username=your_username --password=your_password"
```

Replace `localhost`, `your_username`, and `your_password` with the appropriate values for your MySQL database.

### Sample Data for products
```sql
INSERT INTO products (product_id, name, price, rating, stock_quantity, created_date_time, version, updated_date_time, is_deleted)
VALUES
(UUID(), 'Pinkscale Blazing Star', 456.04, 2.25, 124834, '2024-10-20T00:00:00', 1, '2024-10-20T00:00:00', false),
(UUID(), 'Gila Milkvetch', 899.05, 3.56, 799402, '2024-10-21T00:00:00', 1, '2024-10-21T00:00:00', false),
(UUID(), 'Rocky Mountain Zinnia', 264.37, 3.23, 842192, '2024-10-22T00:00:00', 1, '2024-10-22T00:00:00', false),
(UUID(), 'Guadalupe Suncup', 555.93, 4.09, 236333, '2024-10-23T00:00:00', 1, '2024-10-23T00:00:00', false),
(UUID(), 'Saline Phlox', 82.62, 4.8, 601208, '2024-10-20T00:00:00', 1, '2024-10-20T00:00:00', false),
(UUID(), 'Common Brighteyes', 435.44, 0.27, 124068, '2024-10-21T00:00:00', 1, '2024-10-21T00:00:00', false),
(UUID(), 'Vermejo Phlox', 759.15, 2.46, 234525, '2024-10-22T00:00:00', 1, '2024-10-22T00:00:00', false),
(UUID(), 'Purple Marshlocks', 974.99, 4.82, 739009, '2024-10-23T00:00:00', 1, '2024-10-23T00:00:00', false),
(UUID(), 'Hamatocaulis Moss', 639.9, 1.17, 754285, '2024-10-20T00:00:00', 1, '2024-10-20T00:00:00', false),
(UUID(), 'Wax Myrtle', 62.95, 4.6, 205240, '2024-10-21T00:00:00', 1, '2024-10-21T00:00:00', false);

```