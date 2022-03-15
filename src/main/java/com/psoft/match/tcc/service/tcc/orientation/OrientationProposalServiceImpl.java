package com.psoft.match.tcc.service.tcc.orientation;

import com.psoft.match.tcc.model.tcc.orientation.OrientationProposal;
import com.psoft.match.tcc.model.user.Student;
import com.psoft.match.tcc.repository.tcc.orientation.OrientationProposalRepository;
import com.psoft.match.tcc.service.email.EmailService;
import com.psoft.match.tcc.service.tcc.orientation.OrientationProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrientationProposalServiceImpl implements OrientationProposalService {

    @Autowired
    private OrientationProposalRepository orientationProposalRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    @Override
    public OrientationProposal saveTccProposal(OrientationProposal orientationProposal) {
        Student student = orientationProposal.getInterestedProposal().getStudent();
        emailService.notifyInterestedTCCProposal(student, orientationProposal.getInterestedProposal());
        return orientationProposalRepository.save(orientationProposal);
    }
}
