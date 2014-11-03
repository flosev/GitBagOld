package net.flosev.quiz.entity;

import javax.persistence.*;
import java.util.Objects;
/**
 * Equal to User but with JPA annotations*/

@NamedQuery(
        name="findAllUsersWithEmailLike",
        query="SELECT u FROM JpaUser u WHERE email LIKE :email"
)
@Cacheable
@Entity
@Table(name = "Users")
public class JpaUser {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;

    public JpaUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User toUser() {
        return new User(id, login, password, email);
    }

    @Override
    public boolean equals(Object ref) {
        if (ref == null || getClass() != ref.getClass()) {
            return false;
        }

        JpaUser that = (JpaUser) ref;

        if (this.id != that.id) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email);
    }

    @Override
    public String toString() {
        return "JpaUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

