package biblio.bo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //

    private String nom; //
    private String prenom; //

    @OneToMany(mappedBy = "client") // Un client a plusieurs emprunts [cite: 185]
    private List<Emprunt> emprunts;

    public Client() {}

    // Getters et Setters
    public Integer getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public List<Emprunt> getEmprunts() { return emprunts; }
    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
}