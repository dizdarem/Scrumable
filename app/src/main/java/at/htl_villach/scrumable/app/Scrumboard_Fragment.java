package at.htl_villach.scrumable.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.htl_villach.scrumable.R;
import at.htl_villach.scrumable.app.ScrumboardFragments.Done_Fragment;
import at.htl_villach.scrumable.app.ScrumboardFragments.InProcess_Fragment;
import at.htl_villach.scrumable.app.ScrumboardFragments.Testing_Fragment;
import at.htl_villach.scrumable.app.ScrumboardFragments.ToDo_Fragment;

public class Scrumboard_Fragment extends Fragment implements ToDo_Fragment.OnFragmentInteractionListener, InProcess_Fragment.OnFragmentInteractionListener, Testing_Fragment.OnFragmentInteractionListener, Done_Fragment.OnFragmentInteractionListener{
    /*
    * Scrumboard Fragment which contains toolbar with tabs (Scrumboard - Columns) and their Fragments
    * Logic of Scrumboard
    * */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scrumboard, container, false);
        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tablayout);
        //Scrumboard Tab-Columns
        //TabLayout.Tab tabToDo = new TabLayout.Tab();
        //ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        //ScrumboardPagerAdapter a = (ScrumboardPagerAdapter) pager.getAdapter();
        //tabToDo.setTag( a.getItem(0));
        //tabToDo.setText("ToDo");
        //tabLayout.addTab(tabToDo);

        tabLayout.addTab(tabLayout.newTab().setText("ToDo"));
        tabLayout.addTab(tabLayout.newTab().setText("In Process"));
        tabLayout.addTab(tabLayout.newTab().setText("Testing"));
        tabLayout.addTab(tabLayout.newTab().setText("Done"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)view.findViewById(R.id.pager);
        final ScrumboardPagerAdapter adapter = new ScrumboardPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
}