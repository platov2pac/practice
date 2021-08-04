package dto;

public class Role {
    private int id;
    private String name;

    public Role(String name) {
        this.name = name;
        if(name.equals("admin")){
            this.id = 1;
        }
        if(name.equals("user")){
            this.id = 2;
        }
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
