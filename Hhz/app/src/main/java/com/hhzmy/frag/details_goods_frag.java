package com.hhzmy.frag;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hhzmy.activity.MyMapActivity;
import com.hhzmy.hhz.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hhzmy.hhz.R.id.detailed_market_price;

/**
 * Created by w9072 on 2016/11/16.
 */

public class details_goods_frag extends Fragment {

    @BindView(R.id.naifen_img)
    ImageView naifenImg;
    @BindView(R.id.detailed_goodname)
    TextView detailedGoodname;
    @BindView(R.id.rating_radiobutton)
    CheckBox ratingRadiobutton;
    @BindView(R.id.rating_text)
    TextView ratingText;
    @BindView(R.id.radio)
    LinearLayout radio;
    @BindView(R.id.detailed_shop_price)
    TextView detailedShopPrice;
    @BindView(detailed_market_price)
    TextView detailedMarketPrice;
    @BindView(R.id.Sales_volume_text)
    TextView SalesVolumeText;
    @BindView(R.id.Collection_number_text)
    TextView CollectionNumberText;
    @BindView(R.id.detailedscroll)
    ScrollView detailedscroll;
    @BindView(R.id.details_address)
    TextView detailsAddress;
    private View view;
    public String details_data[] = {"日本进口花王婴幼儿纸尿裤 中号 M64片（6-11kg）", "99.00", "168.00", "12536", "6201"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.details_goods_frag_layout, null, false);
        ButterKnife.bind(this, view);
        List<TextView> list = new ArrayList<>();
        list.add(detailedGoodname);
        list.add(detailedShopPrice);
        list.add(detailedMarketPrice);
        list.add(SalesVolumeText);
        list.add(CollectionNumberText);
        detailedMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setText(details_data[i]);
        }
        detailsAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyMapActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String address = data.getStringExtra("address");
        switch (requestCode) {
            case 0:
                detailsAddress.setText(address);
                break;
        }
    }
}
