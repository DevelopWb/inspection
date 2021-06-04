package com.juntai.wisdom.inspection.bean.search;


import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  搜索更多的结果
 * @date 2021/6/4 15:07
 */
public class SearchResultBean extends BaseResult {

    /**
     * error : null
     * data : {"datas":[{"id":51,"name":"临沂市兰山区翟文翔服装店","content":"山东省临沂市兰山区方城镇东石桥村",
     * "coverPicture":"/unit_picture/66f0359e538d4288b9ec6b6e50d83806.jpeg","gpsAddress":"中国山东省临沂市河东区九曲街道人民大街中段",
     * "longitude":"118.402103","latitude":"35.090522"}],"total":1,"listSize":1,"pageCount":5}
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
         * datas : [{"id":51,"name":"临沂市兰山区翟文翔服装店","content":"山东省临沂市兰山区方城镇东石桥村",
         * "coverPicture":"/unit_picture/66f0359e538d4288b9ec6b6e50d83806.jpeg","gpsAddress":"中国山东省临沂市河东区九曲街道人民大街中段",
         * "longitude":"118.402103","latitude":"35.090522"}]
         * total : 1
         * listSize : 1
         * pageCount : 5
         */

        private int total;
        private int listSize;
        private int pageCount;
        private List<SearchBean.DataBean.ListBean> datas;

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

        public List<SearchBean.DataBean.ListBean> getDatas() {
            return datas;
        }

        public void setDatas(List<SearchBean.DataBean.ListBean> datas) {
            this.datas = datas;
        }

    }
}