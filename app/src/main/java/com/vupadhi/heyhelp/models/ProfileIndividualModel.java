package com.vupadhi.heyhelp.models;

import java.util.List;

public class ProfileIndividualModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","individualProfile":{"sName":"anusha","sEmail":"anusha@gmail.com","sMobileNumber":"9052840527","nUserType":"100","sLocationName":"Gachibowli","sAddress":null,"sAddress2":null,"sPinCode":0},"lowerMenuBar":[{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}],"nClientID":"4084","accessToken":"56ab7f126eaa9395cf592657e9067e5d16e1f7c8354802361b107cbba4f0e03b"}
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
         * individualProfile : {"sName":"anusha","sEmail":"anusha@gmail.com","sMobileNumber":"9052840527","nUserType":"100","sLocationName":"Gachibowli","sAddress":null,"sAddress2":null,"sPinCode":0}
         * lowerMenuBar : [{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]
         * nClientID : 4084
         * accessToken : 56ab7f126eaa9395cf592657e9067e5d16e1f7c8354802361b107cbba4f0e03b
         */

        private String message;
        private IndividualProfileBean individualProfile;
        private String nClientID;
        private String accessToken;
        private List<LowerMenuBarBean> lowerMenuBar;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public IndividualProfileBean getIndividualProfile() {
            return individualProfile;
        }

        public void setIndividualProfile(IndividualProfileBean individualProfile) {
            this.individualProfile = individualProfile;
        }

        public String getNClientID() {
            return nClientID;
        }

        public void setNClientID(String nClientID) {
            this.nClientID = nClientID;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public List<LowerMenuBarBean> getLowerMenuBar() {
            return lowerMenuBar;
        }

        public void setLowerMenuBar(List<LowerMenuBarBean> lowerMenuBar) {
            this.lowerMenuBar = lowerMenuBar;
        }

        public static class IndividualProfileBean {
            /**
             * sName : anusha
             * sEmail : anusha@gmail.com
             * sMobileNumber : 9052840527
             * nUserType : 100
             * sLocationName : Gachibowli
             * sAddress : null
             * sAddress2 : null
             * sPinCode : 0
             */

            private String sName;
            private String sEmail;
            private String sMobileNumber;
            private String nUserType;
            private String sLocationName;
            private String sAddress;
            private String sAddress2;
            private int sPinCode;

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

            public String getNUserType() {
                return nUserType;
            }

            public void setNUserType(String nUserType) {
                this.nUserType = nUserType;
            }

            public String getSLocationName() {
                return sLocationName;
            }

            public void setSLocationName(String sLocationName) {
                this.sLocationName = sLocationName;
            }

            public String getSAddress() {
                return sAddress;
            }

            public void setSAddress(String sAddress) {
                this.sAddress = sAddress;
            }

            public String getSAddress2() {
                return sAddress2;
            }

            public void setSAddress2(String sAddress2) {
                this.sAddress2 = sAddress2;
            }

            public int getSPinCode() {
                return sPinCode;
            }

            public void setSPinCode(int sPinCode) {
                this.sPinCode = sPinCode;
            }
        }

        public static class LowerMenuBarBean {
            /**
             * id : 1
             * name : Home
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
