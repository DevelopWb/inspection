package com.juntai.wisdom.inspection.bean.firecheck;

/**
 * @Author: tobato
 * @Description: 作用描述  适配器中 不合格问题item的实体
 * @CreateDate: 2021/5/22 10:37
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/22 10:37
 */
public class UnQuailityFormBean {

    private  String problems;//表格  选择的问题json（如下）
    private  String otherProblem;//其他问题（第11项）
    private  String concreteProblems;//	具体问题（备注）
    private  String itemOne;//问题项目1；例：（1,2）
    private  String itemOneTime;//	到期时间1
    private  String itemTwo;//问题项目1；例：（1,2）
    private  String itemTwoTime;//	到期时间1

    public UnQuailityFormBean(String problems, String otherProblem, String concreteProblems, String itemOne,
                              String itemOneTime, String itemTwo, String itemTwoTime) {
        this.problems = problems;
        this.otherProblem = otherProblem;
        this.concreteProblems = concreteProblems;
        this.itemOne = itemOne;
        this.itemOneTime = itemOneTime;
        this.itemTwo = itemTwo;
        this.itemTwoTime = itemTwoTime;
    }

    public String getProblems() {
        return problems == null ? "" : problems;
    }

    public void setProblems(String problems) {
        this.problems = problems == null ? "" : problems;
    }

    public String getOtherProblem() {
        return otherProblem == null ? "" : otherProblem;
    }

    public void setOtherProblem(String otherProblem) {
        this.otherProblem = otherProblem == null ? "" : otherProblem;
    }

    public String getConcreteProblems() {
        return concreteProblems == null ? "" : concreteProblems;
    }

    public void setConcreteProblems(String concreteProblems) {
        this.concreteProblems = concreteProblems == null ? "" : concreteProblems;
    }

    public String getItemOne() {
        return itemOne == null ? "" : itemOne;
    }

    public void setItemOne(String itemOne) {
        this.itemOne = itemOne == null ? "" : itemOne;
    }

    public String getItemTwo() {
        return itemTwo == null ? "" : itemTwo;
    }

    public void setItemTwo(String itemTwo) {
        this.itemTwo = itemTwo == null ? "" : itemTwo;
    }

    public String getItemOneTime() {
        return itemOneTime == null ? "" : itemOneTime;
    }

    public void setItemOneTime(String itemOneTime) {
        this.itemOneTime = itemOneTime == null ? "" : itemOneTime;
    }

    public String getItemTwoTime() {
        return itemTwoTime == null ? "" : itemTwoTime;
    }

    public void setItemTwoTime(String itemTwoTime) {
        this.itemTwoTime = itemTwoTime == null ? "" : itemTwoTime;
    }
}
