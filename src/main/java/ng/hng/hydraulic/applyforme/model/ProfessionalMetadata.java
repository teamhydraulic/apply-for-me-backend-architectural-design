package ng.hng.hydraulic.applyforme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ng.hng.hydraulic.applyforme.model.enums.JobLocationType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROFESSIONAL_METADATA")
public class ProfessionalMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "salary_range", nullable = false)
    private String salaryRange;

    @Column(name ="preferred_job_location_type", nullable = false)
    private JobLocationType preferredJobLocationType;

    @Column(name ="job_seniority", nullable = false)
    private String jobSeniority;

    @Column(name ="desired_job_title", nullable = false)
    private String desiredJobTitle;

    @Column(name ="industry", nullable = false)
    private String industry;

    @Column(name ="years_of_experience", nullable = false)
    private float yearsOfExperience;

    @Column(name ="linkedin_link", nullable = true)
    private String linkedinLink;

    @Column(name ="other_link_1", nullable = true)
    private String otherLink1;

    @Column(name ="other_link_2", nullable = true)
    private String otherLink2;

    @Column(name = "hobbies", nullable = true)
    private String hobbies;

    @Column(name ="other_skills", nullable = true)
    private String otherSkills;

    @Column(name ="other_comment", nullable = true)
    private String otherComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="professional_id")
    private Professional professional;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "created_on", updatable = false)
    private Date createdOn;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "updated_on")
    private Date updatedOn;

}
