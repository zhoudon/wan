package com.jiyun.qcloud.newkuangjia.mvp;

import com.jiyun.qcloud.newkuangjia.api.IApi;
import com.jiyun.qcloud.newkuangjia.mvp.model.Card;
import com.jiyun.qcloud.newkuangjia.mvp.model.Info;
import com.jiyun.qcloud.newkuangjia.mvp.model.To;
import com.jiyun.qcloud.newkuangjia.util.Util;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chj on 2017/8/30.
 */

public class Presenter {
    private IView mView;
    private static Info mInfo;
    private static To fo;

    @Inject
    IApi mIApi;

    @Inject
    public Presenter(){

    }


    public void setmView(IView mView){
        this.mView=mView;
    }

    public void getInfo(){
//        if (mInfo == null) {
//            mView.showProgress();
//            Observable<Info> observable = mIApi.getInfo();
//            observable.subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new SelfDefineSubscriber<Info>() {
//                        @Override
//                        public void onNext(Info info) {
//                            mInfo = info;
//                            mView.onUpData(mInfo);
//                            mView.hideProgress();
//
//                        }
//                    });
//        }else{
//            mView.hideProgress();
//            mView.onUpData(mInfo);
//        }



        if (fo == null) {
            mView.showProgress();
            Observable<To> observable = mIApi.getyy();
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SelfDefineSubscriber<To>() {
                        @Override
                        public void onNext(To info) {
                            fo = info;
                            mView.onUpData1(fo);
                            mView.hideProgress();

                        }
                    });
        }else{
            mView.hideProgress();
            mView.onUpData1(fo);
        }


    }
/////////////////////////////////////这里是重复的代码
public void getCardSet(String set) {
    mView.showProgress();

    Observable<List<Card>> observable = mIApi.getCardSet(set, getLocal());
    observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SelfDefineSubscriber<List<Card>>() {
                @Override
                public void onNext(List<Card> cards) {
                    mView.onUpdate(cards);
                    mView.hideProgress();

                }
            });
}

    public void getCardByClass(String classes) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardsByClass(classes, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardByFaction(String faction) {
        mView.showProgress();
        Observable<List<Card>> observable = mIApi.getCardByFaction(faction, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardByQuality(String quality) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardByQuality(quality, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardByRace(String Race) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardByRace(Race, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    public void getCardByType(String type) {
        mView.showProgress();

        Observable<List<Card>> observable = mIApi.getCardByType(type, getLocal());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SelfDefineSubscriber<List<Card>>() {
                    @Override
                    public void onNext(List<Card> cards) {
                        mView.onUpdate(cards);
                        mView.hideProgress();

                    }
                });
    }

    //系统语言信息获取
    private String getLocal() {
        if (mInfo != null && mInfo.locales != null) {
            String local = Util.getCurrentLauguage();
            for (String s : mInfo.locales) {
                if (s.contains(local)) {
                    return s;
                }
            }
        }

        return "enUS";
    }



    //自定义的subscribe
    class SelfDefineSubscriber<T> extends Subscriber<T>{

        @Override
        public void onCompleted() {
            mView.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("出现错误"+e);
            mView.onError(e.getMessage());

        }

        @Override
        public void onNext(T t) {

        }
    }
}
