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

            // 1. Création du compte commun
            LivretA livretCommun = new LivretA();
            livretCommun.setNumero("JOINT-001");
            livretCommun.setSolde(10000.0);
            livretCommun.setTaux(3.0);
            em.persist(livretCommun); // On persiste le compte d'abord

// 2. Création des deux titulaires
            Client monsieur = new Client();
            monsieur.setNom("Dupont");
            monsieur.setPrenom("Jean");
            monsieur.setBanque(maBanque);
            monsieur.getComptes().add(livretCommun); // On lie le compte au client 1

            Client madame = new Client();
            madame.setNom("Dupont");
            madame.setPrenom("Marie");
            madame.setBanque(maBanque);
            madame.getComptes().add(livretCommun); // On lie le MÊME compte au client 2

            em.persist(monsieur);
            em.persist(madame);

            // 3. Création d'une Assurance Vie
            AssuranceVie av = new AssuranceVie();
            av.setNumero("AV-999");
            av.setSolde(500.0);
            av.setTaux(2.0);
            av.setDateFin(LocalDate.of(2040, 1, 1));
            em.persist(av);
// 4. Création d'un virement
            Virement virement = new Virement();
            virement.setDate(LocalDateTime.now());
            virement.setMontant(200.0);
            virement.setMotif("Cadeau anniversaire");
            virement.setBeneficiaire("Benjamin");
            virement.setCompte(livretCommun); // On lie l'opération au livret commun

            em.persist(virement);
// Liaison avec le client existant
            monsieur.getComptes().add(av);

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