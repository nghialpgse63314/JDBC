/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcdemo;

import DAO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Phuong Nguyen
 */
public final class ManagerProgram extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    DefaultTableModel model1;
    DefaultTableModel model2;
    Boolean addNewItem;

    /**
     * Creates new form ManagerProgram
     */
    public ManagerProgram() {
        initComponents();
        model1 = (DefaultTableModel) tblSupplier.getModel();
        model2 = (DefaultTableModel) tblItem.getModel();
        displaySupData();
        displayItemData();

    }

    public void displaySupData() {
        try {
            con = Utils.createConnection();
            model1.getDataVector().removeAllElements();
            if (con != null) {
                String sql = "Select supCode, supName, address, colloborating from supplier";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String code = rs.getString("supCode");
                    String name = rs.getString("supName");
                    String address = rs.getString("address");
                    String coll = rs.getBoolean("colloborating") + "";
                    model1.addRow(new Object[]{code, name, address, coll});
                }
                tblSupplier.setModel(model1);

            }
        } catch (Exception ex) {
            Logger.getLogger(ManagerProgram.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {

            }
        }
    }

    public void displayItemData() {
        try {
            con = Utils.createConnection();
            model2.getDataVector().removeAllElements();
            if (con != null) {
                String sql = "Select itemCode, itemName, supCode, unit, price, supplying from tblItems";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String code = rs.getString("itemCode");
                    String name = rs.getString("itemName");
                    String supCode = rs.getString("supCode");
                    String unit = rs.getString("unit");
                    String price = rs.getInt("price") + "";
                    String isSupplying = rs.getBoolean("supplying") + "";
                    model2.addRow(new Object[]{code, name, supCode, unit, price, isSupplying});
                }
                tblItem.setModel(model2);
                if (txtSupCode.getText().length() != 0) {
                    cbSupCode.addItem(txtSupCode.getText());
                }
                loadSuppliersToComboBox();
            }
        } catch (Exception ex) {
            Logger.getLogger(ManagerProgram.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {

            }
        }
    }

    public void loadSuppliersToComboBox() {
        Connection conn = null;
        PreparedStatement stm = null;
//        ResultSet rs = null;
        cbSupCode.removeAllItems();
        try {
            conn = Utils.createConnection();
            if (conn != null) {
                String sql = "SELECT supCode FROM Supplier";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String subCode = rs.getString("supCode");
                    cbSupCode.addItem(subCode);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean checkSupplierCode() {
        String code = txtSupCode.getText();
        for (int i = 0; i < tblSupplier.getRowCount(); i++) {
            if (code.equalsIgnoreCase((String) tblSupplier.getValueAt(i, 0))) {
                JOptionPane.showMessageDialog(this, "dublicated code");
                return false;
            }
        }
        if (code.equals("")) {
            JOptionPane.showMessageDialog(this, "code is not null");
            return false;
        }
        if (code.length() > 5) {
            JOptionPane.showMessageDialog(this, "Code <=5 character");
            return false;
        }
        return true;
    }

    public boolean checkSupplierTable() {
        if (tblSupplier.getRowCount() == 0) {
            return false;
        }
        return true;
    }

    public boolean isContantItem(String code) {
        for (int i = 0; i < tblItem.getRowCount(); i++) {
            if (code.equalsIgnoreCase((String) tblItem.getValueAt(i, 2))) {
                return true;
            }
        }
        return false;
    }

    public boolean checkItemCode() {
        String code = txtItemCode.getText();
        for (int i = 0; i < tblItem.getRowCount(); i++) {
            if (code.equalsIgnoreCase((String) tblItem.getValueAt(i, 0))) {
                JOptionPane.showMessageDialog(this, "dublicated code");
                return false;
            }
        }
        if (code.equals("")) {
            JOptionPane.showMessageDialog(this, "code is not null");
            return false;
        }
        if (code.length() > 5) {
            JOptionPane.showMessageDialog(this, "Code <=5 character");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItem = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtItemCode = new javax.swing.JTextField();
        txtItemName = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtUnit = new javax.swing.JTextField();
        cbSupCode = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        btnNewItem = new javax.swing.JButton();
        btnSaveItem = new javax.swing.JButton();
        btnDeleteItem = new javax.swing.JButton();
        btnSearchItem = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        ckcIsSupplying = new javax.swing.JCheckBox();
        txtSearchItem = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSupplier = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSupCode = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtSupName = new javax.swing.JTextField();
        chbColl = new javax.swing.JCheckBox();
        btnAddNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearchSupplier = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Item Code", "Item Name", "Supplier Code", "Unit", "Price", "Supplying"
            }
        ));
        tblItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblItem);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item deteil", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N

        jLabel6.setText("Item Code");

        jLabel7.setText("Item Name");

        jLabel8.setText("Unit");

        jLabel9.setText("Price");

        cbSupCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "          " }));

        jLabel10.setText("Supplier Code");

        btnNewItem.setText("New");
        btnNewItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewItemActionPerformed(evt);
            }
        });

        btnSaveItem.setText("Save");
        btnSaveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveItemActionPerformed(evt);
            }
        });

        btnDeleteItem.setText("Delete");
        btnDeleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteItemActionPerformed(evt);
            }
        });

        btnSearchItem.setText("Search");
        btnSearchItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchItemActionPerformed(evt);
            }
        });

        jLabel11.setText("Supplying");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUnit))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnSearchItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearchItem))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnNewItem)
                                .addGap(18, 18, 18)
                                .addComponent(btnSaveItem))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbSupCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteItem))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(ckcIsSupplying)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtPrice)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtItemName))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbSupCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(ckcIsSupplying))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearchItem)
                    .addComponent(txtSearchItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewItem)
                    .addComponent(btnSaveItem)
                    .addComponent(btnDeleteItem))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Item", jPanel3);

        tblSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "supCode", "supName", "address", "colloborating"
            }
        ));
        tblSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSupplier);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N

        jLabel1.setText("Supplier Code:");

        jLabel2.setText("Supplier Name:");

        jLabel3.setText("Address:");

        jLabel4.setText("Colloborating");

        txtAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddressActionPerformed(evt);
            }
        });

        txtSupName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupNameActionPerformed(evt);
            }
        });

        btnAddNew.setText("Add New");
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearchSupplier.setText("Search");
        btnSearchSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchSupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSearchSupplier)
                        .addGap(30, 30, 30)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAddNew)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtSupName, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(txtSupCode, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(chbColl)
                                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSupCode))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSupName))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbColl)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch)
                    .addComponent(btnSearchSupplier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNew)
                    .addComponent(btnSave)
                    .addComponent(btnDelete))
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, Short.MAX_VALUE))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Supplier", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddressActionPerformed

    private void txtSupNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupNameActionPerformed

    private void tblSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupplierMouseClicked
        //TODO add your handling code here:

//        addNewItem = false;
//        int row = tblSupplier.getSelectedRow();
//        String code = (String) tblSupplier.getValueAt(row, 0);
//        String name = (String) tblSupplier.getValueAt(row, 1);
//        String address = (String) tblSupplier.getValueAt(row, 2);
//        String coll = (String) tblSupplier.getValueAt(row, 3);
//        if (coll.equalsIgnoreCase("1")) {
//            chbColl.setSelected(true);
//        } else {
//            chbColl.setSelected(false);
//        }
//        txtSupCode.setText(code);
//        txtSupName.setText(name);
//        txtAddress.setText(address);

         int row = tblSupplier.getSelectedRow();
        String code = (String) tblSupplier.getValueAt(row, 0);
        String name = (String) tblSupplier.getValueAt(row, 1);
        String address = (String) tblSupplier.getValueAt(row, 2);
        String coll = (String) tblSupplier.getValueAt(row, 3);
        DTOSupplier dto = new DTOSupplier(code, name, address, true);
        txtSupCode.setText(dto.getSupCode());
        txtSupName.setText(dto.supName);
        txtAddress.setText(dto.address);
        cbSupCode.setSelectedItem(dto.isColloborating());
        btnSave.setText("Update");
    }//GEN-LAST:event_tblSupplierMouseClicked

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        // TODO add your handling code here:
        addNewItem = true;
        txtSupCode.setText("");
        txtSupName.setText("");
        txtAddress.setText("");
        chbColl.setSelected(false);
        btnSave.setText("Save");
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String code = txtSupCode.getText();
        if (isContantItem(code)) {
            JOptionPane.showMessageDialog(this, "Cant delete supplier has item");
            return;
        }
        try {
            DAOSupplier dao = new DAOSupplier();
            if (dao.deleteSupplier(code)) {
                JOptionPane.showMessageDialog(this, "Deleted!");
            } else {
                JOptionPane.showMessageDialog(this, "Delete failed");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cant delete supplier has item");
        }
        displaySupData();
        displayItemData();


    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String code = txtSupCode.getText();
        String name = txtSupName.getText();
        String address = txtAddress.getText();
        boolean isSupplying;
        if (chbColl.isSelected()) {
            isSupplying = true;
        } else {
            isSupplying = false;
        }
        if (btnSave.getText().equalsIgnoreCase("Save")) {
            if (addNewItem == true) {
                try {
                    if (!checkSupplierCode()) {
                        return;
                    }
                    DTOSupplier dto = new DTOSupplier(code, name, address, isSupplying);
                    DAOSupplier dao = new DAOSupplier();
                    if (dao.insertSupplier(dto)) {
                        JOptionPane.showMessageDialog(this, "Saved");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Nothing to add");
                }
            } else {
                JOptionPane.showMessageDialog(this, "please press add new button");
            }
            displaySupData();
            loadSuppliersToComboBox();
        } else {
            try {
                DTOSupplier dto = new DTOSupplier(code, name, address, isSupplying);
                DAOSupplier dao = new DAOSupplier();
                if (dao.updateSupplier(dto)) {
                    JOptionPane.showMessageDialog(this, "Success");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed");
                }
            } catch (Exception e) {
            }
            displaySupData();
            loadSuppliersToComboBox();
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
//        String searchValue = txtSearch.getText();
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            con = Utils.createConnection();
//            if (con != null) {
//                model1.getDataVector().removeAllElements();
//                String sql = "select supCode, supName, address, colloborating "
//                        + "from Supplier where SupName like '%" + searchValue + "%'";
//                stm = con.prepareStatement(sql);
//                rs = stm.executeQuery();
//                while (rs.next()) {
//                    String code = rs.getString("SupCode");
//                    String name = rs.getString("SupName");
//                    String address = rs.getString("Address");
//                    String coll = rs.getBoolean("colloborating") + "";
//                    model1.addRow(new Object[]{code, name, address, coll});
//                }
//                tblSupplier.setModel(model1);
//                tblSupplier.updateUI();
//            }
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (stm != null) {
//                    stm.close();
//                }
//                if (con != null) {
//                    con.close();
//                }
//            } catch (Exception e) {
//            }
//        }
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnSearchSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchSupplierActionPerformed
        // TODO add your handling code here:
        String searchValue = txtSearch.getText();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.createConnection();
            if (con != null) {
                model1.getDataVector().removeAllElements();
                String sql = "select supCode, supName, address, colloborating "
                        + "from Supplier where SupName like '%" + searchValue + "%'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String code = rs.getString("supCode");
                    String name = rs.getString("supName");
                    String address = rs.getString("address");
                    String coll = rs.getBoolean("colloborating") + "";
                    model1.addRow(new Object[]{code, name, address, coll});
                }
                tblSupplier.setModel(model1);
                tblSupplier.updateUI();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btnSearchSupplierActionPerformed

    private void tblItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemMouseClicked
        // TODO add your handling code here:
        addNewItem = false;
        int row = tblItem.getSelectedRow();
        String code = (String) tblItem.getValueAt(row, 0);
        String name = (String) tblItem.getValueAt(row, 1);
        String supCode = (String) tblItem.getValueAt(row, 2);
        String unit = (String) tblItem.getValueAt(row, 3);
        String price = (String) tblItem.getValueAt(row, 4);
        String isSupplying = (String) tblItem.getValueAt(row, 5);
        txtItemCode.setText(code);
        txtItemName.setText(name);
        cbSupCode.removeAllItems();
        cbSupCode.addItem(supCode);
        loadSuppliersToComboBox();
        cbSupCode.setSelectedItem(supCode);
        txtUnit.setText(unit);
        txtPrice.setText(price);
        if (isSupplying.equalsIgnoreCase("1")) {
            ckcIsSupplying.setSelected(true);
        } else {
            ckcIsSupplying.setSelected(false);
        }
        btnSaveItem.setText("Update");
    }//GEN-LAST:event_tblItemMouseClicked

    private void btnNewItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewItemActionPerformed

        addNewItem = true;
        txtItemCode.setText("");
        txtItemName.setText("");
        txtSupCode.setText("");
        txtUnit.setText("");
        txtPrice.setText("");
        ckcIsSupplying.setSelected(false);
        btnSaveItem.setText("Save");
    }//GEN-LAST:event_btnNewItemActionPerformed

    private void btnSaveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveItemActionPerformed
        // TODO add your handling code here:
        String itemCode = txtItemCode.getText();
        String itemName = txtItemName.getText();
        String supCode = cbSupCode.getSelectedItem().toString();
        String unit = txtUnit.getText();
        int price = 0;
        boolean isSupplying;
        if (ckcIsSupplying.isSelected()) {
            isSupplying = true;
        } else {
            isSupplying = false;
        }
        if (btnSaveItem.getText().equalsIgnoreCase("Save")) {

            if (addNewItem == true) {
                if (!checkItemCode()) {
                    return;
                }
                if (!checkSupplierTable()) {
                    JOptionPane.showMessageDialog(this, "Supplier table is empty");
                    return;
                }
                try {
                    try {
                        price = Integer.parseInt(txtPrice.getText());
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Price is an integer");
                        return;
                    }
                    DTOItem dto = new DTOItem(itemCode, itemName, supCode, unit, price, isSupplying);
                    DAOItem dao = new DAOItem();
                    if (dao.insertItem(dto)) {
                        JOptionPane.showMessageDialog(this, "Saved");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed");
                    }
                } catch (Exception e) {
                }
            }
            displayItemData();
        } else {
            try {
                try {
                    price = Integer.parseInt(txtPrice.getText());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Price is an integer");
                    return;
                }
                DTOItem dto = new DTOItem(itemCode, itemName, supCode, unit, price, isSupplying);
                DAOItem dao = new DAOItem();
                if (dao.updateItem(dto)) {
                    JOptionPane.showMessageDialog(this, "Success");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed");
                }
            } catch (Exception e) {
            }
            displayItemData();
        }

    }//GEN-LAST:event_btnSaveItemActionPerformed

    private void btnDeleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteItemActionPerformed
        // TODO add your handling code here:
        String code = txtItemCode.getText();
        try {
            DAOItem dao = new DAOItem();
            if (dao.deleteItem(code)) {
                JOptionPane.showMessageDialog(this, "Deleted!");
            } else {
                JOptionPane.showMessageDialog(this, "Delete failed");
            }
        } catch (Exception e) {

        }
        displayItemData();
    }//GEN-LAST:event_btnDeleteItemActionPerformed

    private void btnSearchItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchItemActionPerformed
        // TODO add your handling code here:
        String searchValue = txtSearchItem.getText();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = Utils.createConnection();
            if (con != null) {
                model2.getDataVector().removeAllElements();
                String sql = "select itemCode, itemName, supCode, unit, price, supplying from Items where "
                        + "itemName like '%" + searchValue + "%'";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String code = rs.getString("itemCode");
                    String name = rs.getString("itemName");
                    String supCode = rs.getString("supCode");
                    String unit = rs.getString("unit");
                    String price = rs.getInt("price") + "";
                    String supplying = rs.getBoolean("supplying") + "";
                    model2.addRow(new Object[]{code, name, supCode, unit, price, supplying});
                }
                tblItem.setModel(model2);
                tblItem.updateUI();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btnSearchItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManagerProgram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerProgram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerProgram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerProgram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerProgram().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteItem;
    private javax.swing.JButton btnNewItem;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveItem;
    private javax.swing.JButton btnSearchItem;
    private javax.swing.JButton btnSearchSupplier;
    private javax.swing.JComboBox<String> cbSupCode;
    private javax.swing.JCheckBox chbColl;
    private javax.swing.JCheckBox ckcIsSupplying;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblItem;
    private javax.swing.JTable tblSupplier;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtItemCode;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchItem;
    private javax.swing.JTextField txtSupCode;
    private javax.swing.JTextField txtSupName;
    private javax.swing.JTextField txtUnit;
    // End of variables declaration//GEN-END:variables

}
