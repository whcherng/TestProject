package com.example.hcwong.testproject.NewsDetails;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hcwong.testproject.R;
import com.example.hcwong.testproject.shared.GeneralUtil;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetails extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.txt_desc)
    TextView txtDesc;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_share)
    FloatingActionButton btn_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        String title;
        final String description;
        String urlToImage;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras !=null){
                title= extras.getString("Title");
                getSupportActionBar().setTitle(title);
                description= extras.getString("Description");
                //txtDesc.setText(description);
                urlToImage=extras.getString("Image");
                Picasso.get().load(urlToImage).fit().into(imageView);
            }
        }

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = txtDesc.getText().toString();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share News");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,shareBody );
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });

    }
}
