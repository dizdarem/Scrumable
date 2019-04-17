package at.htl_villach.scrumable.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int nrOfTabs;

    public PagerAdapter(FragmentManager fm, int nrOfTabs) {
        super(fm);
        this.nrOfTabs = nrOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = ToDo_Fragment.newInstance("", "");
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
