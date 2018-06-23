package com.zjut.alan.pinkwallet.module.guide;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zjut.alan.pinkwallet.R;
import com.zjut.alan.saluandroid.base.SaluRxBaseActivity;

public class CharacterActivity extends SaluRxBaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_character;
    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity, CharacterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void initToolBar() {

    }
}
