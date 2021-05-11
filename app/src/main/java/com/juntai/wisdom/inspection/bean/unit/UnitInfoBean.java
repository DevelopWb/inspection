package com.juntai.wisdom.inspection.bean.unit;

import android.os.Parcel;
import android.os.Parcelable;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/11 11:16
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/11 11:16
 */
public class UnitInfoBean extends BaseResult {

    /**
     * error : null
     * data : {"id":35,"version":0,"name":"沃尔玛111","typeName":"超市","address":"美国阿肯色州本顿维尔111",
     * "unifiedCreditCode":"92371302MA3N0JQY9X","legal":"罗伯森·沃尔顿111","legalPhone":"15266666666",
     * "personLiable":"董明伦111","liablePhone":"18288888888","sparePerson":"杜克111","sparePhone":"13299999999",
     * "remarks":"这是个版本测试","gpsAddress":"临沂市河东区九曲街道人民大街111号","longitude":"118.265226","latitude":"35.6265426",
     * "coverPicture":"/unit_picture/acb4c354ada14145b0a7509cef554108.jpeg",
     * "photoTwo":"/unit_picture/a083222f2c4c4210ab819c33b02d15dc.jpeg",
     * "photoThree":"/unit_picture/7710a90900a04994813f3c8a366502db.jpeg","photoFour":null,"photoFive":null,
     * "photoSix":null,"qrCode":"/unit_qr_code/c6b01fc5b3d7446a956f00fc9c1e6482.jpeg","state":1}
     */

    private SearchedUnitsBean.DataBean.DatasBean data;

    public SearchedUnitsBean.DataBean.DatasBean getData() {
        return data==null?new SearchedUnitsBean.DataBean.DatasBean():data ;
    }

    public void setData(SearchedUnitsBean.DataBean.DatasBean data) {
        this.data = data;
    }
}
