package com.juntai.wisdom.inspection.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/6/1 16:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/1 16:08
 */
public class MyWorkRecordsBean extends BaseResult {


    /**
     * error : null
     * data : {"datas":[{"id":427,"typeId":3,"name":"测试",
     * "coverPicture":"/key_personnel/c9b2cb51592448b48e6f46e1dd4977c7.jpeg","address":"城开",
     * "gpsAddress":"中国山东省临沂市河东区九曲街道人民大街中段","longitude":"118.402085","latitude":"35.090641"},{"id":51,"typeId":1,
     * "name":"临沂市兰山区翟文翔服装店","coverPicture":"/unit_picture/66f0359e538d4288b9ec6b6e50d83806.jpeg",
     * "address":"山东省临沂市兰山区方城镇东石桥村","gpsAddress":"中国山东省临沂市河东区九曲街道人民大街中段","longitude":"118.402103","latitude":"35
     * .090522"},{"id":5,"typeId":2,"name":"临沂市兰山区耀东快餐店",
     * "coverPicture":"/security_inspection/95adf4a607ce47baa3402abe998f82fd.jpeg","address":"山东省临沂市兰山区方城镇同利村",
     * "gpsAddress":"中国山东省临沂市河东区九曲街道人民大街中段","longitude":"118.403085","latitude":"35.090764"}],"total":15,
     * "listSize":10,"pageCount":2}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * datas : [{"id":427,"typeId":3,"name":"测试","coverPicture":"/key_personnel/c9b2cb51592448b48e6f46e1dd4977c7
         * .jpeg","address":"城开","gpsAddress":"中国山东省临沂市河东区九曲街道人民大街中段","longitude":"118.402085","latitude":"35.090641"
         * },{"id":51,"typeId":1,"name":"临沂市兰山区翟文翔服装店","coverPicture":"/unit_picture/66f0359e538d4288b9ec6b6e50d83806
         * .jpeg","address":"山东省临沂市兰山区方城镇东石桥村","gpsAddress":"中国山东省临沂市河东区九曲街道人民大街中段","longitude":"118.402103",
         * "latitude":"35.090522"},{"id":5,"typeId":2,"name":"临沂市兰山区耀东快餐店",
         * "coverPicture":"/security_inspection/95adf4a607ce47baa3402abe998f82fd.jpeg","address":"山东省临沂市兰山区方城镇同利村",
         * "gpsAddress":"中国山东省临沂市河东区九曲街道人民大街中段","longitude":"118.403085","latitude":"35.090764"}]
         * total : 15
         * listSize : 10
         * pageCount : 2
         */

        private int total;
        private int listSize;
        private int pageCount;
        private List<DatasBean> datas;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getListSize() {
            return listSize;
        }

        public void setListSize(int listSize) {
            this.listSize = listSize;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * id : 427
             * typeId : 3
             * name : 测试
             * coverPicture : /key_personnel/c9b2cb51592448b48e6f46e1dd4977c7.jpeg
             * address : 城开
             * gpsAddress : 中国山东省临沂市河东区九曲街道人民大街中段
             * longitude : 118.402085
             * latitude : 35.090641
             */

            private int id;
            private int typeId;
            private String name;
            private String coverPicture;
            private String address;
            private String gpsAddress;
            private String longitude;
            private String latitude;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCoverPicture() {
                return coverPicture;
            }

            public void setCoverPicture(String coverPicture) {
                this.coverPicture = coverPicture;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getGpsAddress() {
                return gpsAddress;
            }

            public void setGpsAddress(String gpsAddress) {
                this.gpsAddress = gpsAddress;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }
        }
    }
}
