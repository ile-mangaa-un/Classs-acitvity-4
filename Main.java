import java.util.*;
import java.io.*;

public class Main {
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Nurse> nurses = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();
    private static List<Ward> wards = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String DATA_FILE = "hospital_data.ser";

    public static void main(String[] args) {
      
        loadDataFromFile();

        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");
            try {
                switch (choice) {
                    case 1:
                        addDoctor();
                        break;
                    case 2:
                        addNurse();
                        break;
                    case 3:
                        addPatient();
                        break;
                    case 4:
                        assignPatientToWard();
                        break;
                    case 5:
                        displayAll();
                        break;
                    case 6:
                        saveDataDemo();
                        break;
                    case 7:
                        deleteDataDemo();
                        break;
                    case 8:
                        saveAllDataToFile();
                        break;
                    case 9:
                        loadDataFromFile();
                        break;
                    case 10:
                        exit = true;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 10.");
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n==== Hospital Management System ====");
        System.out.println("1. Add Doctor");
        System.out.println("2. Add Nurse");
        System.out.println("3. Add Patient");
        System.out.println("4. Assign Patient to Ward");
        System.out.println("5. Display All Records");
        System.out.println("6. Save Data (Demo)");
        System.out.println("7. Delete Data (Demo)");
        System.out.println("8. Save All Data to File");
        System.out.println("9. Load All Data from File");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

   
    private static int getIntInput(String prompt) { ... }
    private static String getStringInput(String prompt) { ... }

    
    private static void addDoctor() { ... }
    private static void addNurse() { ... }
    private static void addPatient() { ... }

    
    private static void assignPatientToWard() { ... }

    
    private static void displayAll() {
        System.out.println("\n--- All Doctors ---");
        if (doctors.isEmpty()) System.out.println("None");
        else for (Doctor d : doctors) {
            System.out.println(d.getName() + " (" + d.getEmployeeID() + ") - " + d.getSpecialization());
        }

        System.out.println("\n--- All Nurses ---");
        if (nurses.isEmpty()) System.out.println("None");
        else for (Nurse n : nurses) {
            System.out.println(n.getName() + " (" + n.getEmployeeID() + ") - " + n.getDepartment());
        }

        System.out.println("\n--- All Patients ---");
        if (patients.isEmpty()) System.out.println("None");
        else for (Patient p : patients) {
            System.out.println(p.getName() + " (" + p.getPatientID() + ") - " + p.getAilment());
        }

        System.out.println("\n--- Wards ---");
        for (Ward w : wards) {
            System.out.println("Ward " + w.getWardNumber() + " (" + w.getWardType() + ") - " +
                               w.getOccupiedBeds() + "/" + w.getCapacity() + " beds occupied");
        }
        System.out.println("Total people in system: " + Person.getTotalPeopleCount());
        System.out.println("Total occupied beds: " + Ward.getTotalOccupiedBeds(wards));
    }

    
    private static void saveDataDemo() { ... }
    private static void deleteDataDemo() { ... }

   
    private static void saveAllDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
          
            oos.writeObject(doctors);
            oos.writeObject(nurses);
            oos.writeObject(patients);
            oos.writeObject(wards);
            System.out.println("All data saved successfully to " + DATA_FILE);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

   
    @SuppressWarnings("unchecked")
    private static void loadDataFromFile() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("No saved data file found. Starting with empty system.");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
          
            doctors = (List<Doctor>) ois.readObject();
            nurses = (List<Nurse>) ois.readObject();
            patients = (List<Patient>) ois.readObject();
            wards = (List<Ward>) ois.readObject();

            int totalPeople = doctors.size() + nurses.size() + patients.size();
           
            try {
                java.lang.reflect.Field field = Person.class.getDeclaredField("totalPeopleCount");
                field.setAccessible(true);
                field.setInt(null, totalPeople);
            } catch (Exception e) {
                System.out.println("Warning: Could not update totalPeopleCount static field.");
            }

            System.out.println("Data loaded successfully from " + DATA_FILE);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
