package edu.tamu.spinnstone.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class OrderItem extends PgObject {
    public long orderItemId;
    public long orderId;
    public long menuItemId;


    public OrderItem(Connection conn, long orderItemId, long orderId, long menuItemId) throws SQLException {
        super(
          conn,
          "order_item",
          Arrays.asList("order_item_id", "order_id", "menu_item_id"),
          Arrays.asList(ColumnType.LONG, ColumnType.LONG, ColumnType.LONG)
        );

      this.orderId = orderId;
      this.menuItemId = menuItemId;
    }

    
    public long insert() throws SQLException {
      Object[] values = {
        this.orderItemId,
        this.orderId,
        this.menuItemId
      };
      
      return super.insert(
        values
      );
    }

    public static OrderItem create(Connection conn, long orderId, long menuItemId) throws SQLException {
      OrderItem p = new OrderItem(
        conn,
        0,
        orderId,
        menuItemId
      );

      long id = p.insert();
      p.orderItemId = id;

      return p;
    }

    public void addProduct(long productId) throws SQLException {
      PreparedStatement statement = connection.prepareStatement(
        String.format("INSERT INTO order_item_product (order_item_order_item_id, product_product_id) VALUES (%s,%s)", orderItemId, productId)
      );

      statement.execute();
      
    }

}
