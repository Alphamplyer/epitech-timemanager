package edu.epitech.timemanager.domains.models;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams")
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description = "";

    @ManyToMany(targetEntity = User.class)
    private List<User> members = new ArrayList<>();


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

    public Team(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Team(Integer id, String name, String description, List<User> members) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.members = members;
    }

    public void addMember(User user) {
        this.members.add(user);
        user.getTeams().add(this);
    }

    public void removeMember(User user) {
        this.members.remove(user);
        user.getTeams().remove(this);
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
