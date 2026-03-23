package banque.bo; //

import jakarta.persistence.*; // Importe toutes les annotations JPA
import java.time.LocalDateTime; // [cite: 28]

@Entity
@Table(name = "VIREMENT")
public class Virement extends Operation {
    private String beneficiaire; // [cite: 29]

    public Virement() {}
}