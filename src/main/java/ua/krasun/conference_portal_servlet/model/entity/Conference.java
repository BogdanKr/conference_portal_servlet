package ua.krasun.conference_portal_servlet.model.entity;

import java.time.LocalDate;

public class Conference {
    private long id;
    private LocalDate date;
    private String subject;
    private User author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    //Builder
    public static Builder builder() {
        return new Conference().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(Long id) {
            Conference.this.id = id;
            return this;
        }

        public Builder date(LocalDate date) {
            Conference.this.date = date;
            return this;
        }
        public Builder subject(String subject) {
            Conference.this.subject = subject;
            return this;
        }

        public Builder author(User author) {
            Conference.this.author = author;
            return this;
        }

        public Conference build() {
            return Conference.this;
        }
    }

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", date=" + date +
                ", subject='" + subject + '\'' +
                ", author=" + author +
                '}';
    }
}
