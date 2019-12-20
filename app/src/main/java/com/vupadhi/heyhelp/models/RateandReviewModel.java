package com.vupadhi.heyhelp.models;

import java.util.List;

public class RateandReviewModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","workerRatingVM":{"workerList":[{"nWorkerID":2,"workerName":"testworker2","nPartnerID":66}],"companyRating":"CompanyRating","writeaReview":"WriteaReview"}}
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
         * workerRatingVM : {"workerList":[{"nWorkerID":2,"workerName":"testworker2","nPartnerID":66}],"companyRating":"CompanyRating","writeaReview":"WriteaReview"}
         */

        private String message;
        private WorkerRatingVMBean workerRatingVM;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public WorkerRatingVMBean getWorkerRatingVM() {
            return workerRatingVM;
        }

        public void setWorkerRatingVM(WorkerRatingVMBean workerRatingVM) {
            this.workerRatingVM = workerRatingVM;
        }

        public static class WorkerRatingVMBean {
            /**
             * workerList : [{"nWorkerID":2,"workerName":"testworker2","nPartnerID":66}]
             * companyRating : CompanyRating
             * writeaReview : WriteaReview
             */

            private String companyRating;
            private String writeaReview;
            private List<WorkerListBean> workerList;

            public String getCompanyRating() {
                return companyRating;
            }

            public void setCompanyRating(String companyRating) {
                this.companyRating = companyRating;
            }

            public String getWriteaReview() {
                return writeaReview;
            }

            public void setWriteaReview(String writeaReview) {
                this.writeaReview = writeaReview;
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
}
