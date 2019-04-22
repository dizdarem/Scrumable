package at.htl_villach.scrumable.app.ScrumboardFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.app.DetailsActivity;
import at.htl_villach.scrumable.bll.BacklogItem;
import at.htl_villach.scrumable.bll.BacklogItems_Adapter;
import at.htl_villach.scrumable.bll.PopupOptionMenuEnum;
import at.htl_villach.scrumable.bll.StatusEnum;
import at.htl_villach.scrumable.bll.User;

public class ToDo_Fragment extends Fragment {
    private RecyclerView recyclerViewToDo;
    private BacklogItems_Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<BacklogItem> testDataList;

    public static ToDo_Fragment newInstance(String param1, String param2) {
        ToDo_Fragment fragment = new ToDo_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        testDataList = new ArrayList<>();

        recyclerViewToDo = view.findViewById(R.id.recyclerViewToDo);
        recyclerViewToDo.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new BacklogItems_Adapter(generateTestData(), getActivity(), PopupOptionMenuEnum.SCRUMBOARD, getView());

        recyclerViewToDo.setLayoutManager(layoutManager);
        recyclerViewToDo.setAdapter(adapter);

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
            testDataList.add(new BacklogItem(i, "ToDo_ " + i, "Describtion of ToDo_"+ i, StatusEnum.TODO, user));
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

    public interface OnFragmentInteractionListener {
    }
}