package edu.epitech.gotham.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "clocks")
public class Clock implements Serializable {

    @Id
    @Column(name = "user_id")
    private Integer userId;
    private Timestamp time;
    private Boolean status;

    public Clock() {
    }

    public Clock(Timestamp time, Boolean status) {
        this.time = time;
        this.status = status;
    }

    public Clock(Integer userId, Timestamp time, Boolean status) {
        this.userId = userId;
        this.time = time;
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
