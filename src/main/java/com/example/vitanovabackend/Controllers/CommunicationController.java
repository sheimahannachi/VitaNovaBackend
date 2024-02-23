package com.example.vitanovabackend.Controllers;

import com.example.vitanovabackend.DAO.Entities.Communication;

import com.example.vitanovabackend.Service.ICommunicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommunicationController {

    ICommunicationService service;


    @PostMapping("addCommunication")
    public Communication addCommunication (@RequestBody  Communication communication){
        return service.addCommunication(communication);
    }

    @PutMapping("updateCommunication")
    public Communication updateCommunication (@RequestParam long id ,@RequestBody Communication communication){
        return service.updateCommunication(id,communication);
    }

    @DeleteMapping("deleteCommunication")
    public void deleteCommunication(@RequestParam long id){
        service.deleteCommunication(id);
    }

    @GetMapping("findCommunication")
    public Communication findCommunication(@RequestParam long id){
        return service.findCommunication(id);
    }

    @GetMapping("findAllCommunications")
    public List<Communication> findAllComunications(){
        return service.findallCommunications();
    }



}
