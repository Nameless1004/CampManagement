package camp.entity;

public class Student {
    private Long id;
    private String name;
    private String state;

    public Student(String name){
        id = null;
        this.name = name;
        this.state = "Green";
    }

    public Student(Long id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }
}
