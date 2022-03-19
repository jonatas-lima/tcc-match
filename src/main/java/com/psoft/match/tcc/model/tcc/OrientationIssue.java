package com.psoft.match.tcc.model.tcc;

import com.psoft.match.tcc.model.user.TCCMatchUser;

import javax.persistence.*;

@Entity
public class OrientationIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String relatedIssue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private TCCMatchUser user;

    @ManyToOne
    @JoinColumn(name = "tcc_id")
    private TCC tcc;

    public OrientationIssue() {}

    public OrientationIssue(String relatedIssue, TCCMatchUser user, TCC tcc) {
        this.relatedIssue = relatedIssue;
        this.user = user;
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

    public TCCMatchUser getUser() {
        return user;
    }

    public void setUser(TCCMatchUser newUser) {
        this.user = newUser;
    }

    public TCC getTcc() {
        return tcc;
    }

    public void setTcc(TCC tcc) {
        this.tcc = tcc;
    }
}
