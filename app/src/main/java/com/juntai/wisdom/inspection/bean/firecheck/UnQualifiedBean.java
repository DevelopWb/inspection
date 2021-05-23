package com.juntai.wisdom.inspection.bean.firecheck;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  消防检查中  有问题时 表单的实体类
 *
 * @CreateDate: 2021/5/21 16:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/21 16:23
 */
public class UnQualifiedBean  {


    /**
     * itemid : 1
     * head : 1.未依法进行
     * middle :
     * child : [{"selectStatus":0,"name":"消防涉及备案"},{"selectStatus":1,"name":"竣工验收"}]
     * tail :
     */

    private int itemid;
    private String head;
    private String middle;
    private String tail;
    private List<ChildBean> child;

    public UnQualifiedBean(int itemid, String head, String middle, String tail, List<ChildBean> child) {
        this.itemid = itemid;
        this.head = head;
        this.middle = middle;
        this.tail = tail;
        this.child = child;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    public List<ChildBean> getChild() {
        return child;
    }

    public void setChild(List<ChildBean> child) {
        this.child = child;
    }

    public static class ChildBean {
        /**
         * selectStatus : 0
         * name : 消防涉及备案
         */

        private int selectStatus;
        private String name;

        public int getSelectStatus() {
            return selectStatus;
        }

        public void setSelectStatus(int selectStatus) {
            this.selectStatus = selectStatus;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
