package banque.bo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ClientBanque")
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    @Embedded
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "ID_BANQUE")
    private Banque banque;

    @ManyToMany
    @JoinTable(name = "CLIENT_COMPTE",
            joinColumns = @JoinColumn(name = "ID_CLI", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_CPT", referencedColumnName = "ID")
    )
    private List<Compte> comptes = new ArrayList<>(); // Initialisation ici

    public Client() {}

    // Getters et Setters corrigés
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public void setAdresse(Adresse adresse) { this.adresse = adresse; }
    public void setBanque(Banque banque) { this.banque = banque; }

    public List<Compte> getComptes() { return comptes; }
}