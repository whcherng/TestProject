package com.example.hcwong.testproject.Main.ListNews

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.SearchView
import android.widget.Spinner

import com.example.hcwong.testproject.Model.Article
import com.example.hcwong.testproject.R
import com.example.hcwong.testproject.shared.GeneralUtil

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
class ListFragment : Fragment(), SearchView.OnQueryTextListener, ListContract.View {

    private var mContext: Context? = null
    private var mPresenter: ListContract.Presenter? = null
    private var listOfArticles: List<Article>? = null
    private var adapter: ListAdapter? = null

    @BindView(R.id.list)
    internal var listView: ListView? = null
    @BindView(R.id.progressLayout)
    internal var progressLayout: LinearLayout? = null
    @BindView(R.id.simpleSearchView)
    internal var searchView: SearchView? = null
    @BindView(R.id.spinner)
    internal var spinner: Spinner? = null
    // TODO: Customize parameters
    private var mColumnCount = 1
    private var mListener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mColumnCount = arguments!!.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)
        ButterKnife.bind(this, view)

        mPresenter = ListPresenter(mContext, this)
        mPresenter!!.start()
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
        if (context is OnListFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun initView() {
        listOfArticles = ArrayList()
//        val searchManager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        searchView!!.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
//        searchView!!.isSubmitButtonEnabled = true
//        searchView!!.setOnQueryTextListener(this)
//        searchView!!.isFocusable = true
//        searchView!!.requestFocusFromTouch()
    }

    override fun renderView() {
        adapter = ListAdapter(context, listOfArticles)
        listView!!.adapter = adapter

        searchView!!.isSubmitButtonEnabled = false
        val categories = ArrayList<String>()
        categories.add("Tittle")
        categories.add("Description")
        val adapter = ArrayAdapter(context!!, R.layout.support_simple_spinner_dropdown_item, categories)
        spinner!!.adapter = adapter
    }

    override fun updateView() {
        Log.d("Testing", newsList!!.size.toString() + "")
        if (adapter != null)
            adapter!!.updateList(newsList)

    }

    override fun initListener() {

    }

    override fun showEmptyState() {


    }

    override fun hideEmptyState() {

    }

    override fun showLoading() {
        if (!progressLayout!!.isShown)
            progressLayout!!.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        if (progressLayout!!.isShown)
            progressLayout!!.visibility = View.GONE
    }


    override fun setNewsList(newsList: List<Article>) {
        this.listOfArticles = newsList
    }

    override fun getNewsList(): List<Article>? {
        return this.listOfArticles
    }


    override fun hideKeyboard() {
        GeneralUtil.hideKeyboard(mContext, activity!!.currentFocus)
    }

    override fun showToastMessage(resId: Int) {
        GeneralUtil.toastLong(resId)
    }

    override fun setPresenter(presenter: ListContract.Presenter) {
        mPresenter = presenter
    }

    override fun onQueryTextSubmit(s: String): Boolean {
        return false
    }

    override fun onQueryTextChange(s: String): Boolean {
        adapter!!.criteria = spinner!!.selectedItem.toString()
        adapter!!.filter.filter(s)
        return true
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Article)
    }

    companion object {


        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"

        fun newInstance(): Fragment {
            return ListFragment()
        }

        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): ListFragment {
            val fragment = ListFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }

}
