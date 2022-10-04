package edu.tamu.spinnstone.models;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Collections;
import java.util.List;


public class PgObject {
  // class to represent a database backed object
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


    // insert statement should not include id since it is auto generated
    // that's why we skip the first column name
    this.insertStatement = connection.prepareStatement(
      String.format(
        "INSERT INTO %s (%s) VALUES (%s) RETURNING %s",
        this.tableName,
        String.join(
          ", ",
          this.columnNames.subList(1, this.columnNames.size())
        ),
        String.join(
          ", ",
          Collections.nCopies(this.columnNames.size() - 1, "?")
        ),
        this.columnNames.get(0)
      )
    );

  }

  private void setStatementValue(
    PreparedStatement statement,
    int index,
    Object value
  ) throws SQLException {
    // set a statement value at the given index
    switch (columnTypes.get(index)) {
      case STRING:
        statement.setString(index, (String) value);
        break;
      case DOUBLE:
        statement.setDouble(index, (Double) value);
        break;
      case FLOAT:
        statement.setFloat(index, (Float) value);
        break;
      case DATE:
        statement.setDate(index, (Date) value);
        break;
      case INT:
        statement.setInt(index, (Integer) value);
        break;
      case BOOL:
        statement.setBoolean(index, (Boolean) value);
        break;
      case LONG:
        statement.setLong(index, (Long) value);
        break;
      case BOOLEAN:
        statement.setBoolean(index, (Boolean) value);
        break;
      case MONEY:
        statement.setBigDecimal(index, (BigDecimal) value);
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
    for (int i = 0; i < columnNames.size(); i++) {
      // skip id
      if(i == 0) {
        continue;
      }
      setStatementValue(insertStatement, i, values[i]);
    }

    insertStatement.execute();

    ResultSet rs = insertStatement.getResultSet();
    rs.next();
    long id = rs.getLong(1);

    insertStatement.clearParameters();

    return id;

  }
}
