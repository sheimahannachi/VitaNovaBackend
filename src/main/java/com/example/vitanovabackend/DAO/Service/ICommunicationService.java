package com.example.vitanovabackend.DAO.Service;

import com.example.vitanovabackend.DAO.Entities.Communication;

public interface ICommunicationService {

     Communication addCommunication ( Communication communication);

     Communication updateCommunication ( long id , Communication communication);

     void deleteCommunication(long id);

     Communication findCommunication(long id);
}
