package com.juntai.wisdom.inspection.bean.unit;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/21 11:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/21 11:11
 */
public class FireCheckRecordListBean extends BaseResult {


    /**
     * error : null
     * data : {"datas":[{"id":1,"inspectName":"王进喜","inspectTime":"2021-05-17","punish":1},{"id":2,"inspectName
     * ":"王进喜","inspectTime":"2021-05-17","punish":0},{"id":3,"inspectName":"王彬","inspectTime":"2021-05-21",
     * "punish":0}],"total":3,"listSize":3,"pageCount":1}
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
         * datas : [{"id":1,"inspectName":"王进喜","inspectTime":"2021-05-17","punish":1},{"id":2,"inspectName":"王进喜",
         * "inspectTime":"2021-05-17","punish":0},{"id":3,"inspectName":"王彬","inspectTime":"2021-05-21","punish":0}]
         * total : 3
         * listSize : 3
         * pageCount : 1
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
             * id : 1
             * inspectName : 王进喜
             * inspectTime : 2021-05-17
             * punish : 1
             */

            private int id;
            private String inspectName;
            private String inspectTime;
            private int punish;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getInspectName() {
                return inspectName;
            }

            public void setInspectName(String inspectName) {
                this.inspectName = inspectName;
            }

            public String getInspectTime() {
                return inspectTime;
            }

            public void setInspectTime(String inspectTime) {
                this.inspectTime = inspectTime;
            }

            public int getPunish() {
                return punish;
            }

            public void setPunish(int punish) {
                this.punish = punish;
            }
        }
    }
}
