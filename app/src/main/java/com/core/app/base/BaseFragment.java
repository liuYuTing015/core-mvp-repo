package com.core.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.core.app.R;
import com.core.app.utils.GridSpacingItemDecoration;
import com.core.app.utils.ImageUtils;
import com.core.app.utils.ItemDivider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class BaseFragment extends Fragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    public BaseActivity mBaseActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBaseActivity = (BaseActivity) context;
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Nullable
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ImageUtils.clear(mBaseActivity);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Object event) {

    }

    public void initRecyclerViewAsGridView(RecyclerView recyclerView, int spanCount, RecyclerView.OnScrollListener loadMoreListener) {
        initRecyclerViewAsGridView(recyclerView, false, spanCount, loadMoreListener);
    }

    public void initRecyclerViewAsGridView(RecyclerView recyclerView, boolean hasHeader, int spanCount, RecyclerView.OnScrollListener loadMoreListener) {
        final GridLayoutManager mLayoutManager = new GridLayoutManager(mBaseActivity, spanCount);
        mLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        if (loadMoreListener != null) {
            recyclerView.addOnScrollListener(loadMoreListener);
        }
        if (!hasHeader) {
            //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, getResources().getDimensionPixelSize(R.dimen.space), true));
        }
    }

    public void initRecyclerViewAsmHeaderGridView(RecyclerView recyclerView, int spanCount, RecyclerView.OnScrollListener loadMoreListener) {
        initRecyclerViewAsmGridView(recyclerView, true, spanCount, loadMoreListener);
    }

    public void initRecyclerViewAsmGridView(RecyclerView recyclerView, boolean hasHeader, final int spanCount, RecyclerView.OnScrollListener loadMoreListener) {
        final GridLayoutManager mLayoutManager = new GridLayoutManager(mBaseActivity, spanCount);
        mLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? spanCount : 1;
            }
        });
        if (loadMoreListener != null) {
            recyclerView.addOnScrollListener(loadMoreListener);
        }
        if (!hasHeader) {
            //recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, getResources().getDimensionPixelSize(R.dimen.space), true));
        }
    }

    public void initRecyclerViewAsGridView(RecyclerView recyclerView, int spanCount) {
        initRecyclerViewAsGridView(recyclerView, spanCount, null);
    }

    public void initRecyclerViewAsListView(RecyclerView recyclerView) {
        initRecyclerViewAsListView(recyclerView, true, null);
    }

    public void initRecyclerViewAsListView(RecyclerView recyclerView, boolean hasDivider) {
        initRecyclerViewAsListView(recyclerView, hasDivider, null);
    }

    public void initRecyclerViewAsListView(RecyclerView recyclerView, boolean hasDivider, RecyclerView.OnScrollListener loadMoreListener) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mBaseActivity);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        if (hasDivider) {
            // recyclerView.addItemDecoration(new ItemDivider(mBaseActivity, R.drawable.item_divider));
        }
        if (loadMoreListener != null) {
            recyclerView.addOnScrollListener(loadMoreListener);
        }
    }
}
