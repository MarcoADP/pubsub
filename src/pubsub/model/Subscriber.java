package pubsub.model;

import java.io.Serializable;

/**
 * Created by MarcoADP on 23/05/2017.
 */
public class Subscriber implements Serializable {

    private static final long serialVersionUID = 762162360731777545L;

    private Integer id;
    private String name;

    public Subscriber(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
