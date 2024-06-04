public abstract class Person {
    private String name;
    private String id;
    private boolean gender;

    public Person(String name, String id, boolean gender) {
        this.name = name;
        this.id = id;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
    public boolean getGender() {
        return gender;
    }

    public abstract void displayInfo();
}