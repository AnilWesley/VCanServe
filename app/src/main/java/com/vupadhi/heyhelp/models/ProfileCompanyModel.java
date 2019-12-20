package com.vupadhi.heyhelp.models;

import java.util.List;

public class ProfileCompanyModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","companyProfile":{"sName":null,"sEmail":"hshhs@gmail.com","sMobileNumber":"7095404051","sCompanyType":null,"nUserType":"101","sLocationName":"Gachibowli","sAddress":null,"sAddress2":null,"sPinCode":0,"sCompanyName":"nsnsb","sContactPerson":"bsbsb"},"nClientID":"4096","accessToken":"009d1e746041f5ad0fd0e6a79666d1251ee0d5b91a1e8d0a6e9e3d606317cd24","lowerMenuBar":[{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]}
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
         * companyProfile : {"sName":null,"sEmail":"hshhs@gmail.com","sMobileNumber":"7095404051","sCompanyType":null,"nUserType":"101","sLocationName":"Gachibowli","sAddress":null,"sAddress2":null,"sPinCode":0,"sCompanyName":"nsnsb","sContactPerson":"bsbsb"}
         * nClientID : 4096
         * accessToken : 009d1e746041f5ad0fd0e6a79666d1251ee0d5b91a1e8d0a6e9e3d606317cd24
         * lowerMenuBar : [{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]
         */

        private String message;
        private CompanyProfileBean companyProfile;
        private String nClientID;
        private String accessToken;
        private List<LowerMenuBarBean> lowerMenuBar;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public CompanyProfileBean getCompanyProfile() {
            return companyProfile;
        }

        public void setCompanyProfile(CompanyProfileBean companyProfile) {
            this.companyProfile = companyProfile;
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

        public static class CompanyProfileBean {
            /**
             * sName : null
             * sEmail : hshhs@gmail.com
             * sMobileNumber : 7095404051
             * sCompanyType : null
             * nUserType : 101
             * sLocationName : Gachibowli
             * sAddress : null
             * sAddress2 : null
             * sPinCode : 0
             * sCompanyName : nsnsb
             * sContactPerson : bsbsb
             */

            private Object sName;
            private String sEmail;
            private String sMobileNumber;
            private String sCompanyType;
            private String nUserType;
            private String sLocationName;
            private String sAddress;
            private String sAddress2;
            private int sPinCode;
            private String sCompanyName;
            private String sContactPerson;

            public Object getSName() {
                return sName;
            }

            public void setSName(Object sName) {
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

            public String getSCompanyType() {
                return sCompanyType;
            }

            public void setSCompanyType(String sCompanyType) {
                this.sCompanyType = sCompanyType;
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

            public String getSCompanyName() {
                return sCompanyName;
            }

            public void setSCompanyName(String sCompanyName) {
                this.sCompanyName = sCompanyName;
            }

            public String getSContactPerson() {
                return sContactPerson;
            }

            public void setSContactPerson(String sContactPerson) {
                this.sContactPerson = sContactPerson;
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
