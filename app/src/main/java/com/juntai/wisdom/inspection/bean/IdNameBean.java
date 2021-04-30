package com.juntai.wisdom.inspection.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  只id和name
 * @CreateDate: 2021/4/29 17:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/29 17:30
 */
public class IdNameBean  extends BaseResult {


    /**
     * error : null
     * data : [{"id":1,"name":"板厂"},{"id":2,"name":"皮厂"},{"id":3,"name":"幼儿园"},{"id":4,"name":"超市"}]
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
         * id : 1
         * name : 板厂
         */

        private int id;
        private String name;

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
    }
}
