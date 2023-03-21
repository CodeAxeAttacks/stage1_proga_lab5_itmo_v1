import java.util.Hashtable;
import java.util.Collection;
import java.util.Date;

public class LabWorkCollection {

    private Hashtable<Long, LabWork> labWorks;

    Date CDate = new Date();
    private long lastId = 0;
    public LabWorkCollection() {
        this.labWorks = new Hashtable<>();
    }

    public void add(LabWork labWork) {
        labWorks.put(labWork.getId(), labWork);
    }

    public void remove(long id) {
        labWorks.remove(id);
    }

    public LabWork getById(long id) {
        return labWorks.get(id);
    }

    public Collection<LabWork> getAll() {
        return labWorks.values();
    }

    public long generateId() {
        return ++lastId;
    }

    public int getSize() {
        return labWorks.size();
    }

    public java.util.Date getInitializationDate() {
        return CDate;
    }

    public void clearAll() {
        labWorks.clear();
    }

}
