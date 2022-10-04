package edu.tamu.spinnstone.models;

import java.sql.*;
import java.util.Arrays;

public class Shipment extends PgObject {
  public long shipmentId;
  public Date shipmentDate;
  public Boolean fulfilled;

  public Shipment(Connection conn, long shipmentId, Date shipmentDate, Boolean fulfilled) throws SQLException {
    super(
      conn,
      "shipment",
      Arrays.asList("shipment_id", "shipment_date", "fulfilled"),
      Arrays.asList(ColumnType.LONG, ColumnType.DATE, ColumnType.BOOLEAN)
    );

    this.shipmentId = shipmentId;
    this.shipmentDate = shipmentDate;
    this.fulfilled = fulfilled;
  }

  public long insert() throws SQLException {
    Object[] values = {
      this.shipmentId,
      this.shipmentDate,
      this.fulfilled
    };
    
    return super.insert(
      values
    );
  }

  public static Shipment create(Connection conn, Date shipmentDate, Boolean fulfilled) throws SQLException {
    Shipment p = new Shipment(
      conn,
      0,
      shipmentDate,
      fulfilled
    );

    long id = p.insert();
    p.shipmentId = id;

    return p;
  }
  
}
