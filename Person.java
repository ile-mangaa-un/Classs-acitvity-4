import java.io.Serializable;

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private String contactInfo;
    private static int totalPeopleCount = 0;

    
}
