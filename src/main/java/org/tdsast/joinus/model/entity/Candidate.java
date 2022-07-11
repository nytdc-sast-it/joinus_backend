package org.tdsast.joinus.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "t_candidates")
public class Candidate extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String studentId;
    @OneToOne
    @JoinColumn(nullable = false)
    private Club club;
    @OneToOne
    @JoinColumn(nullable = false)
    private Department choice1;
    @OneToOne
    @JoinColumn(nullable = false)
    private Department choice2;
    private String reason;

    public Candidate() {}

    public Candidate(String name, String studentId, Club club, Department choice1,
            Department choice2, String reason, Long id, Instant createdAt, Instant updatedAt) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.studentId = studentId;
        this.club = club;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Department getChoice1() {
        return choice1;
    }

    public void setChoice1(Department choice1) {
        this.choice1 = choice1;
    }

    public Department getChoice2() {
        return choice2;
    }

    public void setChoice2(Department choice2) {
        this.choice2 = choice2;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(name, candidate.name)
                && Objects.equals(studentId, candidate.studentId)
                && Objects.equals(club, candidate.club)
                && Objects.equals(choice1, candidate.choice1)
                && Objects.equals(choice2, candidate.choice2)
                && Objects.equals(reason, candidate.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, studentId, club, choice1, choice2, reason);
    }
}
