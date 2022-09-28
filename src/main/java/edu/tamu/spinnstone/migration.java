package edu.tamu.spinnstone;

import java.sql.*;
import java.util.Properties;

public class migration {
  public static void makeProducts() {
    // String[] productNames = {
    //     "Fountain Cup",
    //     "Bottle Beverage",
    //     "Gatorade",
    //     "Cauliflower",
    //     "Standard",
    //     "Alfredo",
    //     "Traditional Red",
    //     "Zesty Red",
    //     "House Blend",
    //     "Parmesan",
    //     "BBQ Sauce",
    //     "Olive Oil",
    //     "Oregano",
    //     "Ranch",
    //     "Sriracha",
    //     "Diced Ham",
    //     "Italian Sausage",
    //     "Meatball",
    //     "Pepperoni",
    //     "Salami",
    //     "Smoked Chicken",
    //     "Banana Peppers",
    //     "Black Olives",
    //     "Green Peppers",
    //     "Jalapenos",
    //     "Mushrooms",
    //     "Onions",
    //     "Pineapple",
    //     "Roasted Garlic",
    //     "Spinach",
    //     "Tomatoes"
    // };
    // String[] menuItems = {
    //   "one topping pizza",
    //   "two - four topping pizza",
    //   "orginal cheese pizza",
    //   "bottled beverage",
    //   "gatorade",
    //   "fountain drink"
    // };
  }

  public static void up() {
    String url = "jdbc:postgresql://csce-315-db.engr.tamu.edu:5432/csce331_904_52";
    Properties props = new Properties();
    props.setProperty("user", "csce331_904_kevin");
    props.setProperty("password", "friendlyalpaca");

    try {
      Connection conn = DriverManager.getConnection(url, props);

      PreparedStatement statement = conn.prepareStatement(
          "INSERT INTO menu_item (item_name,menu_item_price) VALUES ('soda', 2);"
      );

      statement.execute();
      statement.close();

    } catch (Exception e) {
      System.out.println(e);

    }

  }
}
