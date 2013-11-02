/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Timestamp;
import java.util.LinkedList;

/**
 *
 * @author Khach
 */
public class Booking {
    private int id;
    private String transactionId;
    private int showtimeId;
    private String customerName;
    private String customerHP;
    private String customerEmail;
    private int customerAge;
    private Timestamp time;
    private LinkedList<Integer> seatNumbers;
    private double price;

    public Booking() {
    }

    public Booking(int id, String transactionId, int showtimeId, String customerName, String customerHP, String customerEmail, int customerAge, Timestamp time, LinkedList<Integer> seatNumbers, double price) {
        this.id = id;
        this.transactionId = transactionId;
        this.showtimeId = showtimeId;
        this.customerName = customerName;
        this.customerHP = customerHP;
        this.customerEmail = customerEmail;
        this.customerAge = customerAge;
        this.time = time;
        this.seatNumbers = seatNumbers;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerHP() {
        return customerHP;
    }

    public void setCustomerHP(String customerHP) {
        this.customerHP = customerHP;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public LinkedList<Integer> getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(LinkedList<Integer> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
