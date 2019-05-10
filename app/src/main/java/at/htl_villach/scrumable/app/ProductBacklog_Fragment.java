package at.htl_villach.scrumable.app;

import android.app.Activity;
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
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.bll.BacklogItem;
import at.htl_villach.scrumable.bll.BacklogItem_Adapter_DragAndDrop;
import at.htl_villach.scrumable.bll.BacklogItem_Adapter_Logic;
import at.htl_villach.scrumable.bll.Popup_Option_Menu_Enum;
import at.htl_villach.scrumable.bll.StatusEnum;
import at.htl_villach.scrumable.bll.User;
import at.htl_villach.scrumable.dal.DatabaseManager;

public class ProductBacklog_Fragment extends Fragment {
    private RecyclerView recyclerViewProductBacklog;
    private BacklogItem_Adapter_Logic adapter;
    private RecyclerView.LayoutManager layoutManager;

    private DatabaseManager databaseManager;
    private ArrayList<BacklogItem> backlogItemList;

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
        databaseManager = new DatabaseManager(ProductBacklog_Fragment.this.getContext());
        databaseManager.open();
        recyclerViewProductBacklog = (RecyclerView)view.findViewById(R.id.recyclerViewProductBacklog);
        backlogItemList = databaseManager.fetch_BacklogItem(0, StatusEnum.PRODUCT_BL);

        recyclerViewProductBacklog.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new BacklogItem_Adapter_Logic(backlogItemList, getActivity(), Popup_Option_Menu_Enum.PRODUCT_BACKLOG);

        recyclerViewProductBacklog.setLayoutManager(layoutManager);
        recyclerViewProductBacklog.setAdapter(adapter);

        adapter.setOnItemClickListener(new BacklogItem_Adapter_Logic.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("selectedListItemObj", backlogItemList.get(position));
                startActivityForResult(intent, 10);
            }
        });

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewProductBacklog.addItemDecoration(divider);

        ItemTouchHelper.SimpleCallback helper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder target, int direction) {
                int position = target.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT || direction == ItemTouchHelper.RIGHT) {
                    BacklogItem backlogItem_toDelete = backlogItemList.get(position);
                    backlogItemList.remove(position);
                    backlogItemList.add(position, backlogItem_toDelete);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Cannot move to no existing tab", Toast.LENGTH_LONG).show();
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(helper);
        itemTouchHelper.attachToRecyclerView(recyclerViewProductBacklog);

        ItemTouchHelper.Callback callback = new BacklogItem_Adapter_DragAndDrop(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerViewProductBacklog);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 10) {
            if(resultCode == Activity.RESULT_OK) {
                backlogItemList.clear();
                backlogItemList.addAll(databaseManager.fetch_BacklogItem(0, StatusEnum.PRODUCT_BL));
                adapter.notifyDataSetChanged();
            }
            if(resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }
}