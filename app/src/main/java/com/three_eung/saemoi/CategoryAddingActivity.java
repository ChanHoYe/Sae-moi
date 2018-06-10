package com.three_eung.saemoi;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.GridHolder;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.three_eung.saemoi.databinding.ActivityCategoryAddingBinding;

public class CategoryAddingActivity extends AppCompatActivity {
    private int selected;
    private ActivityCategoryAddingBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = (ActivityCategoryAddingBinding) DataBindingUtil.setContentView(this, R.layout.activity_category_adding);

        setSupportActionBar(mBinding.cateAddToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("카테고리 추가");

        mBinding.cateAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCategoryIcon();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_save:
                if (!TextUtils.isEmpty(mBinding.cateAddName.getText().toString()) && selected != 0) {
                    DatabaseReference mRef = InitApp.sDatabase.getReference("users").child(InitApp.sUser.getUid()).child("info").child("category");
                    if (mBinding.cateAddIn.isChecked())
                        mRef.child("in").child(mBinding.cateAddName.getText().toString()).setValue(selected);
                    if (mBinding.cateAddEx.isChecked())
                        mRef.child("ex").child(mBinding.cateAddName.getText().toString()).setValue(selected);
                    Toast.makeText(getApplicationContext(), "카테고리가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                } else if (selected == 0) {
                    Toast.makeText(getApplicationContext(), "이미지를 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "카테고리 이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showCategoryIcon() { // 다이알로그 보여주는 부분
        DialogPlus dialogPlus = DialogPlus.newDialog(this)
                .setContentHolder(new GridHolder(3))
                .setAdapter(new CategoryAdapter(this, 59))
                .setContentBackgroundResource(R.color.pink_background)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        mBinding.cateAddImage.setImageResource(Utils.getLargeCategoryDrawable(position+1));
                        selected = position+1;
                        dialog.dismiss();
                    }
                })
                .setExpanded(false)
                .create();
        dialogPlus.show();
    }

    class CategoryAdapter extends BaseAdapter {
        private Context mContext;
        private int itemCount;


        public CategoryAdapter(Context context, int count) {
            this.mContext = context;
            this.itemCount = count;
        }

        @Override
        public int getCount() {
            return itemCount;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_add_category, parent, false);

                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.cateImg.setImageResource(Utils.getLargeCategoryDrawable(position + 1));
            viewHolder.cateText.setVisibility(View.GONE);

            return convertView;
        }

        private class ViewHolder {
            private ImageView cateImg;
            private TextView cateText;

            ViewHolder(View v) {
                cateImg = v.findViewById(R.id.item_add_image);
                cateText = v.findViewById(R.id.item_add_title);
            }
        }
    }
}