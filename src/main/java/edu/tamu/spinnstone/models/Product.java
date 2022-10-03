package edu.tamu.spinnstone.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class Product extends PgObject {
    public long id;
    public String product_name;
    public double quantity_in_stock;


    public Product(Connection conn, long id, String product_name, double quantity_in_stock) throws SQLException {
        super(
          conn,
          "product",
          Arrays.asList("id", "product_name", "quantity_in_stock"),
          Arrays.asList(ColumnType.LONG, ColumnType.STRING, ColumnType.DOUBLE)
        );

      this.product_name = product_name;
      this.quantity_in_stock = quantity_in_stock;

    }

    
    public long insert() throws SQLException {
      Object[] values = {
        this.id,
        this.product_name,
        this.quantity_in_stock
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

      long id = p.insert();
      p.id = id;

      return p;
    }

}
