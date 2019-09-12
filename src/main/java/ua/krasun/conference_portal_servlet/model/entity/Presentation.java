package ua.krasun.conference_portal_servlet.model.entity;

public class Presentation {
    private long id;
    private String theme;
    private User author;
    private Conference conference;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    //Builder
    public static Builder builder() {
        return new Presentation().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(Long id) {
            Presentation.this.id = id;
            return this;
        }

        public Builder date(String theme) {
            Presentation.this.theme = theme;
            return this;
        }

        public Builder author(User author) {
            Presentation.this.author = author;
            return this;
        }

        public Builder conference(Conference conference) {
            Presentation.this.conference = conference;
            return this;
        }

        public Presentation build() {
            return Presentation.this;
        }
    }

    @Override
    public String toString() {
        return "Presentation{" +
                "id=" + id +
                ", theme='" + theme + '\'' +
                ", author=" + author.getFirstName() +
                ", conference=" + conference.getDate() + "/" + conference.getSubject() +
                '}';
    }
}
