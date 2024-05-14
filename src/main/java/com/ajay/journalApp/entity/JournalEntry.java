package com.ajay.journalApp.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "journal_entries")
//@NoArgsConstructor
public class JournalEntry {

    @Id
    ObjectId id;
    String title;
    String content;
    LocalDateTime date;
}
