package com.zjut.alan.pinkwallet.module.common;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.zjut.alan.pinkwallet.R;
import com.zjut.alan.saluandroid.base.SaluRxBaseActivity;
import com.zjut.alan.saluandroid.tools.data.SaluDateTool;
import com.zjut.alan.saluandroid.tools.data.SaluRegexTool;
import com.zjut.alan.saluandroid.tools.data.SaluStringTool;
import com.zjut.alan.saluandroid.tools.view.SaluStatusBarHelper;
import com.zjut.alan.saluandroid.tools.view.SaluToastTool;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册
 * @author salu
 */
public class RegisterActivity extends SaluRxBaseActivity{
    @BindView(R.id.et_cellphone_num)
    EditText cellphoneET;

    @BindView(R.id.ib_delete_cellphone)
    ImageButton cellphoneIB;

    @BindView(R.id.et_verify_code)
    EditText verifyCodeET;

    @BindView(R.id.et_login_password)
    EditText passwordET;

    @BindView(R.id.checkbox_apply)
    CheckBox applyCheck;

    private WeakReference<RegisterActivity> reference;


    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        reference = new WeakReference<>(this);
        SaluStatusBarHelper.tintStatusBar(getWindow(), getResources().getColor(R.color.colorPrimary));
        cellphoneET.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus && cellphoneET.getText().length() > 0){
                cellphoneIB.setVisibility(View.VISIBLE);
            }else{
                cellphoneIB.setVisibility(View.GONE);
            }
        });
        cellphoneET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    cellphoneIB.setVisibility(View.VISIBLE);
                }else{
                    cellphoneIB.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 注册按钮
     */
    @OnClick(R.id.btn_register)
    public void register(){
        if(cellphoneET.getText().length() == 0){
            SaluToastTool.displayShortToast(reference.get(), getString(R.string.please_input_cellphone));
            return;
        }else if(!SaluRegexTool.isMobileSimple(cellphoneET.getText().toString().trim())){
            SaluToastTool.displayShortToast(reference.get(), getString(R.string.cell_format));
            return;
        }else if(verifyCodeET.getText().length() == 0){
            SaluToastTool.displayShortToast(reference.get(), getString(R.string.please_input_verify_code));
            return;
        }else if(passwordET.getText().length() == 0){
            SaluToastTool.displayShortToast(reference.get(), getString(R.string.please_input_pwd));
            return;
        }else if(passwordET.getText().toString().contains("**")){
            SaluToastTool.displayShortToast(reference.get(), getString(R.string.input_password));
            return;
        }else if(!applyCheck.isChecked()){
            SaluToastTool.displayShortToast(reference.get(), getString(R.string.need_apply));
            return;
        }
        finishTask();
    }

    /**
     * 清除手机号
     */
    @OnClick(R.id.ib_delete_cellphone)
    public void deleteCellClick(){
        cellphoneET.setText("");
    }

    /**
     * 关闭注册界面
     */
    @OnClick(R.id.tv_login)
    public void goToLogin(){
        finish();
    }


    @Override
    public void finishTask() {

    }

    @Override
    public void initToolBar() {

    }
}
