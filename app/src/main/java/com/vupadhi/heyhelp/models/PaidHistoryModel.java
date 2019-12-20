package com.vupadhi.heyhelp.models;

import java.util.List;

public class PaidHistoryModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","paidHistory":[{"paymentHistory":"Paid","sServiceName":" Elder Care","amount":"500.00"},{"paymentHistory":"Paid","sServiceName":"Child Care","amount":"700.00"}],"accessToken":"e0c66c2f58d4b43ce621866e8974dd688b718090488fc769b215e7c5a8d88b98","nClientID":"4084","lowerMenuBar":[{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]}
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
         * paidHistory : [{"paymentHistory":"Paid","sServiceName":" Elder Care","amount":"500.00"},{"paymentHistory":"Paid","sServiceName":"Child Care","amount":"700.00"}]
         * accessToken : e0c66c2f58d4b43ce621866e8974dd688b718090488fc769b215e7c5a8d88b98
         * nClientID : 4084
         * lowerMenuBar : [{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]
         */

        private String message;
        private String accessToken;
        private String nClientID;
        private List<PaidHistoryBean> paidHistory;
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

        public List<PaidHistoryBean> getPaidHistory() {
            return paidHistory;
        }

        public void setPaidHistory(List<PaidHistoryBean> paidHistory) {
            this.paidHistory = paidHistory;
        }

        public List<LowerMenuBarBean> getLowerMenuBar() {
            return lowerMenuBar;
        }

        public void setLowerMenuBar(List<LowerMenuBarBean> lowerMenuBar) {
            this.lowerMenuBar = lowerMenuBar;
        }

        public static class PaidHistoryBean {
            /**
             * paymentHistory : Paid
             * sServiceName :  Elder Care
             * amount : 500.00
             */

            private String paymentHistory;
            private String sServiceName;
            private String amount;

            public String getPaymentHistory() {
                return paymentHistory;
            }

            public void setPaymentHistory(String paymentHistory) {
                this.paymentHistory = paymentHistory;
            }

            public String getSServiceName() {
                return sServiceName;
            }

            public void setSServiceName(String sServiceName) {
                this.sServiceName = sServiceName;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
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
