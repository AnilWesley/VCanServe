package com.vupadhi.heyhelp.network.volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;
import com.vupadhi.heyhelp.R;
import com.vupadhi.heyhelp.network.constants.NetworkConstants;
import com.vupadhi.heyhelp.network.listener.IDialogCallback;
import com.vupadhi.heyhelp.network.responcedos.CommonResponse;
import com.vupadhi.heyhelp.network.utils.Utils;
import com.vupadhi.heyhelp.sharepref.UserSession;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Prasad on 6/20/2017.
 */
public class APIHandler implements IDialogCallback {


    private static final int MY_SOCKET_TIMEOUT_MS = 10000;
    private Context context;
    private int requestId;
    private int methodType;
    private boolean showLoading = false;
    private String loadingText;
    private String url;
    private ProgressDialog pDialog = null;
    private APICallback apiCallback = null;
    private Map<String, String> headers = null;
    private Map<String, String> params = null;
    private String params2 = null;
    private boolean showToastOnResponse = true;
    private boolean isCanceledOnTouchOutside = false;
    private JSONObject jsonObject = null;

    UserSession userSession;


    //  String ACCESS_TOKEN="dPmDONBncgZq8w8crSsrNOCS0B8fP-rxDsLtGh-SBwgfNRB4cIL2dFPVd9u8yLtpIUulKjSrsT_0OvpGFGLMyivD2qiyeKqYpuWKcDAnJ95JDnMKCaogwrbsprZlm330IpmHYcCoyORLLBBr1w5bvQGVApH04XGYXbNKUJj-gAOgIfYVkTf7tRL5qahTaTs1nd7o5DqAYH9N2kG2bupr-TKUKIJ3r70bzhBj1ByEJbztYH7Dob0wMfgWmfEumhVcha3XNeA2PpreeivPN_3oo0WbN4ltk2mOvKdKJ2Y992XRuzxDi4ifv4c9wHyYYfs4B6Vj3Qb2VDJ1q4_TKrN5RfCVYe-rKP53gJFBTQYRZApeL7kHTxjpjwqR0OGWw38avo-8WD8ReiOsGcOW1TD-TIENesp1PEvg4TTNnskgPLnM1ZY_qcoGZYdyP6vV17bMsMEZcsNpqm9ZbkoObSoHin-pfxdQXih_T1o03Z_xWW2hnL30Ixu-hDQDFq-3DQAI9lUl4g7bESpHne866mKnuk4Gh8tzEGBi_rKnOPB-UrsodnPCFjZpvhpV-LcmO9DxfKDwMgk6XDv9SUiTYBG6RA";


    public APIHandler(Context context, APICallback apiCallback, int requestId, int methodType, String url,
                      boolean showLoading, boolean isCanceledOnTouchOutside, String loadingText, Map<String, String> params, Map<String, String> headers) {

        this.context = context;
        this.apiCallback = apiCallback;
        this.requestId = requestId;
        this.methodType = methodType;
        this.url = url;
        this.showLoading = showLoading;
        this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
        this.loadingText = loadingText;
        this.params = params;
        this.headers = headers;
        userSession = new UserSession(context);
    }


    public APIHandler(Context context, APICallback apiCallback, int requestId, int methodType, String url,
                      boolean showLoading, boolean isCanceledOnTouchOutside, String loadingText, JSONObject jsonObject, Map<String, String> headers) {

        this.context = context;
        this.apiCallback = apiCallback;
        this.requestId = requestId;
        this.methodType = methodType;
        this.url = url;
        this.showLoading = showLoading;
        this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
        this.loadingText = loadingText;
        this.jsonObject = jsonObject;
        this.headers = headers;
        userSession = new UserSession(context);

    }


    public APIHandler(Context context, APICallback apiCallback, int requestId, int methodType, String url,
                      boolean showLoading, boolean isCanceledOnTouchOutside, String loadingText, Map<String, String> headers) {

        this.context = context;
        this.apiCallback = apiCallback;
        this.requestId = requestId;
        this.methodType = methodType;
        this.url = url;
        this.showLoading = showLoading;
        this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
        this.loadingText = loadingText;
        this.headers = headers;
        userSession = new UserSession(context);

    }

    private void showLoading() {
        try {
            pDialog = new ProgressDialog(context);
            pDialog.setMessage(loadingText);
            pDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);

            if (pDialog != null && pDialog.isShowing())
                pDialog.dismiss();

            pDialog.show();
        } catch (Exception e) {
            Log.d("AlertDialog", "Progress dialog can not be shown. ;-(");
        }
    }

    /**
     * Hide loading.
     */
    private void hideLoading() {
        try {
            if (pDialog != null && pDialog.isShowing())
                pDialog.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Request API to get response for respective request.
     */
    public void requestAPI() {
        //check if internet connect found or not
        try {
            if (!Utils.checkInternetConnection(context)) {
                String networkErrorBody = Utils.getNetworkErrorBody(context);
                apiCallback.onAPISuccessResponse(requestId, networkErrorBody);
            } else {
                System.out.println("[API] request url = " + url);
                System.out.println("[API] request body = " + params);
                System.out.println("[API] json body = " + jsonObject);
                System.out.println("[API] request Auth_Token: = " + headers.get(NetworkConstants.Headers.X_AUTH_TOKEN));
                if (showLoading) {
                    showLoading();
                }
                //Send the request to get the response.
                sendRequest(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void requestAPI2() {
        //check if internet connect found or not
        try {
            if (!Utils.checkInternetConnection(context)) {
                String networkErrorBody = Utils.getNetworkErrorBody(context);
                apiCallback.onAPISuccessResponse(requestId, networkErrorBody);
            } else {
                System.out.println("[API] request url = " + url);
                System.out.println("[API] request body = " + params);
                System.out.println("[API] json body = " + jsonObject);
                System.out.println("[API] request Auth_Token: = " + headers.get(NetworkConstants.Headers.X_AUTH_TOKEN));
                if (showLoading) {
                    showLoading();
                }
                //Send the request to get the response.
                sendRequest2(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /* public void requestAPI2() {
//check if internet connect found or not
        try {
            if (!Utils.checkInternetConnection(context)) {
                String networkErrorBody = Utils.getNetworkErrorBody(context);
                apiCallback.onAPISuccessResponse(requestId, networkErrorBody);
            } else {
                System.out.println("[API] request url = " + url);
                System.out.println("[API] request body = " + params);
                System.out.println("[API] request body = " + jsonObject);
                System.out.println("[API] json body = " + jsonObject);
                System.out.println("[API] request Auth_Token: = " + headers.get(NetworkConstants.Headers.X_AUTH_TOKEN));
                if (showLoading) {
                    showLoading();
                }
                //Send the request to get the response.
                sendRequest(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void requestAPI3() {
//check if internet connect found or not
        try {
            if (!Utils.checkInternetConnection(context)) {
                String networkErrorBody = Utils.getNetworkErrorBody(context);
                apiCallback.onAPISuccessResponse(requestId, networkErrorBody);
            } else {
                System.out.println("[API] request url = " + url);
                System.out.println("[API] request body = " + params2);
                System.out.println("[API] json body = " + jsonObject);
                System.out.println("[API] request Auth_Token: = " + headers.get(NetworkConstants.Headers.X_AUTH_TOKEN));
                if (showLoading) {
                    showLoading();
                }
                //Send the request to get the response.
                sendRequest1(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendRequest(final Map<String, String> params) {
        JsonObjectRequest jsonRequest = null;
        try {
            jsonRequest = new JsonObjectRequest(methodType, url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (pDialog != null && pDialog.isShowing())
                                    pDialog.hide();

                                Log.e("response>>>>", response.toString() + "   URL  " + url
                                        + "  PARAMS " + params);
                                //dismiss dialog once response has been received from the server.
                                if (response != null && response.length() > 0) {
                                    //send the response the particular model where all data will parse their.
                                    handelResponse(requestId, response.toString());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(context, "12345", Toast.LENGTH_SHORT).show();

                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (pDialog != null && pDialog.isShowing())
                                pDialog.hide();
                            error.printStackTrace();
                            Toast.makeText(context, "server error", Toast.LENGTH_SHORT).show();

                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    Map<String, String> param = new HashMap<String, String>();

                    return param;
                }

                @Override
                public String getBodyContentType() {
                    return "application/json";
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "123", Toast.LENGTH_SHORT).show();
        }
        setJsonRequestQueue(context, jsonRequest);
        //MyApplication.getInstance().addToRequestQueue(jsonRequest, requestId);
    }

    private void sendRequest2(final Map<String, String> params) {
        JsonObjectRequest jsonRequest = null;
        try {
            jsonRequest = new JsonObjectRequest(methodType, url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (pDialog != null && pDialog.isShowing())
                                    pDialog.hide();

                                Log.e("response>>>>", response.toString() + "   URL  " + url
                                        + "  PARAMS " + params);
                                //dismiss dialog once response has been received from the server.
                                if (response != null && response.length() > 0) {
                                    //send the response the particular model where all data will parse their.
                                    handelResponse(requestId, response.toString());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(context, "123456", Toast.LENGTH_SHORT).show();

                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (pDialog != null && pDialog.isShowing())
                                pDialog.hide();
                            error.printStackTrace();
                            Toast.makeText(context, "server error", Toast.LENGTH_SHORT).show();

                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    Map<String, String> param = new HashMap<String, String>();
                    param.put("Authorization", "Bearer " + userSession.getUserDetails().get(UserSession.KEY_DEVICE_TOKEN));

                    return param;
                }

                @Override
                public String getBodyContentType() {
                    return "application/json";
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "12345678", Toast.LENGTH_SHORT).show();

        }
        setJsonRequestQueue(context, jsonRequest);
        //MyApplication.getInstance().addToRequestQueue(jsonRequest, requestId);
    }



    private void sendRequest1(JSONObject jsonObject) {
        System.out.println("params of string" + params);
        JsonObjectRequest jsonRequest = null;
        try {
            jsonRequest = new JsonObjectRequest(methodType, url, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (pDialog != null && pDialog.isShowing())
                                    pDialog.hide();
                                //dismiss dialog once response has been received from the server.
                                if (response != null && response.length() > 0) {
                                    //send the response the particular model where all data will parse their.
                                    handelResponse(requestId, response.toString());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(context, "012", Toast.LENGTH_SHORT).show();

                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (pDialog != null && pDialog.isShowing())
                                pDialog.hide();
//                            Log.e("status code", String.valueOf(error.networkResponse.statusCode));
                            error.printStackTrace();
                            Toast.makeText(context, "server error", Toast.LENGTH_SHORT).show();

                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> paramsHeader = new HashMap<>();
                    //paramsHeader.put("Content-Type", "application/json");
                    paramsHeader.put("Authorization", "Bearer " + userSession.getUserDetails().get(UserSession.KEY_DEVICE_TOKEN));
                    return paramsHeader;
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "01234", Toast.LENGTH_SHORT).show();
        }
        setJsonRequestQueue(context, jsonRequest);
//MyApplication.getInstance().addToRequestQueue(jsonRequest, requestId);
    }

    private void setJsonRequestQueue(Context context, JsonObjectRequest request) {
        request.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    /**
     * Handel The API response
     *
     * @param requestId
     * @param response
     */
    private void handelResponse(int requestId, String response) {
        Gson gson = new Gson();
        CommonResponse commonResponse = gson.fromJson(response, CommonResponse.class);
        if (commonResponse != null) {
            String message = commonResponse.getMessage();
            int status_code = commonResponse.getStatus_code();

            switch (status_code) {
                case NetworkConstants.RequestCode.SESSION_EXPIRE:
                    Utils.showErrorDialog(context, context.getString(R.string.ok_txt), message,
                            true, this, NetworkConstants.RequestCode.SESSION_EXPIRE);
                    break;
                default:
                    try {
                        apiCallback.onAPISuccessResponse(requestId, response);
                        hideLoading();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(context, "0123456", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        } else {
            Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * If any error occurs during data parsing then all error will handle here only.
     *
     * @param error
     */
    private void handleErrorResponse(VolleyError error) {
        String jsonBody = "";
        System.out.println("[API] response body volly = " + error);
        try {
            String errorMessage = "";
            if (error != null) {
                if (error instanceof TimeoutError) {
                    jsonBody = Utils.constructJSONBody(context.getString(R.string.timeOutTxt));
                    System.out.println("[API] response body volly = " + errorMessage);
                } else if (error instanceof ServerError) {
                    jsonBody = Utils.constructJSONBody(context.getString(R.string.serverError));
                    System.out.println("[API] response body volly = " + errorMessage);
                } else if (error instanceof NoConnectionError) {
                    jsonBody = Utils.constructJSONBody(context.getString(R.string.unknownErrorMsg));
                    System.out.println("[API] response body volly = " + errorMessage);
                } else {
                    jsonBody = Utils.constructJSONBody(context.getString(R.string.unknownErrorMsg));
                    System.out.println("[API] response body volly = " + errorMessage);
                }
            } else {
                errorMessage = context.getResources().getString(R.string.unknownErrorMsg);
                jsonBody = Utils.constructJSONBody(errorMessage);
                System.out.println("[API] response body volly = " + errorMessage);
            }

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        } finally {
            if (apiCallback != null) {
                apiCallback.onAPISuccessResponse(requestId, jsonBody);
                hideLoading();
            }
        }

    }

    /**
     * Check for error msg from volly
     *
     * @param error
     * @param defaultMessage
     * @return
     */
    private String getMessage(String error, String defaultMessage) {
        String finalMsg = null;
        if (error == null || error.isEmpty()) {
            finalMsg = defaultMessage;
        } else {
            finalMsg = error;
        }

        return finalMsg;
    }

    public boolean isShowToastOnResponse() {
        return showToastOnResponse;
    }

    public void setShowToastOnResponse(boolean showToastOnResponse) {
        this.showToastOnResponse = showToastOnResponse;
    }


    @Override
    public void onPositiveButtonPress(int requestCode) {


    }

    public void onNegativeButtonPress(int requestCode) {

    }
}
