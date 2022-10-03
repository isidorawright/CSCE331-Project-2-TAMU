package edu.tamu.spinnstone.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class MenuItem extends PgObject {
    public long menu_item_id;
    public String item_name;
    public double menu_item_price;


    public MenuItem(Connection conn, long menu_item_id, String item_name, double menu_item_price) throws SQLException {
        super(
          conn,
          "menu_item",
          Arrays.asList("menu_item_id", "item_name", "menu_item_price"),
          Arrays.asList(ColumnType.LONG, ColumnType.STRING, ColumnType.DOUBLE)
        );

      this.menu_item_id = menu_item_id;
      this.item_name = item_name;
      this.menu_item_price = menu_item_price;

    }

    
    public long insert() throws SQLException {
      Object[] values = {
        this.menu_item_id,
        this.item_name,
        this.menu_item_price
      };
      
      return super.insert(
        values
      );
    }

    public static MenuItem create(Connection conn, String item_name, double menu_item_price) throws SQLException {
      MenuItem p = new MenuItem(
        conn,
        0,
        item_name,
        menu_item_price
      );

      long id = p.insert();
      p.menu_item_id = id;

      return p;
    }

}
