package com.ajay.journalApp.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    ObjectId id;

    @Indexed(unique = true)
    @NonNull
    String userName;

    @NonNull
    String password;

    @DBRef   //  ye reference rakhega journalEntries ka
    List<JournalEntry> journalEntries = new ArrayList<>();

    List<String> roles;
}
