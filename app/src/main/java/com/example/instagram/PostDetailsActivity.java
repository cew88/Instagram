package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.Date;

public class PostDetailsActivity extends AppCompatActivity {
    private TextView mTvUsername;
    private ImageView mIvImage;
    private TextView mTvDescription;
    private TextView mTvTimeStamp;
    private Post mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        mPost = (Post) Parcels.unwrap(getIntent().getParcelableExtra("Post"));

        mTvUsername = findViewById(R.id.tvUsername);
        mIvImage = findViewById(R.id.ivImage);
        mTvDescription = findViewById(R.id.tvDescription);
        mTvTimeStamp = findViewById(R.id.tvTimeStamp);

        mTvDescription.setText(mPost.getDescription());
        mTvUsername.setText(mPost.getUser().getUsername());
        ParseFile image = mPost.getImage();
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(mIvImage);
        }
        mTvTimeStamp.setText(mPost.calculateTimeAgo(mPost.getCreatedAt()));

    }



}