package com.dirsys.worktest.controller;

import com.dirsys.worktest.service.WorkTestService;
import com.dirsys.worktest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkTestController {

    @Autowired
    private WorkTestService workTestService;
    @Autowired
    private UserService userService;



    @PreAuthorize("isAuthenticated()")
    @GetMapping("/population/landscape")
    public ResponseEntity<?> getPopulationPerLandscapeBetweenSomeYears(){
        return new ResponseEntity<>(workTestService.getPopulationPerLandscapeBetweenSomeYears(), HttpStatus.OK);
    }

    @GetMapping("/population/landscape/test")
    public ResponseEntity<?> populationPerLandscapeBetweenSomeYears(){
        return new ResponseEntity<>(workTestService.populationPerLandscapeBetweenSomeYears(), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/population/percentage")
    public ResponseEntity<?> getPercentageChangeBetweenSomeYears() {
        return new ResponseEntity<>(workTestService.getPercentageChangeInPopulationBetweenSomeYears(), HttpStatus.OK);
    }


    //@PreAuthorize("isAuthenticated()")
    @PreAuthorize("permitAll()")
    @PostMapping(value = "/sort/population/year")
    public ResponseEntity<?> getSortedPopulationPerLandscapeForEachYear(@RequestBody String year) {
        return new ResponseEntity<>(workTestService.sortPopulationPerLandscapeForEachYear(year), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/all")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


//    @RequestMapping("/user")
//    @ResponseBody
//    public Principal user(Principal principal){
//        return principal;
//    }


}
