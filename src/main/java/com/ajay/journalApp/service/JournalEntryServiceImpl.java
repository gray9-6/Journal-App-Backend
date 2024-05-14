package com.ajay.journalApp.service;

import com.ajay.journalApp.entity.JournalEntry;
import com.ajay.journalApp.entity.User;
import com.ajay.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//@Service we can use this as well
@Component
@Slf4j
public class JournalEntryServiceImpl implements JournalEntryService {

    @Autowired
    JournalEntryRepository journalEntryRepository;

    @Autowired
    UserService userService;

    @Transactional
    public void createEntryForUser(JournalEntry journalEntry,String userName){
        try {
            // get the user
            User user = userService.getUserByName(userName);

            // save the journal
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry savedJournal = journalEntryRepository.save(journalEntry);

            // after save the journal, after saving journal add that journal to the user
            user.getJournalEntries().add(savedJournal);
            userService.saveUser(user);
        }catch (Exception e){
            log.error("Exception ",e);
            throw new RuntimeException("An error occurred while saving the entry. " + e);
        }
    }

    public void createEntryForUser(JournalEntry journalEntry){
        try {
            JournalEntry savedJournal = journalEntryRepository.save(journalEntry);
        }catch (Exception e){
            log.error("Exception ",e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<JournalEntry> getAllJournalEntriesOfUser(String userName) {
        User user = userService.getUserByName(userName);
        return user.getJournalEntries();
    }

    @Override
    public Optional<JournalEntry> getJournalEntryById(ObjectId myId) {
        return journalEntryRepository.findById(myId);
    }

    @Override
    public boolean deleteJournalEntryById(String userName, ObjectId myId) {
        // remove the journal from this user
        User user = userService.getUserByName(userName);
        user.getJournalEntries().removeIf(journalEntry -> journalEntry.getId().equals(myId));
        userService.saveUser(user);

        // now remove the journal from journal collections
        journalEntryRepository.deleteById(myId);
        return true;
    }

    @Override
    public JournalEntry updateJournalById(ObjectId id, JournalEntry newEntry, String userName) {
        Optional<JournalEntry> optionalOldJournalEntry = getJournalEntryById(id);
        if(optionalOldJournalEntry.isPresent()){
            optionalOldJournalEntry.get().setTitle(newEntry.getTitle());
            optionalOldJournalEntry.get().setContent(newEntry.getContent());

            // after updating the field , save the entry
            createEntryForUser(optionalOldJournalEntry.get());
        }

        return optionalOldJournalEntry.get();
    }
}
