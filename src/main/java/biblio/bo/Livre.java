package biblio.bo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "LIVRE")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //

    private String titre; //
    private String auteur; //

    @ManyToMany(mappedBy = "livres") // Relation inverse [cite: 185]
    private List<Emprunt> emprunts;

    public Livre() {}

    // Getters et Setters
    public Integer getId() { return id; }
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }

    public Livre(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }



}