package com.vupadhi.heyhelp.models;

public class SignUpModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","customer":{"sName":"ndnndn","sEmail":"hehhs@gmail.com ","sMobileNumber":"6454223787","sLocationName":"Marthalli","nClientID":5166,"sMessage":"Your Registration has Been Successfully Completed","userType":"100","sOTPValue":"1532"}}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * message : MC_SAVED_SUCCESSFUL
         * customer : {"sName":"ndnndn","sEmail":"hehhs@gmail.com ","sMobileNumber":"6454223787","sLocationName":"Marthalli","nClientID":5166,"sMessage":"Your Registration has Been Successfully Completed","userType":"100","sOTPValue":"1532"}
         */

        private String message;
        private CustomerBean customer;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public static class CustomerBean {
            /**
             * sName : ndnndn
             * sEmail : hehhs@gmail.com
             * sMobileNumber : 6454223787
             * sLocationName : Marthalli
             * nClientID : 5166
             * sMessage : Your Registration has Been Successfully Completed
             * userType : 100
             * sOTPValue : 1532
             */

            private String sName;
            private String sEmail;
            private String sMobileNumber;
            private String sLocationName;
            private int nClientID;
            private String sMessage;
            private String userType;
            private String sOTPValue;

            public String getSName() {
                return sName;
            }

            public void setSName(String sName) {
                this.sName = sName;
            }

            public String getSEmail() {
                return sEmail;
            }

            public void setSEmail(String sEmail) {
                this.sEmail = sEmail;
            }

            public String getSMobileNumber() {
                return sMobileNumber;
            }

            public void setSMobileNumber(String sMobileNumber) {
                this.sMobileNumber = sMobileNumber;
            }

            public String getSLocationName() {
                return sLocationName;
            }

            public void setSLocationName(String sLocationName) {
                this.sLocationName = sLocationName;
            }

            public int getNClientID() {
                return nClientID;
            }

            public void setNClientID(int nClientID) {
                this.nClientID = nClientID;
            }

            public String getSMessage() {
                return sMessage;
            }

            public void setSMessage(String sMessage) {
                this.sMessage = sMessage;
            }

            public String getUserType() {
                return userType;
            }

            public void setUserType(String userType) {
                this.userType = userType;
            }

            public String getSOTPValue() {
                return sOTPValue;
            }

            public void setSOTPValue(String sOTPValue) {
                this.sOTPValue = sOTPValue;
            }
        }
    }
}
