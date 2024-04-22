package utils;

import org.json.JSONException;
import org.json.JSONObject;

public interface IModel {
    JSONObject ToJsonObject() throws JSONException;
}
