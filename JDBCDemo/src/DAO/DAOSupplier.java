/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jdbcdemo.DTOSupplier;

/**
 *
 * @author Phuong Nguyen
 */
public class DAOSupplier {

    public boolean insertSupplier(DTOSupplier dto) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = jdbcdemo.Utils.createConnection();
            if (con != null) {
                String sql = "insert into Supplier(supCode, supName, address, colloborating) values(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getSupCode());
                stm.setString(2, dto.getSupName());
                stm.setString(3, dto.getAddress());
                stm.setBoolean(4, dto.isColloborating());
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

    public boolean deleteSupplier(String supCode) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = jdbcdemo.Utils.createConnection();
            if (con != null) {
                String sql = "delete from Supplier where supCode = '" + supCode + "'";
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

    public boolean updateSupplier(DTOSupplier dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        con = jdbcdemo.Utils.createConnection();
        try {
            if (con != null) {
                String sql = "update Supplier set supName = ?, address = ?, colloborating = ? where supCode = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getSupName());
                stm.setString(2, dto.getAddress());
                stm.setBoolean(3, dto.isColloborating());
                stm.setString(4, dto.getSupCode());
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
