package at.htl_villach.scrumable.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import at.htl_villach.scrumable.app.ScrumboardFragments.Done_Fragment;
import at.htl_villach.scrumable.app.ScrumboardFragments.InProcess_Fragment;
import at.htl_villach.scrumable.app.ScrumboardFragments.Testing_Fragment;
import at.htl_villach.scrumable.app.ScrumboardFragments.ToDo_Fragment;

public class ScrumboardPagerAdapter extends FragmentStatePagerAdapter {
    /*
    * Adapter for Scrumboard - Columns
    * Create Fragment when pressed on specific tab
    * */
    int nrOfTabs;
    Fragment toDo = ToDo_Fragment.newInstance("", "");
    public ScrumboardPagerAdapter(FragmentManager fm, int nrOfTabs) {
        super(fm);
        this.nrOfTabs = nrOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment =  toDo;
                break;
            case 1:
                fragment = InProcess_Fragment.newInstance("", "");
                break;
            case 2:
                fragment = Testing_Fragment.newInstance("", "");
                break;
            case 3:
                fragment = Done_Fragment.newInstance("", "");
                break;
            default:
                return null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return nrOfTabs;
    }
}
