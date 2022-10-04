package edu.tamu.spinnstone.models;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class MenuItem extends PgObject {
    public long menuItemId;
    public String itemName;
    public BigDecimal menuItemPrice;


    public MenuItem(Connection conn, long menuItemId, String itemName, BigDecimal menuItemPrice) throws SQLException {
        super(
          conn,
          "menu_item",
          Arrays.asList("menu_item_id", "item_name", "menu_item_price"),
          Arrays.asList(ColumnType.LONG, ColumnType.STRING, ColumnType.MONEY)
        );

      this.menuItemId = menuItemId;
      this.itemName = itemName;
      this.menuItemPrice = menuItemPrice;

    }

    
    public long insert() throws SQLException {
      Object[] values = {
        this.menuItemId,
        this.itemName,
        this.menuItemPrice
      };
      
      return super.insert(
        values
      );
    }

    public static MenuItem create(Connection conn, String itemName, BigDecimal menuItemPrice) throws SQLException {
      MenuItem p = new MenuItem(
        conn,
        0,
        itemName,
        menuItemPrice
      );

      long id = p.insert();
      p.menuItemId = id;

      return p;
    }

}
