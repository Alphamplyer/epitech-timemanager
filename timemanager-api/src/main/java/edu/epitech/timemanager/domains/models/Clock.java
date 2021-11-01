package edu.epitech.timemanager.domains.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clocks")
public class Clock implements Serializable {

    @Id
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enabled_at")
    private Date enabledAt;

    @Column(name = "is_enable", nullable = false)
    private boolean enable = false;

    @OneToOne(mappedBy = "clock", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private User user;



    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;


    public Clock(Integer id) {
        this.id = id;
    }

    public Clock(Integer id, Date enabledAt, boolean enable, User user) {
        this.id = id;
        this.enabledAt = enabledAt;
        this.enable = enable;
        this.user = user;
    }
}
