package biblio.bo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "EMPRUNT")
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //

    @Column(name = "DATE_DEBUT")
    private LocalDateTime dateDebut; //

    @Column(name = "DATE_FIN")
    private LocalDateTime dateFin; // Peut être NULL [cite: 125]

    private Integer delai; //

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT") // Clé étrangère vers CLIENT 
    private Client client;

    @ManyToMany
    @JoinTable(name = "COMPO",
            joinColumns = @JoinColumn(name = "ID_EMP", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_LIV", referencedColumnName = "ID")
    ) // Table d'association entre LIVRE et EMPRUNT
    private List<Livre> livres;

    public Emprunt() {}

    // Getters et Setters
    public Integer getId() { return id; }
    public LocalDateTime getDateDebut() { return dateDebut; }
    public Client getClient() { return client; }
    public List<Livre> getLivres() { return livres; }
    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }
    public void setDelai(Integer delai) { this.delai = delai; }
    public void setClient(Client client) { this.client = client; }
    public void setLivres(List<Livre> livres) { this.livres = livres; }
}