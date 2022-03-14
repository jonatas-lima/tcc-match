package com.psoft.match.tcc.model.tcc;

import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.user.Professor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Collection;

@Entity
public class TCC extends TCCProposal {

    @ManyToOne
    private Professor professor;

    public TCC(String title, String description, Professor professor) {
        super(title, description);
        this.professor = professor;
    }

    public TCC(String title, String description, Professor professor, Collection<StudyArea> studyAreas) {
        super(title, description, studyAreas);
        this.professor = professor;
    }

    public TCC(TCCProposal tccProposal, Professor professor) {
        this(tccProposal.getTitle(), tccProposal.getDescription(), professor);
    }

    public TCC() {
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public boolean isAvailable() {
        return this.professor == null || this.getStudent() == null;
    }

    public String toEmailFormat() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("[INFORMAÇÕES]\n")
                .append("Título: ").append(getTitle()).append("\n")
                .append("Professor: ").append(getProfessor().getFullName()).append("\n");

        getStudyAreas().forEach(studyArea -> sb.append("- ").append(studyArea.getDescription()).append("\n"));

        return sb.toString();
    }
}