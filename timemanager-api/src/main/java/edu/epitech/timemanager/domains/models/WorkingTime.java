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
@Table(name = "working_times")
public class WorkingTime implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "started_at", nullable = false)
    private Date startedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ended_at", nullable = false)
    private Date endedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;



    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;


    public WorkingTime(Integer id) {
        this.id = id;
    }

    public WorkingTime(Date startedAt, Date endedAt) {
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public WorkingTime(Date startedAt, Date endedAt, User user) {
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.user = user;
    }

    public WorkingTime(Integer id, Date startedAt, Date endedAt) {
        this.id = id;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public WorkingTime(Integer id, Date startedAt, Date endedAt, User user) {
        this.id = id;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WorkingTime that = (WorkingTime) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
