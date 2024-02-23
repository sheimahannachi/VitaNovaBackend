package com.example.vitanovabackend.DAO.Service;

import com.example.vitanovabackend.DAO.Entities.Communication;
import com.example.vitanovabackend.DAO.Repositories.CommunicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CommunicationService implements  ICommunicationService{

    CommunicationRepository repository;

    @Override
    public Communication addCommunication(Communication communication) {

        communication.setSentDate(LocalDate.now());
        return repository.save(communication) ;
    }

    @Override
    public Communication updateCommunication(long id, Communication communication) {
        Communication communication1 = repository.findById(id).orElse(null);
        if(communication1==null)
            return null;
        communication.setId(id);
        communication.setStatus(false);

        return repository.save(communication);
    }

    @Override
    public void deleteCommunication(long id) {

        repository.deleteById(id);

    }

    @Override
    public Communication findCommunication(long id) {

        Communication communication= repository.findById(id).orElse(null);
        if(communication!=null && !communication.isStatus()) {
            communication.setStatus(true);
        }

        return communication;
    }
}
