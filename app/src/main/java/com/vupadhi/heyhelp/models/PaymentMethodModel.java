package com.vupadhi.heyhelp.models;

import java.util.List;

public class PaymentMethodModel {


    /**
     * status : SUCCESS
     * data : {"message":"MC_SAVED_SUCCESSFUL","registrationFee":{"serviceType":"Domestic Help/Maid","timings":"Full Time","gender":"male","languages":"Telugu","servicesInclude":[{"subService":"BathRoomCleaning"},{"subService":"Washing Cloths"},{"subService":"Kitchen&utencil Cleaning"},{"subService":"BringingGroceries"},{"subService":"IroningCloths"},{"subService":"CleaningFloor"},{"subService":"Dusting"},{"subService":"OtherCleaningServices"}],"commonAllpackages":{"registrationFee":"500","premiumPackage":{"packagename":"Premium","packageAmount":"1000"},"ultraPremiumPackage":{"packagename":"Ultra Premium","packageAmount":"50000"}},"sAmount":"500"},"nClientID":"4114","accessToken":"489e7d6ebffed723a975c747c7f18bacd9f5637134f1f236515f34c7d92c6272"}
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
         * registrationFee : {"serviceType":"Domestic Help/Maid","timings":"Full Time","gender":"male","languages":"Telugu","servicesInclude":[{"subService":"BathRoomCleaning"},{"subService":"Washing Cloths"},{"subService":"Kitchen&utencil Cleaning"},{"subService":"BringingGroceries"},{"subService":"IroningCloths"},{"subService":"CleaningFloor"},{"subService":"Dusting"},{"subService":"OtherCleaningServices"}],"commonAllpackages":{"registrationFee":"500","premiumPackage":{"packagename":"Premium","packageAmount":"1000"},"ultraPremiumPackage":{"packagename":"Ultra Premium","packageAmount":"50000"}},"sAmount":"500"}
         * nClientID : 4114
         * accessToken : 489e7d6ebffed723a975c747c7f18bacd9f5637134f1f236515f34c7d92c6272
         */

        private String message;
        private RegistrationFeeBean registrationFee;
        private String nClientID;
        private String accessToken;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public RegistrationFeeBean getRegistrationFee() {
            return registrationFee;
        }

        public void setRegistrationFee(RegistrationFeeBean registrationFee) {
            this.registrationFee = registrationFee;
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

        public static class RegistrationFeeBean {
            /**
             * serviceType : Domestic Help/Maid
             * timings : Full Time
             * gender : male
             * languages : Telugu
             * servicesInclude : [{"subService":"BathRoomCleaning"},{"subService":"Washing Cloths"},{"subService":"Kitchen&utencil Cleaning"},{"subService":"BringingGroceries"},{"subService":"IroningCloths"},{"subService":"CleaningFloor"},{"subService":"Dusting"},{"subService":"OtherCleaningServices"}]
             * commonAllpackages : {"registrationFee":"500","premiumPackage":{"packagename":"Premium","packageAmount":"1000"},"ultraPremiumPackage":{"packagename":"Ultra Premium","packageAmount":"50000"}}
             * sAmount : 500
             */

            private String serviceType;
            private String timings;
            private String gender;
            private String languages;
            private CommonAllpackagesBean commonAllpackages;
            private String sAmount;
            private List<ServicesIncludeBean> servicesInclude;

            public String getServiceType() {
                return serviceType;
            }

            public void setServiceType(String serviceType) {
                this.serviceType = serviceType;
            }

            public String getTimings() {
                return timings;
            }

            public void setTimings(String timings) {
                this.timings = timings;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getLanguages() {
                return languages;
            }

            public void setLanguages(String languages) {
                this.languages = languages;
            }

            public CommonAllpackagesBean getCommonAllpackages() {
                return commonAllpackages;
            }

            public void setCommonAllpackages(CommonAllpackagesBean commonAllpackages) {
                this.commonAllpackages = commonAllpackages;
            }

            public String getSAmount() {
                return sAmount;
            }

            public void setSAmount(String sAmount) {
                this.sAmount = sAmount;
            }

            public List<ServicesIncludeBean> getServicesInclude() {
                return servicesInclude;
            }

            public void setServicesInclude(List<ServicesIncludeBean> servicesInclude) {
                this.servicesInclude = servicesInclude;
            }

            public static class CommonAllpackagesBean {
                /**
                 * registrationFee : 500
                 * premiumPackage : {"packagename":"Premium","packageAmount":"1000"}
                 * ultraPremiumPackage : {"packagename":"Ultra Premium","packageAmount":"50000"}
                 */

                private String registrationFee;
                private PremiumPackageBean premiumPackage;
                private UltraPremiumPackageBean ultraPremiumPackage;

                public String getRegistrationFee() {
                    return registrationFee;
                }

                public void setRegistrationFee(String registrationFee) {
                    this.registrationFee = registrationFee;
                }

                public PremiumPackageBean getPremiumPackage() {
                    return premiumPackage;
                }

                public void setPremiumPackage(PremiumPackageBean premiumPackage) {
                    this.premiumPackage = premiumPackage;
                }

                public UltraPremiumPackageBean getUltraPremiumPackage() {
                    return ultraPremiumPackage;
                }

                public void setUltraPremiumPackage(UltraPremiumPackageBean ultraPremiumPackage) {
                    this.ultraPremiumPackage = ultraPremiumPackage;
                }

                public static class PremiumPackageBean {
                    /**
                     * packagename : Premium
                     * packageAmount : 1000
                     */

                    private String packagename;
                    private String packageAmount;

                    public String getPackagename() {
                        return packagename;
                    }

                    public void setPackagename(String packagename) {
                        this.packagename = packagename;
                    }

                    public String getPackageAmount() {
                        return packageAmount;
                    }

                    public void setPackageAmount(String packageAmount) {
                        this.packageAmount = packageAmount;
                    }
                }

                public static class UltraPremiumPackageBean {
                    /**
                     * packagename : Ultra Premium
                     * packageAmount : 50000
                     */

                    private String packagename;
                    private String packageAmount;

                    public String getPackagename() {
                        return packagename;
                    }

                    public void setPackagename(String packagename) {
                        this.packagename = packagename;
                    }

                    public String getPackageAmount() {
                        return packageAmount;
                    }

                    public void setPackageAmount(String packageAmount) {
                        this.packageAmount = packageAmount;
                    }
                }
            }

            public static class ServicesIncludeBean {
                /**
                 * subService : BathRoomCleaning
                 */

                private String subService;

                public String getSubService() {
                    return subService;
                }

                public void setSubService(String subService) {
                    this.subService = subService;
                }
            }
        }
    }
}
