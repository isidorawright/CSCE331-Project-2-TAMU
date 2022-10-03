package edu.tamu.spinnstone.models;

import java.sql.*;
import java.util.Arrays;

public class Shipment extends PgObject {
  public long shipment_id;
  public Date shipment_date;
  public Boolean fulfilled;

  public Shipment(Connection conn, long shipment_id, Date shipment_date, Boolean fulfilled) throws SQLException {
    super(
      conn,
      "shipment",
      Arrays.asList("shipment_id", "shipment_date", "fulfilled"),
      Arrays.asList(ColumnType.LONG, ColumnType.DATE, ColumnType.BOOLEAN)
    );

    this.shipment_id = shipment_id;
    this.shipment_date = shipment_date;
    this.fulfilled = fulfilled;
  }

  public long insert() throws SQLException {
    Object[] values = {
      this.shipment_id,
      this.shipment_date,
      this.fulfilled
    };
    
    return super.insert(
      values
    );
  }

  public static Shipment create(Connection conn, Date shipment_date, Boolean fulfilled) throws SQLException {
    Shipment p = new Shipment(
      conn,
      0,
      shipment_date,
      fulfilled
    );

    long id = p.insert();
    p.shipment_id = id;

    return p;
  }
  
}
