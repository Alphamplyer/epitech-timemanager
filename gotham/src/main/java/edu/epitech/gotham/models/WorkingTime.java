package edu.epitech.gotham.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "working_times")
public class WorkingTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Timestamp start;
    private Timestamp end;

    @Column(name = "user_id")
    private Integer userId;

    public WorkingTime() {
    }

    public WorkingTime(Timestamp start, Timestamp end, Integer userId) {
        this.start = start;
        this.end = end;
        this.userId = userId;
    }

    public WorkingTime(Integer id, Timestamp start, Timestamp end, Integer userId) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
