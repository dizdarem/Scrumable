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
import android.widget.Toast;

import java.util.ArrayList;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.app.DetailsActivity;
import at.htl_villach.scrumable.bll.BacklogItem;
import at.htl_villach.scrumable.bll.BacklogItem_Adapter_DragAndDrop;
import at.htl_villach.scrumable.bll.BacklogItem_Adapter_Logic;
import at.htl_villach.scrumable.bll.Popup_Option_Menu_Enum;
import at.htl_villach.scrumable.bll.StatusEnum;
import at.htl_villach.scrumable.dal.DatabaseManager;

public class ToDo_Fragment extends Fragment {
    private RecyclerView recyclerViewToDo;
    private BacklogItem_Adapter_Logic adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<BacklogItem> backlogItemList;
    private DatabaseManager databaseManager;

    public static ToDo_Fragment newInstance(String param1, String param2) {
        ToDo_Fragment fragment = new ToDo_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        initControls(view);

        return view;
    }

    private void initControls(View view) {
        databaseManager = new DatabaseManager(ToDo_Fragment.this.getContext());
        databaseManager.open();
        recyclerViewToDo = view.findViewById(R.id.recyclerViewToDo);
        backlogItemList = databaseManager.fetch_BacklogItem(0, StatusEnum.TODO);

        recyclerViewToDo.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new BacklogItem_Adapter_Logic(backlogItemList, getActivity(), Popup_Option_Menu_Enum.SCRUMBOARD);

        recyclerViewToDo.setLayoutManager(layoutManager);
        recyclerViewToDo.setAdapter(adapter);

        adapter.setOnItemClickListener(new BacklogItem_Adapter_Logic.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("selectedListItemObj", backlogItemList.get(position));
                startActivity(intent);
            }
        });

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewToDo.addItemDecoration(divider);

        ItemTouchHelper.SimpleCallback helper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder dragged, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder target, int direction) {
                TabLayout tabLayout = (TabLayout)getActivity().findViewById(R.id.tablayout);
                int position = target.getAdapterPosition();
                BacklogItem backlogItemToUpdate = backlogItemList.get(position);

                if (direction == ItemTouchHelper.RIGHT && tabLayout.getSelectedTabPosition() == 0) {    //if swipe left
                    backlogItemList.remove(position);
                    adapter.notifyDataSetChanged();

                    backlogItemToUpdate.setStatus(StatusEnum.IN_PROCESS);
                    databaseManager.update_BacklogItem(backlogItemToUpdate);

                    Toast.makeText(getContext(), "Successful shift", Toast.LENGTH_LONG).show();

                    TabLayout.Tab tab = tabLayout.getTabAt(1);
                    tabLayout.getTabAt(1).select();
                } else if (direction == ItemTouchHelper.LEFT && tabLayout.getSelectedTabPosition() == 0) {    //if swipe left
                    BacklogItem backlogItem_toDelete = backlogItemList.get(position);
                    backlogItemList.remove(position);
                    backlogItemList.add(position, backlogItem_toDelete);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(getContext(), "Cannot move to no existing tab", Toast.LENGTH_LONG).show();
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(helper);
        itemTouchHelper.attachToRecyclerView(recyclerViewToDo); //set swipe to recylcerview

        ItemTouchHelper.Callback callback = new BacklogItem_Adapter_DragAndDrop(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerViewToDo);
    }

    public interface OnFragmentInteractionListener {
    }
}