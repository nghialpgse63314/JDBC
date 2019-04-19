/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcdemo;

import java.util.Vector;

/**
 *
 * @author Phuong Nguyen
 */
public class DTOSupplier {

    String supCode = "", supName = "", address = "";
    boolean colloborating = true;

    public DTOSupplier() {
    }

    public DTOSupplier(String supCode, String supName, String address, boolean colloborating) {
        this.supCode = supCode;
        this.supName = supName;
        this.address = address;
        this.colloborating = colloborating;
    }

    public String getSupCode() {
        return supCode;
    }

    public void setSupCode(String supCode) {
        this.supCode = supCode;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isColloborating() {
        return colloborating;
    }

    public void setColloborating(boolean colloborating) {
        this.colloborating = colloborating;
    }

    public String toString(String supName) {
        return supCode + "-" + supName;
    }

    public Vector toVector() {
        Vector v = new Vector();
        v.add(this.supCode);
        v.add(this.supName);
        v.add(this.address);
        return v;
    }
}
