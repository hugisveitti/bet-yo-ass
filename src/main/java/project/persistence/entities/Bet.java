package project.persistence.entities;

import javax.persistence.*;

/**
 * The class for the Postit Note itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "bet") // If you want to specify a table name, you can do so here
public class Bet {

    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usernameReceiver;
    private String name;
    private String description;

    //todo decide var/functions needed
    //@Column(name = "betuser")
    //private User creator;


    // Notice the empty constructor, because we need to be able to create an empty PostitNote to add
    // to our model so we can use it with our form
    public Bet() {
    }

    public Bet(String usernameReceiver, String name, String description) {
        this.usernameReceiver = usernameReceiver;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getUsernameReceiver() { return usernameReceiver; }

    public void setUsernameReceiver(String usernameReceiver) { this.usernameReceiver = usernameReceiver; }

    // This is for easier debug.
    @Override
    public String toString() {
        return String.format(
                "Postit Note[usernameReceiver=%s, name=%s, description=%s]",
                usernameReceiver,name,description);
    }
}
