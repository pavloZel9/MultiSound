package com.expert.multisound.util_view;


import static com.expert.multisound.util_view.ViewUtil.ViewErrorEdtxt;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.expert.multisound.R;

public class SmsViewInit {
    EditText number_1;
    EditText number_2;
    EditText number_3;
    EditText number_4;
    EditText number_5;
    EditText number_6;
    LinearLayout sms_linear;
    Context ctx;

    public SmsViewInit(Context ctx, LinearLayout sms_linear, EditText number_1, EditText number_2, EditText number_3, EditText number_4, EditText number_5, EditText number_6) {
        this.number_1 = number_1;
        this.number_2 = number_2;
        this.number_3 = number_3;
        this.number_4 = number_4;
        this.number_5 = number_5;
        this.number_6 = number_6;
        this.sms_linear = sms_linear;
        this.ctx = ctx;
    }

    public void init() {
        full_H_smsCode();
        inputFocus(number_1);
        inputFocus(number_2);
        inputFocus(number_3);
        inputFocus(number_4);
        inputFocus(number_5);
        inputFocus(number_6);

    }

    public void full_H_smsCode() {
        sms_linear.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ((Activity) ctx).runOnUiThread(() -> {
                    int lin_width = sms_linear.getWidth();
                    int delta_mrg = ViewUtil.SpTOpx(7, ctx);
                    lin_width = lin_width - 5 * delta_mrg;
                    int ed_width = (int) (lin_width / 6);

                    setNewParEdt(number_1, ed_width);
                    ChngEd(number_1);
                    setNewParEdt(number_2, ed_width);
                    ChngEd(number_2);
                    setNewParEdt(number_3, ed_width);
                    ChngEd(number_3);
                    setNewParEdt(number_4, ed_width);
                    ChngEd(number_4);
                    setNewParEdt(number_5, ed_width);
                    ChngEd(number_5);
                    setNewParEdt(number_6, ed_width);
                    ChngEd(number_6);


                });
                sms_linear.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public void inputFocus(EditText view) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                number_1.setBackgroundResource(R.drawable.editext);
                number_2.setBackgroundResource(R.drawable.editext);
                number_3.setBackgroundResource(R.drawable.editext);
                number_4.setBackgroundResource(R.drawable.editext);
                number_5.setBackgroundResource(R.drawable.editext);
                number_6.setBackgroundResource(R.drawable.editext);
            }
        });
    }


    public void setNewParEdt(EditText edt, int ed_width) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) edt.getLayoutParams();
        params.height = ed_width;
        params.width = ed_width;
        edt.setLayoutParams(params);
    }

    public void NumNotNull() {
        if (number_1.getText().length() == 0)
            ViewErrorEdtxt(number_1);
        if (number_2.getText().length() == 0)
            ViewErrorEdtxt(number_2);
        if (number_3.getText().length() == 0)
            ViewErrorEdtxt(number_3);
        if (number_4.getText().length() == 0)
            ViewErrorEdtxt(number_4);
        if (number_5.getText().length() == 0)
            ViewErrorEdtxt(number_5);
        if (number_6.getText().length() == 0)
            ViewErrorEdtxt(number_6);
    }


    public void ChngEd(EditText v) {
        v.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    switch (v.getId()) {
                        case R.id.number_1:
                            number_2.requestFocus();
                            break;
                        case R.id.number_2:
                            number_3.requestFocus();
                            break;
                        case R.id.number_3:
                            number_4.requestFocus();
                            break;
                        case R.id.number_4:
                            number_5.requestFocus();
                            break;
                        case R.id.number_5:
                            number_6.requestFocus();
                            break;
                        case R.id.number_6:
                            if (number_1.getText().length() > 0 &&
                                    number_2.getText().length() > 0 &&
                                    number_3.getText().length() > 0 &&
                                    number_4.getText().length() > 0 &&
                                    number_5.getText().length() > 0 &&
                                    number_6.getText().length() > 0) {
                                InputMethodManager imm = (InputMethodManager)ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                            }
                            break;




                    }
                }
            }
        });
    }


}

