import java.io.Serializable;

public abstract class Employee extends Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String employeeID;
    private boolean isAvailable;

  
}
