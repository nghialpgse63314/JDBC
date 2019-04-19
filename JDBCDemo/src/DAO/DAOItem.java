/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jdbcdemo.DTOItem;

/**
 *
 * @author Phuong Nguyen
 */
public class DAOItem {

    public boolean insertItem(DTOItem dto) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = jdbcdemo.Utils.createConnection();
            if (con != null) {
                String sql = "insert into tblItems(itemCode, itemName, supCode, unit, price, supplying) values(?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getItemCode());
                stm.setString(2, dto.getItemName());
                stm.setString(3, dto.getSupCode());
                stm.setString(4, dto.getUnit());
                stm.setInt(5, dto.getPrice());
                stm.setBoolean(6, dto.isIssupplying());
                check = stm.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }


    public boolean deleteItem(String itemCode) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = jdbcdemo.Utils.createConnection();
            if (con != null) {
                String sql = "delete from tblItems where itemCode = '" + itemCode + "'";
                stm = con.prepareStatement(sql);
                check = stm.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public boolean updateItem(DTOItem dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        con = jdbcdemo.Utils.createConnection();
        try {
            if (con != null) {
                String sql = "update tblItems set itemName = ?, supCode = ?, unit = ?, price = ?, supplying = ? where itemCode = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getItemName());
                stm.setString(2, dto.getSupCode());
                stm.setString(3, dto.getUnit());
                stm.setInt(4, dto.getPrice());
                stm.setBoolean(5, dto.isIssupplying());
                stm.setString(6, dto.getItemCode());
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

}
