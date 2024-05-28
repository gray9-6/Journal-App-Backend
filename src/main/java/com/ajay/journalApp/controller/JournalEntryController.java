package com.ajay.journalApp.controller;

import com.ajay.journalApp.entity.JournalEntry;
import com.ajay.journalApp.service.JournalEntryService;
import com.ajay.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    UserService userService;


    @GetMapping("/{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(@PathVariable String userName){
        try {
            return new ResponseEntity<>(journalEntryService.getAllJournalEntriesOfUser(userName),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntryForUser(@RequestBody JournalEntry journalEntry,@PathVariable String userName){
        try {
            journalEntryService.createEntryForUser(journalEntry,userName);
            return new ResponseEntity<>(journalEntry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable("myId") ObjectId myId){
        try {
            Optional<JournalEntry> optionalJournalEntry = journalEntryService.getJournalEntryById(myId);
            if(optionalJournalEntry.isPresent()){
                return new ResponseEntity<>(optionalJournalEntry.get(), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/id/{myId}/{userName}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId,@PathVariable String userName){
        try {
            journalEntryService.deleteJournalEntryById(userName,myId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/id/{myId}/{userName}")
    public ResponseEntity<?> updateJournalById(@PathVariable("myId") ObjectId myId,
                                               @RequestBody JournalEntry newEntry,@PathVariable String userName){
        try {
            JournalEntry updatedJournalEntry = journalEntryService.updateJournalById(myId,newEntry,userName);
            return new ResponseEntity<>(updatedJournalEntry,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
