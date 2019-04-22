package at.htl_villach.scrumable.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.bll.BacklogItem;
import at.htl_villach.scrumable.bll.BacklogItems_Adapter;
import at.htl_villach.scrumable.bll.PopupOptionMenuEnum;
import at.htl_villach.scrumable.bll.StatusEnum;
import at.htl_villach.scrumable.bll.User;

public class ProductBacklog_Fragment extends Fragment {
    private RecyclerView recyclerViewProductBacklog;
    private BacklogItems_Adapter adapter;
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

        recyclerViewProductBacklog = (RecyclerView)view.findViewById(R.id.recyclerViewProductBacklog);
        testDataList = new ArrayList<>();

        recyclerViewProductBacklog.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new BacklogItems_Adapter(generateTestData(), getActivity(), PopupOptionMenuEnum.PRODUCT_BACKLOG, getView());

        recyclerViewProductBacklog.setLayoutManager(layoutManager);
        recyclerViewProductBacklog.setAdapter(adapter);

        adapter.setOnItemClickListener(new BacklogItems_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("selectedListItemObj", testDataList.get(position));
                startActivity(intent);
            }
        });

        return view;
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