package ng.hng.hydraulic.applyforme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="PROFESSIONAL_ATTACHMENT")
public class ProfessionalAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="passport_link", nullable = false)
    private String passportLink;

    @Column(name ="resume_link", nullable = false)
    private String resumeLink;

    @Column(name ="cover_letter_link", nullable = false)
    private String coverLetterLink;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="professional_id")
    private Professional professional;


}
