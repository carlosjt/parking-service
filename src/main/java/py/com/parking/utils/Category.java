package py.com.parking.utils;

public enum Category {
    student("student", "08:00-18:00"),
    teacher("teacher", "07:00-20:00"),
    staff("staff", "06:00-22:00");

    private String name;
    private String accessHours;

    Category(String name, String accessHours) {
        this.name = name;
        this.accessHours = accessHours;
    }

    public String getName() {
        return name;
    }

    public String getAccessHours() {
        return accessHours;
    }
}
