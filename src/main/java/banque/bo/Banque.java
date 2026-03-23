package banque.bo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BANQUE")
public class Banque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom; // [cite: 21]

    @OneToMany(mappedBy = "banque")
    private List<Client> clients = new ArrayList<>();

    // 1. Constructeur sans argument (obligatoire pour JPA)
    public Banque() {}

    // 2. AJOUTE CE CONSTRUCTEUR pour ton TestBanque
    public Banque(String nom) {
        this.nom = nom;
    }

    // N'oublie pas les getters et setters pour 'nom'
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
}