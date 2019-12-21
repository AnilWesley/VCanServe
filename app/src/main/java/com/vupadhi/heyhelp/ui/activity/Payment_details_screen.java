package com.vupadhi.heyhelp.ui.activity;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vupadhi.heyhelp.adapter.PremiumPkgRecyclerAdapter;
import com.vupadhi.heyhelp.adapter.UltraPremiumPkgRecyclerAdapter;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.base.BaseAbstractActivity;
import com.vupadhi.heyhelp.models.PackageSelectionModel;
import com.vupadhi.heyhelp.mvp.contract.activity.PaymentDetailsScreenContract;
import com.vupadhi.heyhelp.mvp.presenter.activity.PaymentDetailsScreenImpl;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.APIResponseCallback;
import com.vupadhi.heyhelp.sharepref.UserSession;
import com.vupadhi.heyhelp.utils.Util;
import com.vupadhi.heyhelp.utils.Variables;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Payment_details_screen extends BaseAbstractActivity<PaymentDetailsScreenImpl> implements PaymentDetailsScreenContract.IView, APIResponseCallback {
    ImageView imageView;
    LinearLayout linearLayout;
    TextView package_name, amount_text, coupon_text, subtotal_text, gst_text, totalamount_text;
    APIResponseCallback apiResponseCallback;
    String nClientid, nPackageid;
    UserSession userSession;
    RecyclerView package_rv;
    Spinner spinner;
    TextView coupon_tv,discount_tv;
    String packagename, amount, coupon,discount, gst, totalamount;
    PremiumPkgRecyclerAdapter premiumPkgRecyclerAdapter;
    UltraPremiumPkgRecyclerAdapter ultraPremiumPkgRecyclerAdapter;

    ArrayList<String> couponslist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.start();
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_payment_details_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

        presenter = new PaymentDetailsScreenImpl(this, this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        apiResponseCallback = this;
        userSession = new UserSession(this);
        nClientid = userSession.getUserDetails().get(UserSession.KEY_nCLIENTID);

        Bundle b = getIntent().getExtras();
        nPackageid = b.getString("packageID");

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("nClientID",nClientid);
        requestBody.put("nServiceID", String.valueOf(Variables.ServiceID));
        requestBody.put("nPackageID",nPackageid);
        presenter.retreive_package(Payment_details_screen.this, apiResponseCallback, requestBody);


        imageView = findViewById(R.id.details_back_but);
        package_rv = findViewById(R.id.package_rv);
        linearLayout = findViewById(R.id.details_submit_lay_out);

        package_name = findViewById(R.id.package_name);
        amount_text = findViewById(R.id.amount_text);
        coupon_text = findViewById(R.id.coupon_text);
        subtotal_text = findViewById(R.id.subtotal_text);
        gst_text = findViewById(R.id.gst_text);
        totalamount_text = findViewById(R.id.totalamount_text);
        spinner = findViewById(R.id.spinner);
        coupon_tv = findViewById(R.id.coupon_tv);
        discount_tv = findViewById(R.id.discount_tv);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment_details_screen.this, Payment_okay_screen.class);
                startActivity(intent);
            }
        });

        Map<String, String> requestBody1 = new HashMap<>();
        presenter.package_selection(Payment_details_screen.this, apiResponseCallback, requestBody1, nClientid);

    }


    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {

                Toast.makeText(Payment_details_screen.this, "Please check your internet connection", Toast.LENGTH_LONG).show();


            } else if (NetworkConstants.RequestCode.PACKAGE_SELECTION == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                    PackageSelectionModel packageSelectionModel = new Gson().fromJson(responseString, PackageSelectionModel.class);

                    if (nPackageid.equalsIgnoreCase("1")) {
                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(Payment_details_screen.this);
                        //setting horizontal list
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        package_rv.setLayoutManager(mLayoutManager);
                        package_rv.setItemAnimator(new DefaultItemAnimator());


                        //Adding Adapter to recyclerView
                        premiumPkgRecyclerAdapter = new PremiumPkgRecyclerAdapter(packageSelectionModel, Payment_details_screen.this);
                        package_rv.setAdapter(premiumPkgRecyclerAdapter);
                        premiumPkgRecyclerAdapter.setOnItemClickListener(new PremiumPkgRecyclerAdapter.OnitemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                            }
                        });

                    } else {
                        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(Payment_details_screen.this);
                        //setting horizontal list
                        mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                        package_rv.setLayoutManager(mLayoutManager1);
                        package_rv.setItemAnimator(new DefaultItemAnimator());


                        //Adding Adapter to recyclerView
                        ultraPremiumPkgRecyclerAdapter = new UltraPremiumPkgRecyclerAdapter(packageSelectionModel, Payment_details_screen.this);
                        package_rv.setAdapter(ultraPremiumPkgRecyclerAdapter);
                        ultraPremiumPkgRecyclerAdapter.setOnItemClickListener(new UltraPremiumPkgRecyclerAdapter.OnitemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {


                            }
                        });
                    }

                } else {

                    Util.getInstance().cusToast(Payment_details_screen.this, jsonObject.optString("message"));
                }
            } else if (NetworkConstants.RequestCode.RETREIVE_PACKAGE == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("packageSelection");

                    packagename = jsonObject2.getString("paymentDesc");
                    amount = jsonObject2.getString("sAmount");
//                    coupon = jsonObject2.getString("coupnCode");
                    gst = jsonObject2.getString("sGst");
                    totalamount = jsonObject2.getString("sTotalAmount");


                    package_name.setText(packagename);
                    amount_text.setText("₹" + amount);
                    /*if (coupon != null) {
                        coupon_text.setText(coupon);
                    }*/
                    subtotal_text.setText("₹" + amount);
                    gst_text.setText("₹" + gst);
                    totalamount_text.setText("₹" + totalamount);


                    JSONArray jsonArray=jsonObject2.getJSONArray("coupnCodes");


                    for (int i=0;i<jsonArray.length();i++)
                    {
                        couponslist.add(jsonArray.getString(i));
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, couponslist);

                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                    spinner.setAdapter(adapter);


                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            String couponname = couponslist.get(position);
//                            coupon_tv.setVisibility(View.VISIBLE);
//                            coupon_tv.setText(couponname);
                            coupon_text.setText(couponname);

                            Map<String, String> requestBody = new HashMap<>();
                            requestBody.put("nClientID",nClientid);
                            requestBody.put("nOfferCode",couponname);
                            requestBody.put("sTotalAmount",totalamount);
                            presenter.apply_coupon(Payment_details_screen.this, apiResponseCallback, requestBody);


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else {

                    Util.getInstance().cusToast(Payment_details_screen.this, jsonObject.optString("message"));
                }
            }else if (NetworkConstants.RequestCode.APPLY_COUPON == requestId) {
                if (jsonObject.optString("status").equalsIgnoreCase("SUCCESS")) {

                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("packageTotalVM");


                    amount = jsonObject2.getString("sAmount");
                    discount = jsonObject2.getString("sDiscount");
                    gst = jsonObject2.getString("sGst");
                    totalamount = jsonObject2.getString("sTotalAmount");



                    amount_text.setText("₹" + amount);
                    subtotal_text.setText("₹" + amount);
                    gst_text.setText("₹" + gst);
                    discount_tv.setText("₹"+ discount);
                    totalamount_text.setText("₹" + totalamount);



                } else {

                    Util.getInstance().cusToast(Payment_details_screen.this, jsonObject.optString("message"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }
}
