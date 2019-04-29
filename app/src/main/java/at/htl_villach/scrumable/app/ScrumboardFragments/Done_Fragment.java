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
import at.htl_villach.scrumable.bll.BacklogItem;
import at.htl_villach.scrumable.bll.BacklogItem_Adapter_DragAndDrop;
import at.htl_villach.scrumable.bll.BacklogItem_Adapter_Logic;
import at.htl_villach.scrumable.bll.Popup_Option_Menu_Enum;
import at.htl_villach.scrumable.bll.StatusEnum;
import at.htl_villach.scrumable.bll.User;

public class Done_Fragment extends Fragment {
    private RecyclerView recyclerViewDone;
    private BacklogItem_Adapter_Logic adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<BacklogItem> testDataList;

    private FrameLayout flDone;

    public static Done_Fragment newInstance(String param1, String param2) {
        Done_Fragment fragment = new Done_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_done, container, false);

        initControls(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initControls(view);
    }

    private void initControls(View view) {
        testDataList = new ArrayList<>();

        flDone = (FrameLayout)view.findViewById(R.id.flDone);

        recyclerViewDone = view.findViewById(R.id.recyclerViewDone);
        recyclerViewDone.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new BacklogItem_Adapter_Logic(generateTestData(), getActivity(), Popup_Option_Menu_Enum.SCRUMBOARD, getActivity(), recyclerViewDone);

        recyclerViewDone.setLayoutManager(layoutManager);
        recyclerViewDone.setAdapter(adapter);

        adapter.setOnItemClickListener(new BacklogItem_Adapter_Logic.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("selectedListItemObj", testDataList.get(position));
                startActivity(intent);
            }
        });

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewDone.addItemDecoration(divider);

        ItemTouchHelper.SimpleCallback helper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder dragged, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder target, int direction) {
                TabLayout tabLayout = (TabLayout)getActivity().findViewById(R.id.tablayout);
                int position = target.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT && tabLayout.getSelectedTabPosition() == 3) {
                    testDataList.remove(position);
                    adapter.notifyDataSetChanged();
                    tabLayout.getTabAt(2).select();

                    Toast.makeText(getContext(), "Successful shift", Toast.LENGTH_LONG).show();
                } else if (direction == ItemTouchHelper.RIGHT && tabLayout.getSelectedTabPosition() == 3) {    //if swipe left
                    BacklogItem backlogItem_toDelete = testDataList.get(position);
                    testDataList.remove(position);
                    testDataList.add(position, backlogItem_toDelete);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(getContext(), "Cannot move to no existing tab", Toast.LENGTH_LONG).show();
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(helper);
        itemTouchHelper.attachToRecyclerView(recyclerViewDone); //set swipe to recylcerview

        ItemTouchHelper.Callback callback = new BacklogItem_Adapter_DragAndDrop(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerViewDone);
    }

    private ArrayList<BacklogItem> generateTestData() {
        for(int i=1; i<=5; i++) {
            User user = new User("User_" + i, "User_" + i, new Date());
            testDataList.add(new BacklogItem(i, "Done_ " + i, "Describtion of Done_"+ i, StatusEnum.DONE, user));
        }
        return testDataList;
    }

    public interface OnFragmentInteractionListener {
    }
}