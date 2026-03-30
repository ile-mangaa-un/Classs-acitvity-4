import java.io.Serializable;

public class Patient extends Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String patientID;
    private String ailment;
    private double billAmount;

    
}
