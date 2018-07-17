package com.example.hcwong.testproject.Main.News;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.hcwong.testproject.Base.BaseMvpFragment;
import com.example.hcwong.testproject.DI.component.AppComponent;
import com.example.hcwong.testproject.Model.Article;
import com.example.hcwong.testproject.R;
import com.example.hcwong.testproject.shared.GeneralUtil;
import com.example.hcwong.testproject.NewsApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NewsFragment extends BaseMvpFragment<NewsPresenter> implements NewsContract.View{

    private Context mContext;
    private List<Article> listOfArticles;
    private NewsRecyclerViewAdapter adapter;

    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.progressLayout)
    LinearLayout progressLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public NewsFragment() {
    }

    public static NewsFragment newInstance(){
        return new NewsFragment();
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NewsFragment newInstance(int columnCount) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_recycler, container, false);
        ButterKnife.bind(this, view);
        initInject(NewsApplication.get(getContext())
                .getAppComponent());
        mvpPresenter.start();
        return view;
    }

    @Override
    protected void initInject(AppComponent appComponent) {
        appComponent.addSub(new NewsModule(this))
                .inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void initView() {
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, mColumnCount));
        }
        listOfArticles=new ArrayList<>();
    }

    @Override
    public void renderView() {
        adapter=new NewsRecyclerViewAdapter(listOfArticles, mListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateView() {
        if(adapter!=null)
            adapter.updateList(getNewsList());

    }

    @Override
    public void initListener() {

    }

    @Override
    public void showEmptyState() {


    }

    @Override
    public void hideEmptyState() {

    }

    @Override
    public void updateProgressBar(Integer pb) {
        progressBar.incrementProgressBy(pb);
    }

    @Override
    public void showLoading() {
        if(!progressLayout.isShown())
            progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if(progressLayout.isShown())
            progressLayout.setVisibility(View.GONE);
    }


    @Override
    public void setNewsList(List<Article> newsList) {
        this.listOfArticles=newsList;
    }

    @Override
    public List<Article> getNewsList() {
        return this.listOfArticles;
    }


    @Override
    public void hideKeyboard() {
        GeneralUtil.hideKeyboard(mContext,getActivity().getCurrentFocus());
    }

    @Override
    public void showToastMessage(int resId) {
        GeneralUtil.toastLong(resId);
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mvpPresenter=(NewsPresenter) presenter;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Article item);
    }

}
