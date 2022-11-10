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
@Table(name ="MEMBER_ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name ="user_id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private User user;
}
