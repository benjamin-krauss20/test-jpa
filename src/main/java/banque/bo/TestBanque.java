package banque.bo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestBanque {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // 1. Créer une Banque
            Banque maBanque = new Banque("Ma Super Banque");
            em.persist(maBanque);

            // 2. Créer une Adresse et un Client
            Adresse adr = new Adresse(10, "Rue de la Paix", 75000, "Paris");
            Client client = new Client();
            client.setNom("Krauss");
            client.setPrenom("Benjamin");
            client.setDateNaissance(LocalDate.of(2000, 1, 1));
            client.setAdresse(adr);
            client.setBanque(maBanque);
            em.persist(client);

            // 3. Créer un Livret A (Héritage de Compte)
            LivretA monLivret = new LivretA();
            monLivret.setNumero("L-001");
            monLivret.setSolde(1500.0);
            monLivret.setTaux(2.5);
            em.persist(monLivret);

            tx.commit();
            System.out.println("Données bancaires insérées !");

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}