package com.example.demofirebasetorecycler.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demofirebasetorecycler.R;

public  abstract  class BaseActivity extends AppCompatActivity {
    Context mContext;
    public static Animation shakeAnimation;
    //    public APIinterface apiInterface;
//    SessionManager sessionManager;
    public String LOG_TAG = "OnlineBaseActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        shakeAnimation = AnimationUtils.loadAnimation(this,
                R.anim.shake);
//        apiInterface  = APIclient.getClient().create(APIinterface.class);
//        sessionManager= new SessionManager(mContext);
    }

    public void openActivity(Class aClass) {
        startActivity(new Intent(mContext, aClass));
        enterActivityAnimation();
//        openToTopAnimation();
    }
//

    public void closeActivity() {
        finish();
        exitActivityAnimation();
    }

    public void customToast(String valToast, int vibTime, boolean valBoo, ViewGroup viewGroup) {
        Toast.makeText(mContext, valToast, Toast.LENGTH_LONG).show();
        vibrate(vibTime);
        if (viewGroup!=null)
            shareScreen(valBoo, viewGroup);
    }

    public void shareScreen(boolean valBoo, ViewGroup viewGroup) {
        /* if (valBoo)*/
        viewGroup.startAnimation(shakeAnimation);
    }

    public boolean isETEmpty(EditText editText, int i) {
        if (editText.getText().toString().length() < i) {
            return true;
        }
        return false;
    }

    public String getETValue(EditText editText) {
        return editText.getText().toString().trim();
    }

    //todo calling animation when entering form activity
    public void enterActivityAnimation() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    //todo calling animation when exit form activity
    public void exitActivityAnimation() {
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }
    //todo calling open activity when slide in from activity
    public void openToTopAnimation() {
        overridePendingTransition(R.anim.slide_in_up, R.anim.nothing);
    }
    public void closeToBottomAnimation() {
        overridePendingTransition(R.anim.nothing, R.anim.slide_out_up);
    }

    //todo for vibrating the device when get error
    public void vibrate(int duration) {
        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(duration);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        closeActivity();
    }
}


