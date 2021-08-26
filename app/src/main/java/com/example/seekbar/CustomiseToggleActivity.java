package com.example.seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomiseToggleActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_demo_notiterested,tv_demo_interested;
    ImageView imgAddnDetailsArrow;

    LinearLayout ll_partner_services;
    boolean isExpand = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customise_togglenew);

        //tv_demo_interested = findViewById(R.id.tv_demo_interested);
       // tv_demo_notiterested = findViewById(R.id.tv_demo_notiterested);
        imgAddnDetailsArrow = findViewById(R.id.imgAddnDetailsArrow);
        ll_partner_services = findViewById(R.id.ll_partner_services);

        imgAddnDetailsArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               ll_partner_services.setVisibility(isExpand ? (View.VISIBLE) : View.GONE);
               isExpand  = !isExpand;//?false:true;
                //isExpand = "no"? ll_partner_services.setVisibility(View.VISIBLE);
                //isExpand = ll_partner_services.setVisibility(View.GONE);

                //ll_partner_services.setVisibility(View.VISIBLE);
            }
        });

        /*tv_demo_interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_demo_interested.setBackgroundResource(R.drawable.background_selected_toggle);
                tv_demo_notiterested.setBackground(null);
            }
        });

        tv_demo_notiterested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_demo_interested.setBackground(null);
                tv_demo_notiterested.setBackgroundResource(R.drawable.background_selected_toggle);
            }*/
        //});
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_csm_interested:



                break;
        }
    }
}