package edu.tamu.spinnstone;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import edu.tamu.spinnstone.models.Product;

public class Migration {
  Connection connection;

  public Migration(String username, String password, String databaseUrl) throws SQLException {
    String url = databaseUrl;
    Properties props = new Properties();
    props.setProperty("user", username);
    props.setProperty("password", password);
    this.connection = DriverManager.getConnection(url, props);
  }

  public void populate() throws SQLException {
    String[] productNames = {
        "Fountain Cup",
        "Bottle Beverage",
        "Gatorade",
        "Cauliflower",
        "Standard",
        "Alfredo",
        "Traditional Red",
        "Zesty Red",
        "House Blend",
        "Parmesan",
        "BBQ Sauce",
        "Olive Oil",
        "Oregano",
        "Ranch",
        "Sriracha",
        "Diced Ham",
        "Italian Sausage",
        "Meatball",
        "Pepperoni",
        "Salami",
        "Smoked Chicken",
        "Banana Peppers",
        "Black Olives",
        "Green Peppers",
        "Jalapenos",
        "Mushrooms",
        "Onions",
        "Pineapple",
        "Roasted Garlic",
        "Spinach",
        "Tomatoes"
    };

    ArrayList<Product> products = new ArrayList<Product>();

    // for (int i = 0; i < productNames.length; i++) {
    //   products.add(new Product(i, productNames[i], 0, this.connection));
    // }

    Product p = Product.create(this.connection, "Test Product");

    System.out.println(p.id);
  }
}
