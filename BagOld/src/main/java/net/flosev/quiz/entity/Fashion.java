package net.flosev.quiz.entity;

import javax.persistence.*;

@Embeddable
@Cacheable
@Entity
@Table(name = "Fashions")
public class Fashion {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "caption")
    private String caption;

    public Fashion(){

    }

    public Fashion(int id, String caption) {
        this.id = id;
        this.caption = caption;
    }

    public int getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }



    /*@Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fashion that = (Fashion) o;
        // todo: only id or all fields?
        return !(id != null ? !id.equals(that.id) : that.id != null);
    }

    @Override
    public int hashCode() {
        // todo: only id or all fields?
        return id != null ? id.hashCode() : 0;
    }*/

    @Override
    public String toString() {
        return "Fashion{" +
                "id='" + id + '\'' +
                ", caption='" + caption + '\'' +
                '}';
    }
}
