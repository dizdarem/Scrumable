package at.htl_villach.scrumable.app.ScrumboardFragments;

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
import at.htl_villach.scrumable.bll.BacklogItem;
import at.htl_villach.scrumable.bll.BacklogItems_Adapter;
import at.htl_villach.scrumable.bll.StatusEnum;
import at.htl_villach.scrumable.bll.User;

public class InProcess_Fragment extends Fragment {
    private RecyclerView recyclerViewInProcess;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<BacklogItem> testDataList;

    public static InProcess_Fragment newInstance(String param1, String param2) {
        InProcess_Fragment fragment = new InProcess_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inprocess, container, false);

        testDataList = new ArrayList<>();

        recyclerViewInProcess = view.findViewById(R.id.recyclerViewInProcess);
        recyclerViewInProcess.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new BacklogItems_Adapter(generateTestData(), getActivity());

        recyclerViewInProcess.setLayoutManager(layoutManager);
        recyclerViewInProcess.setAdapter(adapter);

        return view;
    }

    private ArrayList<BacklogItem> generateTestData() {
        for(int i=1; i<=5; i++) {
            User user = new User("User_" + i, "User_" + i, new Date());
            testDataList.add(new BacklogItem(i, "InProcess_ " + i, "Describtion of InProcess_"+ i, StatusEnum.TODO, user));
        }
        return testDataList;
    }

    public interface OnFragmentInteractionListener {
    }
}