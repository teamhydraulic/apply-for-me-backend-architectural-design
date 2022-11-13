package ng.hng.hydraulic.applyforme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ng.hng.hydraulic.applyforme.model.enums.JobLocationType;
import ng.hng.hydraulic.applyforme.model.enums.JobSeniority;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * In the system, a developer or designer or professional can create and have multiple
 * profile which can be suited to different roles or jobs he or she is
 * interested in. The professional should be given the flexibility to upload
 * new details like passport or cv or cover letter tailored to the job title of interest.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="PROFESSIONAL_PROFILE")
public class ProfessionalProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="profile_title", nullable = false)
    private String profileTitle;

    @Column(name = "main_profile", nullable = false)
    private Boolean mainProfile = false;

    @Column(name ="passport_link", nullable = false)
    private String passportLink;

    @Column(name ="resume_link", nullable = false)
    private String resumeLink;

    @Column(name ="cover_letter_link", nullable = false)
    private String coverLetterLink;

    @Column(name = "salary_range", nullable = false)
    private String salaryRange = "0";

    @Column(name ="preferred_job_location_type", nullable = false)
    private JobLocationType preferredJobLocationType = JobLocationType.ONSITE;

    @Column(name ="job_seniority", nullable = false)
    private JobSeniority jobSeniority = JobSeniority.TRAINEE;

    @Column(name ="desired_job_title", nullable = false)
    private String desiredJobTitle;

    @Column(name ="industry")
    private String industry;

    @Column(name ="years_of_experience")
    private Integer yearsOfExperience = 0;

    @Column(name ="other_skills")
    private String otherSkills;

    @Column(name ="other_comment")
    private String otherComment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
