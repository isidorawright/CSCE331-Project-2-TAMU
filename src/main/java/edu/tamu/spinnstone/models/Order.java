package edu.tamu.spinnstone.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

public class Order extends PgObject {
    public long order_id;
    public Date order_date;
    public double order_total;


    public Order(Connection conn, long order_id, Date order_date, double order_total) throws SQLException {
        super(
          conn,
          "order_item",
          Arrays.asList("order_id", "order_date", "order_total"),
          Arrays.asList(ColumnType.LONG, ColumnType.DATE, ColumnType.DOUBLE)
        );

      this.order_id = order_id;
      this.order_date = order_date;
      this.order_total = order_total;
    }

    
    public long insert() throws SQLException {
      Object[] values = {
        this.order_id,
        this.order_date,
        this.order_total
      };
      
      return super.insert(
        values
      );
    }

    public static Order create(Connection conn, long order_id, Date order_date, double order_total) throws SQLException {
      Order p = new Order(
        conn,
        0,
        order_date,
        order_total
      );

      long id = p.insert();
      p.order_id = id;

      return p;
    }

}
