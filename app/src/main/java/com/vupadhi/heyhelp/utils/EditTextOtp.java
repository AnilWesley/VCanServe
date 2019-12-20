package com.vupadhi.heyhelp.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.vupadhi.heyhelp.R;


/**
 * @author yuana <andhikayuana@gmail.com>
 * @since 9/7/17
 */

public class EditTextOtp extends LinearLayout implements View.OnFocusChangeListener, TextWatcher,
        View.OnKeyListener {

    private EditText etDigit1;
    private EditText etDigit2;
    private EditText etDigit3;
    private EditText etDigit4;
    private EditText etDigit5;
    private EditText etDigit6;
    private EditText etDigitCurrent;

    public EditTextOtp(Context context) {
        super(context);
        initialize(null);
    }

    public EditTextOtp(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(attrs);
    }

    public EditTextOtp(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(attrs);
    }

    private void initialize(AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.edit_text_otp, this);

        etDigit1 = (EditText) findViewById(R.id.etDigit1);
        etDigit2 = (EditText) findViewById(R.id.etDigit2);
        etDigit3 = (EditText) findViewById(R.id.etDigit3);
        etDigit4 = (EditText) findViewById(R.id.etDigit4);
        etDigit5 = (EditText) findViewById(R.id.etDigit5);
        etDigit6 = (EditText) findViewById(R.id.etDigit6);

        initFocusListener();
        initTextChangeListener();
        initKeyListener();

    }

    private void initFocusListener() {
        etDigit1.setOnFocusChangeListener(this);
        etDigit2.setOnFocusChangeListener(this);
        etDigit3.setOnFocusChangeListener(this);
        etDigit4.setOnFocusChangeListener(this);
        etDigit5.setOnFocusChangeListener(this);
        etDigit6.setOnFocusChangeListener(this);
    }

    private void initTextChangeListener() {
        etDigit1.addTextChangedListener(this);
        etDigit2.addTextChangedListener(this);
        etDigit3.addTextChangedListener(this);
        etDigit4.addTextChangedListener(this);
        etDigit5.addTextChangedListener(this);
        etDigit6.addTextChangedListener(this);
    }

    private void initKeyListener() {
        etDigit1.setOnKeyListener(this);
        etDigit2.setOnKeyListener(this);
        etDigit3.setOnKeyListener(this);
        etDigit4.setOnKeyListener(this);
        etDigit5.setOnKeyListener(this);
        etDigit6.setOnKeyListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        etDigitCurrent = (EditText) v;
        etDigitCurrent.setSelection(etDigitCurrent.getText().length());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (etDigitCurrent != null) {

            if (etDigitCurrent.getText().length() >= 1 && etDigitCurrent != etDigit6) {

                etDigitCurrent.focusSearch(View.FOCUS_RIGHT).requestFocus();

            } else if (etDigitCurrent.getText().length() >= 1 && etDigitCurrent == etDigit6) {

                InputMethodManager imm = (InputMethodManager) getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);

                if (imm != null)
                    imm.hideSoftInputFromWindow(getWindowToken(), 0);

            } else {
                String curretValue = etDigitCurrent.getText().toString();
                if (curretValue.length() <= 0 && etDigitCurrent.getSelectionStart() <= 0) {
                    etDigitCurrent.focusSearch(View.FOCUS_LEFT).requestFocus();
                }

            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public String getOtp() {
        return makeOtp();
    }

    public void setOtp(String otp) {
        if (otp.length() == 6) {
            etDigit1.setText(String.valueOf(otp.charAt(0)));
            etDigit2.setText(String.valueOf(otp.charAt(1)));
            etDigit3.setText(String.valueOf(otp.charAt(2)));
            etDigit4.setText(String.valueOf(otp.charAt(3)));
            etDigit5.setText(String.valueOf(otp.charAt(4)));
            etDigit6.setText(String.valueOf(otp.charAt(5)));
        } else {
            etDigit1.setText("");
            etDigit2.setText("");
            etDigit3.setText("");
            etDigit4.setText("");
            etDigit5.setText("");
            etDigit6.setText("");
            etDigit1.requestFocus();
        }

    }

    private String makeOtp() {
        StringBuilder sb = new StringBuilder();
        sb.append(etDigit1.getText().toString());
        sb.append(etDigit2.getText().toString());
        sb.append(etDigit3.getText().toString());
        sb.append(etDigit4.getText().toString());
        sb.append(etDigit5.getText().toString());
        sb.append(etDigit6.getText().toString());
        return sb.toString();
    }

    public boolean isValid() {
        return makeOtp().length() == 6;
    }

    public EditText getCurrentFocused() {
        return etDigitCurrent;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {

            int id = v.getId();

            switch (id) {
                case R.id.etDigit1:

                    return isKeyDel(etDigit1, keyCode);

                case R.id.etDigit2:

                    return isKeyDel(etDigit2, keyCode);

                case R.id.etDigit3:

                    return isKeyDel(etDigit3, keyCode);

                case R.id.etDigit4:

                    return isKeyDel(etDigit4, keyCode);

                case R.id.etDigit5:

                    return isKeyDel(etDigit5, keyCode);
                case R.id.etDigit6:

                    return isKeyDel(etDigit6, keyCode);

                default:
                    return false;
            }

        }

        return false;
    }

    private boolean isKeyDel(EditText etDigit, int keyCode) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            etDigit.setText(null);
            return true;
        }
        return false;
    }
}
