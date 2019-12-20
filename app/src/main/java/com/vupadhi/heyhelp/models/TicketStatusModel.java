package com.vupadhi.heyhelp.models;

import java.util.List;

public class TicketStatusModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","ticketsStatus":[{"data":"sfsfdg","nStatus":"In Progress"}],"nClientID":"4084","accessToken":"e0c66c2f58d4b43ce621866e8974dd688b718090488fc769b215e7c5a8d88b98","lowerMenuBar":[{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]}
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
         * ticketsStatus : [{"data":"sfsfdg","nStatus":"In Progress"}]
         * nClientID : 4084
         * accessToken : e0c66c2f58d4b43ce621866e8974dd688b718090488fc769b215e7c5a8d88b98
         * lowerMenuBar : [{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]
         */

        private String message;
        private String nClientID;
        private String accessToken;
        private List<TicketsStatusBean> ticketsStatus;
        private List<LowerMenuBarBean> lowerMenuBar;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
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

        public List<TicketsStatusBean> getTicketsStatus() {
            return ticketsStatus;
        }

        public void setTicketsStatus(List<TicketsStatusBean> ticketsStatus) {
            this.ticketsStatus = ticketsStatus;
        }

        public List<LowerMenuBarBean> getLowerMenuBar() {
            return lowerMenuBar;
        }

        public void setLowerMenuBar(List<LowerMenuBarBean> lowerMenuBar) {
            this.lowerMenuBar = lowerMenuBar;
        }

        public static class TicketsStatusBean {
            /**
             * data : sfsfdg
             * nStatus : In Progress
             */

            private String data;
            private String nStatus;

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getNStatus() {
                return nStatus;
            }

            public void setNStatus(String nStatus) {
                this.nStatus = nStatus;
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
