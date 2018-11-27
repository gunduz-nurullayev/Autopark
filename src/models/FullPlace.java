
package models;


public class FullPlace {
   
    
    long id;
    String reservedPlace;

    public FullPlace() {
    }

    public FullPlace(long id, String reservedPlace) {
        this.id = id;
        this.reservedPlace = reservedPlace;
    }

    public FullPlace(String reservedPlace) {
        this.reservedPlace = reservedPlace;
    }

  
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReservedPlace() {
        return reservedPlace;
    }

    public void setReservedPlace(String reservedPlace) {
        this.reservedPlace = reservedPlace;
    }

    @Override
    public String toString() {
        return this.reservedPlace;
    }

    
    
    

    
 

}