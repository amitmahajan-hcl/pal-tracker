package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private TimeEntry timeEntry;
    private Map timeEntries = new HashMap<Long, TimeEntry>();
    private long id = 0;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        this.timeEntry = new TimeEntry(++id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntries.put(this.timeEntry.getId(), this.timeEntry);
        return this.timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return (TimeEntry) timeEntries.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(timeEntries.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {

        TimeEntry timeEntryCreated = (TimeEntry) timeEntries.get(id);
        TimeEntry timeEntryUpdated = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntries.put(id, timeEntryUpdated);
        return timeEntryUpdated;
    }

    @Override
    public ResponseEntity<TimeEntry> delete(long l) {
        this.timeEntries = new HashMap();
        return null;
    }
}
