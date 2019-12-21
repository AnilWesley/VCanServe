package com.vupadhi.heyhelp.network.constants;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.ABOUT_US;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.APPLY_COUPON;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.CHANGE_PASSWORD;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.CONTACT_US;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.DEPLOYMENT_NOTIFLIST;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.DOMESTIC_HELP;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.FEEDBACK_QUESTIONS;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.FORGOT_PASSWORD;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.GETWORKERLIST;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.HOMESCREEN;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.LOGIN;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.MYSERVICE_REQUESTS;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.MY_PROFILE;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.PACKAGE_SELECTION;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.PAIDHISTORY_INFORM;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.PAYMENT_METHOD_DETAILS;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.PENDINGPAY_INFORM;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.PROFILEFOUND_SERREQ;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.PROMOTIONS_OFFERS;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.RAISETICKETS;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.RATED_WORKERS;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.RESEND_OTP;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.RETREIVE_PACKAGE;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.SAVECUSTOMER_RATING;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.SAVEDOMESTICSERVICE_HELP;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.SAVEDOMESTIC_HELP;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.SAVEFEEDBACK;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.SAVERAISETICKETS;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.SAVEWORKERABSENCE_LIST;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.TERMS_COND;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.TICKETSTATUS;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.UPDATE_PROFILE;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.USER_COMPANY_LOCATIONS;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.USER_INDIVIDUAL_LOCATIONS;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.USER_LOCATIONS;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.USER_REGISTER;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.USER_SIGNOUT;
import static com.vupadhi.heyhelp.network.constants.NetworkConstants.RequestCode.VERIFY_OTP;


public interface NetworkConstants {
    String PAGE = "?page=";
    String LIMIT = "&limit=";

    interface Headers {
        String X_AUTH_TOKEN = "X-AUTH-TOKEN";
        String BASIC_AUTH_TOKEN = "Authorization";
    }

    /**
     * This is the network request to all apis.
     */
    interface URL {

        //String PRIMARY_BASE_URL = BuildConfig.BASE_URL;

        String BASE_URL = "https://a2zprocure.com/vcanserv/api/";



        //Account Services
        String USER_INDIVIDUAL_LOCATIONS = BASE_URL +"selectionType?nUserType=100";
        String USER_COMPANY_LOCATIONS = BASE_URL +"selectionType?nUserType=101";
        String USER_LOCATIONS = BASE_URL +"selectionType?nUserType=";
        String USER_REGISTER = BASE_URL +"createClientReg";
        String VERIFY_OTP = BASE_URL +"verifyotp";
        String LOGIN = BASE_URL +"login";
        String HOMESCREEN = BASE_URL +"serviceRequest?nClientID=";
        String DOMESTIC_HELP = BASE_URL +"commonRequest?nClientID=";
        String SAVEDOMESTIC_HELP = BASE_URL +"savecommonRequest";
        String SAVEDOMESTICSERVICE_HELP = BASE_URL +"savedomesticHelpService";
        String PACKAGE_SELECTION = BASE_URL +"packageselection?nClientID=";
        String RETREIVE_PACKAGE = BASE_URL +"retrivepackage";
        String MYSERVICE_REQUESTS = BASE_URL +"myservicerequest?nClientID=";
        String MY_PROFILE = BASE_URL +"myprofile?nRegisteredID=";
        String UPDATE_PROFILE = BASE_URL +"updateprofile";
        String PROFILEFOUND_SERREQ = BASE_URL +"searchservicerequest?nClientID=";
        String PENDINGPAY_INFORM = BASE_URL +"pendingpaymentinformation?nClientID=";
        String PAIDHISTORY_INFORM = BASE_URL +"paymentinformation?nClientID=";
        String GETWORKERLIST = BASE_URL +"getworkers?nClientID=";
        String RAISETICKETS = BASE_URL +"raiseTickets?nClientID=";
        String TICKETSTATUS = BASE_URL +"ticketStatus?nClientID=";
        String FEEDBACK_QUESTIONS = BASE_URL +"feedbackQuestions?nClientID=";
        String RATED_WORKERS = BASE_URL +"getratedworkers?nClientID=";
        String ABOUT_US = BASE_URL +"aboutUS?nClientID=";
        String CONTACT_US = BASE_URL +"contactUs?nClientID=";
        String PROMOTIONS_OFFERS = BASE_URL +"promotionsandoffers?nClientID=";
        String PAYMENT_METHOD_DETAILS = BASE_URL +"registrationFee?nClientID=";
        String DEPLOYMENT_NOTIFLIST = BASE_URL +"deploymentnotification?nClientID=";
        String USER_SIGNOUT = BASE_URL +"userSignOut?nClientID=";
        String TERMS_COND = BASE_URL +"usertermsandconditions?nTermID=1";
        String RESEND_OTP = BASE_URL +"resendOTP?sMobileNumber=";
        String SAVERAISETICKETS = BASE_URL +"saveraiseTickets";
        String FORGOT_PASSWORD = BASE_URL +"forgotpassword";
        String CHANGE_PASSWORD = BASE_URL +"changePassword";
        String SAVEFEEDBACK = BASE_URL +"saveFeedbackQuestions";
        String SAVEWORKERABSENCE_LIST = BASE_URL +"workerabsence";
        String SAVECUSTOMER_RATING = BASE_URL +"customerRating";
        String APPLY_COUPON = BASE_URL +"applyCoupon";


    }

    /**
     * Application Controller events ids
     * Maintain all app level event ids and def of that event ids
     */
    @Retention(RetentionPolicy.CLASS)
    @IntDef({USER_INDIVIDUAL_LOCATIONS,USER_COMPANY_LOCATIONS,USER_REGISTER,VERIFY_OTP,LOGIN,HOMESCREEN,DOMESTIC_HELP,
            SAVEDOMESTIC_HELP,SAVEDOMESTICSERVICE_HELP,PACKAGE_SELECTION,RETREIVE_PACKAGE,MYSERVICE_REQUESTS,MY_PROFILE,UPDATE_PROFILE,
            PROFILEFOUND_SERREQ,PENDINGPAY_INFORM,PAIDHISTORY_INFORM,GETWORKERLIST,RAISETICKETS,SAVERAISETICKETS,TICKETSTATUS,FEEDBACK_QUESTIONS,
            RATED_WORKERS,ABOUT_US,CONTACT_US,FORGOT_PASSWORD,CHANGE_PASSWORD,SAVEFEEDBACK,PROMOTIONS_OFFERS,SAVEWORKERABSENCE_LIST,
            SAVECUSTOMER_RATING,RESEND_OTP,PAYMENT_METHOD_DETAILS,DEPLOYMENT_NOTIFLIST,USER_LOCATIONS,APPLY_COUPON,USER_SIGNOUT,TERMS_COND})
    @interface RequestCode {
        int SESSION_EXPIRE = 1017;
        int USER_INDIVIDUAL_LOCATIONS = 110;
        int USER_COMPANY_LOCATIONS = 111;
        int USER_REGISTER = 112;
        int VERIFY_OTP = 113;
        int LOGIN = 114;
        int HOMESCREEN = 115;
        int DOMESTIC_HELP = 116;
        int SAVEDOMESTIC_HELP = 117;
        int SAVEDOMESTICSERVICE_HELP = 118;
        int PACKAGE_SELECTION = 119;
        int RETREIVE_PACKAGE = 120;
        int MYSERVICE_REQUESTS = 121;
        int MY_PROFILE = 122;
        int UPDATE_PROFILE = 123;
        int PROFILEFOUND_SERREQ = 124;
        int PENDINGPAY_INFORM = 125;
        int PAIDHISTORY_INFORM = 126;
        int GETWORKERLIST = 127;
        int RAISETICKETS = 128;
        int SAVERAISETICKETS = 129;
        int TICKETSTATUS = 130;
        int FEEDBACK_QUESTIONS = 131;
        int RATED_WORKERS = 132;
        int ABOUT_US = 133;
        int CONTACT_US = 134;
        int FORGOT_PASSWORD = 135;
        int CHANGE_PASSWORD = 136;
        int SAVEFEEDBACK = 137;
        int PROMOTIONS_OFFERS = 138;
        int SAVEWORKERABSENCE_LIST = 139;
        int SAVECUSTOMER_RATING = 140;
        int RESEND_OTP = 141;
        int PAYMENT_METHOD_DETAILS = 142;
        int DEPLOYMENT_NOTIFLIST = 143;
        int USER_LOCATIONS = 144;
        int APPLY_COUPON = 145;
        int USER_SIGNOUT = 146;
        int TERMS_COND = 147;



    }
}