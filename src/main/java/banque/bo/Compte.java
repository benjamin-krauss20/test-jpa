package banque.bo;

import jakarta.persistence.*;
import java.util.ArrayList; // Import manquant
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "COMPTE")
public abstract class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numero;
    private double solde;

    @ManyToMany(mappedBy = "comptes")
    private List<Client> clients = new ArrayList<>();

    @OneToMany(mappedBy = "compte")
    private List<Operation> operations = new ArrayList<>();

    public Compte() {}

    // Getters et Setters
    public void setNumero(String numero) { this.numero = numero; }
    public String getNumero() { return numero; }
    public void setSolde(double solde) { this.solde = solde; }
    public double getSolde() { return solde; }
    public List<Client> getClients() { return clients; }
}