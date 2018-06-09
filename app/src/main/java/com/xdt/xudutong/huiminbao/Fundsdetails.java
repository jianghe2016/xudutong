package com.xdt.xudutong.huiminbao;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2018\2\6 0006.
 */

public class Fundsdetails extends BaseActivity {
    private LinearLayout mfundsdetailslayoutbuttomlayoutmorecontentt1;
    private LinearLayout mfundsdetailslayoutbuttomlayout1;

    @Override
    public void initView() {
        LinearLayout mfundsdetailslayoutback = (LinearLayout) findViewById(R.id.fundsdetailslayoutback);
        mfundsdetailslayoutbuttomlayout1 = (LinearLayout) findViewById(R.id.fundsdetailslayoutbuttomlayout1);
        TextView mfundsdetailslayoutbuttomselect1 = (TextView) findViewById(R.id.fundsdetailslayoutbuttomselect1);
        TextView mfundsdetailslayoutbuttomselect2 = (TextView) findViewById(R.id.fundsdetailslayoutbuttomselect2);
        Button mfundsdetailslayoutbuttomlayoutmorebutton1 = (Button) findViewById(R.id.fundsdetailslayoutbuttomlayoutmorebutton1);
        Button mfundsdetailslayoutbuttomlayoutmoresubmit = (Button) findViewById(R.id.fundsdetailslayoutbuttomlayoutmoresubmit);
        mfundsdetailslayoutbuttomlayoutmorecontentt1 = (LinearLayout) findViewById(R.id.fundsdetailslayoutbuttomlayoutmorecontentt1);
        mfundsdetailslayoutbuttomlayout1.addView(addview1());
        mfundsdetailslayoutbuttomselect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfundsdetailslayoutbuttomlayoutmorecontentt1.setVisibility(View.GONE);
                if (mfundsdetailslayoutbuttomlayout1.getChildCount() > 0) {
                    mfundsdetailslayoutbuttomlayout1.removeAllViews();
                }
                mfundsdetailslayoutbuttomlayout1.addView(addview1());
            }
        });
        mfundsdetailslayoutbuttomselect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfundsdetailslayoutbuttomlayoutmorecontentt1.setVisibility(View.GONE);
                if (mfundsdetailslayoutbuttomlayout1.getChildCount() > 0) {
                    mfundsdetailslayoutbuttomlayout1.removeAllViews();
                }
                mfundsdetailslayoutbuttomlayout1.addView(addview2());
            }
        });
        mfundsdetailslayoutbuttomlayoutmorebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfundsdetailslayoutbuttomlayoutmorecontentt1.setVisibility(View.VISIBLE);
            }
        });
        mfundsdetailslayoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mfundsdetailslayoutbuttomlayoutmoresubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.fundsdetailslayout);
    }

    public View addview1() {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater3 = LayoutInflater.from(Fundsdetails.this);
        View view = inflater3.inflate(R.layout.fundsdetailslayoutbuttom1, null);
        view.setLayoutParams(lp);
        return view;
    }

    public View addview2() {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater3 = LayoutInflater.from(Fundsdetails.this);
        View view = inflater3.inflate(R.layout.fundsdetailslayoutbuttom2, null);
        view.setLayoutParams(lp);
        return view;
    }
}
