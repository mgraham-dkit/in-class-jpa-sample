package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

// Mark this class as an Entity - a type that can be saved to the database
// This will define how the data is structured in the database, too
@Entity
// Specify the name of the table - if this is left out then it defaults to the Entity's class name
@Table(name="users")
public class User {
    // Create a primary key (Id marks a variable as being a primary key)
    @Id
    // @GeneratedValue marks this field as auto-generated
    // Strategh type decides how to generate it. Auto will let Hibernate decide how it's generated
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    /*
        The Column attribute lets us set details about a field
        We can include as many or as few components from this as needed, and they can appear in any order
        Specify that this field will:
            Have a max character limit of 20, (length = 20)
            Not be allowed to contain nulls (nullable = false)
            Be a unique value (unique = true)
     */
    @Column(length=20, nullable = false, unique = true)
    private String username;
    /*
        Specify that this field will:
            Have a max character limit of 60, (length = 60)
            Not be allowed to contain nulls (nullable = false)
     */
    @Column(length=60, nullable=false)
    private String password;

    // Mark this as a DATE type of time information
    // This will tell the persistence system to convert from this variable's LocalDate into a
    // Date TemporalType (i.e. java.sql.date)
    @Temporal(TemporalType.DATE)
    private LocalDate creationDate;

    // Create our various constructors
    // The only real rule here is that you HAVE TO provide a default (no-args) constructor.
    // It can have code inside it, it just can't take in any values
    public User() {
    }

    // You can have as many other constructors as you want
    public User(long id) {
        this.id = id;
    }

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public User(String username, String password, LocalDate creationDate) {
        setUsername(username);
        setPassword(password);
        this.creationDate = creationDate;
    }

    public User(long id, String username, String password, LocalDate creationDate) {
        this.id = id;
        setUsername(username);
        setPassword(password);
        this.creationDate = creationDate;
    }

    // Create getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    // We have requirements/constraints/modifications for this field (see the @Column modifications on the field above)
    // so we should also enforce them here
    public void setUsername(String username) {
        // Don't allow nulls
        if(username == null){
            throw new NullPointerException("Username field cannot be null");
        }
        // Don't allow the length to be too long
        if(username.length() > 20){
            throw new IllegalArgumentException("Username field cannot be greater than 20 characters");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    // We have requirements/constraints/modifications for this field (see the @Column modifications on the field above)
    // so we should also enforce them here
    public void setPassword(String password) {
        // Don't allow nulls
        if(password == null){
            throw new NullPointerException("Password field cannot be null");
        }
        // Don't allow the length to be too long
        if(password.length() > 60){
            throw new IllegalArgumentException("Password field cannot be greater than 60 characters");
        }
        this.password = password;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    // Don't forget the good practice methods! Standard toString()
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    // equals and hashCode should obey the same rules as with DAOs:
    // base the equality check on the primary key field(s)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}