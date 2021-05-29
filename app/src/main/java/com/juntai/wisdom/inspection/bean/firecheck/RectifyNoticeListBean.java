package com.juntai.wisdom.inspection.bean.firecheck;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/28 15:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/28 15:55
 */
public class RectifyNoticeListBean extends BaseResult {

    /**
     * error : null
     * data : {"datas":[{"noticeId":1,"recordId":1,"gmtCreate":"2021-05-17"}],"total":1,"listSize":1,"pageCount":1}
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
         * datas : [{"noticeId":1,"recordId":1,"gmtCreate":"2021-05-17"}]
         * total : 1
         * listSize : 1
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
             * noticeId : 1
             * recordId : 1
             * gmtCreate : 2021-05-17
             */

            private int noticeId;
            private int recordId;
            private String gmtCreate;

            public int getNoticeId() {
                return noticeId;
            }

            public void setNoticeId(int noticeId) {
                this.noticeId = noticeId;
            }

            public int getRecordId() {
                return recordId;
            }

            public void setRecordId(int recordId) {
                this.recordId = recordId;
            }

            public String getGmtCreate() {
                return gmtCreate;
            }

            public void setGmtCreate(String gmtCreate) {
                this.gmtCreate = gmtCreate;
            }
        }
    }
}
