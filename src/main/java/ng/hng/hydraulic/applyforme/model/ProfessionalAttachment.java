package ng.hng.hydraulic.applyforme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="professional_id")
    private Professional professional;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", updatable = false, nullable = false)
    private Date createdOn;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", nullable = false)
    private Date updatedOn;

}
