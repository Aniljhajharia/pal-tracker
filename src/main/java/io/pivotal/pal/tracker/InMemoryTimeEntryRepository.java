package io.pivotal.pal.tracker;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> entries = new HashMap<>();
    private long idValue = 1;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
//
        timeEntry.setId(idValue++);
        entries.put(timeEntry.getId(),timeEntry);

        return timeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return entries.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {

        List<TimeEntry> mylist=new ArrayList<>(entries.values());

        return mylist;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        if(entries.get(id)==null)
        return null;
        else
        {
            entries.put(id,timeEntry);
            return timeEntry;
        }
    }

    @Override
    public void delete(long timeEntryId) {
        entries.remove(timeEntryId);

    }
}
