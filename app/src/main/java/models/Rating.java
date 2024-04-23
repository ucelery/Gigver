package models;

import org.json.JSONException;
import org.json.JSONObject;

import utils.IModel;

public class Rating implements IModel {
    public String _id;
    public String user_id;
    public String rater_id;
    public float rating;

    public Rating(String user_id, String rater_id, float rating) {
        this._id = "";
        this.user_id = user_id;
        this.rater_id = rater_id;
        this.rating = rating;
    }

    public Rating(JSONObject ratingObj) throws JSONException {
        this._id = ratingObj.getString("_id");
        this.user_id = ratingObj.getString("user_id");
        this.rater_id = ratingObj.getString("rater_id");
        this.rating = (float)ratingObj.getDouble("rating");
    }

    public String GetID() {
        return this._id;
    }

    public String GetUserID() {
        return this.user_id;
    }

    public String GetRaterID() {
        return this.rater_id;
    }

    public float GetRating() {
        return this.rating;
    }

    @Override
    public JSONObject ToJsonObject() throws JSONException {
        JSONObject obj = new JSONObject();

        obj.put("user_id", this.user_id);
        obj.put("rater_id", this.rater_id);
        obj.put("rating", this.rating);

        return obj;
    }
}
