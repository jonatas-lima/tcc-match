package com.psoft.match.tcc.model.tcc;

import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.user.Student;

import javax.persistence.*;

@Entity
public class OrientationIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String relatedIssue;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "tcc_id")
    private TCC tcc;

    public OrientationIssue() {}

    public OrientationIssue(String relatedIssue, Student student, TCC tcc) {
        this.relatedIssue = relatedIssue;
        this.student = student;
        this.tcc = tcc;
    }

    public Long getId() {
        return id;
    }

    public String getRelatedIssue() {
        return relatedIssue;
    }

    public void setRelatedIssue(String relatedIssue) {
        this.relatedIssue = relatedIssue;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public TCC getTcc() {
        return tcc;
    }

    public void setTcc(TCC tcc) {
        this.tcc = tcc;
    }
}
