package com.psoft.match.tcc.model.user;

import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ProfessorTest {

    private Professor professor;
    private TCC tcc1;
    private TCC tcc2;
    private TCC registeredTCC;
    private StudyArea studyArea;

    @BeforeEach
    public void setUp() {
        this.professor = new Professor("prof", "prof@gmail.com", "prof", "123", new ArrayList<>(), 2);

        this.tcc1 = new TCC();
        this.tcc1.setId(1L);
        this.tcc1.setTccStatus(TCCStatus.PENDING_APPROVAL);

        this.tcc2 = new TCC();
        this.tcc2.setId(2L);
        this.tcc2.setTccStatus(TCCStatus.PENDING_APPROVAL);

        this.registeredTCC = new TCC();
        this.registeredTCC.setId(3L);
        this.registeredTCC.setTccStatus(TCCStatus.APPROVED);

        this.studyArea = new StudyArea();
        this.studyArea.setId(1L);

        this.professor.registerTCC(registeredTCC);
        this.professor.addOrientationInterest(tcc1);
        this.professor.addOrientationInterest(tcc2);
        this.professor.addInterestedStudyArea(studyArea);
    }

    @Test
    public void should_add_orientation_interest() {
        TCC newTCC = new TCC();
        newTCC.setId(5L);

        boolean added = this.professor.addOrientationInterest(newTCC);

        Assertions.assertTrue(added);
        Assertions.assertTrue(this.professor.getInterestedTCCs().contains(newTCC));
    }

    @Test
    public void should_not_add_existent_orientation_interest() {
        boolean added = this.professor.addOrientationInterest(tcc1);

        Assertions.assertFalse(added);
        Assertions.assertTrue(this.professor.getInterestedTCCs().contains(tcc1));
    }

    @Test
    public void should_add_interested_study_area() {
        StudyArea studyArea = new StudyArea();
        studyArea.setId(2L);
        boolean added = this.professor.addInterestedStudyArea(studyArea);

        Assertions.assertTrue(added);
        Assertions.assertTrue(this.professor.getInterestedStudyAreas().contains(studyArea));
    }

    @Test
    public void should_not_add_existent_interested_study_area() {
        boolean added = this.professor.addInterestedStudyArea(studyArea);

        Assertions.assertFalse(added);
        Assertions.assertTrue(this.professor.getInterestedStudyAreas().contains(studyArea));
    }

    @Test
    public void should_remove_interested_study_area() {
        boolean removed = this.professor.removeInterestedStudyArea(studyArea);

        Assertions.assertTrue(removed);
        Assertions.assertFalse(this.professor.getInterestedStudyAreas().contains(studyArea));
    }

    @Test
    public void should_not_remove_nonexistent_interested_study_area() {
        StudyArea studyArea = new StudyArea();
        studyArea.setId(40L);
        boolean removed = this.professor.removeInterestedStudyArea(studyArea);

        Assertions.assertFalse(removed);
        Assertions.assertFalse(this.professor.getInterestedStudyAreas().contains(studyArea));
    }

    @Test
    public void should_register_tcc() {
        TCC newTCC = new TCC();
        newTCC.setId(6L);

        boolean registered = this.professor.registerTCC(newTCC);

        Assertions.assertTrue(registered);
        Assertions.assertTrue(this.professor.getRegisteredTCCs().contains(newTCC));
    }

    @Test
    public void should_not_register_existing_tcc() {
        boolean registered = this.professor.registerTCC(registeredTCC);

        Assertions.assertFalse(registered);
        Assertions.assertTrue(this.professor.getRegisteredTCCs().contains(registeredTCC));
    }

    @Test
    public void test_is_available() {
        Assertions.assertTrue(this.professor.isAvailable());

        TCC newTCC = new TCC();
        newTCC.setId(6L);
        newTCC.setTccStatus(TCCStatus.ON_GOING);

        TCC newTCC2 = new TCC();
        newTCC2.setId(7L);
        newTCC2.setTccStatus(TCCStatus.ON_GOING);

        this.professor.registerTCC(newTCC);
        this.professor.registerTCC(newTCC2);

        Assertions.assertFalse(this.professor.isAvailable());
    }

    @Test
    public void test_get_ongoing_tcc() {
        Assertions.assertTrue(this.professor.getOnGoingTCCs().isEmpty());

        TCC newTCC = new TCC();
        newTCC.setId(6L);
        newTCC.setTccStatus(TCCStatus.ON_GOING);

        TCC newTCC2 = new TCC();
        newTCC2.setId(7L);
        newTCC2.setTccStatus(TCCStatus.ON_GOING);

        this.professor.registerTCC(newTCC);
        this.professor.registerTCC(newTCC2);

        Assertions.assertFalse(this.professor.getOnGoingTCCs().isEmpty());
        Assertions.assertEquals(2, this.professor.getOnGoingTCCs().size());
        Assertions.assertTrue(this.professor.getOnGoingTCCs().contains(newTCC));
        Assertions.assertTrue(this.professor.getOnGoingTCCs().contains(newTCC2));
    }

    @Test
    public void test_have_shared_interests_with_student() {
        Student student = new Student();
        student.addInterestedArea(studyArea);

        Assertions.assertTrue(this.professor.hasSharedInterestedWith(student));

        student.removeInterestedArea(studyArea);
        Assertions.assertFalse(this.professor.hasSharedInterestedWith(student));
    }

}
