package com.juntai.wisdom.inspection.bean.search;


import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  搜索结果
 * @date 2021/6/4 15:17
 */
public class SearchBean extends BaseResult {


    /**
     * error : null
     * data : [{"name":"消防巡检单位","typeId":1,"list":[{"id":38,"name":"牛牛木业有限公司","content":"兰山区大山路234号",
     * "coverPicture":"/unit_picture/20bf8477ddcd4a5698f4e4cdbe33260a.jpeg","gpsAddress":"孝友路与桃源街交叉口南150米",
     * "longitude":"118.3990107726555","latitude":"35.10416460315092"}]},{"name":"重点人员","typeId":2,"list":[{"id":1,
     * "name":"张牛牛444","content":"sadafwagagag","coverPicture":"/key_personnel/keyPersonnel.png",
     * "gpsAddress":"山东临沂河东区","longitude":"118.6515161","latitude":"35.1561561"}]},{"name":"治安巡检点","typeId":3,
     * "list":[{"id":1,"name":"牛牛村委会11","content":"兰山区大山路2314号2",
     * "coverPicture":"/security_inspection/c38caec3095a4927b49e8405c55dbc72.jpeg","gpsAddress":"临沂市河东区安居街133号",
     * "longitude":"118.40030433258987","latitude":"35.08729353519207"}]}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 消防巡检单位
         * typeId : 1
         * list : [{"id":38,"name":"牛牛木业有限公司","content":"兰山区大山路234号",
         * "coverPicture":"/unit_picture/20bf8477ddcd4a5698f4e4cdbe33260a.jpeg","gpsAddress":"孝友路与桃源街交叉口南150米",
         * "longitude":"118.3990107726555","latitude":"35.10416460315092"}]
         */

        private String name;
        private int typeId;
        private Integer count;//总数
        private List<ListBean> list;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 38
             * name : 牛牛木业有限公司
             * content : 兰山区大山路234号
             * coverPicture : /unit_picture/20bf8477ddcd4a5698f4e4cdbe33260a.jpeg
             * gpsAddress : 孝友路与桃源街交叉口南150米
             * longitude : 118.3990107726555
             * latitude : 35.10416460315092
             */

            private int id;
            private String name;
            private String content;
            private String coverPicture;
            private String gpsAddress;
            private String longitude;
            private String latitude;
            private int resultType;//搜索结果的类型

            public int getResultType() {
                return resultType;
            }

            public void setResultType(int resultType) {
                this.resultType = resultType;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCoverPicture() {
                return coverPicture;
            }

            public void setCoverPicture(String coverPicture) {
                this.coverPicture = coverPicture;
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
