package com.psoft.match.tcc.model.tcc.orientation;

import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.model.user.Professor;

import javax.persistence.*;

@Entity
public class OrientationProposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Professor interestedProfessor;

    @ManyToOne
    private TCCProposal interestedProposal;

    public OrientationProposal() {
    }

    public OrientationProposal(Professor interestedProfessor, TCCProposal interestedProposal) {
        this.interestedProfessor = interestedProfessor;
        this.interestedProposal = interestedProposal;
    }

    public Long getId() {
        return id;
    }

    public Professor getInterestedProfessor() {
        return interestedProfessor;
    }

    public void setInterestedProfessor(Professor interestedProfessor) {
        this.interestedProfessor = interestedProfessor;
    }

    public TCCProposal getInterestedProposal() {
        return interestedProposal;
    }

    public void setInterestedProposal(TCCProposal interestedProposal) {
        this.interestedProposal = interestedProposal;
    }
}
