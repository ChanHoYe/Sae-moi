package com.three_eung.saemoi.bind;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.three_eung.saemoi.Utils;

public class BudgetBind {
    private final int id;
    private final String title;
    private final String value;

    public BudgetBind(String title, String value, int id) {
        this.title = title;
        this.value = value;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }

    @BindingAdapter("android:src")
    public static void setImage(ImageView image, int id) {
        image.setImageResource(Utils.getLargeCategoryDrawable(id));
    }
}
