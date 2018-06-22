package com.zjut.alan.pinkwallet.module.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.zjut.alan.pinkwallet.R;
import com.zjut.alan.saluandroid.base.SaluRxBaseActivity;
import com.zjut.alan.saluandroid.tools.data.SaluRegexTool;
import com.zjut.alan.saluandroid.tools.view.SaluToastTool;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 找回密码
 * @author salu
 */
public class ForgetPwdActivity extends SaluRxBaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_cellphone_num)
    EditText cellphoneET;

    @BindView(R.id.et_verify_code)
    EditText verifyCodeET;

    private WeakReference<ForgetPwdActivity> reference;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity, ForgetPwdActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        reference = new WeakReference<>(this);
    }

    @Override
    public void initToolBar() {

    }

    /**
     * 返回上层
     */
    @OnClick(R.id.ib_back)
    public void back(){
        finish();
    }

    /**
     * 提交按钮
     */
    @OnClick(R.id.btn_submit)
    public void submit(){
        if(cellphoneET.getText().length() == 0){
            SaluToastTool.displayShortToast(reference.get(), getString(R.string.please_input_cellphone));
            return;
        }else if(!SaluRegexTool.isMobileSimple(cellphoneET.getText().toString().trim())){
            SaluToastTool.displayShortToast(reference.get(), getString(R.string.cell_format));
            return;
        }else if(verifyCodeET.getText().length() == 0){
            SaluToastTool.displayShortToast(reference.get(), getString(R.string.please_input_verify_code));
            return;
        }
    }
}
