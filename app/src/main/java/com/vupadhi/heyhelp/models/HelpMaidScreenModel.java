package com.vupadhi.heyhelp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class HelpMaidScreenModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","count":0,"subservicelist":[{"nSubServiceID":"1","nServiceID":"1","sSubServiceName":"BathRoomCleaning","bMandatory":1,"typeList":null},{"nSubServiceID":"2","nServiceID":"1","sSubServiceName":"Washing Cloths","bMandatory":1,"typeList":null},{"nSubServiceID":"3","nServiceID":"1","sSubServiceName":"Kitchen&utencil Cleaning","bMandatory":1,"typeList":null},{"nSubServiceID":"4","nServiceID":"1","sSubServiceName":"BringingGroceries","bMandatory":1,"typeList":null},{"nSubServiceID":"5","nServiceID":"1","sSubServiceName":"IroningCloths","bMandatory":1,"typeList":null},{"nSubServiceID":"6","nServiceID":"1","sSubServiceName":"CleaningFloor","bMandatory":1,"typeList":null},{"nSubServiceID":"7","nServiceID":"1","sSubServiceName":"Dusting","bMandatory":1,"typeList":null},{"nSubServiceID":"8","nServiceID":"1","sSubServiceName":"OtherCleaningServices","bMandatory":1,"typeList":null}],"languages":[{"nLanguageName":"Telugu"},{"nLanguageName":"English"}],"accessToken":"f89288ca72781a4b3b0273054b1dc32dff44ff92971201270b8cbb0e6219620f","nClientID":"4084","lowerMenuBar":[{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}],"commonlist":{"nOrderID":3058,"nServiceID":1,"nTimingName":"Full Time","nGenderName":"female"}}
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

    public static class DataBean implements  Parcelable{
        /**
         * message : MC_SAVED_SUCCESSFUL
         * count : 0
         * subservicelist : [{"nSubServiceID":"1","nServiceID":"1","sSubServiceName":"BathRoomCleaning","bMandatory":1,"typeList":null},{"nSubServiceID":"2","nServiceID":"1","sSubServiceName":"Washing Cloths","bMandatory":1,"typeList":null},{"nSubServiceID":"3","nServiceID":"1","sSubServiceName":"Kitchen&utencil Cleaning","bMandatory":1,"typeList":null},{"nSubServiceID":"4","nServiceID":"1","sSubServiceName":"BringingGroceries","bMandatory":1,"typeList":null},{"nSubServiceID":"5","nServiceID":"1","sSubServiceName":"IroningCloths","bMandatory":1,"typeList":null},{"nSubServiceID":"6","nServiceID":"1","sSubServiceName":"CleaningFloor","bMandatory":1,"typeList":null},{"nSubServiceID":"7","nServiceID":"1","sSubServiceName":"Dusting","bMandatory":1,"typeList":null},{"nSubServiceID":"8","nServiceID":"1","sSubServiceName":"OtherCleaningServices","bMandatory":1,"typeList":null}]
         * languages : [{"nLanguageName":"Telugu"},{"nLanguageName":"English"}]
         * accessToken : f89288ca72781a4b3b0273054b1dc32dff44ff92971201270b8cbb0e6219620f
         * nClientID : 4084
         * lowerMenuBar : [{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]
         * commonlist : {"nOrderID":3058,"nServiceID":1,"nTimingName":"Full Time","nGenderName":"female"}
         */

        private String message;
        private int count;
        private String accessToken;
        private String nClientID;
        private CommonlistBean commonlist;
        private List<SubservicelistBean> subservicelist;
        private List<LanguagesBean> languages;
        private List<LowerMenuBarBean> lowerMenuBar;



        protected DataBean(Parcel in) {
            message = in.readString();
            count = in.readInt();
            accessToken = in.readString();
            nClientID = in.readString();

        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };



        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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

        public CommonlistBean getCommonlist() {
            return commonlist;
        }

        public void setCommonlist(CommonlistBean commonlist) {
            this.commonlist = commonlist;
        }

        public List<SubservicelistBean> getSubservicelist() {
            return subservicelist;
        }

        public void setSubservicelist(List<SubservicelistBean> subservicelist) {
            this.subservicelist = subservicelist;
        }

        public List<LanguagesBean> getLanguages() {
            return languages;
        }

        public void setLanguages(List<LanguagesBean> languages) {
            this.languages = languages;
        }

        public List<LowerMenuBarBean> getLowerMenuBar() {
            return lowerMenuBar;
        }

        public void setLowerMenuBar(List<LowerMenuBarBean> lowerMenuBar) {
            this.lowerMenuBar = lowerMenuBar;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(message);
            dest.writeInt(count);
            dest.writeString(accessToken);
            dest.writeString(nClientID);


        }
        public static class CommonlistBean {
            /**
             * nOrderID : 3058
             * nServiceID : 1
             * nTimingName : Full Time
             * nGenderName : female
             */

            private int nOrderID;
            private int nServiceID;
            private String nTimingName;
            private String nGenderName;

            public int getNOrderID() {
                return nOrderID;
            }

            public void setNOrderID(int nOrderID) {
                this.nOrderID = nOrderID;
            }

            public int getNServiceID() {
                return nServiceID;
            }

            public void setNServiceID(int nServiceID) {
                this.nServiceID = nServiceID;
            }

            public String getNTimingName() {
                return nTimingName;
            }

            public void setNTimingName(String nTimingName) {
                this.nTimingName = nTimingName;
            }

            public String getNGenderName() {
                return nGenderName;
            }

            public void setNGenderName(String nGenderName) {
                this.nGenderName = nGenderName;
            }
        }

        public static class SubservicelistBean {
            /**
             * nSubServiceID : 1
             * nServiceID : 1
             * sSubServiceName : BathRoomCleaning
             * bMandatory : 1
             * typeList : null
             */

            private String nSubServiceID;
            private String nServiceID;
            private String sSubServiceName;
            private int bMandatory;
            private Object typeList;


            public boolean isClicked = true;

            public boolean isClicked() {
                return isClicked;
            }

            public void setClicked(boolean clicked) {
                isClicked = clicked;
            }


            public String getNSubServiceID() {
                return nSubServiceID;
            }

            public void setNSubServiceID(String nSubServiceID) {
                this.nSubServiceID = nSubServiceID;
            }

            public String getNServiceID() {
                return nServiceID;
            }

            public void setNServiceID(String nServiceID) {
                this.nServiceID = nServiceID;
            }

            public String getSSubServiceName() {
                return sSubServiceName;
            }

            public void setSSubServiceName(String sSubServiceName) {
                this.sSubServiceName = sSubServiceName;
            }

            public int getBMandatory() {
                return bMandatory;
            }

            public void setBMandatory(int bMandatory) {
                this.bMandatory = bMandatory;
            }

            public Object getTypeList() {
                return typeList;
            }

            public void setTypeList(Object typeList) {
                this.typeList = typeList;
            }
        }

        public static class LanguagesBean {
            /**
             * nLanguageName : Telugu
             */

            private String nLanguageName;

            public String getNLanguageName() {
                return nLanguageName;
            }

            public void setNLanguageName(String nLanguageName) {
                this.nLanguageName = nLanguageName;
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
