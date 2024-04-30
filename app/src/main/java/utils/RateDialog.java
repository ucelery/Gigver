package utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.system.SystemCleaner;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gigver.R;

import org.json.JSONException;

import models.Rating;
import models.User;

public class RateDialog {
    private Activity activity;
    private AlertDialog dialog;

    public RateDialog(Activity activity) {
        this.activity = activity;
    }

    public void ShowDialog(User rater, User rated) {
        ServerManager server = new ServerManager("");
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog, null));
        builder.setCancelable(false);

        dialog = builder.create();

        ConstraintLayout layout = dialog.findViewById(R.id.dialogLayout); // Replace R.id.my_layout with the ID of your layout

        for (int i = 0; i < layout.getChildCount(); i++) {
            View view = layout.getChildAt(i);
            if (view instanceof Button) {
                System.out.print(i);

                Button button = (Button) view;
                int finalI = i;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Rating rating = new Rating(rated.GetID(), rater.GetID(), finalI);
                        server.AddRating(rating, new IServerEvent<Rating>() {
                            @Override
                            public void OnComplete(Rating result) {
                                try {
                                    System.out.println(result.ToJsonObject());
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            @Override
                            public void OnFailure(String errorMessage) {
                                System.out.print(errorMessage);
                            }
                        });
                    }
                });
            }
        }

        dialog.show();
    }

    public void DismissDialog() {
        dialog.dismiss();
    }
}
