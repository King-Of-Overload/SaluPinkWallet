package com.zjut.alan.pinkwallet.module.common;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.zjut.alan.pinkwallet.R;
import com.zjut.alan.pinkwallet.constants.WalletConstant;
import com.zjut.alan.pinkwallet.module.guide.CharacterActivity;
import com.zjut.alan.pinkwallet.tools.interpolator.SpringScaleInterpolator;
import com.zjut.alan.saluandroid.base.SaluRxBaseActivity;
import com.zjut.alan.saluandroid.tools.data.SaluPreferenceTool;
import com.zjut.alan.saluandroid.tools.data.SaluRegexTool;
import com.zjut.alan.saluandroid.tools.view.SaluStatusBarHelper;
import com.zjut.alan.saluandroid.tools.view.SaluToastTool;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册
 * @author salu
 */
public class LoginActivity extends SaluRxBaseActivity {
    @BindView(R.id.et_password)
    EditText passwordET;
    @BindView(R.id.ib_delete_cellphone)
    ImageButton deleteCellIB;

    @BindView(R.id.et_cellphone_num)
    EditText cellphoneET;
    @BindView(R.id.ib_delete_password)
    ImageButton deletePwdIB;

    @BindView(R.id.iv_header_view)
    ImageView headerIV;

    @BindView(R.id.btn_login)
    Button loginBtn;

    private AnimatorSet set;
    private WeakReference<LoginActivity> reference;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        reference = new WeakReference<>(this);
        SaluStatusBarHelper.tintStatusBar(getWindow(), getResources().getColor(R.color.colorPrimary));
        //设置手机与密码编辑框的焦点与文字改变监听事件
        //设置手机号码
        cellphoneET.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus && cellphoneET.getText().length() > 0){
                deleteCellIB.setVisibility(View.VISIBLE);
            }else{
                deleteCellIB.setVisibility(View.GONE);
            }
            headerIV.setBackgroundResource(R.drawable.login_name);
        });
        cellphoneET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //若手机号码发生了变化，清空密码
                passwordET.setText("");
                if(s.length() > 0){
                    deleteCellIB.setVisibility(View.VISIBLE);
                }else{
                    deleteCellIB.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //设置密码监听事件
        passwordET.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus && passwordET.getText().length() > 0){
                deletePwdIB.setVisibility(View.VISIBLE);
            }else{
                deletePwdIB.setVisibility(View.GONE);
            }
            headerIV.setBackgroundResource(R.drawable.login_pwd);
        });
        passwordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    deletePwdIB.setVisibility(View.VISIBLE);
                }else{
                    deletePwdIB.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //为登录按钮添加弹性果冻动画效果,通过interpolator（差值器）实现弹性效果
        //差值器设置:http://inloop.github.io/interpolator/
        //重写interpolator类
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(loginBtn, "scaleX",1.0f,1.1f,1.0f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(loginBtn,"scaleY",1.0f,1.1f,1.0f);
        set = new AnimatorSet();
        set.setDuration(1000);//间隔
        set.setInterpolator(new SpringScaleInterpolator(0.4f));
        set.playTogether(animatorX, animatorY);
    }

    /**
     * 删除电话点击事件
     */
    @OnClick(R.id.ib_delete_cellphone)
    public void deleteCellClick(){
        cellphoneET.setText("");
        passwordET.setText("");
    }

    /**
     * 删除密码点击事件
     */
    @OnClick(R.id.ib_delete_password)
    public void deletePwdClick(){
        passwordET.setText("");
    }

    /**
     * 登录按钮
     */
    @OnClick(R.id.btn_login)
    public void loginBtnClick(){
        set.start();//开始动画
        if(cellphoneET.getText().length() == 0){
            SaluToastTool.displayShortToast(reference.get(), getString(R.string.cell_not_null));
            return;
        }else if(passwordET.getText().length() == 0){
            SaluToastTool.displayShortToast(reference.get(), getString(R.string.pwd_not_null));
            return;
        }else if(!SaluRegexTool.isMobileSimple(cellphoneET.getText().toString().trim())){
            SaluToastTool.displayShortToast(reference.get(), getString(R.string.cell_format));
            return;
        }
        finishTask();
    }


    /**
     * 注册按钮
     */
    @OnClick(R.id.tv_register)
    public void goToRegister(){
        RegisterActivity.launch(this);
    }

    /**
     * 忘记密码
     */
    @OnClick(R.id.tv_forget_password)
    public void forgetPassword(){
        ForgetPwdActivity.launch(this);
    }


    @Override
    public void finishTask() {
        //模拟登录
        String cellphone = "15861376658";
        String password = "alanzjut";
        if(cellphoneET.getText().toString().equals(cellphone) &&
                passwordET.getText().toString().equals(password)){
            //存入shared preference
            SaluPreferenceTool.putString(WalletConstant.PREFERENCE_CELLPHONE, cellphone);
            SaluPreferenceTool.putString(WalletConstant.PREFERENCE_PASSWORD, password);
            //跳转
            if(SaluPreferenceTool.retrieveBoolean(WalletConstant.PREFERENCE_FIRST_LOGIN, true)){
                CharacterActivity.launch(this);
            }else{
                //:TODO:进入主页

            }

        }else{
            SaluToastTool.displayShortToast(reference.get(), getString(R.string.info_error));
        }
    }

    @Override
    public void initToolBar() {

    }
}
