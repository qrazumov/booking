package ru.booking.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date eventStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date eventEnd;

    @ManyToOne
    private User user;

    public Booking(String title, Date eventStart, Date eventEnd, User user) {
        this.title = title;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.user = user;
    }

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEventStart() {
        return eventStart;
    }

    public void setEventStart(Date eventStart) {
        this.eventStart = eventStart;
    }

    public Date getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(Date eventEnd) {
        this.eventEnd = eventEnd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
