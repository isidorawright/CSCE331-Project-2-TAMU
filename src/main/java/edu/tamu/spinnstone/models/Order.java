package edu.tamu.spinnstone.models;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

public class Order extends PgObject {
    public long orderId;
    public Date orderDate;
    public BigDecimal orderTotal;


    public Order(Connection conn, long orderId, Date orderDate, BigDecimal orderTotal) throws SQLException {
        super(
          conn,
          "\"order\"",
          Arrays.asList("order_id", "order_date", "order_total"),
          Arrays.asList(ColumnType.LONG, ColumnType.DATE, ColumnType.MONEY)
        );

      this.orderId = orderId;
      this.orderDate = orderDate;
      this.orderTotal = orderTotal;
    }

    
    public long insert() throws SQLException {
      Object[] values = {
        this.orderId,
        this.orderDate,
        this.orderTotal
      };
      
      return super.insert(
        values
      );
    }

    public static Order create(Connection conn, Date orderDate, BigDecimal orderTotal) throws SQLException {
      Order p = new Order(
        conn,
        0,
        orderDate,
        orderTotal
      );

      long id = p.insert();
      p.orderId = id;

      return p;
    }

}
