package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import models.Post;
import models.Rating;
import models.User;

public class ServerManager {
    private String hostURL;
    public ServerManager(String hostURL) {
        this.hostURL = hostURL;
    }

    public void GetUsers(IServerEvent<List<User>> callback) {
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
                                userObject.getString("password"),
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

    public void AddUser(User newUser, IServerEvent<User> callback) {
        String apiUrl = ServerManager.this.hostURL + "/users/add";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuffer response = PostData(apiUrl, newUser);

                    User userFromDB = new User(new JSONObject(response.toString()));

                    callback.OnComplete(userFromDB);
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.OnFailure("Failed");
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.OnFailure("Failed");
                }
            }
        }).start();
    }

    public void AddRating(Rating newRate, IServerEvent<Rating> callback) {
        String apiUrl = ServerManager.this.hostURL + "/users/rating/add";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuffer response = PostData(apiUrl, newRate);

                    Rating ratingFromDB = new Rating(new JSONObject(response.toString()));

                    callback.OnComplete(ratingFromDB);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    callback.OnFailure("Failed to Add Rating: " + e.getMessage());
                }
            }
        }).start();
    }

    public void GetPosts(IServerEvent<List<Post>> callback) {
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
                                postObject.getString("rewards"),
                                postObject.getBoolean("complete")
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

    public void AddPost(Post newPost, IServerEvent<Post> callback) {
        String apiUrl = ServerManager.this.hostURL + "/posts/add";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuffer response = PostData(apiUrl, newPost);

                    System.out.println(response.toString());
                    Post postFromDB = new Post(new JSONObject(response.toString()).getJSONObject("post"));

                    callback.OnComplete(postFromDB);
                } catch (IOException e) {
                    callback.OnFailure("Failed");
                } catch (JSONException e) {
                    callback.OnFailure(e.getMessage());
                }
            }
        }).start();
    }

    public void CompletePost(String postId, IServerEvent<String> callback) {
        String apiUrl = ServerManager.this.hostURL + "/post/complete";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // URL to which you want to make the request
                    URL url = new URL(apiUrl);

                    // Open a connection to the URL
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    // Set the request method to POST
                    connection.setRequestMethod("POST");

                    // Enable input and output streams
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    // Write the post_id to the connection output stream
                    connection.getOutputStream().write(postId.getBytes());

                    // Get the response from the server
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer response = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    // Print the response
                    System.out.println("Response: " + response.toString());

                    // Close the connection
                    connection.disconnect();

                    callback.OnComplete("Post Completed Successfully");
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.OnFailure("Something unexpected happened");
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

    private StringBuffer PostData(String apiUrl, IModel data) throws IOException {
        StringBuffer response = new StringBuffer();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            String jsonPayload = data.ToJsonObject().toString();

            System.out.println(jsonPayload);
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(jsonPayload.getBytes("UTF-8"));
            }

            // Retrieve the HTTP status code
            int statusCode = connection.getResponseCode();

            if (statusCode >= 200 && statusCode < 300) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                }
            } else {
                System.err.println("HTTP Error: " + statusCode);
                try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                    String errorLine;
                    while ((errorLine = errorReader.readLine()) != null) {
                        System.err.println(errorLine);
                    }
                }
            }

            // Close the connection
            connection.disconnect();
        } catch (IOException e) {
            throw e;
        } catch (JSONException e) {
            try {
                throw e;
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
        }

        return response;
    }
}
