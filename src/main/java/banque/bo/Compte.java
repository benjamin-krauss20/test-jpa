package banque.bo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "COMPTE")
public abstract class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numero; // [cite: 11]
    private double solde; // [cite: 11]

    @ManyToMany(mappedBy = "comptes")
    private List<Client> clients;

    public Compte() {}

    // Getters et Setters
    public void setNumero(String numero) { this.numero = numero; }
    public void setSolde(double solde) { this.solde = solde; }
}