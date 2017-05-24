package pubsub.model;

import java.io.Serializable;

/**
 * Created by MarcoADP on 23/05/2017.
 */
public class Event implements Serializable {

    private Publisher publisher;
    private String description;

    private static final long serialVersionUID = -6922246640376469678L;


    public Event(Publisher publisher, String description) {
        this.publisher = publisher;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Event{" +
                "publisher=" + publisher +
                ", description='" + description + '\'' +
                '}';
    }
}
