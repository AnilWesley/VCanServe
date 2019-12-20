package com.vupadhi.heyhelp.models;

import java.util.List;

public class MyServicerequestsModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","statusVM":[{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"}],"accessToken":"be6d3384aedc9c49cdcd339da1b44166feedd3577dccf52a17bb8c2aef295d39","nClientID":"4084","lowerMenuBar":[{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]}
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
         * statusVM : [{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"},{"serviceId":1,"serviceName":"Domestic Help/Maid","status":"underProcess"}]
         * accessToken : be6d3384aedc9c49cdcd339da1b44166feedd3577dccf52a17bb8c2aef295d39
         * nClientID : 4084
         * lowerMenuBar : [{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]
         */

        private String message;
        private String accessToken;
        private String nClientID;
        private List<StatusVMBean> statusVM;
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

        public List<StatusVMBean> getStatusVM() {
            return statusVM;
        }

        public void setStatusVM(List<StatusVMBean> statusVM) {
            this.statusVM = statusVM;
        }

        public List<LowerMenuBarBean> getLowerMenuBar() {
            return lowerMenuBar;
        }

        public void setLowerMenuBar(List<LowerMenuBarBean> lowerMenuBar) {
            this.lowerMenuBar = lowerMenuBar;
        }

        public static class StatusVMBean {
            /**
             * serviceId : 1
             * serviceName : Domestic Help/Maid
             * status : underProcess
             */

            private int serviceId;
            private String serviceName;
            private String status;

            public int getServiceId() {
                return serviceId;
            }

            public void setServiceId(int serviceId) {
                this.serviceId = serviceId;
            }

            public String getServiceName() {
                return serviceName;
            }

            public void setServiceName(String serviceName) {
                this.serviceName = serviceName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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
