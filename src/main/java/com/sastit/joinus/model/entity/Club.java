package com.sastit.joinus.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "t_clubs")
public class Club extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Department> departments;

    public Club() {
    }

    public Club(String name, Set<Department> departments, Long id, Instant createdAt, Instant updatedAt) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.departments = departments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Club club = (Club) o;
        return Objects.equals(name, club.name) && Objects.equals(departments, club.departments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, departments);
    }
}
