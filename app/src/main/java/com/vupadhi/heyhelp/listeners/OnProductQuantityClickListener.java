package com.vupadhi.heyhelp.listeners;

import android.widget.ImageView;
import android.widget.TextView;

public interface OnProductQuantityClickListener {

    void onQuantityClick(int productId, int productQuantity, TextView devicePriceView, ImageView quantityPlus, ImageView quantityMinus);
}
