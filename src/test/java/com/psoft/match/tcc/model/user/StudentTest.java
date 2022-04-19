package com.psoft.match.tcc.model.user;

import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCStatus;
import com.psoft.match.tcc.model.user.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentTest {

    private Student student;
    private TCC interestedTCC1;
    private TCC interestedTCC2;
    private TCC registeredTCC;
    private StudyArea studyArea;

    @BeforeEach
    public void setUp() {
        this.student = new Student();

        this.interestedTCC1 = new TCC(null, "tcc1", "descp1", TCCStatus.APPROVED);
        this.interestedTCC1.setId(1L);

        this.interestedTCC2 = new TCC(null, "tcc2", "descp2", TCCStatus.APPROVED);
        this.interestedTCC2.setId(2L);

        this.registeredTCC = new TCC(student, "registered tcc", "descp reg", TCCStatus.PENDING_APPROVAL);
        this.registeredTCC.setId(3L);

        this.studyArea = new StudyArea();
        this.studyArea.setId(1L);

        this.student.addOrientationInterest(interestedTCC1);
        this.student.addOrientationInterest(interestedTCC2);
        this.student.registerTCC(registeredTCC);
        this.student.addInterestedArea(studyArea);
    }

    @Test
    public void should_add_orientation_interest() {
        TCC tcc = new TCC();

        boolean added = this.student.addOrientationInterest(tcc);

        Assertions.assertTrue(added);
        Assertions.assertTrue(this.student.getOrientationInterests().contains(tcc));
    }

    @Test
    public void should_not_add_existing_orientation_interest() {
        boolean added = this.student.addOrientationInterest(this.interestedTCC1);

        Assertions.assertFalse(added);
        Assertions.assertTrue(this.student.getOrientationInterests().contains(interestedTCC1));
    }

    @Test
    public void should_remove_existing_orientation_interest() {
        boolean removed = this.student.removeOrientationInterest(this.interestedTCC1);

        Assertions.assertTrue(removed);
    }

    @Test
    public void should_not_remove_nonexistent_orientation_interest() {
        boolean removed = this.student.removeOrientationInterest(new TCC());
        Assertions.assertFalse(removed);
    }

    @Test
    public void should_add_new_interested_study_area() {
        StudyArea newStudyArea = new StudyArea();
        newStudyArea.setId(2L);
        boolean added = this.student.addInterestedArea(newStudyArea);

        Assertions.assertTrue(added);
        Assertions.assertTrue(this.student.getInterestedStudyAreas().contains(newStudyArea));
    }

    @Test
    public void should_not_add_existing_interested_study_area() {
        boolean added = this.student.addInterestedArea(studyArea);

        Assertions.assertFalse(added);
        Assertions.assertTrue(this.student.getInterestedStudyAreas().contains(studyArea));
    }

    @Test
    public void should_remove_existing_interested_study_area() {
        boolean removed = this.student.removeInterestedArea(studyArea);

        Assertions.assertTrue(removed);
        Assertions.assertFalse(this.student.getInterestedStudyAreas().contains(studyArea));
    }

    @Test
    public void should_not_remove_nonexistent_interested_study_area() {
        StudyArea studyArea = new StudyArea();
        studyArea.setId(2L);
        boolean removed = this.student.removeInterestedArea(studyArea);

        Assertions.assertFalse(removed);
        Assertions.assertFalse(this.student.getInterestedStudyAreas().contains(studyArea));
    }
}
