package com.three_eung.saemoi.bind;

import android.view.View;

public class DailyBind {
    private final String inex;
    private final String value;
    private final String memo;
    private final String cate;
    private final View.OnClickListener deleteListener;
    private final View.OnClickListener modifyListener;

    public DailyBind(String inex, String value, String memo, String cate, View.OnClickListener deleteListener, View.OnClickListener modifyListener) {
        this.inex = inex;
        this.value = value;
        this.memo = memo;
        this.cate = cate;
        this.deleteListener = deleteListener;
        this.modifyListener = modifyListener;
    }

    public String getValue() {
        return value;
    }

    public String getMemo() {
        return memo;
    }

    public View.OnClickListener getDeleteListener() {
        return deleteListener;
    }

    public View.OnClickListener getModifyListener() {
        return modifyListener;
    }

    public String getCate() {
        return cate;
    }

    public String getInex() {
        return inex;
    }
}
