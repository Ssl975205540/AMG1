package lanou.amg1.advert;

/**
 * Created by dllo on 16/10/13.
 */
public class AdvertBean {


    /**
     * returncode : 0
     * message :
     * result : {"ishavead":1,"pvid":"05eb6156-3d27-40d4-94a3-357a7f1334300","rdposturl":"http://rd.autohome.com.cn/adfront/realdeliver?pvid=05eb6156-3d27-40d4-94a3-357a7f1334300","areaid":19637,"ad":{"imgad":{"imgurl":"http://adm3.autoimg.cn/admdfs/g18/M13/57/5F/wKgH2VfqM7iAF5ItAALWrxkq86w101.jpg","openurl":"http://e.cn.miaozhen.com/r/k=2029400&p=72QZA&dx=__IPDX__&rt=2&ns=__IP__&ni=__IESID__&v=__LOC__&xa=__ADPLATFORM__&ro=sm&o=http://m.buick.com.cn/v3/car_envision.html?utm_source=appautohome&utm_medium=APP&utm_term=SP-MS1600014_HS-201609324_INT155-766_HY201600071&utm_content=SGMMRK2015000400&utm_campaign=envision2016_appautohome_MDBUXXXXXXXXXXX"},"gifad":{"imgurl":"","openurl":""},"showtime":3,"skipbtn":1,"expiretime":"2016-10-20","splashid":19637,"materialid":"393975","thirdadurl":""}}
     */

    private int returncode;
    private String message;
    /**
     * ishavead : 1
     * pvid : 05eb6156-3d27-40d4-94a3-357a7f1334300
     * rdposturl : http://rd.autohome.com.cn/adfront/realdeliver?pvid=05eb6156-3d27-40d4-94a3-357a7f1334300
     * areaid : 19637
     * ad : {"imgad":{"imgurl":"http://adm3.autoimg.cn/admdfs/g18/M13/57/5F/wKgH2VfqM7iAF5ItAALWrxkq86w101.jpg","openurl":"http://e.cn.miaozhen.com/r/k=2029400&p=72QZA&dx=__IPDX__&rt=2&ns=__IP__&ni=__IESID__&v=__LOC__&xa=__ADPLATFORM__&ro=sm&o=http://m.buick.com.cn/v3/car_envision.html?utm_source=appautohome&utm_medium=APP&utm_term=SP-MS1600014_HS-201609324_INT155-766_HY201600071&utm_content=SGMMRK2015000400&utm_campaign=envision2016_appautohome_MDBUXXXXXXXXXXX"},"gifad":{"imgurl":"","openurl":""},"showtime":3,"skipbtn":1,"expiretime":"2016-10-20","splashid":19637,"materialid":"393975","thirdadurl":""}
     */

    private ResultBean result;

    public int getReturncode() {
        return returncode;
    }

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private int ishavead;
        private String pvid;
        private String rdposturl;
        private int areaid;
        /**
         * imgad : {"imgurl":"http://adm3.autoimg.cn/admdfs/g18/M13/57/5F/wKgH2VfqM7iAF5ItAALWrxkq86w101.jpg","openurl":"http://e.cn.miaozhen.com/r/k=2029400&p=72QZA&dx=__IPDX__&rt=2&ns=__IP__&ni=__IESID__&v=__LOC__&xa=__ADPLATFORM__&ro=sm&o=http://m.buick.com.cn/v3/car_envision.html?utm_source=appautohome&utm_medium=APP&utm_term=SP-MS1600014_HS-201609324_INT155-766_HY201600071&utm_content=SGMMRK2015000400&utm_campaign=envision2016_appautohome_MDBUXXXXXXXXXXX"}
         * gifad : {"imgurl":"","openurl":""}
         * showtime : 3
         * skipbtn : 1
         * expiretime : 2016-10-20
         * splashid : 19637
         * materialid : 393975
         * thirdadurl :
         */

        private AdBean ad;

        public int getIshavead() {
            return ishavead;
        }

        public void setIshavead(int ishavead) {
            this.ishavead = ishavead;
        }

        public String getPvid() {
            return pvid;
        }

        public void setPvid(String pvid) {
            this.pvid = pvid;
        }

        public String getRdposturl() {
            return rdposturl;
        }

        public void setRdposturl(String rdposturl) {
            this.rdposturl = rdposturl;
        }

        public int getAreaid() {
            return areaid;
        }

        public void setAreaid(int areaid) {
            this.areaid = areaid;
        }

        public AdBean getAd() {
            return ad;
        }

        public void setAd(AdBean ad) {
            this.ad = ad;
        }

        public static class AdBean {
            /**
             * imgurl : http://adm3.autoimg.cn/admdfs/g18/M13/57/5F/wKgH2VfqM7iAF5ItAALWrxkq86w101.jpg
             * openurl : http://e.cn.miaozhen.com/r/k=2029400&p=72QZA&dx=__IPDX__&rt=2&ns=__IP__&ni=__IESID__&v=__LOC__&xa=__ADPLATFORM__&ro=sm&o=http://m.buick.com.cn/v3/car_envision.html?utm_source=appautohome&utm_medium=APP&utm_term=SP-MS1600014_HS-201609324_INT155-766_HY201600071&utm_content=SGMMRK2015000400&utm_campaign=envision2016_appautohome_MDBUXXXXXXXXXXX
             */

            private ImgadBean imgad;
            /**
             * imgurl :
             * openurl :
             */

            private GifadBean gifad;
            private int showtime;
            private int skipbtn;
            private String expiretime;
            private int splashid;
            private String materialid;
            private String thirdadurl;

            public ImgadBean getImgad() {
                return imgad;
            }

            public void setImgad(ImgadBean imgad) {
                this.imgad = imgad;
            }

            public GifadBean getGifad() {
                return gifad;
            }

            public void setGifad(GifadBean gifad) {
                this.gifad = gifad;
            }

            public int getShowtime() {
                return showtime;
            }

            public void setShowtime(int showtime) {
                this.showtime = showtime;
            }

            public int getSkipbtn() {
                return skipbtn;
            }

            public void setSkipbtn(int skipbtn) {
                this.skipbtn = skipbtn;
            }

            public String getExpiretime() {
                return expiretime;
            }

            public void setExpiretime(String expiretime) {
                this.expiretime = expiretime;
            }

            public int getSplashid() {
                return splashid;
            }

            public void setSplashid(int splashid) {
                this.splashid = splashid;
            }

            public String getMaterialid() {
                return materialid;
            }

            public void setMaterialid(String materialid) {
                this.materialid = materialid;
            }

            public String getThirdadurl() {
                return thirdadurl;
            }

            public void setThirdadurl(String thirdadurl) {
                this.thirdadurl = thirdadurl;
            }

            public static class ImgadBean {
                private String imgurl;
                private String openurl;

                public String getImgurl() {
                    return imgurl;
                }

                public void setImgurl(String imgurl) {
                    this.imgurl = imgurl;
                }

                public String getOpenurl() {
                    return openurl;
                }

                public void setOpenurl(String openurl) {
                    this.openurl = openurl;
                }
            }

            public static class GifadBean {
                private String imgurl;
                private String openurl;

                public String getImgurl() {
                    return imgurl;
                }

                public void setImgurl(String imgurl) {
                    this.imgurl = imgurl;
                }

                public String getOpenurl() {
                    return openurl;
                }

                public void setOpenurl(String openurl) {
                    this.openurl = openurl;
                }
            }
        }
    }
}
