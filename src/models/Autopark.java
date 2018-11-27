
package models;

public class Autopark {
    String serial_num;
    String name;
    String surname;
    String phone;
    String car_num;
    String mark;
    String model;
    String color;
    String place;
   
    
    

    public Autopark(String serial_num, String name, String surname, String phone, String car_num, String mark, String model,String color,String place) {
        this.serial_num = serial_num;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.car_num = car_num;
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.place = place;
       
    }

    public Autopark(String name) {
        this.name = name;
    }

   

    public Autopark() {
    }

    public String getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    
    
 
    

   
    

   
    
}
