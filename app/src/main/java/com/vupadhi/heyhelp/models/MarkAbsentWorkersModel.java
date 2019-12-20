package com.vupadhi.heyhelp.models;

import java.util.List;

public class MarkAbsentWorkersModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","workerList":[{"nWorkerID":2,"workerName":"testworker2","nPartnerID":66}]}
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
         * workerList : [{"nWorkerID":2,"workerName":"testworker2","nPartnerID":66}]
         */

        private String message;
        private List<WorkerListBean> workerList;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<WorkerListBean> getWorkerList() {
            return workerList;
        }

        public void setWorkerList(List<WorkerListBean> workerList) {
            this.workerList = workerList;
        }

        public static class WorkerListBean {
            /**
             * nWorkerID : 2
             * workerName : testworker2
             * nPartnerID : 66
             */

            private int nWorkerID;
            private String workerName;
            private int nPartnerID;

            public String getSelecteddates() {
                return selecteddates;
            }

            public void setSelecteddates(String selecteddates) {
                this.selecteddates = selecteddates;
            }

            String selecteddates;

            public int getNWorkerID() {
                return nWorkerID;
            }

            public void setNWorkerID(int nWorkerID) {
                this.nWorkerID = nWorkerID;
            }

            public String getWorkerName() {
                return workerName;
            }

            public void setWorkerName(String workerName) {
                this.workerName = workerName;
            }

            public int getNPartnerID() {
                return nPartnerID;
            }

            public void setNPartnerID(int nPartnerID) {
                this.nPartnerID = nPartnerID;
            }
        }
    }
}
