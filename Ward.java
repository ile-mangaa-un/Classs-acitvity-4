import java.io.Serializable;

public class Ward implements IResource, Serializable {
    private static final long serialVersionUID = 1L;

    private int wardNumber;
    private int capacity;
    private String wardType;
    private int occupiedBeds;   

    public Ward(int wardNumber, int capacity, String wardType) {
        this.wardNumber = wardNumber;
        this.capacity = capacity;
        this.wardType = wardType;
        this.occupiedBeds = 0;
    }

    @Override
    public boolean checkAvailability() {
        return occupiedBeds < capacity;
    }

    @Override
    public void assign() {
        if (checkAvailability()) {
            occupiedBeds++;
            System.out.println("Patient assigned to ward " + wardNumber +
                               ". Occupied beds in this ward: " + occupiedBeds);
        } else {
            System.out.println("Ward " + wardNumber + " is full.");
        }
    }

    
    public int getOccupiedBeds() { return occupiedBeds; }

   
    public static int getTotalOccupiedBeds(java.util.List<Ward> wards) {
        int total = 0;
        for (Ward w : wards) total += w.occupiedBeds;
        return total;
    }

    // ... other getters/setters unchanged ...
}
