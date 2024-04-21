package models;

public class Post {
    private String id;
    private String poster_id;
    private String subject;
    private String description;
    private String rewards;

    public Post(String id, String poster_id, String subject, String description, String rewards) {
        this.id = id;
        this.poster_id = poster_id;
        this.subject = subject;
        this.description = description;
        this.rewards = rewards;
    }

    public String GetID() {
        return this.id;
    }

    public String GetPosterID() {
        return this.poster_id;
    }

    public String GetSubject() {
        return this.subject;
    }

    public String GetDescription() {
        return this.description;
    }

    public String GetRewards() {
        return this.rewards;
    }
}
