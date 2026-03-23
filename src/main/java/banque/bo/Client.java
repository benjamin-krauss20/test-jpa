package banque.bo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "ClientBanque")
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom; // [cite: 8]
    private String prenom; // [cite: 8]
    private LocalDate dateNaissance; // [cite: 9]

    @Embedded // Inclut numero, rue, codePostal et ville dans la table CLIENT
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "ID_BANQUE") // Clé étrangère vers la banque [cite: 13, 16]
    private Banque banque;

    @ManyToMany // Un client peut avoir plusieurs comptes et inversement
    @JoinTable(name = "CLIENT_COMPTE",
            joinColumns = @JoinColumn(name = "ID_CLI", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_CPT", referencedColumnName = "ID")
    )
    private List<Compte> comptes;

    public Client() {}

    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public void setAdresse(Adresse adresse) { this.adresse = adresse; }
    public void setBanque(Banque banque) { this.banque = banque; }
    public void setComptes(List<Compte> comptes) { this.comptes = comptes; }
}