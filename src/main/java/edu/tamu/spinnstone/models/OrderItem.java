package edu.tamu.spinnstone.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class OrderItem extends PgObject {
    public long order_item_id;
    public long order_id;
    public long menu_item_id;


    public OrderItem(Connection conn, long order_item_id, long order_id, long menu_item_id) throws SQLException {
        super(
          conn,
          "order_item",
          Arrays.asList("order_item_id", "order_id", "menu_item_id"),
          Arrays.asList(ColumnType.LONG, ColumnType.LONG, ColumnType.LONG)
        );

      this.order_id = order_id;
      this.menu_item_id = menu_item_id;
    }

    
    public long insert() throws SQLException {
      Object[] values = {
        this.order_item_id,
        this.order_id,
        this.menu_item_id
      };
      
      return super.insert(
        values
      );
    }

    public static OrderItem create(Connection conn, long order_id, long menu_item_id) throws SQLException {
      OrderItem p = new OrderItem(
        conn,
        0,
        order_id,
        menu_item_id
      );

      long id = p.insert();
      p.order_item_id = id;

      return p;
    }

}
