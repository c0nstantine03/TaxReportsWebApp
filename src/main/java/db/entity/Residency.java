package db.entity;

import java.util.Objects;

public class Residency implements java.io.Serializable {
    private Long id;
    private String name;

    public Residency() {}
    public Residency(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Residency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
