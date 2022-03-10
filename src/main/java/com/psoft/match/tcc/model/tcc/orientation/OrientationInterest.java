package com.psoft.match.tcc.model.tcc.orientation;

import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.user.Student;

import javax.persistence.*;

@Entity
public class OrientationInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student interestedStudent;

    @ManyToOne
    private TCC interestedTCC;

    public OrientationInterest() {}

    public OrientationInterest(Student interestedStudent, TCC interestedTCC) {
        this.interestedStudent = interestedStudent;
        this.interestedTCC = interestedTCC;
    }

    public Long getId() {
        return id;
    }

    public Student getInterestedStudent() {
        return interestedStudent;
    }

    public void setInterestedStudent(Student interestedProfessor) {
        this.interestedStudent = interestedProfessor;
    }

    public TCC getInterestedTCC() {
        return interestedTCC;
    }

    public void setInterestedTCC(TCC interestedTCC) {
        this.interestedTCC = interestedTCC;
    }
}
