package com.example.vitanovabackend.AllServices;

import com.example.vitanovabackend.DAO.Entities.PersonalGoals;

public interface IPersonalGoalsService {

    PersonalGoals AddPersonalGoal (PersonalGoals personalGoals);
    int DeleteGoal(Long Id);

    PersonalGoals updateGoal(PersonalGoals personalGoals);

}
