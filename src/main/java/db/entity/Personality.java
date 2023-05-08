package db.entity;

import java.util.Objects;

public class Personality implements Cloneable {
    private Long id;
    private String code;
    private String name;

    public Personality() {}

    public Personality(Long id) {
        this.id = id;
    }

    public Personality(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Personality(Personality personality) {
        this.id = personality.id;
        this.code = personality.code;
        this.name = personality.name;
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
        Personality personality = (Personality) obj;
        return id.equals(personality.id) && name.equals(personality.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name);
    }

    @Override
    public String toString() {
        return "Personality{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
