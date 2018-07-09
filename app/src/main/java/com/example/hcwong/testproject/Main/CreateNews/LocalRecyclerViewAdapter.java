package com.example.hcwong.testproject.Main.CreateNews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hcwong.testproject.Model.Article;
import com.example.hcwong.testproject.R;
import com.example.hcwong.testproject.shared.GeneralUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Article} and makes a call to the
 * specified {@link LocalFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class LocalRecyclerViewAdapter extends RecyclerView.Adapter<LocalRecyclerViewAdapter.ViewHolder> {

    private List<Article> mValues;
    private final LocalFragment.OnListFragmentInteractionListener mListener;

    public LocalRecyclerViewAdapter(List<Article> items, LocalFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_local, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.txtTitle.setText(mValues.get(position).getTitle());
        holder.txtDescription.setText(mValues.get(position).getDescription());
        holder.txtPublishedAt.setText(mValues.get(position).getPublishedAt());
        holder.txtAuthor.setText(mValues.get(position).getAuthor());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
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
        @BindView(R.id.txt_published_at)
        TextView txtPublishedAt;
        @BindView(R.id.txt_author)
        TextView txtAuthor;

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
