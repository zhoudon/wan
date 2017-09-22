package com.jiyun.qcloud.newkuangjia.mvp;

import com.jiyun.qcloud.newkuangjia.mvp.model.BaseCard;
import com.jiyun.qcloud.newkuangjia.mvp.model.Card;
import com.jiyun.qcloud.newkuangjia.mvp.model.Info;
import com.jiyun.qcloud.newkuangjia.mvp.model.To;

import java.util.List;

/**
 * Created by chj on 2017/8/30.
 */

public interface IView {
    void onComplete();
    void onError();
    public void onError(String error);

    public void onUpdate(BaseCard card);
    void onUpdate(List<Card> card);

    public void onUpData(Info info);

    public void onUpData1(To info);

    public void showProgress();

    public void hideProgress();
}
