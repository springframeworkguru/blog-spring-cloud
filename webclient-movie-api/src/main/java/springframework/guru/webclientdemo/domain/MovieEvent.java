package springframework.guru.webclientdemo.domain;
import java.util.Date;

public class MovieEvent {
    private String eventMessage;
    private Date date;
    public MovieEvent() {
    }
    public MovieEvent(String eventMessage, Date date) {
        this.eventMessage = eventMessage;
        this.date = date;
    }
    public String getEventMessage() {
        return eventMessage;
    }
    public void setEventMessage(String eventMessage) {
        this.eventMessage = eventMessage;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
