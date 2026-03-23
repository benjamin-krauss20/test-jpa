package banque.bo; //

import jakarta.persistence.*; // Importe toutes les annotations JPA
import java.time.LocalDateTime; // [cite: 28]

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "OPERATION")
public abstract class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private java.time.LocalDateTime date; // [cite: 28]
    private double montant; // [cite: 28]
    private String motif; // [cite: 28]

    @ManyToOne
    @JoinColumn(name = "ID_CPT") // L'opération appartient à un compte [cite: 12, 38]
    private Compte compte;

    public Operation() {}
}