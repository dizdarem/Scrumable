package at.htl_villach.scrumable.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.bll.BacklogItem;
import at.htl_villach.scrumable.bll.BacklogItem_Adapter_DragAndDrop;
import at.htl_villach.scrumable.bll.BacklogItem_Adapter_Logic;
import at.htl_villach.scrumable.bll.Popup_Option_Menu_Enum;
import at.htl_villach.scrumable.bll.StatusEnum;
import at.htl_villach.scrumable.bll.User;

public class ProductBacklog_Fragment extends Fragment {
    private RecyclerView recyclerViewProductBacklog;
    private BacklogItem_Adapter_Logic adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<BacklogItem> testDataList;

    public static ProductBacklog_Fragment newInstance() {
        ProductBacklog_Fragment fragment = new ProductBacklog_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_backlog, container, false);

        initControls(view);

        return view;
    }

    private void initControls(View view) {
        recyclerViewProductBacklog = (RecyclerView)view.findViewById(R.id.recyclerViewProductBacklog);
        testDataList = new ArrayList<>();

        recyclerViewProductBacklog.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new BacklogItem_Adapter_Logic(generateTestData(), getActivity(), Popup_Option_Menu_Enum.PRODUCT_BACKLOG, getActivity(), recyclerViewProductBacklog);

        recyclerViewProductBacklog.setLayoutManager(layoutManager);
        recyclerViewProductBacklog.setAdapter(adapter);

        adapter.setOnItemClickListener(new BacklogItem_Adapter_Logic.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("selectedListItemObj", testDataList.get(position));
                startActivity(intent);
            }
        });

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewProductBacklog.addItemDecoration(divider);

        ItemTouchHelper.Callback callback = new BacklogItem_Adapter_DragAndDrop(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerViewProductBacklog);
    }

    private ArrayList<BacklogItem> generateTestData() {
        for(int i=1; i<=5; i++) {
            User user = new User("User_" + i, "User_" + i, new Date());
            testDataList.add(new BacklogItem(i, "Product_Backlog_ " + i, "Describtion of Prodcut_Backlog_"+ i, StatusEnum.PRODUCT_BACKLOG, user));
        }
        return testDataList;
    }

    public void addListItem(BacklogItem selectedBacklogItem) {
        testDataList.add(selectedBacklogItem);
        adapter.notifyDataSetChanged();
    }

    public void removeListItem(BacklogItem selectedBacklogItem) {
        testDataList.remove(selectedBacklogItem);
        adapter.notifyDataSetChanged();
    }
}