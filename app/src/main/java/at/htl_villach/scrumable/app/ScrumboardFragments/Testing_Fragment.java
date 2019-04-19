package at.htl_villach.scrumable.app.ScrumboardFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.bll.BacklogItem;
import at.htl_villach.scrumable.bll.BacklogItems_Adapter;
import at.htl_villach.scrumable.bll.StatusEnum;
import at.htl_villach.scrumable.bll.User;

public class Testing_Fragment extends Fragment {
    private RecyclerView recyclerViewTesting;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<BacklogItem> testDataList;

    public static Testing_Fragment newInstance(String param1, String param2) {
        Testing_Fragment fragment = new Testing_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_testing, container, false);

        testDataList = new ArrayList<>();

        recyclerViewTesting = view.findViewById(R.id.recyclerViewTesting);
        recyclerViewTesting.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new BacklogItems_Adapter(generateTestData());

        recyclerViewTesting.setLayoutManager(layoutManager);
        recyclerViewTesting.setAdapter(adapter);

        return view;
    }

    private ArrayList<BacklogItem> generateTestData() {
        for(int i=1; i<=5; i++) {
            User user = new User("User_" + i, "User_" + i, new Date());
            testDataList.add(new BacklogItem(i, "Testing_ " + i, "Describtion of Testing_"+ i, StatusEnum.TODO, user));
        }
        return testDataList;
    }

    public interface OnFragmentInteractionListener {
    }
}