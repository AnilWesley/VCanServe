package com.vupadhi.heyhelp.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogsUtils {

public static ProgressDialog showProgressDialog(Context context, String text){
String message=text;
ProgressDialog m_Dialog = new ProgressDialog(context);
m_Dialog.setMessage(message);
m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
m_Dialog.setCancelable(false);

m_Dialog.show();
return m_Dialog;
}
public static ProgressDialog showProgressDialog1(Context context, String text){
String message1=text;
ProgressDialog m_Dialog = new ProgressDialog(context);
m_Dialog.setMessage(message1);
m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
m_Dialog.setCancelable(false);

m_Dialog.show();
return m_Dialog;
}
}
