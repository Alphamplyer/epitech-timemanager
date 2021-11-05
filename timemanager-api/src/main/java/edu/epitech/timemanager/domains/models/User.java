package edu.epitech.timemanager.domains.models;

import edu.epitech.timemanager.domains.models.enumerations.Role;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue
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
    @ToString.Exclude
    private Set<WorkingTime> workingTimes;

    @ManyToMany
    @JoinTable(
        name = "teams_members",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Team> joinedTeams = new HashSet<>();


    public User(Integer id) {
        this.id = id;
    }

    public User(String username, String email, String hashedPassword) {
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.role = Role.EMPLOYEE;
    }

    public User(Integer id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Collection<GrantedAuthority> getPermissions() {
        switch (role) {
            case EMPLOYEE:
                return Collections.singleton(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
            case MANAGER:
                return Collections.singleton(new SimpleGrantedAuthority("ROLE_MANAGER"));
            case GLOBAL_MANAGER:
                return Collections.singleton(new SimpleGrantedAuthority("ROLE_GLOBAL_MANAGER"));
            case NONE:
                return Collections.singleton(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
            default:
                return Collections.emptyList();
        }
    }
}
