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

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.bll.BacklogItem;
import at.htl_villach.scrumable.bll.BacklogItem_Adapter_DragAndDrop;
import at.htl_villach.scrumable.bll.BacklogItem_Adapter_Logic;
import at.htl_villach.scrumable.bll.Popup_Option_Menu_Enum;
import at.htl_villach.scrumable.bll.StatusEnum;
import at.htl_villach.scrumable.dal.DatabaseManager;

public class SprintBacklog_Fragment extends Fragment {

    private RecyclerView recyclerViewSprintBacklog;
    private BacklogItem_Adapter_Logic adapter;
    private RecyclerView.LayoutManager layoutManager;

    private DatabaseManager databaseManager;
    private ArrayList<BacklogItem> backlogItemList;

    public static SprintBacklog_Fragment newInstance(BacklogItem selectedBacklogItem) {
        SprintBacklog_Fragment fragment = new SprintBacklog_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SprintBacklog_Fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sprint_backlog, container, false);

        /*if (savedInstanceState != null) {
            BacklogItem backlogItem_ToAdd = savedInstanceState.getParcelable(BACKLOG_ITEM);
            Toast.makeText(getContext(), backlogItem_ToAdd.toString(), Toast.LENGTH_LONG).show();

            addListItem(backlogItem_ToAdd);
        }*/

        //Toast.makeText(getContext(), "First time", Toast.LENGTH_LONG).show();

        if(savedInstanceState==null)
            init(view);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void init(final View view) {
        databaseManager = new DatabaseManager(SprintBacklog_Fragment.this.getContext());
        databaseManager.open();
        recyclerViewSprintBacklog = (RecyclerView)view.findViewById(R.id.recyclerViewSprintBacklog);
        backlogItemList = databaseManager.fetch_BacklogItem(0, StatusEnum.SPRINT_BL);

        recyclerViewSprintBacklog.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());

        adapter = new BacklogItem_Adapter_Logic(backlogItemList, getActivity(), Popup_Option_Menu_Enum.SPRINT_BACKLOG);

        recyclerViewSprintBacklog.setLayoutManager(layoutManager);
        recyclerViewSprintBacklog.setAdapter(adapter);

        adapter.setOnItemClickListener(new BacklogItem_Adapter_Logic.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("selectedListItemObj", backlogItemList.get(position));
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
                    BacklogItem backlogItem_toDelete = backlogItemList.get(position);
                    backlogItemList.remove(position);
                    backlogItemList.add(position, backlogItem_toDelete);
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
}