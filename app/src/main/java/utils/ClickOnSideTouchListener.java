package utils;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;

public class ClickOnSideTouchListener implements OnTouchListener {
    private Context context;


    private Runnable runLeft;
    private Runnable runRight;
    public ClickOnSideTouchListener(Context context, Runnable runLeft, Runnable runRight) {
        this.context = context;
        this.runLeft = runLeft;
        this.runRight = runRight;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int screenWidth = getScreenWidth();

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                float x = event.getX();

                // Click on the left side of the screen
                if (x < screenWidth / 2) {
                    onLeftSideClick();
                }
                // Click on the right side of the screen
                else {
                    onRightSideClick();
                }
                break;
        }
        return true;
    }

    private int getScreenWidth() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            return wm.getDefaultDisplay().getWidth();
        }
        return 0;
    }

    public void onLeftSideClick() {
        runLeft.run();
    }

    public void onRightSideClick() {
        runRight.run();
    }
}