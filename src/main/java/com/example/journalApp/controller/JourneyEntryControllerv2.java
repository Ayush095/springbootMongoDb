package com.example.journalApp.controller;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/journal")
@RestController
public class JourneyEntryControllerv2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry newEntry) {
        journalEntryService.saveEntry(newEntry);
        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteJournalDoc(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }
    @PutMapping("id/{id}")
    public JournalEntry updateJournalDoc(@PathVariable ObjectId id, @RequestBody JournalEntry updatedEntry){
        JournalEntry oldJournalDoc = journalEntryService.findById(id).orElse(null);
        if(oldJournalDoc!=null){
            String newContent = updatedEntry.getContent();
            String newTitle = updatedEntry.getTitle();
            oldJournalDoc.setTitle(newTitle != null && !newTitle.equals("") ? newTitle : oldJournalDoc.getTitle());
            oldJournalDoc.setContent(newContent!=null && !newContent.equals("") ? newContent : oldJournalDoc.getContent());
        }
        journalEntryService.saveEntry(oldJournalDoc);
        return oldJournalDoc;
    }

}
