package com.psoft.match.tcc.response;

import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.user.Student;

import java.util.Collection;

public class SolicitationResponse {
    private TCC tcc;

    private Collection<Student> interestedStudents;

    public SolicitationResponse(TCC tcc) {
        this.tcc = tcc;
        this.interestedStudents = tcc.getInterestedStudents();
    }

    public TCC getTcc() {
        return tcc;
    }

    public void setTcc(TCC tcc) {
        this.tcc = tcc;
    }

    public Collection<Student> getInterestedStudents() {
        return interestedStudents;
    }

    public void setInterestedStudents(Collection<Student> interestedStudents) {
        this.interestedStudents = interestedStudents;
    }
}
