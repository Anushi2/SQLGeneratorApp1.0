/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chris
 */
import javax.swing.table.AbstractTableModel;
import java.sql.*;


public class ResultSetTableModel extends AbstractTableModel {
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int rowCount;

    public ResultSetTableModel(ResultSet rs) throws SQLException {
        this.resultSet = rs;
        this.metaData = rs.getMetaData();
        rs.last();  // Move to the last row to get row count
        this.rowCount = rs.getRow();
        rs.beforeFirst();  // Reset cursor to before the first row
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        try {
            return metaData.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        try {
            return metaData.getColumnName(columnIndex + 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            resultSet.absolute(rowIndex + 1);  // Move to the specific row
            return resultSet.getObject(columnIndex + 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
