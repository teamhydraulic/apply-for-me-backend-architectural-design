package ng.hng.hydraulic.applyforme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="MEMBER",
        indexes = {
            @Index(
                    columnList = "email_address",
                    name = "email_address_idx",
                    unique = true
            ),
        },
        uniqueConstraints = {
            @UniqueConstraint(
                    columnNames = {"email_address", "phone_number"},
                    name = "email_address_phone_number_uq"
            )
        }
)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "nationality_id", nullable = false)
    private Country nationality;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "country_of_residence", nullable = false)
    private Country countryOfResidence;

    @Temporal(TemporalType.DATE)
    @Column(name ="date_of_birth", nullable = false, updatable = false)
    private Date dateOfBirth;

    @Column(name ="job_title", nullable = false)
    private String jobTitle;

    @Column(name = "email_address", nullable = false, updatable = true)
    private String emailAddress;

    @Column(name ="phone_number", nullable = false, updatable = true)
    private String phoneNumber;

    @Column(name ="password", nullable = false)
    private String password;

    @Column(name ="avatar", nullable = true)
    private String avatar;

    @Column(name ="active", nullable = false)
    private boolean isActive = true;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", updatable = false, nullable = false)
    private Date createdOn;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", nullable = false)
    private Date updatedOn;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "member_roles",
            joinColumns = @JoinColumn(
                    name = "member_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();


}
