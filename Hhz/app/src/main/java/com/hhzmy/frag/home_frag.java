package com.hhzmy.frag;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hhzmy.activity.MyMapActivity;
import com.hhzmy.activity.QRCodeActivity;
import com.hhzmy.activity.WebActivity;
import com.hhzmy.adpater.HomePagerAdapter;
import com.hhzmy.adpater.Home_frag_gv1_adapter;
import com.hhzmy.adpater.Home_frag_lv1_adapter;
import com.hhzmy.adpater.Home_frag_lv2_adapter;
import com.hhzmy.adpater.Home_frag_lv3_adapter;
import com.hhzmy.adpater.Home_frag_lv4_adapter;
import com.hhzmy.adpater.Home_frag_lv5_adapter;
import com.hhzmy.bean.Home_DataBean;
import com.hhzmy.hhz.R;
import com.hhzmy.util.OkHttp;
import com.hhzmy.view.MyGridView;
import com.hhzmy.view.MyListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by w9072 on 2016/11/8.
 */

public class home_frag extends Fragment implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.home_frag_pager)
    ViewPager homeFragPager;
    @BindView(R.id.home_frag_gv_view)
    MyGridView homeFragGvView;
    @BindView(R.id.home_frag_RelativeLayout)
    RelativeLayout homeFragRelativeLayout;
    @BindView(R.id.homefrag_pager_radiogroup)
    RadioGroup homefragPagerRadiogroup;
    @BindView(R.id.home_frag_lv_view)
    MyListView homeFragLvView;
    @BindView(R.id.home_frag_lv2_view)
    MyListView homeFragLv2View;
    @BindView(R.id.home_frag_lv3_view)
    MyListView homeFragLv3View;
    @BindView(R.id.home_frag_img_zt)
    ImageView homeFragImgZt;
    @BindView(R.id.home_frag_lv4_view)
    MyListView homeFragLv4View;
    @BindView(R.id.home_frag_QRcode)
    ImageView homeFragQRcode;
    @BindView(R.id.home_frag_img_pt)
    ImageView homeFragImgPt;
    @BindView(R.id.home_frag_lv5_view)
    MyListView homeFragLv5View;
    @BindView(R.id.my_map)
    ImageView myMap;
    private View view;
    private String json;
    private String ImageUrl = "http://image1.suning.cn";
    private ImageLoader loader;
    private ArrayList<ImageView> list_img;
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    //handler消息处理
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                int currentItem = homeFragPager.getCurrentItem();
                currentItem++;
                homeFragPager.setCurrentItem(currentItem);
            } else if (msg.what == 1) {
                // Toast.makeText(getActivity(), json, Toast.LENGTH_SHORT).show();
                //analysis_data();
                return;
            }
        }
    };
    private List<Home_DataBean.DataBean.TagBean> tag2;
    private List<Home_DataBean.DataBean> pt_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_frag_layout, null, false);
        ButterKnife.bind(this, view);
        loader = ImageLoader.getInstance();
        loader.init(ImageLoaderConfiguration.createDefault(getActivity()));
        //获取屏幕大小，计算后设置viewpager区域的高度
        setVp_Height();
        //扫描二维码
        homeFragQRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QRCodeActivity.class);
                startActivity(intent);
            }
        });
        //获取首页网络数据
        gethome_HttpData();
        myMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyMapActivity.class);
                startActivity(intent);
            }
        });
        onClick();
        return view;
    }

    //获取屏幕大小，计算后设置viewpager区域的高度
    private void setVp_Height() {
        //获取屏幕高度宽度
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        //Toast.makeText(getActivity(), "宽:" + width + "高:" + height, Toast.LENGTH_SHORT).show();
        int vp_hight_px = height / 3;

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) homeFragRelativeLayout.getLayoutParams();
        //获取当前控件的布局对象
        params.height = height / 9 * 2;//设置当前控件布局的高度
        homeFragRelativeLayout.setLayoutParams(params);//将设置好的布局参数应用到控件中
    }

    // handler发送延时消息更新viewpager页面
    public void change_pager() {
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    //获取首页网络数据
    public void gethome_HttpData() {
        OkHttp.getAsync("http://mock.eoapi.cn/success/jSWAxchCQfuhI6SDlIgBKYbawjM3QIga", new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                json = result;
                analysis_data();
            }
        });
    }

    //解析网络数据
    private void analysis_data() {
        Gson gson = new Gson();
        Home_DataBean home_dataBean = gson.fromJson(json, Home_DataBean.class);
        List<Home_DataBean.DataBean> data = home_dataBean.getData();
        List<Home_DataBean.DataBean.TagBean> frag_home_pager_tag = data.get(0).getTag();
        tag2 = data.get(1).getTag();
        List<Home_DataBean.DataBean.TagBean> tag3 = data.get(2).getTag();
        //品牌专区数据解析
        List<Home_DataBean.DataBean.TagBean> tag4 = data.get(4).getTag();
        List<Home_DataBean.DataBean.TagBean> tag5 = data.get(5).getTag();
        List<Home_DataBean.DataBean.TagBean> tag6 = data.get(6).getTag();
        List<Home_DataBean.DataBean.TagBean> tag7 = data.get(7).getTag();
        List<Home_DataBean.DataBean.TagBean> tag8 = data.get(9).getTag();
        List<Home_DataBean.DataBean.TagBean> tag9 = data.get(10).getTag();
        List<Home_DataBean.DataBean.TagBean> tag10 = data.get(11).getTag();
        List<Home_DataBean.DataBean.TagBean> tag11 = data.get(13).getTag();
        String PP_picUrl_1 = tag4.get(0).getPicUrl();
        String PP_picUrl_2 = tag5.get(0).getPicUrl();
        String PP_picUrl_3 = tag5.get(1).getPicUrl();
        String PP_picUrl_4 = tag6.get(0).getPicUrl();
        String PP_picUrl_5 = tag6.get(1).getPicUrl();
        String PP_picUrl_6 = tag7.get(0).getPicUrl();
        String PP_picUrl_7 = tag7.get(1).getPicUrl();
        ArrayList<String> PP_picUrl_list = new ArrayList<String>();
        PP_picUrl_list.add(PP_picUrl_1);
        PP_picUrl_list.add(PP_picUrl_2);
        PP_picUrl_list.add(PP_picUrl_3);
        PP_picUrl_list.add(PP_picUrl_4);
        PP_picUrl_list.add(PP_picUrl_5);
        PP_picUrl_list.add(PP_picUrl_6);
        PP_picUrl_list.add(PP_picUrl_7);
        //母婴专区数据解析
        String ZS_picUrl_1 = tag8.get(0).getPicUrl();
        String ZS_picUrl_2 = tag9.get(0).getPicUrl();
        String ZS_picUrl_3 = tag9.get(1).getPicUrl();
        String ZS_picUrl_4 = tag10.get(0).getPicUrl();
        String ZS_picUrl_5 = tag10.get(1).getPicUrl();
        String ZS_picUrl_6 = tag10.get(2).getPicUrl();
        String ZS_picUrl_7 = tag10.get(3).getPicUrl();
        ArrayList<String> ZS_picUrl_list = new ArrayList<String>();
        ZS_picUrl_list.add(ZS_picUrl_1);
        ZS_picUrl_list.add(ZS_picUrl_2);
        ZS_picUrl_list.add(ZS_picUrl_3);
        ZS_picUrl_list.add(ZS_picUrl_4);
        ZS_picUrl_list.add(ZS_picUrl_5);
        ZS_picUrl_list.add(ZS_picUrl_6);
        ZS_picUrl_list.add(ZS_picUrl_7);
        // viewpager适配
        list_img = new ArrayList<ImageView>();
        List<String> pager_url = new ArrayList<String>();
        for (int i = 0; i < frag_home_pager_tag.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            // Log.e("TAG", frag_home_pager_tag.get(i).getPicUrl());
            loader.displayImage(ImageUrl + frag_home_pager_tag.get(i).getPicUrl(), imageView);
            list_img.add(imageView);
            pager_url.add(frag_home_pager_tag.get(i).getLinkUrl());
        }
        // 给viewpager设置小圆点
        setdot(list_img.size());
        // 设置适配器
        homeFragPager.setAdapter(new HomePagerAdapter(list_img, pager_url, getActivity()));
        change_pager();
        //viewpager设置滑动监听
        homeFragPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                homefragPagerRadiogroup.check(position % list_img.size());
                change_pager();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    //手指滑动状态
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        handler.removeCallbacksAndMessages(null);
                        break;
                    //停止状态
                    case ViewPager.SCROLL_STATE_IDLE:
                        break;
                    //自动滑动状态
                    case ViewPager.SCROLL_STATE_SETTLING:
                        break;
                    default:
                        break;
                }
            }
        });
        homeFragGvView.setAdapter(new Home_frag_gv1_adapter(tag2, getActivity()));
        homeFragLvView.setAdapter(new Home_frag_lv1_adapter(tag3, getActivity()));
        homeFragLv2View.setAdapter(new Home_frag_lv2_adapter(PP_picUrl_list, getActivity()));
        homeFragLv3View.setAdapter(new Home_frag_lv3_adapter(ZS_picUrl_list, getActivity()));
        loader.displayImage(ImageUrl + tag11.get(0).getPicUrl(), homeFragImgZt);
        //主题特卖专区
        List<Home_DataBean.DataBean.TagBean> mTagBean = new ArrayList<>();
        mTagBean.addAll(data.get(14).getTag());
        mTagBean.addAll(data.get(16).getTag());
        mTagBean.addAll(data.get(18).getTag());
        mTagBean.addAll(data.get(20).getTag());
        List<Home_DataBean.DataBean> mDataBean = new ArrayList<>();
        mDataBean.add(data.get(15));
        mDataBean.add(data.get(17));
        mDataBean.add(data.get(19));
        mDataBean.add(data.get(21));
        homeFragLv4View.setAdapter(new Home_frag_lv4_adapter(mTagBean, mDataBean, getActivity()));
        //拼团
        loader.displayImage(ImageUrl + data.get(23).getTag().get(0).getPicUrl(), homeFragImgPt);
        pt_list = new ArrayList<Home_DataBean.DataBean>();
        pt_list.add(data.get(24));
        pt_list.add(data.get(26));
        pt_list.add(data.get(28));
        pt_list.add(data.get(30));
        pt_list.add(data.get(32));
        pt_list.add(data.get(33));
        TextView tv_view = new TextView(getActivity());
        tv_view.setText("~~到底啦，明天10点1元秒杀，千万别错过哦~~");
        tv_view.setMaxLines(1);
        tv_view.setGravity(Gravity.CENTER);
        homeFragLv5View.addFooterView(tv_view);
        homeFragLv5View.setAdapter(new Home_frag_lv5_adapter(pt_list, getActivity()));
    }

    // 给viewpager设置小圆点
    private void setdot(int size) {
        int wrop = RadioGroup.LayoutParams.WRAP_CONTENT;
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(wrop, wrop);
        params.leftMargin = 5;
        for (int i = 0; i < size; i++) {
            RadioButton button = new RadioButton(getActivity());
            button.setEnabled(false);
            button.setId(i);
            button.setButtonDrawable(R.drawable.dot_selector);
            if (i == 0) {
                homefragPagerRadiogroup.addView(button);
            } else {
                homefragPagerRadiogroup.addView(button, params);
            }
        }
        homefragPagerRadiogroup.check(0);
    }

    //扫描跳转Activity RequestCode
    public static final int REQUEST_CODE = 111;


    @Override
    public void onStart() {
        super.onStart();
        requestCodeQrcodePermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQrcodePermissions() {
        String[] perms = {Manifest.permission.CAMERA};
        if (!EasyPermissions.hasPermissions(getActivity(), perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }


    public void onClick() {
        //签到栏目
        homeFragGvView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", tag2.get(i).getLinkUrl());
                intent.putExtra("webtitle", tag2.get(i).getElementName());
                startActivity(intent);
            }
        });
        homeFragLv5View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", pt_list.get(i).getTag().get(0).getLinkUrl());
                startActivity(intent);
            }
        });
    }
}
