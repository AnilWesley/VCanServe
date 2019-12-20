package com.vupadhi.heyhelp.models;

import java.util.List;

public class DeploymentNotifModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","notification":[{"sName":"testworker is ready for deployment kindly pay monthly advance"}]}
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
         * notification : [{"sName":"testworker is ready for deployment kindly pay monthly advance"}]
         */

        private String message;
        private List<NotificationBean> notification;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<NotificationBean> getNotification() {
            return notification;
        }

        public void setNotification(List<NotificationBean> notification) {
            this.notification = notification;
        }

        public static class NotificationBean {
            /**
             * sName : testworker is ready for deployment kindly pay monthly advance
             */

            private String sName;

            public String getSName() {
                return sName;
            }

            public void setSName(String sName) {
                this.sName = sName;
            }
        }
    }
}
