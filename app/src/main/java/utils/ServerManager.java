package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import models.Post;
import models.User;

public class ServerManager {
    private String hostURL;
    public ServerManager(String hostURL) {
        this.hostURL = hostURL;
    }

    public void GetUsers(ServerEvent<List<User>> callback) {
        String apiUrl = ServerManager.this.hostURL + "/users/get";

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> users = new ArrayList<User>();

                try {
                    StringBuffer response = FetchData(apiUrl);

                    JSONArray usersArrayString = new JSONArray(response.toString());
                    for (int i = 0; i < usersArrayString.length(); i++) {
                        JSONObject userObject = usersArrayString.getJSONObject(i);
                        JSONObject contactInfo = new JSONObject(userObject.getString("contactInfo"));
                        User newUser = new User(
                                userObject.getString("_id"),
                                userObject.getString("name"),
                                contactInfo.getString("email"),
                                userObject.getString("address"),
                                contactInfo.getString("mobile"),
                                contactInfo.getString("telephone")
                        );

                        users.add(newUser);
                    }


                    callback.OnComplete(users);
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.OnFailure("Failed");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void GetPosts(ServerEvent<List<Post>> callback) {
        String apiUrl = ServerManager.this.hostURL + "/posts/get";

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Post> posts = new ArrayList<Post>();

                try {
                    StringBuffer response = FetchData(apiUrl);

                    JSONArray postsArrayString = new JSONArray(response.toString());
                    for (int i = 0; i < postsArrayString.length(); i++) {
                        JSONObject postObject = postsArrayString.getJSONObject(i);
                        Post newPost = new Post(
                                postObject.getString("_id"),
                                postObject.getString("poster_id"),
                                postObject.getString("subject"),
                                postObject.getString("description"),
                                postObject.getString("rewards")
                        );

                        posts.add(newPost);
                    }


                    callback.OnComplete(posts);
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.OnFailure("Failed");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private StringBuffer FetchData(String apiUrl) throws IOException {
        StringBuffer response = new StringBuffer();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            connection.disconnect();
        } catch (IOException e) {
            throw e;
        }

        return response;
    }
}
