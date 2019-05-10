package at.htl_villach.scrumable.app.ScrumboardFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.app.DetailsActivity;
import at.htl_villach.scrumable.app.SprintBacklog_Fragment;
import at.htl_villach.scrumable.bll.BacklogItem;
import at.htl_villach.scrumable.bll.BacklogItem_Adapter_DragAndDrop;
import at.htl_villach.scrumable.bll.BacklogItem_Adapter_Logic;
import at.htl_villach.scrumable.bll.Popup_Option_Menu_Enum;
import at.htl_villach.scrumable.bll.StatusEnum;
import at.htl_villach.scrumable.bll.User;
import at.htl_villach.scrumable.dal.DatabaseManager;

public class Testing_Fragment extends Fragment {
    private RecyclerView recyclerViewTesting;
    private BacklogItem_Adapter_Logic adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<BacklogItem> backlogItemList;
    private DatabaseManager databaseManager;

    public static Testing_Fragment newInstance(String param1, String param2) {
        Testing_Fragment fragment = new Testing_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initControls(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_testing, container, false);

        initControls(view);

        return view;
    }

    private void initControls(View view) {
        databaseManager = new DatabaseManager(Testing_Fragment.this.getContext());
        databaseManager.open();
        recyclerViewTesting = view.findViewById(R.id.recyclerViewTesting);
        backlogItemList = databaseManager.fetch_BacklogItem(0, StatusEnum.TESTING);

        recyclerViewTesting.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new BacklogItem_Adapter_Logic(backlogItemList, getActivity(), Popup_Option_Menu_Enum.SCRUMBOARD, getActivity(), recyclerViewTesting);

        recyclerViewTesting.setLayoutManager(layoutManager);
        recyclerViewTesting.setAdapter(adapter);

        adapter.setOnItemClickListener(new BacklogItem_Adapter_Logic.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("selectedListItemObj", backlogItemList.get(position));
                startActivity(intent);
            }
        });

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewTesting.addItemDecoration(divider);

        ItemTouchHelper.SimpleCallback helper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder dragged, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder target, int direction) {
                TabLayout tabLayout = (TabLayout)getActivity().findViewById(R.id.tablayout);
                int position = target.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT && tabLayout.getSelectedTabPosition() == 2) {
                    backlogItemList.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Successful shift", Toast.LENGTH_LONG).show();
                    tabLayout.getTabAt(1).select();
                } else  if (direction == ItemTouchHelper.RIGHT && tabLayout.getSelectedTabPosition() == 2) {
                    backlogItemList.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Successful shift", Toast.LENGTH_LONG).show();
                    tabLayout.getTabAt(3).select();
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(helper);
        itemTouchHelper.attachToRecyclerView(recyclerViewTesting); //set swipe to recylcerview

        ItemTouchHelper.Callback callback = new BacklogItem_Adapter_DragAndDrop(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerViewTesting);
    }

    public interface OnFragmentInteractionListener {
    }
}