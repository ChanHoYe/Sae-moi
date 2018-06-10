package com.three_eung.saemoi;

import android.support.annotation.NonNull;

import com.three_eung.saemoi.infos.HousekeepInfo;
import com.three_eung.saemoi.infos.SavingInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class Events {
    private final ArrayList<HousekeepInfo> mHkList;
    private final ArrayList<SavingInfo> mSvList;
    private final HashMap<String, Integer> mBudget;

    public Events(@NonNull ArrayList<HousekeepInfo> mHkList, @NonNull ArrayList<SavingInfo> mSvList, HashMap<String, Integer> mBudget) {
        this.mHkList = mHkList;
        this.mSvList = mSvList;
        this.mBudget = mBudget;
    }

    public ArrayList<HousekeepInfo> getHkList() {
        return mHkList;
    }

    public ArrayList<SavingInfo> getSvList() {
        return mSvList;
    }

    public HashMap<String, Integer> getBudget() {
        return mBudget;
    }
}
