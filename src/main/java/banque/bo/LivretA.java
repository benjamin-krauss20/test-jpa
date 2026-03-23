package banque.bo; //

import jakarta.persistence.*; // Importe toutes les annotations JPA
import java.time.LocalDateTime; // [cite: 28]

@Entity
@Table(name = "LIVRET_A")
public class LivretA extends Compte {
    private double taux; // [cite: 24, 53]
    public void setTaux(double taux) { this.taux = taux; }
    public LivretA() {}
}