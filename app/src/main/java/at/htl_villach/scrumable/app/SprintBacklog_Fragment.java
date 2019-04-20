package at.htl_villach.scrumable.app;

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

public class SprintBacklog_Fragment extends Fragment {
    private RecyclerView recyclerViewSprintBacklog;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<BacklogItem> testDataList;

    public static SprintBacklog_Fragment newInstance() {
        SprintBacklog_Fragment fragment = new SprintBacklog_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sprint_backlog, container, false);

        recyclerViewSprintBacklog = (RecyclerView)view.findViewById(R.id.recyclerViewSprintBacklog);
        testDataList = new ArrayList<>();

        recyclerViewSprintBacklog.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new BacklogItems_Adapter(generateTestData(), getActivity(), PopupOptionMenuEnum.SPRINT_BACKLOG);

        recyclerViewSprintBacklog.setLayoutManager(layoutManager);
        recyclerViewSprintBacklog.setAdapter(adapter);

        return view;
    }

    private ArrayList<BacklogItem> generateTestData() {
        for(int i=1; i<=5; i++) {
            User user = new User("User_" + i, "User_" + i, new Date());
            testDataList.add(new BacklogItem(i, "Sprint_Backlog_ " + i, "Describtion of Sprint_Backlog_"+ i, StatusEnum.SPRINT_BACKLOG, user));
        }
        return testDataList;
    }
}