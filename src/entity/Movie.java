package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "movies", schema = "XPZ9FZTUMY")
@NamedQueries({
        @NamedQuery(name = "Movie.findByDirector",
                query = "SELECT m FROM Movie m  WHERE m.director.name =: name")})
public class Movie {
    private int id;
    private String name;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JoinColumn(name = "director_id", referencedColumnName = "id")
    @ManyToOne
    private Person director;

    public void setDirectorName(String directorName) { this.director.setName(directorName); }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                Objects.equals(name, movie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
