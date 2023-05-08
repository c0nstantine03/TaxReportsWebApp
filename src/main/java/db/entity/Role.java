package db.entity;


import java.util.Objects;

public class Role implements Cloneable{
    private Long id;
    private String code;
    private String name;

    public Role() {}

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Role(Role role) {
        this.id = role.id;
        this.code = role.code;
        this.name = role.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Role role = (Role) obj;
        return id.equals(role.id) && name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
