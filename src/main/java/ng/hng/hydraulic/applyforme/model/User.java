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
@Table(name ="MEMBER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name ="nationality", nullable = false)
    private String nationality;

    @Temporal(TemporalType.DATE)
    @Column(name ="date_of_birth", nullable = false, updatable = true)
    private Date dateOfBirth;

    @Column(name ="job_title", nullable = false)
    private String jobTitle;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name ="password", nullable = false)
    private String password;

    @Column(name ="active", nullable = false)
    private boolean isActive;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "created_on", updatable = false)
    private Date createdOn;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "updated_on")
    private Date updatedOn;
}
