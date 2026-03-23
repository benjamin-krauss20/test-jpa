package banque.bo; //

import jakarta.persistence.*; // Importe toutes les annotations JPA
import java.time.LocalDateTime; // [cite: 28]

@Entity
@Table(name = "ASSURANCE_VIE")
public class AssuranceVie extends Compte {
    private java.time.LocalDate dateFin; // [cite: 26]
    private double taux; // [cite: 26]

    public AssuranceVie() {}
}