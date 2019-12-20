package com.vupadhi.heyhelp.models;

import java.util.List;

public class Individual_locModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SELECTION_TYPE","individualSelection":{"nUserType":"100","sName":"sName","sEmail":"sEmail","sMobileNumber":"sMobileNumber","sPassword":"sPassword","sLocation":[{"sLocationID":1,"sLocationName":"Gachibowli"},{"sLocationID":2,"sLocationName":"Kondapur"},{"sLocationID":3,"sLocationName":"KPHB"},{"sLocationID":4,"sLocationName":"Jubli Hills"}]}}
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
         * message : MC_SELECTION_TYPE
         * individualSelection : {"nUserType":"100","sName":"sName","sEmail":"sEmail","sMobileNumber":"sMobileNumber","sPassword":"sPassword","sLocation":[{"sLocationID":1,"sLocationName":"Gachibowli"},{"sLocationID":2,"sLocationName":"Kondapur"},{"sLocationID":3,"sLocationName":"KPHB"},{"sLocationID":4,"sLocationName":"Jubli Hills"}]}
         */

        private String message;
        private IndividualSelectionBean individualSelection;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public IndividualSelectionBean getIndividualSelection() {
            return individualSelection;
        }

        public void setIndividualSelection(IndividualSelectionBean individualSelection) {
            this.individualSelection = individualSelection;
        }

        public static class IndividualSelectionBean {
            /**
             * nUserType : 100
             * sName : sName
             * sEmail : sEmail
             * sMobileNumber : sMobileNumber
             * sPassword : sPassword
             * sLocation : [{"sLocationID":1,"sLocationName":"Gachibowli"},{"sLocationID":2,"sLocationName":"Kondapur"},{"sLocationID":3,"sLocationName":"KPHB"},{"sLocationID":4,"sLocationName":"Jubli Hills"}]
             */

            private String nUserType;
            private String sName;
            private String sEmail;
            private String sMobileNumber;
            private String sPassword;
            private List<SLocationBean> sLocation;

            public String getNUserType() {
                return nUserType;
            }

            public void setNUserType(String nUserType) {
                this.nUserType = nUserType;
            }

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

            public String getSPassword() {
                return sPassword;
            }

            public void setSPassword(String sPassword) {
                this.sPassword = sPassword;
            }

            public List<SLocationBean> getSLocation() {
                return sLocation;
            }

            public void setSLocation(List<SLocationBean> sLocation) {
                this.sLocation = sLocation;
            }

            public static class SLocationBean {
                /**
                 * sLocationID : 1
                 * sLocationName : Gachibowli
                 */

                private int sLocationID;
                private String sLocationName;

                public int getSLocationID() {
                    return sLocationID;
                }

                public void setSLocationID(int sLocationID) {
                    this.sLocationID = sLocationID;
                }

                public String getSLocationName() {
                    return sLocationName;
                }

                public void setSLocationName(String sLocationName) {
                    this.sLocationName = sLocationName;
                }
            }
        }
    }
}
