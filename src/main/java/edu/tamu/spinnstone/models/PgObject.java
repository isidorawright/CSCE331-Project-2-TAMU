package edu.tamu.spinnstone.models;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class PgObject {
  public String tableName;
  public List<String> columnNames;
  public PreparedStatement insertStatement;
  public List<ColumnType> columnTypes;
  public Connection connection;

  public PgObject(Connection conn, String tableName, List<String> columnNames, List<ColumnType> columnTypes)
      throws SQLException {
    this.connection = conn;
    this.tableName = tableName;
    this.columnNames = columnNames;
    this.columnTypes = columnTypes;



    this.insertStatement = connection.prepareStatement(
      String.format(
        "INSERT INTO %s (%s) VALUES (%s) RETURNING product_id",
        this.tableName,
        String.join(
          ", ",
          this.columnNames.stream()
            .filter(s-> !s.equals("id"))
            .toArray(String[]::new)
        ),
        String.join(
          ", ",
          Collections.nCopies(this.columnNames.size() - 1, "?")
        )
      )
    );

  }

  private void setColumn(int index, Object value) throws SQLException {
    switch (columnTypes.get(index)) {
      case STRING:
        insertStatement.setString(index, (String) value);
        break;
      case DOUBLE:
        insertStatement.setDouble(index, (Double) value);
        break;
      case FLOAT:
        insertStatement.setFloat(index, (Float) value);
        break;
      case DATE:
        insertStatement.setDate(index, (Date) value);
        break;
      case INT:
        insertStatement.setInt(index, (Integer) value);
        break;
      case BOOL:
        insertStatement.setBoolean(index, (Boolean) value);
        break;
      case LONG:
        insertStatement.setLong(index, (Long) value);
        break;
      case BOOLEAN:
        insertStatement.setBoolean(index, (Boolean) value);
        break;
    }
  }

  /*
   * Inserts a row into the table
   * @param values the values to insert into the table
   * @return the id of the inserted row
   *
   */
  public long insert(Object[] values) throws SQLException {
    for (int i = 0; i < values.length; i++) {
      if (columnNames.get(i) == "id")
        continue;
      setColumn(i, values[i]);
    }

    insertStatement.execute();

    ResultSet rs = insertStatement.getResultSet();
    rs.next();
    long id = rs.getLong(1);

    insertStatement.clearParameters();

    return id;

  }
}
