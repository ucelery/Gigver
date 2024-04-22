package models;

import org.json.JSONException;
import org.json.JSONObject;

import utils.IModel;

public class Post implements IModel {
    private String id;
    private String poster_id;
    private String subject;
    private String description;
    private String rewards;

    public Post(JSONObject postObj) throws JSONException {
        if (postObj.getString("_id") == "")
            this.id = "";
        else this.id = postObj.getString("_id");

        this.poster_id = postObj.getString("poster_id");
        this.subject = postObj.getString("subject");
        this.description = postObj.getString("description");
        this.rewards = postObj.getString("rewards");
    }

    public Post(String poster_id, String subject, String description, String rewards) {
        this.id = "";
        this.poster_id = poster_id;
        this.subject = subject;
        this.description = description;
        this.rewards = rewards;
    }

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

    @Override
    public JSONObject ToJsonObject() throws JSONException {
        JSONObject postObj = new JSONObject();

        postObj.put("user_id", this.poster_id);
        postObj.put("subject", this.subject);
        postObj.put("description", this.description);
        postObj.put("rewards", this.rewards);

        return postObj;
    }
}
