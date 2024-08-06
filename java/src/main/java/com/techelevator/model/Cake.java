package com.techelevator.model;


import java.math.BigDecimal;

public class Cake {

    private int CakeId;

    private String cakeName;

    private int cakeStyle;

    private int cakeSize;

    private int cakeFlavor;

    private int cakeFilling;

    private int cakeFrosting;

    private String cakeType;

    private boolean hasWriting;

    private String customText;

    private int amountAvailable;

    private BigDecimal price;

    public Cake(int cakeId, String cakeName, int cakeStyle, int cakeSize, int cakeFlavor, int cakeFilling, int cakeFrosting, String cakeType, boolean hasWriting, String customText, int amountAvailable, BigDecimal price) {
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

    public int getCakeStyle() {
        return cakeStyle;
    }

    public void setCakeStyle(int cakeStyle) {
        this.cakeStyle = cakeStyle;
    }

    public int getCakeSize() {
        return cakeSize;
    }

    public void setCakeSize(int cakeSize) {
        this.cakeSize = cakeSize;
    }

    public int getCakeFlavor() {
        return cakeFlavor;
    }

    public void setCakeFlavor(int cakeFlavor) {
        this.cakeFlavor = cakeFlavor;
    }

    public int getCakeFilling() {
        return cakeFilling;
    }

    public void setCakeFilling(int cakeFilling) {
        this.cakeFilling = cakeFilling;
    }

    public int getCakeFrosting() {
        return cakeFrosting;
    }

    public void setCakeFrosting(int cakeFrosting) {
        this.cakeFrosting = cakeFrosting;
    }

    public String getCakeType() {
        return cakeType;
    }

    public void setCakeType(String cakeType) {
        this.cakeType = cakeType;
    }

    public boolean isHasWriting() {
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
