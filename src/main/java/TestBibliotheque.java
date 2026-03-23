import biblio.bo.Client;
import biblio.bo.Emprunt;
import biblio.bo.Livre;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestBibliotheque {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-essai");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin(); // Début de l'unité de travail

            // 1. Création des Livres
            Livre l1 = new Livre("Vingt mille lieues sous les mers", "Jules Verne");
            Livre l2 = new Livre("Germinal", "Emile Zola");
            em.persist(l1); // Enregistre en base
            em.persist(l2);

            // 2. Création du biblio.bo.Client
            Client c1 = new Client("Brigand", "Pierre");
            em.persist(c1);

            // 3. Création de l'biblio.bo.Emprunt
            Emprunt emp1 = new Emprunt();
            emp1.setDateDebut(LocalDateTime.of(2017, 11, 12, 0, 0));
            emp1.setDelai(15);
            emp1.setClient(c1); // Lie l'emprunt au client

            // Ajout des livres à l'emprunt (Relation ManyToMany)
            ArrayList<Livre> listeLivres = new ArrayList<>();
            listeLivres.add(l1);
            listeLivres.add(l2);
            emp1.setLivres(listeLivres);

            em.persist(emp1);

            tx.commit(); // Valide l'insertion dans la base de données
            System.out.println("Données insérées avec succès !");

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback(); // Annule tout en cas d'erreur
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}