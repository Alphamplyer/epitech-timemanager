package edu.epitech.timemanager.domains.models;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams")
public class Team implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description = "";

    @ManyToMany(mappedBy = "joinedTeams")
    @ToString.Exclude
    private Set<User> members = new HashSet<>();



    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;


    public Team(Integer id) {
        this.id = id;
    }

    public Team(Integer id, String name, String description, Set<User> members) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.members = members;
    }

    public void addUser(User user) {
        if (members.stream().noneMatch(user1 -> user1.getId().equals(user.getId())))
            members.add(user);
    }

    public void removeUser(User user) {
        members.removeIf(user1 -> user1.getId().equals(user.getId()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Team team = (Team) o;
        return id != null && Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
