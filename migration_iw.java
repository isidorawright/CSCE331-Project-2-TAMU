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


        //What are the 15 SQL queries we want to create?:

        // QUERY 1: table containing shipment date, quantity ordered, and if shipment was fulfilled
        SELECT shipment.shipment_date, shipment_product.quantity_ordered, shipment.fulfilled FROM shipment
        JOIN shipment_product ON shipment.shipment_id = shipment_product.shipment_shipment_id;

        // QUERY 2: get all items on an order by order id
        SELECT * FROM "order"
        JOIN order_item ON order_item.order_id = "order".order_id
        WHERE "order".order_id = 1;

        // QUERY 3: get view of all orders
        SELECT * FROM "order";

        // QUERY 4: view all inventory (the products table)
        SELECT * FROM product;

        // QUERY 5: view shipment history (the shipments table)
        SELECT * FROM shipment;

        // QUERY 6: get all of the products associated with specific order item
        SELECT product.* FROM order_item
        JOIN order_item_product ON order_item_product.order_item_order_item_id = order_item.order_item_order_item_id
        JOIN product ON order_item_product.product_product_id = product.product_product_id
        WHERE order_item.order_id = 1;

        // QUERY 7: find all products on a shipment
        SELECT product.* FROM shipment
        JOIN shipment_product ON shipment_product.shipment_shipment_id = shipment.shipment_id
        JOIN product ON shipment_product.product_product_id = product.product_product_id
        WHERE shipment.shipment_id = 1;

        // QUERY 8: get all menu items on an order item by order item id
        SELECT * FROM order_item
        JOIN menu_item ON menu_item.menu_item_id = order_item.order_item_id
        WHERE order_item.order_item_id = 1;


        statement.execute();
        statement.close();

    } 
    catch (Exception e) {
        System.out.println(e);

    }

    }
    }