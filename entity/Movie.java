/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Khach
 */
public class Movie {
    private int id;
    private String type;
    private String name;
    private String status;
    private double rating;
    
    public Movie() {
    }

    public Movie(int id, String type, String name, String status, double rating) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.status = status;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
