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

public class SprintBacklog_Fragment extends Fragment {
    private static final String BACKLOG_ITEM = "backlogItem";

    private BacklogItem backlogItem;

    private RecyclerView recyclerViewSprintBacklog;
    private BacklogItem_Adapter_Logic adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<BacklogItem> testDataList;

    public static SprintBacklog_Fragment newInstance(BacklogItem selectedBacklogItem) {
        SprintBacklog_Fragment fragment = new SprintBacklog_Fragment();
        Bundle args = new Bundle();
        args.putParcelable(BACKLOG_ITEM, selectedBacklogItem);
        return fragment;
    }

    public SprintBacklog_Fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sprint_backlog, container, false);

        if (savedInstanceState != null) {
            backlogItem = savedInstanceState.getParcelable(BACKLOG_ITEM);
            Toast.makeText(getContext(), backlogItem.toString(), Toast.LENGTH_LONG).show();

            //addListItem(data);
        }

        init(view);

        return view;
    }

    public void init(final View view) {
        recyclerViewSprintBacklog = (RecyclerView)view.findViewById(R.id.recyclerViewSprintBacklog);
        testDataList = new ArrayList<>();

        recyclerViewSprintBacklog.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());

        adapter = new BacklogItem_Adapter_Logic(generateTestData(), getActivity(), Popup_Option_Menu_Enum.SPRINT_BACKLOG, getActivity(), recyclerViewSprintBacklog);

        recyclerViewSprintBacklog.setLayoutManager(layoutManager);
        recyclerViewSprintBacklog.setAdapter(adapter);

        adapter.setOnItemClickListener(new BacklogItem_Adapter_Logic.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("selectedListItemObj", testDataList.get(position));
                startActivity(intent);
            }
        });

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewSprintBacklog.addItemDecoration(divider);

        ItemTouchHelper.SimpleCallback helper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder target, int direction) {
                int position = target.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT || direction == ItemTouchHelper.RIGHT) {
                    BacklogItem backlogItem_toDelete = testDataList.get(position);
                    testDataList.remove(position);
                    testDataList.add(position, backlogItem_toDelete);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Cannot move to no existing tab", Toast.LENGTH_LONG).show();
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(helper);
        itemTouchHelper.attachToRecyclerView(recyclerViewSprintBacklog);

        ItemTouchHelper.Callback callback = new BacklogItem_Adapter_DragAndDrop(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerViewSprintBacklog);
    }

    private ArrayList<BacklogItem> generateTestData() {
        for(int i=1; i<=5; i++) {
            User user = new User("User_" + i, "User_" + i, new Date());
            testDataList.add(new BacklogItem(i, "Sprint_Backlog_ " + i, "Describtion of Sprint_Backlog_"+ i, StatusEnum.SPRINT_BACKLOG, user));
        }
        return testDataList;
    }

    public void addListItem(BacklogItem selectedBacklogItem) {
        testDataList.add(selectedBacklogItem);
        notifyAll();
        adapter.notifyDataSetChanged();
    }

    public void removeListItem(BacklogItem selectedBacklogItem) {
        testDataList.remove(selectedBacklogItem);
        adapter.notifyDataSetChanged();
    }
}