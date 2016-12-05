package com.hhzmy.frag;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhzmy.hhz.R;
import com.hhzmy.util.isPhone;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by w9072 on 2016/11/8.
 */

public class user_frag extends Fragment implements View.OnClickListener {

    @BindView(R.id.user_AN_ed)
    EditText userANEd;
    @BindView(R.id.user_PS_ed)
    EditText userPSEd;
    @BindView(R.id.user_login_bt)
    Button userLoginBt;
    @BindView(R.id.user_forget_tv)
    TextView userForgetTv;
    @BindView(R.id.user_register_bt)
    Button userRegisterBt;
    @BindView(R.id.user_MD_login)
    LinearLayout userMDLogin;
    @BindView(R.id.user_YFB_login)
    LinearLayout userYFBLogin;
    @BindView(R.id.ivDeleteText_1)
    ImageView ivDeleteText1;
    @BindView(R.id.ivDeleteText_2)
    ImageView ivDeleteText2;
    @BindView(R.id.isno_check)
    CheckBox isnoCheck;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_frag_layout, null, false);
        ButterKnife.bind(this, view);
        ivDeleteText1.setOnClickListener(this);
        ivDeleteText2.setOnClickListener(this);
        userMDLogin.setOnClickListener(this);
        userYFBLogin.setOnClickListener(this);
        userLoginBt.setOnClickListener(this);
        userANEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

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
                    userANEd.setText(sb.toString());
                    userANEd.setSelection(index);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    userLoginBt.setBackgroundColor(Color.rgb(202, 202, 197));
                    ivDeleteText1.setVisibility(View.INVISIBLE);
                } else if (editable.length() == 13) {
                    String text = userANEd.getText().toString().trim();
                    String phone_num = text.replace(" ", "");
                    boolean b = isPhone.isMobileNO(phone_num);
                    if (b == false) {
                        Toast.makeText(getActivity(), "输入的号码有误!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    ivDeleteText1.setVisibility(View.VISIBLE);
                    String PS = userPSEd.getText().toString();
                    int length = PS.length();
                    if (length > 0) {
                        userLoginBt.setBackgroundColor(Color.rgb(251, 203, 61));
                        userLoginBt.setClickable(true);
                    } else {
                        userLoginBt.setClickable(false);
                    }
                }
            }
        });
        userPSEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    userLoginBt.setBackgroundColor(Color.rgb(202, 202, 197));
                    ivDeleteText2.setVisibility(View.INVISIBLE);
                } else {
                    ivDeleteText2.setVisibility(View.VISIBLE);
                    String AN = userANEd.getText().toString();
                    int length = AN.length();
                    if (length > 0) {
                        userLoginBt.setBackgroundColor(Color.rgb(251, 203, 61));
                        userLoginBt.setClickable(true);
                    } else {
                        userLoginBt.setClickable(false);
                    }
                }
            }
        });

        isnoCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    userPSEd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    userPSEd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivDeleteText_1:
                userANEd.setText("");
                break;
            case R.id.ivDeleteText_2:
                userPSEd.setText("");
                break;
            case R.id.user_YFB_login:
                new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.QQ)
                        .withText("你好！")
                        .setCallback(umShareListener)
                        .share();
                break;
            case R.id.user_MD_login:
                UMShareAPI mShareAPI = UMShareAPI.get(getActivity());
                mShareAPI.doOauthVerify(getActivity(), SHARE_MEDIA.QQ, umAuthListener);
                //mShareAPI.getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.user_login_bt:
                if (userPSEd.getText().toString().length() > 0 && userANEd.getText().toString().length() > 0) {
                    Toast.makeText(getActivity(), "登录成功!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //分享回调
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);

            Toast.makeText(getActivity(), platform + "分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), platform + "分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), platform + "分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
    //授权回调
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Set<String> set = data.keySet();
            for (String string : set) {
                Log.i("msg",
                        "============================Map=========================");
                Log.i("msg", string + "::::" + data.get(string));
                //获取用户名称
                String username = data.get("screen_name");
                /* 用户头像
                string.equals("profile_image_url")*/
                Toast.makeText(getActivity(), "已获取用户" + username + "的信息", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getActivity(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getActivity(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
}
