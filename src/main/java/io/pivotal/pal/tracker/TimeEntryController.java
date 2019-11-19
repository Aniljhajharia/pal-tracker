package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository = timeEntryRepository;
    }
@PostMapping
    public ResponseEntity<TimeEntry> create(TimeEntry timeEntry) {
    TimeEntry timeEntry1=timeEntryRepository.create(timeEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(timeEntry1);
    }
@GetMapping({"timeEntryId"})
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry2=timeEntryRepository.find(timeEntryId);

        if(timeEntry2==null)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(timeEntry2);
        else
            return ResponseEntity.status(HttpStatus.OK).body(timeEntry2);

    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list() {

        List<TimeEntry> list=new ArrayList<>();
        list=timeEntryRepository.list();


        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
@PutMapping({"timeEntryId"})
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId,@RequestBody TimeEntry timeEntry) {
        TimeEntry timeEntry2=timeEntryRepository.update(timeEntryId,timeEntry);
        if(timeEntry2==null)
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    return  ResponseEntity.status(HttpStatus.OK).body(timeEntry2);
    }
@DeleteMapping({"timeEntryId"})
    public ResponseEntity delete(@PathVariable long timeEntryId) {

        timeEntryRepository.delete(timeEntryId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
