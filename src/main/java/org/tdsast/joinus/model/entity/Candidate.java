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
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String qq;
    @Column(nullable = false)
    private String major;
    @Column(nullable = false)
    private String counselor;
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

    public Candidate(String name, String studentId, String phone, String qq, String major,
            String counselor, Club club, Department choice1, Department choice2, String reason,
            Long id, Instant createdAt, Instant updatedAt) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.studentId = studentId;
        this.phone = phone;
        this.qq = qq;
        this.major = major;
        this.counselor = counselor;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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
                && Objects.equals(phone, candidate.phone) && Objects.equals(qq, candidate.qq)
                && Objects.equals(major, candidate.major)
                && Objects.equals(counselor, candidate.counselor)
                && Objects.equals(club, candidate.club)
                && Objects.equals(choice1, candidate.choice1)
                && Objects.equals(choice2, candidate.choice2)
                && Objects.equals(reason, candidate.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, studentId, phone, qq, major, counselor, club,
                choice1, choice2, reason);
    }
}
