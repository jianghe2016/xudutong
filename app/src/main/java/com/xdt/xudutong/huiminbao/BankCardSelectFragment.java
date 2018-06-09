package com.xdt.xudutong.huiminbao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BankCardSelectFragment extends BottomSheetDialogFragment {

    private OnTimeClickedListener mOnTimeClickedListener;
    @BindView(R.id.iv_cancle)
    ImageView mIvCancle;
    @BindView(R.id.list_view)
    ListView mListView;
    @BindView(R.id.tv_add_bank_card)
    TextView mTvAddBankCard;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_bank_card_select, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_cancle, R.id.tv_add_bank_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cancle:
                dismiss();
                break;
            case R.id.tv_add_bank_card:
                ToastUtils.getInstance(getActivity()).showMessage("添加付款卡");
                break;
        }
    }

    public interface OnTimeClickedListener {
        void onTimeClicked(String s);
    }

    public void setOnTimeClickedListener(OnTimeClickedListener onTimeClickedListener) {
        mOnTimeClickedListener = onTimeClickedListener;
    }
}
