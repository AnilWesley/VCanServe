package com.vupadhi.heyhelp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.mvp.contract.activity.NavigationPaymentsScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.NavigationPaymentsScreenImpl;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.ui.Fragments.Paid_history_frag;
import com.vupadhi.heyhelp.ui.Fragments.Pending_payment_frag;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.customfonts.CustomTextViewBold;

public class Navigation_payments_screen extends BaseAbstractActivity<NavigationPaymentsScreenImpl> implements NavigationPaymentsScreenContract.IView, APIResponseCallback {
ImageView imageView;
CustomTextViewBold customTextViewBold_one,customTextViewBold_two;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();


    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_navigation_payments_screen, null);
        return view;
    }

    public void fragment_paid() {
        Paid_history_frag paid_history_frag=new Paid_history_frag();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.frag,paid_history_frag);
        transaction.commit();
    }

    public void fragment_pending() {
        Pending_payment_frag pending_payment_frag=new Pending_payment_frag();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.frag,pending_payment_frag);
        transaction.commit();
    }

    @Override
    public void setPresenter() {

        presenter = new NavigationPaymentsScreenImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        fragment_pending();
        imageView=findViewById(R.id.payments_back_but);
        customTextViewBold_one=findViewById(R.id.pending_but);
        customTextViewBold_two=findViewById(R.id.paid_but);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        customTextViewBold_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_pending();
                customTextViewBold_one.setTextColor(getResources().getColor(R.color.text_black));
                customTextViewBold_two.setTextColor(getResources().getColor(R.color.normal_gray));
            }
        });
        customTextViewBold_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_paid();
                customTextViewBold_one.setTextColor(getResources().getColor(R.color.normal_gray));
                customTextViewBold_two.setTextColor(getResources().getColor(R.color.text_black));
            }
        });

    }


    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {

    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
