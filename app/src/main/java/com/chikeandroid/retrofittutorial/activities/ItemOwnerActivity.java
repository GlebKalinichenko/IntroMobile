package com.chikeandroid.retrofittutorial.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.chikeandroid.retrofittutorial.R;
import com.chikeandroid.retrofittutorial.data.model.Item;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemOwnerActivity extends AppCompatActivity {
    public static final String OWNER_DATA = "owner_data";
    @BindView(R.id.image_owner)
    public ImageView mOwnerImage;
    @BindView(R.id.text_owner)
    public TextView mOwnerNameText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_owner);
        ButterKnife.bind(this);
        initVars();
    }

    private void initVars() {
        Item item = (Item) getIntent().getParcelableExtra(OWNER_DATA);
        String name = item.getOwner().getDisplayName();
        String url = item.getOwner().getProfileImage();

        Picasso.with(this).load(url).into(mOwnerImage);
        mOwnerNameText.setText(name);
    }
}
