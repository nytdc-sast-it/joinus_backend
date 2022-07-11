package org.tdsast.joinus.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "t_departments")
public class Department extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @OneToOne
    @JoinColumn(nullable = false)
    private Club club;

    public Department() {}

    public Department(String name, Club club, Long id, Instant createdAt, Instant updatedAt) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.club = club;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name) && Objects.equals(club, that.club);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, club);
    }
}
