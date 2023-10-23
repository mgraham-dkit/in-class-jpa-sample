package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(length=20, nullable = false, unique = true)
    private String username;
    @Column(length=60, nullable=false)
    private String password;

    @Temporal(TemporalType.DATE)
    private LocalDate creationDate;

    public User() {
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username != null){
            throw new NullPointerException("Password field cannot be null");
        }
        if(username.length() > 20){
            throw new IllegalArgumentException("Username field cannot be greater than 20 characters");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password == null){
            throw new NullPointerException("Password field cannot be null");
        }
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

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