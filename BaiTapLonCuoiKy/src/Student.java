public class Student extends Person {
    private String major;

    public Student(String name, String id, boolean gender, String major) {
        super(name, id, gender);
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public void displayInfo() {
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Gender: " + (getGender() ? "Male" : "Female"));
        System.out.println("Major: " + getMajor());
    }
}
