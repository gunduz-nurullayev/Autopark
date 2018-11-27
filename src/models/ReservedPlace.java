
package models;


public class ReservedPlace {
    long id;
    String place;

    public ReservedPlace(long id, String place) {
        this.id = id;
        this.place = place;
    }

    public ReservedPlace(String place) {
        this.place = place;
    }

    public ReservedPlace(long id) {
        this.id = id;
    }
    
    

    public ReservedPlace() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return this.place;
    }
    
}

