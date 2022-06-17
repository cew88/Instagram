package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    public Context context;
    public List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private ImageView ivHeart;
        private ImageView ivComment;
        private ImageView ivSend;
        private ImageView ivSave;
        private TextView tvLikeCount;
        private TextView tvTimeStamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivHeart = itemView.findViewById(R.id.ivHeart);
            ivComment = itemView.findViewById(R.id.ivComment);
            ivSend = itemView.findViewById(R.id.ivSend);
            ivSave = itemView.findViewById(R.id.ivSave);
            tvLikeCount = itemView.findViewById(R.id.tvLikeCount);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
        }

        public void bind(Post post) {
            // Bind the post data to the view elements
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());

            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), PostDetailsActivity.class);
                    i.putExtra("Post", Parcels.wrap(post));
                    context.startActivity(i);
                }
            });

            tvTimeStamp.setText(post.calculateTimeAgo(post.getCreatedAt()));

//            tvLikeCount.setText("Liked by " + post.getLikedBy().size());
            ivHeart.setImageResource(R.drawable.ufi_heart);
            Log.d("PostAdapter", String.valueOf(post.getLikedBy()));
            if (post.getLikedBy().contains(ParseUser.getCurrentUser())){
                ivHeart.setImageResource(R.drawable.ufi_heart_active);
            }

            ivHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // If already clicked
                    if (post.getLikedBy().contains(ParseUser.getCurrentUser())){
                        try {
                            post.removeLikedBy(ParseUser.getCurrentUser());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        ivHeart.setImageResource(R.drawable.ufi_heart);
                        // Toast.makeText(context, "UNLIKED: " + String.valueOf(post.getLikedBy()), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // If not already clicked
                        post.addLikedBy(ParseUser.getCurrentUser());
                        ivHeart.setImageResource(R.drawable.ufi_heart_active);
                        // Toast.makeText(context, "LIKED: " + String.valueOf(post.getLikedBy()), Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }
}
