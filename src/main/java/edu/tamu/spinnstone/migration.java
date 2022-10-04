package edu.tamu.spinnstone;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import edu.tamu.spinnstone.models.MenuItem;


import edu.tamu.spinnstone.models.MenuItem;
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



  public void generateRandomOrder(boolean gameday, Date orderDate) {
    // TODO: choose random order items and update order total 
    // TODO: add to database 
    return;
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

     String[] menuItems = {
      "one topping pizza",
      "two - four topping pizza",
      "orginal cheese pizza",
      "bottled beverage",
      "gatorade",
      "fountain drink",
     };
      

    Double[] menuItemPrices = {
      7.79,
      8.99,
      6.79,
      2.39,
      2.39
    };

    // add products to inventory// add products to inventory
    //ArrayList<Product> products = new ArrayList<Product>();

    //Adds products to menu_item
    // ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
    
    //  // add products to menu_item    


    // } 
    // for (int i = 0; i < menuItems.length; i++) {
    //   menu.add(m);



    // // start on 9/4/2022,     for(int day = 4, day < 25, ++day){
    //   Date date = new Date(2022, 9, da
      // y);
    //   for(){

    //   }
    // }        mrmal day, 400 order s s pe;r gameday
    // Date tartDate = ne w Date() 
    
     //Product p = Product.create(this.connection, "Test Product");

    // System.out.println(p.id);

    // TODO: generate and add x random orders to database
    // two gamedays, provide date 
    
    /* Updates all order totals */
    for(int i = 1; i <= 230; ++i) {
      PreparedStatement query = connection.prepareStatement("select sum(menu_item_price) from order_item join menu_item on menu_item.menu_item_id = order_item.menu_item_id where order_item.order_id = " + i + ";");
      query.execute();
      ResultSet result = query.getResultSet();
      result.next();
      float order_total = result.getFloat(1);
      query.clearParameters();
      
      query = connection.prepareStatement("update \"order\" set order_total = " + order_total + " where order_id = " + i);
      query.execute();
      query.clearParameters();
    }
  }
}
