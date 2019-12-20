package com.vupadhi.heyhelp.models;

import java.util.List;

public class OffersModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_PROMOTIONS_OFFERS","promotionsAndOffers":[{"offerCode":"NEWUSER","validDate":"29/11/2019","offerDescription":"Use Code For First Time Service Only"},{"offerCode":"NEWUSER1","validDate":"29/11/2019","offerDescription":"FirstTimeUser1"}]}
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
         * message : MC_PROMOTIONS_OFFERS
         * promotionsAndOffers : [{"offerCode":"NEWUSER","validDate":"29/11/2019","offerDescription":"Use Code For First Time Service Only"},{"offerCode":"NEWUSER1","validDate":"29/11/2019","offerDescription":"FirstTimeUser1"}]
         */

        private String message;
        private List<PromotionsAndOffersBean> promotionsAndOffers;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<PromotionsAndOffersBean> getPromotionsAndOffers() {
            return promotionsAndOffers;
        }

        public void setPromotionsAndOffers(List<PromotionsAndOffersBean> promotionsAndOffers) {
            this.promotionsAndOffers = promotionsAndOffers;
        }

        public static class PromotionsAndOffersBean {
            /**
             * offerCode : NEWUSER
             * validDate : 29/11/2019
             * offerDescription : Use Code For First Time Service Only
             */

            private String offerCode;
            private String validDate;
            private String offerDescription;

            public String getOfferCode() {
                return offerCode;
            }

            public void setOfferCode(String offerCode) {
                this.offerCode = offerCode;
            }

            public String getValidDate() {
                return validDate;
            }

            public void setValidDate(String validDate) {
                this.validDate = validDate;
            }

            public String getOfferDescription() {
                return offerDescription;
            }

            public void setOfferDescription(String offerDescription) {
                this.offerDescription = offerDescription;
            }
        }
    }
}
