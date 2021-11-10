package edu.epitech.timemanager.domains.models;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clocks")
public class Clock implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enabled_at")
    private Date enabledAt;

    @Column(name = "is_enable", nullable = false)
    private boolean enable = false;

    @OneToOne(mappedBy = "clock", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public Clock(Date enabledAt, boolean enable, User user) {
        this.enabledAt = enabledAt;
        this.enable = enable;
        this.user = user;
    }

    public Clock(Integer id, Date enabledAt, boolean enable, User user) {
        this.id = id;
        this.enabledAt = enabledAt;
        this.enable = enable;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Clock clock = (Clock) o;
        return id != null && Objects.equals(id, clock.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
