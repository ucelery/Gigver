package utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.system.SystemCleaner;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gigver.R;
import com.google.android.material.snackbar.Snackbar;

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
        ServerManager server = new ServerManager("https://gigver-server.onrender.com");
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.rate_dialog, null);

        ImageButton[] buttons = {
                dialogView.findViewById(R.id.rateBtn1),
                dialogView.findViewById(R.id.rateBtn2),
                dialogView.findViewById(R.id.rateBtn3),
                dialogView.findViewById(R.id.rateBtn4),
                dialogView.findViewById(R.id.rateBtn5)
        };

        for (int i = 0; i < buttons.length; i++) {
            ImageButton button = buttons[i];
            int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Rating rating = new Rating(rated.GetID(), rater.GetID(), Float.parseFloat(String.valueOf(finalI)));
                    server.AddRating(rating, new IServerEvent<Rating>() {
                        @Override
                        public void OnComplete(Rating result) {
                            try {
                                System.out.println(result.ToJsonObject());
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                            MakeToast("Thanks for rating!");

                            DismissDialog();
                        }

                        @Override
                        public void OnFailure(String errorMessage) {
                            System.out.print(errorMessage);
                            DismissDialog();

                            MakeToast("You've already rated this user!");
                        }
                    });
                }
            });
        }

        builder.setCancelable(false);
        builder.setView(dialogView);
        dialog = builder.create();
        dialog.show();
    }

    public void DismissDialog() {
        dialog.dismiss();
    }

    private void MakeToast(String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
