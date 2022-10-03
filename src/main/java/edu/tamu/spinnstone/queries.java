package edu.tamu.spinnstone;

// QUERY 1: table containing shipment date, quantity ordered, and if shipment was fullfilled 
SELECT shipment.shipment_date, shipment_product.shipment_ordered, shipment.fullfilled FROM shipment
    JOIN shipment_product ON shipment.shipment_id = shipment_product.shipment_id;

// QUERY 2: get specific order by order id

// QUERY 3: get view of all orders

// QUERY 4: view all inventory (the products table)

// QUERY 5: view shipment history (the shipments table)

// QUERY 6: get all of the products associated with specific order item
SELECT product.* FROM order_item
    JOIN order_item_product ON order_item_product.order_item_order_item_id = order_item.order_item_order_item_id
    JOIN product ON order_item_product.product_product_id = product.product_product_id
    WHERE order_item.order_id = 1;

// QUERY 7: find all products on a shipment 
SELECT product.* FROM shipment
    JOIN shipment_product 

// QUERY 8: 

// QUERY 9:

// QUERY 10:

// QUERY 11: 

// QUERY 12:

// QUERY 13:

// QUERY 14:

// QUERY 15:
