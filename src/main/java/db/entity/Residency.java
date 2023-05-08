package db.entity;

import java.util.Objects;

public class Residency implements Cloneable {
    private Long id;
    private String code;
    private String name;

    public Residency() {}

    public Residency(Long id) {
        this.id = id;
    }

    public Residency(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Residency(Residency residency) {
        this.id = residency.id;
        this.code = residency.code;
        this.name = residency.name;
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
        Residency residency = (Residency) obj;
        return id.equals(residency.id) && name.equals(residency.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name);
    }

    @Override
    public String toString() {
        return "Residency{" +
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
