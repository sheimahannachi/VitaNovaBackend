package com.example.vitanovabackend.Service;

import com.example.vitanovabackend.DAO.Entities.Communication;

import java.util.List;

public interface ICommunicationService {

     Communication addCommunication ( Communication communication);

     Communication updateCommunication ( long id , Communication communication);

     void deleteCommunication(long id);

     Communication findCommunication(long id);

     List<Communication> findallCommunications();
}
