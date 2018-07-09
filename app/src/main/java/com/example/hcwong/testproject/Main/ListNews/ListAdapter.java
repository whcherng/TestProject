package com.example.hcwong.testproject.Main.ListNews;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hcwong.testproject.Model.Article;
import com.example.hcwong.testproject.R;
import com.example.hcwong.testproject.shared.GeneralUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Hau Cherng on 12/07/2016.
 */

//CustomerAdapter for Customer ListView
public class ListAdapter extends ArrayAdapter<Article> {
    private List<Article> filteredList;
    private List<Article> articleList;
    private ArticleFilter articleFilter;
    private String criteria ="Title";



    public ListAdapter(Context context, List<Article> customers)
    {
        super(context,0,customers);
        this.filteredList=customers;
        this.articleList =customers;
        getFilter();
    }

    @Override
    public Article getItem(int i) {
        return filteredList.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        //Get the data item for this position
        Article customer=getItem(position);
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_adapter, parent, false);

            holder = new ViewHolder();
            holder.title =  convertView.findViewById(R.id.txt_title);
            holder.description = convertView.findViewById(R.id.txt_description);
            holder.date = convertView.findViewById(R.id.txt_published_at);
            holder.image =  convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder) convertView.getTag();
        }

        GeneralUtil.loadImgToView(customer.getUrlToImage(),holder.image);
        holder.title.setText(customer.getTitle());
        holder.title.setMaxLines(1);
        holder.title.setEllipsize(TextUtils.TruncateAt.END);
        holder.title.setFadingEdgeLength(13);
        holder.description.setText(customer.getDescription());
        holder.description.setMaxLines(2);
        holder.description.setFadingEdgeLength(17);
        holder.description.setEllipsize(TextUtils.TruncateAt.END);
        holder.description.setWidth(250);
        holder.date.setText(customer.getPublishedAt());

        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView description;
        TextView date;
        ImageView image;
    }

    /**
     * Get custom filter
     * @return filter
     */
    @Override
    public int getCount() {
        return filteredList.size();
    }


    @Override
    public Filter getFilter() {
        if ( articleFilter == null) {
            articleFilter = new ArticleFilter();
        }

        return articleFilter;
    }

    public void updateList(List<Article> articles){
        this.articleList = articles;
        this.filteredList=articles;
        this.notifyDataSetChanged();
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    /**
     * Custom filter for friend list
     * Filter content in friend list according to the search text
     */
    private class ArticleFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            constraint=constraint.toString().toLowerCase();
            FilterResults filterResults = new FilterResults();
            if (constraint!=null && constraint.length()>0) {
                ArrayList<Article> tempList = new ArrayList<>();

                // search content in item list
                for (Article article : articleList) {
                    Log.d("asd",getCriteria());
                    if(getCriteria().equals("Tittle")) {
                        if (article.getTitle().toLowerCase().contains(constraint.toString().toLowerCase()))
                            tempList.add(article);
                    }
                    else if(getCriteria().equals("Description")){
                        if (article.getDescription().toLowerCase().contains(constraint.toString().toLowerCase()))
                            tempList.add(article);
                    }
                }
                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = articleList.size();
                filterResults.values = articleList;
            }

            return filterResults;
        }

        /**
         * Notify about filtered list to ui
         * @param constraint text
         * @param results filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (List<Article>) results.values;
            notifyDataSetChanged();
        }
    }
}
