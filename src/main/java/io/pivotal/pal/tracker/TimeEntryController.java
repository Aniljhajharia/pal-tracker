package io.pivotal.pal.tracker;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;
    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

    public TimeEntryController(TimeEntryRepository timeEntryRepository, MeterRegistry meterRegistry) {

        this.timeEntryRepository = timeEntryRepository;
        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
    }
@PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        TimeEntry timeEntry1=timeEntryRepository.create(timeEntry);
        /*return ResponseEntity.status(HttpStatus.CREATED).body(timeEntry1);*/
        return new ResponseEntity<>(timeEntry1,HttpStatus.CREATED);

    }

@GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry2=timeEntryRepository.find(id);

        if(timeEntry2==null)
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(timeEntry2);
        return new ResponseEntity<>(timeEntry2,HttpStatus.NOT_FOUND);
        else
            //return ResponseEntity.status(HttpStatus.OK).body(timeEntry2);
            return new ResponseEntity<>(timeEntry2,HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {

        List<TimeEntry> list=new ArrayList<>();
        list=timeEntryRepository.list();


        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
@PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id,@RequestBody TimeEntry timeEntry) {
        TimeEntry timeEntry2=timeEntryRepository.update(id,timeEntry);
        if(timeEntry2==null)
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    return  ResponseEntity.status(HttpStatus.OK).body(timeEntry2);
    }
@DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {

        timeEntryRepository.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
