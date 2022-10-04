package edu.tamu.spinnstone.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class Product extends PgObject {
    public long productId;
    public String productName;
    public double quantityInStock;




    public Product(Connection conn, long productId, String productName, double quantityInStock) throws SQLException {
        super(
          conn,
          "product",
          Arrays.asList("product_id", "product_name", "quantity_in_stock"),
          Arrays.asList(ColumnType.LONG, ColumnType.STRING, ColumnType.DOUBLE)
        );

      this.productName = productName;
      this.quantityInStock = quantityInStock;

    }

    
    public long insert() throws SQLException {
      Object[] values = {
        this.productId,
        this.productName,
        this.quantityInStock
      };
      
      return super.insert(
        values
      );
    }

    public static Product create(Connection conn, String name) throws SQLException {
      Product p = new Product(
        conn,
        0,
        name,
        0.0
      );

      long productId = p.insert();
      p.productId = productId;

      return p;
    }

}
