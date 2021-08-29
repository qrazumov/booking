package ru.booking.payload.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class BookingRequest {

    @NotEmpty
    private String title;

    @NotNull
    private Date eventStart;

    @NotNull
    private Date eventEnd;

    public BookingRequest(String title, Date eventStart, Date eventEnd) {
        this.title = title;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
