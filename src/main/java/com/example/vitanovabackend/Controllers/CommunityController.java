package com.example.vitanovabackend.Controllers;

import com.example.vitanovabackend.DAO.Entities.Community;
import com.example.vitanovabackend.Service.CommunityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CommunityController {

    CommunityService service;

    @PostMapping("addCommunity")
    public Community addCommmunity (@RequestBody Community community){
        return service.addCommmunity(community);

    }

    @PutMapping("updateComunity")
    public Community updateCommmunity (@RequestParam long id ,@RequestBody Community community){
        return service.updateCommmunity(id,community);
    }

    @DeleteMapping("deleteCommunity")
    public void deleteCommunity(@RequestParam long id){
        service.deleteCommunity(id);
    }

    @GetMapping("findCommunity")
    public Community findCommunity(@RequestParam long id){
        return service.findCommunity(id);
    }



}
