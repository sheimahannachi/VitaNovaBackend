package com.example.vitanovabackend.DAO.Service;

import com.example.vitanovabackend.DAO.Entities.Challenges;

public interface IChallengesService {


    Challenges addChallenge(Challenges challenge);

    Challenges updateChallenge ( long id , Challenges challenge);

    void deleteChallenge(long id);

    Challenges findChallenges(long id);

}
