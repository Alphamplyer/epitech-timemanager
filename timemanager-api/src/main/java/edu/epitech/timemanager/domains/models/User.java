package edu.epitech.timemanager.domains.models;

import edu.epitech.timemanager.domains.models.enumerations.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String hashedPassword;

    @Enumerated(EnumType.STRING)
    private Role role;



    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;



    @OneToOne()
    @JoinColumn(name = "clock_id", referencedColumnName = "id")
    private Clock clock;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<WorkingTime> workingTimes;

    @ManyToMany
    @JoinTable(
        name = "teams_members",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id")
    )
    private Set<Team> joinedTeams = new HashSet<>();


    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String username, String email, String hashedPassword) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.role = Role.EMPLOYEE;
    }

    public User(Integer id, String username, String email, String hashedPassword, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }
}
