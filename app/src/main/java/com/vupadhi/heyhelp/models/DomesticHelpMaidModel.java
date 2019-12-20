package com.vupadhi.heyhelp.models;

import java.util.List;

public class DomesticHelpMaidModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","commontimingslist":[{"c_ID":1,"name":"Full Time"},{"c_ID":2,"name":"Part Time"},{"c_ID":3,"name":"Resident"}],"commongenderlist":[{"c_ID":4,"name":"male"},{"c_ID":5,"name":"female"}],"commonlanguagelist":[{"c_ID":6,"name":"Telugu"},{"c_ID":7,"name":"Hindi"},{"c_ID":8,"name":"English"},{"c_ID":9,"name":"Others"}],"nClientID":"4084","accessToken":"307b814989bc1271ad43f4d7c4c4c4b5535c2027b55e560bc9bed7876e06f51f","lowerMenuBar":[{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]}
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
         * commontimingslist : [{"c_ID":1,"name":"Full Time"},{"c_ID":2,"name":"Part Time"},{"c_ID":3,"name":"Resident"}]
         * commongenderlist : [{"c_ID":4,"name":"male"},{"c_ID":5,"name":"female"}]
         * commonlanguagelist : [{"c_ID":6,"name":"Telugu"},{"c_ID":7,"name":"Hindi"},{"c_ID":8,"name":"English"},{"c_ID":9,"name":"Others"}]
         * nClientID : 4084
         * accessToken : 307b814989bc1271ad43f4d7c4c4c4b5535c2027b55e560bc9bed7876e06f51f
         * lowerMenuBar : [{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]
         */

        private String message;
        private String nClientID;
        private String accessToken;
        private List<CommontimingslistBean> commontimingslist;
        private List<CommongenderlistBean> commongenderlist;
        private List<CommonlanguagelistBean> commonlanguagelist;
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

        public List<CommontimingslistBean> getCommontimingslist() {
            return commontimingslist;
        }

        public void setCommontimingslist(List<CommontimingslistBean> commontimingslist) {
            this.commontimingslist = commontimingslist;
        }

        public List<CommongenderlistBean> getCommongenderlist() {
            return commongenderlist;
        }

        public void setCommongenderlist(List<CommongenderlistBean> commongenderlist) {
            this.commongenderlist = commongenderlist;
        }

        public List<CommonlanguagelistBean> getCommonlanguagelist() {
            return commonlanguagelist;
        }

        public void setCommonlanguagelist(List<CommonlanguagelistBean> commonlanguagelist) {
            this.commonlanguagelist = commonlanguagelist;
        }

        public List<LowerMenuBarBean> getLowerMenuBar() {
            return lowerMenuBar;
        }

        public void setLowerMenuBar(List<LowerMenuBarBean> lowerMenuBar) {
            this.lowerMenuBar = lowerMenuBar;
        }

        public static class CommontimingslistBean {
            /**
             * c_ID : 1
             * name : Full Time
             */

            private int c_ID;
            private String name;

            public int getC_ID() {
                return c_ID;
            }

            public void setC_ID(int c_ID) {
                this.c_ID = c_ID;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class CommongenderlistBean {
            /**
             * c_ID : 4
             * name : male
             */

            private int c_ID;
            private String name;

            public int getC_ID() {
                return c_ID;
            }

            public void setC_ID(int c_ID) {
                this.c_ID = c_ID;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class CommonlanguagelistBean {
            /**
             * c_ID : 6
             * name : Telugu
             */

            private int c_ID;
            private String name;

            public int getC_ID() {
                return c_ID;
            }

            public void setC_ID(int c_ID) {
                this.c_ID = c_ID;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
