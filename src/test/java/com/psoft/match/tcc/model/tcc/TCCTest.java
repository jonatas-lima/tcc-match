package com.psoft.match.tcc.model.tcc;

import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TCCTest {

    private TCC approvedTCC;
    private TCC ongoingTCC;
    private TCC pendingTCC;
    private TCC finishedTCC;

    @BeforeEach
    public void setUp() {
        this.approvedTCC = new TCC();
        this.approvedTCC.setId(1L);
        this.approvedTCC.setTccStatus(TCCStatus.APPROVED);
        this.approvedTCC.setAuthor(new Professor());

        this.ongoingTCC = new TCC();
        this.ongoingTCC.setId(2L);
        this.ongoingTCC.setTccStatus(TCCStatus.ON_GOING);
        this.ongoingTCC.setAuthor(new Professor());

        this.pendingTCC = new TCC();
        this.pendingTCC.setId(3L);
        this.pendingTCC.setTccStatus(TCCStatus.PENDING_APPROVAL);
        this.pendingTCC.setAuthor(new Student());
        this.pendingTCC.setAdvisedStudent(new Student());

        this.finishedTCC = new TCC();
        this.finishedTCC.setId(4L);
        this.finishedTCC.setTccStatus(TCCStatus.FINISHED);
        this.finishedTCC.setAuthor(new Student());
        this.finishedTCC.setTerm("2021.2");
        this.finishedTCC.setAdvisedStudent(new Student());
        this.finishedTCC.setAdvisor(new Professor());
    }

    @Test
    public void test_is_created_by() {
        Assertions.assertTrue(this.pendingTCC.isCreatedByStudent());
        Assertions.assertTrue(this.approvedTCC.isCreatedByProfessor());
        Assertions.assertFalse(this.finishedTCC.isCreatedByProfessor());
    }

    @Test
    public void test_is_available() {
        Assertions.assertFalse(this.finishedTCC.isAvailable());
        Assertions.assertTrue(this.pendingTCC.isAvailable());
    }

    @Test
    public void test_approve_tcc() {
        Assertions.assertEquals(TCCStatus.PENDING_APPROVAL, this.pendingTCC.getTccStatus());
        this.pendingTCC.approveTCC();
        Assertions.assertEquals(TCCStatus.ON_GOING, this.pendingTCC.getTccStatus());
    }

    @Test
    public void test_finish_tcc() {
        Assertions.assertEquals(TCCStatus.ON_GOING, this.ongoingTCC.getTccStatus());
        this.ongoingTCC.finalizeTCC();
        Assertions.assertEquals(TCCStatus.FINISHED, this.ongoingTCC.getTccStatus());
    }

    @Test
    public void test_to_email_format() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("[INFORMAÇÕES]\n")
                .append("Título do TCC: null").append("\n")
                .append("Descrição: null").append("\n");

        Assertions.assertTrue(this.ongoingTCC.toEmailFormat().contains(sb.toString()));
    }
}
