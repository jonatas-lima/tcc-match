package com.psoft.match.tcc.service.impl;

import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.model.tcc.TCCStatus;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;
import com.psoft.match.tcc.model.user.User;
import com.psoft.match.tcc.repository.StudyAreaRepository;
import com.psoft.match.tcc.repository.tcc.TCCProposalRepository;
import com.psoft.match.tcc.repository.tcc.TCCRepository;
import com.psoft.match.tcc.repository.user.ProfessorRepository;
import com.psoft.match.tcc.repository.user.StudentRepository;
import com.psoft.match.tcc.repository.user.UserRepository;
import com.psoft.match.tcc.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TestDBService implements DBService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudyAreaRepository studyAreaRepository;

    @Autowired
    private TCCProposalRepository tccProposalRepository;

    @Autowired
    private TCCRepository tccRepository;

    @Override
    public void seed() {
        Professor professor1 = new Professor("Elmar Melcher", "elmar@gmail.com", "elmar", "1234");
        Professor professor2 = new Professor("Francisco Vilar", "fubica@gmail.com", "fubica", "1234");
        Professor professor3 = new Professor("Joao Arthur", "joao.arthur@gmail.com", "joao_arthur", "1234");

        Student student1 = new Student("Jonatas Ferreira de Lima", "119210036", "ferreiradelimajonatas@gmail.com", "2024.2", "jonatas", "123");
        Student student2 = new Student("Bernard Dantas Odon", "123", "bernard.odon@ccc.ufcg.edu.br", "2024.2", "bodon", "123");

        List<User> users = Arrays.asList(student1, student2, professor1);

        List<Student> students = Arrays.asList(student1, student2);
        List<Professor> professors = Arrays.asList(professor1, professor2, professor3);

        userRepository.saveAll(users);
        studentRepository.saveAll(students);
        professorRepository.saveAll(professors);

        StudyArea studyArea1 = new StudyArea("Ciencia de Dados");
        StudyArea studyArea2 = new StudyArea("Inteligencia Artifical");
        StudyArea studyArea3 = new StudyArea("Engenharia de software");
        StudyArea studyArea4 = new StudyArea("Sistemas Operacionais");

        List<StudyArea> studyAreas = Arrays.asList(studyArea1, studyArea2, studyArea3, studyArea4);
        studyAreaRepository.saveAll(studyAreas);

        professor1.addInterestedStudyArea(studyArea1);
        professor1.addInterestedStudyArea(studyArea2);
        professor1.addInterestedStudyArea(studyArea3);

        professor2.addInterestedStudyArea(studyArea4);
        professor2.addInterestedStudyArea(studyArea1);

        student1.addInterestedArea(studyArea1);
        student1.addInterestedArea(studyArea2);

        student2.addInterestedArea(studyArea3);
        student2.addInterestedArea(studyArea4);

        TCCProposal tccProposal1 = new TCCProposal("Proposta 1", "descricao da proposta 1");
        TCCProposal tccProposal2 = new TCCProposal("Proposta 2", "descricao da proposta 2");
        TCCProposal tccProposal3 = new TCCProposal("Proposta 3", "descricao da proposta 3");

        List<TCCProposal> tccProposals = Arrays.asList(tccProposal1, tccProposal2, tccProposal3);
        tccProposalRepository.saveAll(tccProposals);

        tccProposal1.addStudyArea(studyArea1);
        tccProposal1.addStudyArea(studyArea2);

        tccProposal2.addStudyArea(studyArea1);

        tccProposalRepository.saveAll(tccProposals);

        TCC tcc1 = new TCC(tccProposal1, professor1);
        TCC tcc2 = new TCC(tccProposal2, professor2);

        List<TCC> tccs = Arrays.asList(tcc1, tcc2);

        tccRepository.saveAll(tccs);
        professorRepository.saveAll(professors);
        studentRepository.saveAll(students);
    }
}
