package utils;

import java.time.LocalDate;
import java.util.Objects;

public class Property{
    private String area;//区域名称	
    private String position;//存放地点	
    private String code;//资产编码
    private String name;//资产名称	
    private String mode;//规格型号	
    public Integer number;//数量
    public Double price;//单价
    public Double totalPrice;//总价
    private LocalDate buyDate;//购置日期

    public Property() {
    }

    public Property(String area, String position, String code, String name, String mode, Integer number, Double price, LocalDate buyDate) {
        this.area = area;
        this.position = position;
        this.code = code;
        this.name = name;
        this.mode = mode;
        this.number = number;
        this.price = price;
        this.totalPrice = price;
        this.buyDate = buyDate;
    }


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String  getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return area + "," + position + "," + code + "," + name + "," + mode + ", " + number + "," +String.format("%.2f,%.2f",price,totalPrice)+ ","+ buyDate + "\n";
    }
    public String toString2(){
        return area + "," + name + "," + mode + ", " + number + "," +String.format("%.2f,%.2f",price,totalPrice)+ ","+ buyDate + "\n";
    }
    public String toString3(){
        return area + "," + position + "," + name + "," + mode + ", " + number + "," +String.format("%.2f,%.2f",price,totalPrice)+ ","+ buyDate + "\n";
    }
    public boolean equalsName(Property property){
       return this.name.equals(property.getName()) && this.mode.equals(property.getMode());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return Objects.equals(area, property.area) && Objects.equals(position, property.position) && Objects.equals(code, property.code) && Objects.equals(name, property.name) && Objects.equals(mode, property.mode) && Objects.equals(number, property.number) && Objects.equals(price, property.price) && Objects.equals(buyDate, property.buyDate);
    }
    public Object[] toArray(){
        Object[] o = {this.area,this.position,this.code,this.name,this.mode,this.number,this.price,this.totalPrice,this.buyDate};
        return o;
    }
    public Object[] toArray2(){
        Object[] o = {this.area,this.name,this.mode,this.number,this.price,this.totalPrice,this.buyDate};
        return o;
    }

};
