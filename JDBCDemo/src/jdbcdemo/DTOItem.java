/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcdemo;

/**
 *
 * @author Phuong Nguyen
 */
public class DTOItem {
    String  itemCode, itemName, supCode, unit;
    int price;
    boolean issupplying;

    public DTOItem(String itemCode, String itemName, String supCode, String unit, int price, boolean issupplying) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.supCode = supCode;
        this.unit = unit;
        this.price = price;
        this.issupplying = issupplying;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSupCode() {
        return supCode;
    }

    public void setSupCode(String supCode) {
        this.supCode = supCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isIssupplying() {
        return issupplying;
    }

    public void setIssupplying(boolean issupplying) {
        this.issupplying = issupplying;
    }

    
    
}
