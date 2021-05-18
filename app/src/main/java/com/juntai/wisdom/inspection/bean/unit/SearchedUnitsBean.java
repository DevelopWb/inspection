package com.juntai.wisdom.inspection.bean.unit;

import android.os.Parcel;
import android.os.Parcelable;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  搜索到的单位
 * @CreateDate: 2021/5/7 10:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/7 10:11
 */
public class SearchedUnitsBean  extends BaseResult {


    /**
     * error : null
     * data : {"datas":[{"id":33,"userId":0,"name":"国家电网111","address":"中国北京111",
     * "coverPicture":"/unit_picture/af5979e41bcc4de8a58d513b3c186ddf.jpeg","state":1,"isAdd":1}],"total":1,
     * "listSize":1,"pageCount":1}
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
         * datas : [{"id":33,"userId":0,"name":"国家电网111","address":"中国北京111",
         * "coverPicture":"/unit_picture/af5979e41bcc4de8a58d513b3c186ddf.jpeg","state":1,"isAdd":1}]
         * total : 1
         * listSize : 1
         * pageCount : 1
         */

        private int total;
        private int listSize;
        private int pageCount;
        private List<UnitDetailBean.DataBean> datas;

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

        public List<UnitDetailBean.DataBean> getDatas() {
            return datas;
        }

        public void setDatas(List<UnitDetailBean.DataBean> datas) {
            this.datas = datas;
        }

    }
}
