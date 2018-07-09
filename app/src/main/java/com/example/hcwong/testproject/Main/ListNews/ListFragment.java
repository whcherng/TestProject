package com.example.hcwong.testproject.Main.ListNews;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.hcwong.testproject.Model.Article;
import com.example.hcwong.testproject.R;
import com.example.hcwong.testproject.shared.GeneralUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ListFragment extends Fragment implements SearchView.OnQueryTextListener, ListContract.View{

    private Context mContext;
    private ListContract.Presenter mPresenter;
    private List<Article> listOfArticles;
    private ListAdapter adapter;

    @BindView(R.id.list)
    ListView listView;
    @BindView(R.id.progressLayout)
    LinearLayout progressLayout;
    @BindView(R.id.simpleSearchView)
    SearchView searchView;
    @BindView(R.id.spinner)
    Spinner spinner;


    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public ListFragment() {
    }

    public static ListFragment newInstance(){
        return new ListFragment();
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ListFragment newInstance(int columnCount) {
        ListFragment fragment = new ListFragment();
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
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        ButterKnife.bind(this, view);

        mPresenter= new ListPresenter(mContext,this);
        mPresenter.start();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
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
        listOfArticles=new ArrayList<>();
        SearchManager searchManager=(SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        searchView.setFocusable(true);
        searchView.requestFocusFromTouch();
    }

    @Override
    public void renderView() {
        adapter=new ListAdapter(getContext(),listOfArticles);
        listView.setAdapter(adapter);

        ArrayList<String>categories=new ArrayList<>();
        categories.add("Tittle");
        categories.add("Description");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,categories);
        spinner.setAdapter(adapter);
    }

    @Override
    public void updateView() {
        Log.d("Testing",getNewsList().size()+"");
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
    public void setPresenter(ListContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.setCriteria(spinner.getSelectedItem().toString());
        adapter.getFilter().filter(s);
        return true;
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
