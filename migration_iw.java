    package edu.tamu.spinnstone;

    import java.sql.*;
    import java.util.Properties;

    /*
    * Product names array holds all products used (ingredients(toppings), boxes, drinks, etc.)
    * Menu Items array holds the names for all the items on the menu
    * These two arrays hold all the components that we will need to populate the other tables as orders and shipments are placed
    */

    public class migration {
    public static void makeProducts() {
    // String[] productNames = {
    //     "Fountain Cup",
    //     "Boxes",
    //     "Paper"
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
    props.setProperty("user", "csce331_904_isidora");
    props.setProperty("password", "Csce331!");

    try {
        Connection conn = DriverManager.getConnection(url, props);

        PreparedStatement statement = conn.prepareStatement(
            "INSERT INTO menu_item (item_name,menu_item_price) VALUES ('paper', .5);"
        );

        /* SO, if I wanted to populate a table with the values in the arrays above, then I would do this:
        //PreparedStatement is suitable for executing DML commands – SELECT, INSERT, UPDATE and DELETE
        //To create, modify or drop a database object like a table or view you use the execute() method. This method is similar to the method executeQuery() 
        */

        //could aslo do this: st.setArray(i, productNames); st.executeUpdate(); --Then we wouldn't need the for loop
        for(int i = 0; i < menuItems; ++i){
            PreparedStatement st = conn.prepareStatement("INSERT INTO menu_item (item_name, menu_item_price) VALUES (?,?)");
            //Can only populate the first column with the prductNames array
            //The second column will be for the price
            st.setString(1, menuItems[i]);
            //st.setDouble(2, priceeArry[i]); -- This is just an example
        }

        st.exectue();
        st.close();

        //Making a new SQL Command
        //Im making this up, but this is an example of what we need to do
        //Let's say that I want a table that contains the shipment date, quantity ordered, and if shipment fullfilled -- correlate with shipment ID
        SELECT shipment.shipment_date, shipment_has_products.shipment_ordered, shipment.fullfilled FROM shipment 
            INNER JOIN shipment_has_products ON shipment.shipment_id=shipment_has_products.shipment_id;

        statement.execute();
        statement.close();

    } 
    catch (Exception e) {
        System.out.println(e);

    }

    }
    }