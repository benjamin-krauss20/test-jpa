import biblio.bo.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJpa {
    public static void main(String[] args) {
        // 1. Créer l'instance d'EntityManagerFactory (nom du persistence.xml) [cite: 82]
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-essai");

        // 2. Créer l'instance d'EntityManager
        EntityManager em = emf.createEntityManager();

        try {
            // Vérifier la connexion [cite: 85]
            System.out.println("Connexion réussie : " + em.isOpen());

            // --- TEST D'INSERTION ---
            em.getTransaction().begin(); // Début transaction

            Produit p = new Produit("Ordinateur", 899.99);
            em.persist(p); // Enregistrement

            em.getTransaction().commit(); // Validation
            System.out.println("biblio.bo.Produit ajouté avec succès, ID : " + p.getId());

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // 3. Fermer les connexions (Obligatoire)
            em.close();
            emf.close();
        }
    }
}