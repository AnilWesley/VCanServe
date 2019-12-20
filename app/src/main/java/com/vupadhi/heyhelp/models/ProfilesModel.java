package com.vupadhi.heyhelp.models;

import java.util.List;

public class ProfilesModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","profilenotfoundMsg":"Some Profiles are found","setlist":[{"nWorkerID":"7","nWorkerName":"Worker1","photo":null,"workerRating":"0.0","photoPath":null}],"accessToken":"9baac0a797ec1ffdaa7ab244a0456e4f192d7ca07006a9e0ee6b717aaff3be8e","nClientID":"4114"}
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
         * profilenotfoundMsg : Some Profiles are found
         * setlist : [{"nWorkerID":"7","nWorkerName":"Worker1","photo":null,"workerRating":"0.0","photoPath":null}]
         * accessToken : 9baac0a797ec1ffdaa7ab244a0456e4f192d7ca07006a9e0ee6b717aaff3be8e
         * nClientID : 4114
         */

        private String message;
        private String profilenotfoundMsg;
        private String accessToken;
        private String nClientID;
        private List<SetlistBean> setlist;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getProfilenotfoundMsg() {
            return profilenotfoundMsg;
        }

        public void setProfilenotfoundMsg(String profilenotfoundMsg) {
            this.profilenotfoundMsg = profilenotfoundMsg;
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

        public List<SetlistBean> getSetlist() {
            return setlist;
        }

        public void setSetlist(List<SetlistBean> setlist) {
            this.setlist = setlist;
        }

        public static class SetlistBean {
            /**
             * nWorkerID : 7
             * nWorkerName : Worker1
             * photo : null
             * workerRating : 0.0
             * photoPath : null
             */

            private String nWorkerID;
            private String nWorkerName;
            private Object photo;
            private String workerRating;
            private Object photoPath;

            public String getNWorkerID() {
                return nWorkerID;
            }

            public void setNWorkerID(String nWorkerID) {
                this.nWorkerID = nWorkerID;
            }

            public String getNWorkerName() {
                return nWorkerName;
            }

            public void setNWorkerName(String nWorkerName) {
                this.nWorkerName = nWorkerName;
            }

            public Object getPhoto() {
                return photo;
            }

            public void setPhoto(Object photo) {
                this.photo = photo;
            }

            public String getWorkerRating() {
                return workerRating;
            }

            public void setWorkerRating(String workerRating) {
                this.workerRating = workerRating;
            }

            public Object getPhotoPath() {
                return photoPath;
            }

            public void setPhotoPath(Object photoPath) {
                this.photoPath = photoPath;
            }
        }
    }
}
