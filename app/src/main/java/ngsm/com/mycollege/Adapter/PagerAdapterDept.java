package ngsm.com.mycollege.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ngsm.com.mycollege.Tabs.Commerce;
import ngsm.com.mycollege.Tabs.Mba;
import ngsm.com.mycollege.Tabs.Mca;

/**
 * Created by Rishi on 11/29/2016.
 */
public class PagerAdapterDept extends FragmentStatePagerAdapter {
    private String tabTitles[]=new String[]{"MBA","MCA","Commerce"};


    //Constructor to the class
    public PagerAdapterDept(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                Mba mba = new Mba();
                return mba;
            case 1:
                Mca mca = new Mca();
                return mca;
            case 2:
                Commerce comm = new Commerce();
                return comm;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
