package com.ajay.journalApp.service;

import com.ajay.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface JournalEntryService {
    void createEntryForUser(JournalEntry journalEntry,String userName);

    List<JournalEntry> getAllJournalEntriesOfUser(String userName);

    Optional<JournalEntry> getJournalEntryById(ObjectId myId);

    boolean deleteJournalEntryById(String userName, ObjectId myId);

    JournalEntry updateJournalById(ObjectId id, JournalEntry journalEntry, String userName);
}
