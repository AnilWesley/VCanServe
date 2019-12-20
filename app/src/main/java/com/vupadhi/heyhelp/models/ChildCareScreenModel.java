package com.vupadhi.heyhelp.models;

import java.util.List;

public class ChildCareScreenModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","count":0,"subservicelist":[{"nSubServiceID":"13","nServiceID":"2","sSubServiceName":"childAge","bMandatory":1,"typeList":[{"nID":"5","sName":"Less Than 2"},{"nID":"6","sName":"Less Than 6"},{"nID":"7","sName":"Above 6"}]},{"nSubServiceID":"14","nServiceID":"2","sSubServiceName":"gender","bMandatory":1,"typeList":[{"nID":"8","sName":"Boy"},{"nID":"9","sName":"Girl"}]},{"nSubServiceID":"15","nServiceID":"2","sSubServiceName":"babyBathing","bMandatory":1,"typeList":null},{"nSubServiceID":"16","nServiceID":"2","sSubServiceName":"babyMassage","bMandatory":1,"typeList":null},{"nSubServiceID":"17","nServiceID":"2","sSubServiceName":"Preparing Baby For Food","bMandatory":1,"typeList":null},{"nSubServiceID":"18","nServiceID":"2","sSubServiceName":"takingBabyForWalk","bMandatory":1,"typeList":null},{"nSubServiceID":"19","nServiceID":"2","sSubServiceName":"changingDiaper","bMandatory":1,"typeList":null},{"nSubServiceID":"20","nServiceID":"2","sSubServiceName":"feeding","bMandatory":1,"typeList":null},{"nSubServiceID":"21","nServiceID":"2","sSubServiceName":"washingChildCloths","bMandatory":1,"typeList":null},{"nSubServiceID":"22","nServiceID":"2","sSubServiceName":"preparingkidforsleep","bMandatory":1,"typeList":null},{"nSubServiceID":"23","nServiceID":"2","sSubServiceName":"otherChildCareServices","bMandatory":1,"typeList":null}],"languages":[{"nLanguageName":"Telugu"},{"nLanguageName":"English"}],"accessToken":"d9e554a242e43c81927dece75f2d3f70bd47523f04be6329818d0d1f597afff5","nClientID":"4084","lowerMenuBar":[{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}],"commonlist":{"nOrderID":3092,"nServiceID":2,"nTimingName":"Full Time","nGenderName":"female"}}
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
         * count : 0
         * subservicelist : [{"nSubServiceID":"13","nServiceID":"2","sSubServiceName":"childAge","bMandatory":1,"typeList":[{"nID":"5","sName":"Less Than 2"},{"nID":"6","sName":"Less Than 6"},{"nID":"7","sName":"Above 6"}]},{"nSubServiceID":"14","nServiceID":"2","sSubServiceName":"gender","bMandatory":1,"typeList":[{"nID":"8","sName":"Boy"},{"nID":"9","sName":"Girl"}]},{"nSubServiceID":"15","nServiceID":"2","sSubServiceName":"babyBathing","bMandatory":1,"typeList":null},{"nSubServiceID":"16","nServiceID":"2","sSubServiceName":"babyMassage","bMandatory":1,"typeList":null},{"nSubServiceID":"17","nServiceID":"2","sSubServiceName":"Preparing Baby For Food","bMandatory":1,"typeList":null},{"nSubServiceID":"18","nServiceID":"2","sSubServiceName":"takingBabyForWalk","bMandatory":1,"typeList":null},{"nSubServiceID":"19","nServiceID":"2","sSubServiceName":"changingDiaper","bMandatory":1,"typeList":null},{"nSubServiceID":"20","nServiceID":"2","sSubServiceName":"feeding","bMandatory":1,"typeList":null},{"nSubServiceID":"21","nServiceID":"2","sSubServiceName":"washingChildCloths","bMandatory":1,"typeList":null},{"nSubServiceID":"22","nServiceID":"2","sSubServiceName":"preparingkidforsleep","bMandatory":1,"typeList":null},{"nSubServiceID":"23","nServiceID":"2","sSubServiceName":"otherChildCareServices","bMandatory":1,"typeList":null}]
         * languages : [{"nLanguageName":"Telugu"},{"nLanguageName":"English"}]
         * accessToken : d9e554a242e43c81927dece75f2d3f70bd47523f04be6329818d0d1f597afff5
         * nClientID : 4084
         * lowerMenuBar : [{"id":"1","name":"Home"},{"id":"2","name":"Profile"},{"id":"3","name":"Promotions & Offers"}]
         * commonlist : {"nOrderID":3092,"nServiceID":2,"nTimingName":"Full Time","nGenderName":"female"}
         */

        private String message;
        private int count;
        private String accessToken;
        private String nClientID;
        private CommonlistBean commonlist;
        private List<SubservicelistBean> subservicelist;
        private List<LanguagesBean> languages;
        private List<LowerMenuBarBean> lowerMenuBar;

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

        public static class CommonlistBean {
            /**
             * nOrderID : 3092
             * nServiceID : 2
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
             * nSubServiceID : 13
             * nServiceID : 2
             * sSubServiceName : childAge
             * bMandatory : 1
             * typeList : [{"nID":"5","sName":"Less Than 2"},{"nID":"6","sName":"Less Than 6"},{"nID":"7","sName":"Above 6"}]
             */

            private String nSubServiceID;
            private String nServiceID;
            private String sSubServiceName;
            private int bMandatory;
            private List<TypeListBean> typeList;


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

            public List<TypeListBean> getTypeList() {
                return typeList;
            }

            public void setTypeList(List<TypeListBean> typeList) {
                this.typeList = typeList;
            }

            public static class TypeListBean {
                /**
                 * nID : 5
                 * sName : Less Than 2
                 */

                private String nID;
                private String sName;

                public String getNID() {
                    return nID;
                }

                public void setNID(String nID) {
                    this.nID = nID;
                }

                public String getSName() {
                    return sName;
                }

                public void setSName(String sName) {
                    this.sName = sName;
                }
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
