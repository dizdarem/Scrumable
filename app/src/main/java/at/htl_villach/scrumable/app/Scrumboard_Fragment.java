package at.htl_villach.scrumable.app;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import at.htl_villach.scrumable.R;

public class Scrumboard_Fragment extends Fragment implements ToDo_Fragment.OnFragmentInteractionListener, InProcess_Fragment.OnFragmentInteractionListener, Testing_Fragment.OnFragmentInteractionListener, Done_Fragment.OnFragmentInteractionListener{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.fragment_scrumboard, container, false);
        TabLayout tabLayout = (TabLayout)x.findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("ToDo"));
        tabLayout.addTab(tabLayout.newTab().setText("In Process"));
        tabLayout.addTab(tabLayout.newTab().setText("Testing"));
        tabLayout.addTab(tabLayout.newTab().setText("Done"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)x.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
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

        return x;
        //toDo: Show Scrumboard with several Fragments (ToDo, InProcess, Testing, Done) by swiping to the right/left
    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}