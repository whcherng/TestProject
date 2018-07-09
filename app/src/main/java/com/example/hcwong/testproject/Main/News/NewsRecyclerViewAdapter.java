package com.example.hcwong.testproject.Main.News;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hcwong.testproject.Model.Article;
import com.example.hcwong.testproject.NewsDetails.NewsDetails;
import com.example.hcwong.testproject.R;
import com.example.hcwong.testproject.shared.GeneralUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Article} and makes a call to the
 * specified {@link NewsFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    private List<Article> mValues;
    private final NewsFragment.OnListFragmentInteractionListener mListener;

    public NewsRecyclerViewAdapter(List<Article> items, NewsFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        GeneralUtil.loadImgToView(mValues.get(position).getUrlToImage(),holder.img);
        holder.txtTitle.setText(mValues.get(position).getTitle());
        holder.txtDescription.setText(mValues.get(position).getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    Intent intent= new Intent(v.getContext(), NewsDetails.class);
                    intent.putExtra("Title",mValues.get(position).getTitle());
                    intent.putExtra("Description",mValues.get(position).getDescription());
                    intent.putExtra("Image",mValues.get(position).getUrlToImage());
                    v.getContext().startActivity(intent);
                }
            }
        });
    }


    public void updateList(List<Article> articles){
        this.mValues = articles;
        this.notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        @BindView(R.id.imgThumbnail)
        ImageView img;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_description)
        TextView txtDescription;

        public Article mItem;

        public ViewHolder(View view) {
            super(view);
            mView=view;
            ButterKnife.bind(this,view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtDescription.getText() + "'";
        }
    }
}
