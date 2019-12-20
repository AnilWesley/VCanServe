package com.vupadhi.heyhelp.models;

import java.util.List;

public class PackageSelectionModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","premiumPackage":[{"nPackageID":"1","sno":1,"premiumPackage":"Verified"},{"nPackageID":"1","sno":2,"premiumPackage":""},{"nPackageID":"1","sno":3,"premiumPackage":"Skilled"},{"nPackageID":"1","sno":4,"premiumPackage":""},{"nPackageID":"1","sno":5,"premiumPackage":""}],"ultraPremiumPackage":[{"nPackageID":"2","sno":1,"ultraPremiumPackage":"Verified"},{"nPackageID":"2","sno":2,"ultraPremiumPackage":"Police Verified"},{"nPackageID":"2","sno":3,"ultraPremiumPackage":"Skilled"},{"nPackageID":"2","sno":4,"ultraPremiumPackage":"Softskilled Trained"},{"nPackageID":"2","sno":5,"ultraPremiumPackage":"Vcanserve Certified"}],"accessToken":"fe1b754445572b81f1b28be3ca03240e22a820a77ba8e09ed587523d4c664df7","nClientID":"4084","lowerMenuBar":[{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]}
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
         * premiumPackage : [{"nPackageID":"1","sno":1,"premiumPackage":"Verified"},{"nPackageID":"1","sno":2,"premiumPackage":""},{"nPackageID":"1","sno":3,"premiumPackage":"Skilled"},{"nPackageID":"1","sno":4,"premiumPackage":""},{"nPackageID":"1","sno":5,"premiumPackage":""}]
         * ultraPremiumPackage : [{"nPackageID":"2","sno":1,"ultraPremiumPackage":"Verified"},{"nPackageID":"2","sno":2,"ultraPremiumPackage":"Police Verified"},{"nPackageID":"2","sno":3,"ultraPremiumPackage":"Skilled"},{"nPackageID":"2","sno":4,"ultraPremiumPackage":"Softskilled Trained"},{"nPackageID":"2","sno":5,"ultraPremiumPackage":"Vcanserve Certified"}]
         * accessToken : fe1b754445572b81f1b28be3ca03240e22a820a77ba8e09ed587523d4c664df7
         * nClientID : 4084
         * lowerMenuBar : [{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]
         */

        private String message;
        private String accessToken;
        private String nClientID;
        private List<PremiumPackageBean> premiumPackage;
        private List<UltraPremiumPackageBean> ultraPremiumPackage;
        private List<LowerMenuBarBean> lowerMenuBar;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getNClientID() {
            return nClientID;
        }

        public void setNClientID(String nClientID) {
            this.nClientID = nClientID;
        }

        public List<PremiumPackageBean> getPremiumPackage() {
            return premiumPackage;
        }

        public void setPremiumPackage(List<PremiumPackageBean> premiumPackage) {
            this.premiumPackage = premiumPackage;
        }

        public List<UltraPremiumPackageBean> getUltraPremiumPackage() {
            return ultraPremiumPackage;
        }

        public void setUltraPremiumPackage(List<UltraPremiumPackageBean> ultraPremiumPackage) {
            this.ultraPremiumPackage = ultraPremiumPackage;
        }

        public List<LowerMenuBarBean> getLowerMenuBar() {
            return lowerMenuBar;
        }

        public void setLowerMenuBar(List<LowerMenuBarBean> lowerMenuBar) {
            this.lowerMenuBar = lowerMenuBar;
        }

        public static class PremiumPackageBean {
            /**
             * nPackageID : 1
             * sno : 1
             * premiumPackage : Verified
             */

            private String nPackageID;
            private int sno;
            private String premiumPackage;

            private boolean isSelected;

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

            public String getNPackageID() {
                return nPackageID;
            }

            public void setNPackageID(String nPackageID) {
                this.nPackageID = nPackageID;
            }

            public int getSno() {
                return sno;
            }

            public void setSno(int sno) {
                this.sno = sno;
            }

            public String getPremiumPackage() {
                return premiumPackage;
            }

            public void setPremiumPackage(String premiumPackage) {
                this.premiumPackage = premiumPackage;
            }
        }

        public static class UltraPremiumPackageBean {
            /**
             * nPackageID : 2
             * sno : 1
             * ultraPremiumPackage : Verified
             */

            private String nPackageID;
            private int sno;
            private String ultraPremiumPackage;

            private boolean isSelected;

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

            public String getNPackageID() {
                return nPackageID;
            }

            public void setNPackageID(String nPackageID) {
                this.nPackageID = nPackageID;
            }

            public int getSno() {
                return sno;
            }

            public void setSno(int sno) {
                this.sno = sno;
            }

            public String getUltraPremiumPackage() {
                return ultraPremiumPackage;
            }

            public void setUltraPremiumPackage(String ultraPremiumPackage) {
                this.ultraPremiumPackage = ultraPremiumPackage;
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
