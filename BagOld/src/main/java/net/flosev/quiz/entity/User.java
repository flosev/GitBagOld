package net.flosev.quiz.entity;

import java.io.Serializable;
import java.util.Objects;

// must be serializable to be attribute on session
public class User implements Serializable {
    private final int id;
    private final String login;
    private final String password;
    private final String email;

    public User(int id, String login, String password, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object ref) {
        if (ref == null || getClass() != ref.getClass()) {
            return false;
        }

        User that = (User) ref;

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
        return "User{login='" + login + "\'}";
    }
}
