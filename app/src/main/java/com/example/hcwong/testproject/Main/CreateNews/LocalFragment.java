package com.example.hcwong.testproject.Main.CreateNews;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.hcwong.testproject.Model.Article;
import com.example.hcwong.testproject.Model.Source;
import com.example.hcwong.testproject.NewsApplication;
import com.example.hcwong.testproject.R;
import com.example.hcwong.testproject.shared.GeneralUtil;

import java.util.ArrayList;
import java.util.Calendar;
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
public class LocalFragment extends Fragment implements LocalContract.View{

    private Context mContext;
    @Inject
    public LocalPresenter mPresenter;
    private List<Article> listOfArticles;
    private LocalRecyclerViewAdapter adapter;

    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.progressLayout)
    LinearLayout progressLayout;
    @BindView(R.id.edit_title)
    EditText editTitle;
    @BindView(R.id.edit_description)
    EditText editDescription;
    @BindView(R.id.radio_yes)
    RadioButton radioYes;
    @BindView(R.id.save_author)
    CheckBox saveAuthor;
    @BindView(R.id.btn_save)
    Button btnSave;



    private void setupActivityComponent(){
        NewsApplication.get(mContext)
                .getAppComponent()
                .addSub(new LocalModule(this))
                .inject(this);

    }



    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public LocalFragment() {
    }

    public static LocalFragment newInstance(){
        return new LocalFragment();
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static LocalFragment newInstance(int columnCount) {
        LocalFragment fragment = new LocalFragment();
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
        View view = inflater.inflate(R.layout.fragment_local_list, container, false);
        ButterKnife.bind(this, view);
        setupActivityComponent();
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
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, mColumnCount));
        }
        listOfArticles=new ArrayList<>();
    }

    @Override
    public void renderView() {
        adapter=new LocalRecyclerViewAdapter(listOfArticles, mListener);
        recyclerView.setAdapter(adapter);
        SnapHelper snapHelper= new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void updateView() {
        if(adapter!=null)
            adapter.updateList(getNewsList());

    }

    @Override
    public void initListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTitle.getText().toString().trim().equals("") &&editDescription.getText().toString().trim().equals(""))
                {
                    Toast.makeText(mContext,"Empty Title/ Description",Toast.LENGTH_SHORT).show();
                }
                else {
                    Article tempArticle = new Article();
                    Source tempSrc=new Source();
                    tempArticle.setSource(tempSrc);
                    tempArticle.setTitle(editTitle.getText().toString());
                    tempArticle.setDescription(editDescription.getText().toString());
                    if (saveAuthor.isChecked())
                        tempArticle.setAuthor("James Ray");
                    if (radioYes.isChecked())
                        tempArticle.setPublishedAt(GeneralUtil.simpleDateToDateFormatString(Calendar.getInstance()));
                    mPresenter.saveArticles(tempArticle);
                    updateView();
                }
            }
        });

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
    public void setPresenter(LocalContract.Presenter presenter) {
        mPresenter=(LocalPresenter) presenter;
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
