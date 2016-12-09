package com.hhzmy.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hhzmy.MyService;
import com.hhzmy.hhz.R;
import com.hhzmy.util.isPhone;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.yz_phonenum)
    EditText yzPhonenum;
    @BindView(R.id.if_tyzc)
    CheckBox ifTyzc;
    @BindView(R.id.zc_tv)
    TextView zcTv;
    @BindView(R.id.next_bt)
    Button nextBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //启动服务
        //startService(new Intent(this, MyService.class));
        /*Intent intent = new Intent(RegisterActivity.this, MyService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);*/

        //监听输入框输入的手机号
        yzPhonenum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() == 0) return;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    if (i != 3 && i != 8 && s.charAt(i) == ' ') {
                        continue;
                    } else {
                        sb.append(s.charAt(i));
                        if ((sb.length() == 4 || sb.length() == 9) && sb.charAt(sb.length() - 1) != ' ') {
                            sb.insert(sb.length() - 1, ' ');
                        }
                    }
                }
                if (!sb.toString().equals(s.toString())) {
                    int index = start + 1;
                    if (sb.charAt(start) == ' ') {
                        if (before == 0) {
                            index++;
                        } else {
                            index--;
                        }
                    } else {
                        if (before == 1) {
                            index--;
                        }
                    }
                    yzPhonenum.setText(sb.toString());
                    yzPhonenum.setSelection(index);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    nextBt.setBackgroundColor(Color.rgb(202, 202, 197));
                } else if (editable.length() == 13) {
                    String text = yzPhonenum.getText().toString().trim();
                    String phone_num = text.replace(" ", "");
                    boolean b = isPhone.isMobileNO(phone_num);
                    if (b == false) {
                        Toast.makeText(RegisterActivity.this, "输入的号码有误!", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean checked = ifTyzc.isChecked();
                        if (checked == false) {
                            nextBt.setBackgroundColor(Color.rgb(202, 202, 197));
                        } else {
                            nextBt.setBackgroundColor(Color.rgb(251, 203, 61));
                        }
                    }
                }
            }
        });
        //是否同意条款监听
        ifTyzc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    String text = yzPhonenum.getText().toString().trim();
                    String phone_num = text.replace(" ", "");
                    if (phone_num.length() == 11) {
                        nextBt.setBackgroundColor(Color.rgb(251, 203, 61));
                        nextBt.setClickable(true);
                    } else {
                        nextBt.setBackgroundColor(Color.rgb(202, 202, 197));
                        nextBt.setClickable(false);
                    }
                } else {
                    nextBt.setBackgroundColor(Color.rgb(202, 202, 197));
                    nextBt.setClickable(false);
                }
            }
        });
        //下一步按钮监听
        nextBt.setOnClickListener(this);
    }

    //这里需要用到ServiceConnection在Context.bindService和context.unBindService()里用到
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        //当我bindService时，让TextView显示MyService里getSystemTime()方法的返回值
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
        }

        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub

        }
    };

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.stopService(new Intent(this, MyService.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //下一步
            case R.id.next_bt:
                SMSSDK.initSDK(RegisterActivity.this, "19ba81bb93830", "7352424464c0bbeeb14c73278018b90d");
                String text = yzPhonenum.getText().toString().trim();
                String phone_num = text.replace(" ", "");
                /*//发送短信
                cn.smssdk.SMSSDK.getVerificationCode("86", phone_num);
                Toast.makeText(RegisterActivity.this, "短信已发送!", Toast.LENGTH_SHORT).show();*/
                break;
        }

    }
}
