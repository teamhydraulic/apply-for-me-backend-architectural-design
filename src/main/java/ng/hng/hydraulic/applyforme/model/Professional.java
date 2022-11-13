package ng.hng.hydraulic.applyforme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROFESSIONAL")
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "available_for_interview" , nullable = false)
    private boolean availableForInterview = false;

    @OneToOne(mappedBy = "professional")
    private Applier applier;

    @OneToMany(mappedBy = "professional")
    private ProfessionalAttachment professionalAttachment;

    @OneToMany(mappedBy = "professional")
    private ProfessionalMetadata professionalMetadata;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professional")
    private Set<Submission> submissions = new HashSet<>();
}
