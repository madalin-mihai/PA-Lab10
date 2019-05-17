package app;

import entity.Movie;
import entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class MovieManager {
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MoviesPU");

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input command:");
            String command = scanner.nextLine();
            if (command.equals("exit")) break;
            String[] params = command.trim().split("\\s+");
            switch (params[0]) {
                case "create-person":
                    createPerson(params[1]); //the person name
                    break;
                case "create-movie":
                    createMovie(params[1], params[2]); //the album name and the director
                    break;
                case "list-movies":
                    listMovies(params[1]); //the director name
                    break;
            }
        }
        emf.close();
    }
    private void createPerson(String personName) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Person person = new Person();
        person.setName(personName);

        em.persist(person);
        em.getTransaction().commit();
        em.close();
    }
    private void createMovie(String movieName, String directorName) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Movie movie = new Movie();
        movie.setName(movieName);
        movie.setDirectorName(directorName);

        em.persist(movie);
        em.getTransaction().commit();
        em.close();
    }
    private void listMovies(String directorName) {
        EntityManager em = emf.createEntityManager();
        Movie movieList = (Movie) em.createNamedQuery("Movie.findByDirector").setParameter("name","ceva");
        em.getTransaction().commit();
        em.close();
    }
    public static void main(String args[]) {
        new MovieManager().run();
    }
}
