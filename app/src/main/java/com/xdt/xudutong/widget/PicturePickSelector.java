package com.xdt.xudutong.widget;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/7.
 */

public class PicturePickSelector extends BottomSheetDialogFragment {

    @BindView(R.id.ll_layout0)
    LinearLayout mLlLayout0;
    @BindView(R.id.ll_layout1)
    LinearLayout mLlLayout1;
    @BindView(R.id.ll_layout2)
    LinearLayout mLlLayout2;
    private Unbinder mButterBind;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onResume() {
//        getDialog().getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.bottom_sheet_picture_pick, container, false);
        mButterBind = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.ll_layout2)
    public void onCancelClicked() {
        dismiss();
    }

    @OnClick(R.id.ll_layout1)
    public void onPickPhotoClicked() {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onPickPhotoClicked();
        }
        dismiss();
    }

    @OnClick(R.id.ll_layout0)
    public void onTakePhotoClicked() {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onTakePhotoClicked();
        }
        dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mButterBind.unbind();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public interface OnItemClickListener {
        void onPickPhotoClicked();

        void onTakePhotoClicked();

        void onCancelClicked();
    }
}
