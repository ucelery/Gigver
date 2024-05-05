package utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.system.SystemCleaner;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gigver.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import models.Rating;
import models.User;

public class ConfirmDialog {
    private Activity activity;
    private AlertDialog dialog;
    private Runnable onConfirmFunction;
    public ConfirmDialog(Activity activity, Runnable onConfirmFunction) {
        this.activity = activity;
        this.onConfirmFunction = onConfirmFunction;
    }

    public void ShowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.confirm_dialog, null);

        TextView confirmButton = dialogView.findViewById(R.id.confirmButton);
        TextView cancelButton = dialogView.findViewById(R.id.cancelButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(onConfirmFunction);
                thread.start();

                DismissDialog();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DismissDialog();
            }
        });

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
