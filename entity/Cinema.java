/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Khach
 */
public class Cinema {
    private int id;
    private String cinemaClass;
    private String name;
    private int cineplexId;
    private String cinemaCode;
    private int layoutTemplateId;

    public Cinema() {
    }

    public Cinema(int id, String cinemaClass, String name, int cineplexId, String cinemaCode, int layoutTemplateId) {
        this.id = id;
        this.cinemaClass = cinemaClass;
        this.name = name;
        this.cineplexId = cineplexId;
        this.cinemaCode = cinemaCode;
        this.layoutTemplateId = layoutTemplateId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCinemaClass() {
        return cinemaClass;
    }

    public void setCinemaClass(String cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCineplexId() {
        return cineplexId;
    }

    public void setCineplexId(int cineplexId) {
        this.cineplexId = cineplexId;
    }

    public String getCinemaCode() {
        return cinemaCode;
    }

    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }
    
    public int getLayoutTemplateId() {
        return layoutTemplateId;
    }
    public void setLayoutTemplateId(int layoutTemplateId) {
        this.layoutTemplateId = layoutTemplateId;
    }
}
