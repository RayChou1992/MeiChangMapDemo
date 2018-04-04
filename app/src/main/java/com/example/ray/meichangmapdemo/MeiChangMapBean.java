package com.example.ray.meichangmapdemo;

import java.util.List;

public class MeiChangMapBean {

    /**
     * ylList : [{"NAME":"信标1","YARD":"汽车沟","LOCATION_X":24,"LOCATION_Y":4,"HEIGHT":20,"FLAG":0,"WIDTH":200},{"NAME":"信标2","YARD":"火车沟","LOCATION_X":61,"LOCATION_Y":17,"HEIGHT":20,"FLAG":0,"WIDTH":500},{"NAME":"33额","YARD":"火车沟","LOCATION_X":106,"LOCATION_Y":9,"HEIGHT":20,"FLAG":1,"WIDTH":500},{"NAME":"信标2","YARD":"火车沟","LOCATION_X":193,"LOCATION_Y":14,"HEIGHT":20,"FLAG":0,"WIDTH":500},{"NAME":"信标3","YARD":"火车沟","LOCATION_X":69,"LOCATION_Y":11,"HEIGHT":20,"FLAG":1,"WIDTH":500},{"NAME":"信标2","YARD":"汽车沟","LOCATION_X":44,"LOCATION_Y":17,"HEIGHT":20,"FLAG":0,"WIDTH":200},{"NAME":"信标3","YARD":"汽车沟","LOCATION_X":122,"LOCATION_Y":7,"HEIGHT":20,"FLAG":0,"WIDTH":200},{"NAME":"信标4","YARD":"汽车沟","LOCATION_X":25,"LOCATION_Y":8,"HEIGHT":20,"FLAG":0,"WIDTH":200}]
     * mc : {"id":1,"name":"一期","maxLat":40.069522,"maxLng":113.198042,"status":1,"acctTime":"2017-05-09","acctUser":0,"updateTime":"2017-05-09","org":"1","xD":0,"yD":224.00000000000009,"xR":296.26749611197494,"yR":224.00000000000009,"xU":296.26749611197494,"yU":0,"xL":0,"yL":0,"minLat":40.066651,"minLng":113.191332,"url":null}
     * coalData : {"二期":"74883.1","三期":"131874.79","一期":"264276.4"}
     * doulj : [{"course":null,"lng":54.26419563660465,"courseAngle":15,"lat":192.6353187042843},{"course":"SW","lng":90.42560835131515,"courseAngle":120.11,"lat":82.936955764542}]
     * mdList : [{"amount":5000,"qe":5300,"sgq":0.45,"xDu":0,"yDu":0,"xRu":0,"yRu":0,"xUu":0,"yUu":0,"xLu":0,"yLu":0,"h":0,"id":0,"name":"甲煤场南侧1","latD":0,"lngD":0,"status":0,"acctTime":null,"acctUser":0,"updateTime":null,"belong":null,"xD":10.111066558814047,"yD":176.2507836990596,"xR":54.26419563660465,"yR":176.2507836990596,"xU":54.26419563660465,"yU":97.44897248345528,"xL":10.111066558814047,"yL":97.44897248345528,"latR":0,"lngR":0,"latU":0,"lngU":0,"latL":0,"lngL":0,"org":null},{"amount":5250,"qe":5300,"sgq":0.45,"xDu":0,"yDu":0,"xRu":0,"yRu":0,"xUu":0,"yUu":0,"xLu":0,"yLu":0,"h":0,"id":0,"name":"甲煤场北侧1","latD":0,"lngD":0,"status":0,"acctTime":null,"acctUser":0,"updateTime":null,"belong":null,"xD":10.111066558814047,"yD":70.29745733194011,"xR":98.41732471439525,"yR":70.29745733194011,"xU":98.41732471439525,"yU":5.383490073145247,"xL":10.111066558814047,"yL":5.383490073145247,"latR":0,"lngR":0,"latU":0,"lngU":0,"latL":0,"lngL":0,"org":null},{"amount":5000,"qe":5300,"sgq":0.45,"xDu":0,"yDu":0,"xRu":0,"yRu":0,"xUu":0,"yUu":0,"xLu":0,"yLu":0,"h":0,"id":0,"name":"甲煤场南侧3","latD":0,"lngD":0,"status":0,"acctTime":null,"acctUser":0,"updateTime":null,"belong":null,"xD":177.89295705441833,"yD":176.2507836990596,"xR":292.6910926566739,"yR":176.2507836990596,"xU":292.6910926566739,"yU":97.44897248345528,"xL":177.89295705441833,"yL":97.44897248345528,"latR":0,"lngR":0,"latU":0,"lngU":0,"latL":0,"lngL":0,"org":null},{"amount":2000,"qe":5400,"sgq":0.4,"xDu":0,"yDu":0,"xRu":0,"yRu":0,"xUu":0,"yUu":0,"xLu":0,"yLu":0,"h":0,"id":0,"name":"甲煤场南侧2","latD":0,"lngD":0,"status":0,"acctTime":null,"acctUser":0,"updateTime":null,"belong":null,"xD":58.67950854438371,"yD":176.2507836990596,"xR":173.47764414663928,"yR":176.2507836990596,"xU":173.47764414663928,"yU":97.44897248345528,"xL":58.67950854438371,"yL":97.44897248345528,"latR":0,"lngR":0,"latU":0,"lngU":0,"latL":0,"lngL":0,"org":null},{"amount":2000,"qe":5400,"sgq":0.4,"xDu":0,"yDu":0,"xRu":0,"yRu":0,"xUu":0,"yUu":0,"xLu":0,"yLu":0,"h":0,"id":0,"name":"甲煤场北侧3","latD":0,"lngD":0,"status":0,"acctTime":null,"acctUser":0,"updateTime":null,"belong":null,"xD":177.89295705441833,"yD":70.29745733194011,"xR":288.2757797488948,"yR":70.29745733194011,"xU":288.2757797488948,"yU":5.383490073145247,"xL":177.89295705441833,"yL":5.383490073145247,"latR":0,"lngR":0,"latU":0,"lngU":0,"latL":0,"lngL":0,"org":null},{"amount":5000,"qe":4230,"sgq":2.55,"xDu":0,"yDu":0,"xRu":0,"yRu":0,"xUu":0,"yUu":0,"xLu":0,"yLu":0,"h":0,"id":0,"name":"甲煤场北侧2","latD":0,"lngD":0,"status":0,"acctTime":null,"acctUser":0,"updateTime":null,"belong":null,"xD":102.8326376221743,"yD":70.29745733194011,"xR":173.47764414663928,"yR":70.29745733194011,"xU":173.47764414663928,"yU":5.383490073145247,"xL":102.8326376221743,"yL":5.383490073145247,"latR":0,"lngR":0,"latU":0,"lngU":0,"latL":0,"lngL":0,"org":null}]
     */

    private McBean mc;
    private CoalDataBean coalData;
    private List<YlListBean> ylList;
    private List<DouljBean> doulj;
    private List<MdListBean> mdList;

    public McBean getMc() {
        return mc;
    }

    public void setMc(McBean mc) {
        this.mc = mc;
    }

    public CoalDataBean getCoalData() {
        return coalData;
    }

    public void setCoalData(CoalDataBean coalData) {
        this.coalData = coalData;
    }

    public List<YlListBean> getYlList() {
        return ylList;
    }

    public void setYlList(List<YlListBean> ylList) {
        this.ylList = ylList;
    }

    public List<DouljBean> getDoulj() {
        return doulj;
    }

    public void setDoulj(List<DouljBean> doulj) {
        this.doulj = doulj;
    }

    public List<MdListBean> getMdList() {
        return mdList;
    }

    public void setMdList(List<MdListBean> mdList) {
        this.mdList = mdList;
    }

    public static class McBean {
        /**
         * id : 1
         * name : 一期
         * maxLat : 40.069522
         * maxLng : 113.198042
         * status : 1
         * acctTime : 2017-05-09
         * acctUser : 0
         * updateTime : 2017-05-09
         * org : 1
         * xD : 0.0
         * yD : 224.00000000000009
         * xR : 296.26749611197494
         * yR : 224.00000000000009
         * xU : 296.26749611197494
         * yU : 0.0
         * xL : 0.0
         * yL : 0.0
         * minLat : 40.066651
         * minLng : 113.191332
         * url : null
         */

        private int id;
        private String name;
        private double maxLat;
        private double maxLng;
        private int status;
        private String acctTime;
        private int acctUser;
        private String updateTime;
        private String org;
        private double xD;
        private double yD;
        private double xR;
        private double yR;
        private double xU;
        private double yU;
        private double xL;
        private double yL;
        private double minLat;
        private double minLng;
        private Object url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getMaxLat() {
            return maxLat;
        }

        public void setMaxLat(double maxLat) {
            this.maxLat = maxLat;
        }

        public double getMaxLng() {
            return maxLng;
        }

        public void setMaxLng(double maxLng) {
            this.maxLng = maxLng;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAcctTime() {
            return acctTime;
        }

        public void setAcctTime(String acctTime) {
            this.acctTime = acctTime;
        }

        public int getAcctUser() {
            return acctUser;
        }

        public void setAcctUser(int acctUser) {
            this.acctUser = acctUser;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getOrg() {
            return org;
        }

        public void setOrg(String org) {
            this.org = org;
        }

        public double getXD() {
            return xD;
        }

        public void setXD(double xD) {
            this.xD = xD;
        }

        public double getYD() {
            return yD;
        }

        public void setYD(double yD) {
            this.yD = yD;
        }

        public double getXR() {
            return xR;
        }

        public void setXR(double xR) {
            this.xR = xR;
        }

        public double getYR() {
            return yR;
        }

        public void setYR(double yR) {
            this.yR = yR;
        }

        public double getXU() {
            return xU;
        }

        public void setXU(double xU) {
            this.xU = xU;
        }

        public double getYU() {
            return yU;
        }

        public void setYU(double yU) {
            this.yU = yU;
        }

        public double getXL() {
            return xL;
        }

        public void setXL(double xL) {
            this.xL = xL;
        }

        public double getYL() {
            return yL;
        }

        public void setYL(double yL) {
            this.yL = yL;
        }

        public double getMinLat() {
            return minLat;
        }

        public void setMinLat(double minLat) {
            this.minLat = minLat;
        }

        public double getMinLng() {
            return minLng;
        }

        public void setMinLng(double minLng) {
            this.minLng = minLng;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }
    }

    public static class CoalDataBean {
        /**
         * 二期 : 74883.1
         * 三期 : 131874.79
         * 一期 : 264276.4
         */

        private String 二期;
        private String 三期;
        private String 一期;

        public String get二期() {
            return 二期;
        }

        public void set二期(String 二期) {
            this.二期 = 二期;
        }

        public String get三期() {
            return 三期;
        }

        public void set三期(String 三期) {
            this.三期 = 三期;
        }

        public String get一期() {
            return 一期;
        }

        public void set一期(String 一期) {
            this.一期 = 一期;
        }
    }

    public static class YlListBean {
        /**
         * NAME : 信标1
         * YARD : 汽车沟
         * LOCATION_X : 24
         * LOCATION_Y : 4
         * HEIGHT : 20
         * FLAG : 0
         * WIDTH : 200
         */

        private String NAME;
        private String YARD;
        private int LOCATION_X;
        private int LOCATION_Y;
        private int HEIGHT;
        private int FLAG;
        private int WIDTH;

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getYARD() {
            return YARD;
        }

        public void setYARD(String YARD) {
            this.YARD = YARD;
        }

        public int getLOCATION_X() {
            return LOCATION_X;
        }

        public void setLOCATION_X(int LOCATION_X) {
            this.LOCATION_X = LOCATION_X;
        }

        public int getLOCATION_Y() {
            return LOCATION_Y;
        }

        public void setLOCATION_Y(int LOCATION_Y) {
            this.LOCATION_Y = LOCATION_Y;
        }

        public int getHEIGHT() {
            return HEIGHT;
        }

        public void setHEIGHT(int HEIGHT) {
            this.HEIGHT = HEIGHT;
        }

        public int getFLAG() {
            return FLAG;
        }

        public void setFLAG(int FLAG) {
            this.FLAG = FLAG;
        }

        public int getWIDTH() {
            return WIDTH;
        }

        public void setWIDTH(int WIDTH) {
            this.WIDTH = WIDTH;
        }
    }

    public static class DouljBean {
        /**
         * course : null
         * lng : 54.26419563660465
         * courseAngle : 15
         * lat : 192.6353187042843
         */

        private Object course;
        private double lng;
        private double courseAngle;
        private double lat;

        public Object getCourse() {
            return course;
        }

        public void setCourse(Object course) {
            this.course = course;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getCourseAngle() {
            return courseAngle;
        }

        public void setCourseAngle(double courseAngle) {
            this.courseAngle = courseAngle;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }

    public static class MdListBean {
        /**
         * amount : 5000.0
         * qe : 5300.0
         * sgq : 0.45
         * xDu : 0.0
         * yDu : 0.0
         * xRu : 0.0
         * yRu : 0.0
         * xUu : 0.0
         * yUu : 0.0
         * xLu : 0.0
         * yLu : 0.0
         * h : 0.0
         * id : 0
         * name : 甲煤场南侧1
         * latD : 0.0
         * lngD : 0.0
         * status : 0
         * acctTime : null
         * acctUser : 0
         * updateTime : null
         * belong : null
         * xD : 10.111066558814047
         * yD : 176.2507836990596
         * xR : 54.26419563660465
         * yR : 176.2507836990596
         * xU : 54.26419563660465
         * yU : 97.44897248345528
         * xL : 10.111066558814047
         * yL : 97.44897248345528
         * latR : 0.0
         * lngR : 0.0
         * latU : 0.0
         * lngU : 0.0
         * latL : 0.0
         * lngL : 0.0
         * org : null
         */

        private double amount;
        private double qe;
        private double sgq;
        private double xDu;
        private double yDu;
        private double xRu;
        private double yRu;
        private double xUu;
        private double yUu;
        private double xLu;
        private double yLu;
        private double h;
        private int id;
        private String name;
        private double latD;
        private double lngD;
        private int status;
        private Object acctTime;
        private int acctUser;
        private Object updateTime;
        private Object belong;
        private double xD;
        private double yD;
        private double xR;
        private double yR;
        private double xU;
        private double yU;
        private double xL;
        private double yL;
        private double latR;
        private double lngR;
        private double latU;
        private double lngU;
        private double latL;
        private double lngL;
        private Object org;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getQe() {
            return qe;
        }

        public void setQe(double qe) {
            this.qe = qe;
        }

        public double getSgq() {
            return sgq;
        }

        public void setSgq(double sgq) {
            this.sgq = sgq;
        }

        public double getXDu() {
            return xDu;
        }

        public void setXDu(double xDu) {
            this.xDu = xDu;
        }

        public double getYDu() {
            return yDu;
        }

        public void setYDu(double yDu) {
            this.yDu = yDu;
        }

        public double getXRu() {
            return xRu;
        }

        public void setXRu(double xRu) {
            this.xRu = xRu;
        }

        public double getYRu() {
            return yRu;
        }

        public void setYRu(double yRu) {
            this.yRu = yRu;
        }

        public double getXUu() {
            return xUu;
        }

        public void setXUu(double xUu) {
            this.xUu = xUu;
        }

        public double getYUu() {
            return yUu;
        }

        public void setYUu(double yUu) {
            this.yUu = yUu;
        }

        public double getXLu() {
            return xLu;
        }

        public void setXLu(double xLu) {
            this.xLu = xLu;
        }

        public double getYLu() {
            return yLu;
        }

        public void setYLu(double yLu) {
            this.yLu = yLu;
        }

        public double getH() {
            return h;
        }

        public void setH(double h) {
            this.h = h;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getLatD() {
            return latD;
        }

        public void setLatD(double latD) {
            this.latD = latD;
        }

        public double getLngD() {
            return lngD;
        }

        public void setLngD(double lngD) {
            this.lngD = lngD;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getAcctTime() {
            return acctTime;
        }

        public void setAcctTime(Object acctTime) {
            this.acctTime = acctTime;
        }

        public int getAcctUser() {
            return acctUser;
        }

        public void setAcctUser(int acctUser) {
            this.acctUser = acctUser;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getBelong() {
            return belong;
        }

        public void setBelong(Object belong) {
            this.belong = belong;
        }

        public double getXD() {
            return xD;
        }

        public void setXD(double xD) {
            this.xD = xD;
        }

        public double getYD() {
            return yD;
        }

        public void setYD(double yD) {
            this.yD = yD;
        }

        public double getXR() {
            return xR;
        }

        public void setXR(double xR) {
            this.xR = xR;
        }

        public double getYR() {
            return yR;
        }

        public void setYR(double yR) {
            this.yR = yR;
        }

        public double getXU() {
            return xU;
        }

        public void setXU(double xU) {
            this.xU = xU;
        }

        public double getYU() {
            return yU;
        }

        public void setYU(double yU) {
            this.yU = yU;
        }

        public double getXL() {
            return xL;
        }

        public void setXL(double xL) {
            this.xL = xL;
        }

        public double getYL() {
            return yL;
        }

        public void setYL(double yL) {
            this.yL = yL;
        }

        public double getLatR() {
            return latR;
        }

        public void setLatR(double latR) {
            this.latR = latR;
        }

        public double getLngR() {
            return lngR;
        }

        public void setLngR(double lngR) {
            this.lngR = lngR;
        }

        public double getLatU() {
            return latU;
        }

        public void setLatU(double latU) {
            this.latU = latU;
        }

        public double getLngU() {
            return lngU;
        }

        public void setLngU(double lngU) {
            this.lngU = lngU;
        }

        public double getLatL() {
            return latL;
        }

        public void setLatL(double latL) {
            this.latL = latL;
        }

        public double getLngL() {
            return lngL;
        }

        public void setLngL(double lngL) {
            this.lngL = lngL;
        }

        public Object getOrg() {
            return org;
        }

        public void setOrg(Object org) {
            this.org = org;
        }
    }
}
