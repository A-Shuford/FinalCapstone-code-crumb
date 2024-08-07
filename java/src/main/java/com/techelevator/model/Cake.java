package com.techelevator.model;


import java.math.BigDecimal;

public class Cake {

    private int CakeId;

    private String cakeName;

    private String cakeStyle;

    private String cakeSize;

    private String cakeFlavor;

    private String cakeFilling;

    private String cakeFrosting;

    private String cakeType;

    private boolean hasWriting;

    private String customText;

    private int amountAvailable;

    private BigDecimal price;

    public Cake(int cakeId, String cakeName, String cakeStyle, String cakeSize, String cakeFlavor, String cakeFilling,
                String cakeFrosting, String cakeType, boolean hasWriting, String customText, int amountAvailable, BigDecimal price) {
        CakeId = cakeId;
        this.cakeName = cakeName;
        this.cakeStyle = cakeStyle;
        this.cakeSize = cakeSize;
        this.cakeFlavor = cakeFlavor;
        this.cakeFilling = cakeFilling;
        this.cakeFrosting = cakeFrosting;
        this.cakeType = cakeType;
        this.hasWriting = hasWriting;
        this.customText = customText;
        this.amountAvailable = amountAvailable;
        this.price = price;
    }

    public Cake(){

    }

    public int getCakeId() {
        return CakeId;
    }

    public void setCakeId(int cakeId) {
        CakeId = cakeId;
    }

    public String getCakeName() {
        return cakeName;
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public String getCakeStyle() {
        return cakeStyle;
    }

    public void setCakeStyle(String cakeStyle) {
        this.cakeStyle = cakeStyle;
    }

    public String getCakeSize() {
        return cakeSize;
    }

    public void setCakeSize(String cakeSize) {
        this.cakeSize = cakeSize;
    }

    public String getCakeFlavor() {
        return cakeFlavor;
    }

    public void setCakeFlavor(String cakeFlavor) {
        this.cakeFlavor = cakeFlavor;
    }

    public String getCakeFilling() {
        return cakeFilling;
    }

    public void setCakeFilling(String cakeFilling) {
        this.cakeFilling = cakeFilling;
    }

    public String getCakeFrosting() {
        return cakeFrosting;
    }

    public void setCakeFrosting(String cakeFrosting) {
        this.cakeFrosting = cakeFrosting;
    }

    public String getCakeType() {
        return cakeType;
    }

    public void setCakeType(String cakeType) {
        this.cakeType = cakeType;
    }

    public boolean hasWriting() {
        return hasWriting;
    }

    public void setHasWriting(boolean hasWriting) {
        this.hasWriting = hasWriting;
    }

    public String getCustomText() {
        return customText;
    }

    public void setCustomText(String customText) {
        this.customText = customText;
    }

    public int getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(int amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }




}
