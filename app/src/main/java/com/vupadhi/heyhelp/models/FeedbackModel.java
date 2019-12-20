package com.vupadhi.heyhelp.models;

import java.util.List;

public class FeedbackModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","feedback":{"fQuestionList":[{"sName":"Question1","fAnswersList":[{"sAnswerID":1,"sAnswerName":"Question1Answer1"},{"sAnswerID":2,"sAnswerName":"Question1Answer2"},{"sAnswerID":3,"sAnswerName":"Question1Answer3"}],"id":1},{"sName":"Question2","fAnswersList":[{"sAnswerID":4,"sAnswerName":"Question2Answer1"},{"sAnswerID":5,"sAnswerName":"Question2Answer2"},{"sAnswerID":6,"sAnswerName":"Question2Answer3"}],"id":2},{"sName":"Question3","fAnswersList":[{"sAnswerID":7,"sAnswerName":"Question3Answer1"},{"sAnswerID":8,"sAnswerName":"Question3Answer2"},{"sAnswerID":9,"sAnswerName":"Question3Answer3"}],"id":3},{"sName":"Question4","fAnswersList":[],"id":4}]},"nClientID":"4084","accessToken":"e0c66c2f58d4b43ce621866e8974dd688b718090488fc769b215e7c5a8d88b98","lowerMenuBar":[{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]}
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
         * feedback : {"fQuestionList":[{"sName":"Question1","fAnswersList":[{"sAnswerID":1,"sAnswerName":"Question1Answer1"},{"sAnswerID":2,"sAnswerName":"Question1Answer2"},{"sAnswerID":3,"sAnswerName":"Question1Answer3"}],"id":1},{"sName":"Question2","fAnswersList":[{"sAnswerID":4,"sAnswerName":"Question2Answer1"},{"sAnswerID":5,"sAnswerName":"Question2Answer2"},{"sAnswerID":6,"sAnswerName":"Question2Answer3"}],"id":2},{"sName":"Question3","fAnswersList":[{"sAnswerID":7,"sAnswerName":"Question3Answer1"},{"sAnswerID":8,"sAnswerName":"Question3Answer2"},{"sAnswerID":9,"sAnswerName":"Question3Answer3"}],"id":3},{"sName":"Question4","fAnswersList":[],"id":4}]}
         * nClientID : 4084
         * accessToken : e0c66c2f58d4b43ce621866e8974dd688b718090488fc769b215e7c5a8d88b98
         * lowerMenuBar : [{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]
         */

        private String message;
        private FeedbackBean feedback;
        private String nClientID;
        private String accessToken;
        private List<LowerMenuBarBean> lowerMenuBar;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public FeedbackBean getFeedback() {
            return feedback;
        }

        public void setFeedback(FeedbackBean feedback) {
            this.feedback = feedback;
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

        public List<LowerMenuBarBean> getLowerMenuBar() {
            return lowerMenuBar;
        }

        public void setLowerMenuBar(List<LowerMenuBarBean> lowerMenuBar) {
            this.lowerMenuBar = lowerMenuBar;
        }

        public static class FeedbackBean {
            private List<FQuestionListBean> fQuestionList;

            public List<FQuestionListBean> getFQuestionList() {
                return fQuestionList;
            }

            public void setFQuestionList(List<FQuestionListBean> fQuestionList) {
                this.fQuestionList = fQuestionList;
            }

            public static class FQuestionListBean {
                /**
                 * sName : Question1
                 * fAnswersList : [{"sAnswerID":1,"sAnswerName":"Question1Answer1"},{"sAnswerID":2,"sAnswerName":"Question1Answer2"},{"sAnswerID":3,"sAnswerName":"Question1Answer3"}]
                 * id : 1
                 */

                private String sName;
                private int id;

                private List<FAnswersListBean> fAnswersList;

                public String getSName() {
                    return sName;
                }

                public void setSName(String sName) {
                    this.sName = sName;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public List<FAnswersListBean> getFAnswersList() {
                    return fAnswersList;
                }

                public void setFAnswersList(List<FAnswersListBean> fAnswersList) {
                    this.fAnswersList = fAnswersList;
                }

                public static class FAnswersListBean {
                    /**
                     * sAnswerID : 1
                     * sAnswerName : Question1Answer1
                     */

                    private int sAnswerID;
                    private String sAnswerName;


                    private boolean isSelected;

                    public boolean isSelected() {
                        return isSelected;
                    }

                    public void setSelected(boolean selected) {
                        isSelected = selected;
                    }

                    public int getSAnswerID() {
                        return sAnswerID;
                    }



                    public void setSAnswerID(int sAnswerID) {
                        this.sAnswerID = sAnswerID;
                    }

                    public String getSAnswerName() {
                        return sAnswerName;
                    }

                    public void setSAnswerName(String sAnswerName) {
                        this.sAnswerName = sAnswerName;
                    }
                }
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
