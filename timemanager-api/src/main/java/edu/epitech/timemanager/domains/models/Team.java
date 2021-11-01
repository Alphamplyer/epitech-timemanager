package edu.epitech.timemanager.domains.models;

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
@Table(name = "teams")
public class Team implements Serializable {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description = "";

    @ManyToMany(mappedBy = "joinedTeams")
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
}
