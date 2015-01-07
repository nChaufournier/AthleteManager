package com.sleepypirate.athletemanager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

/**
 * Created by Nic on 1/6/2015.
 */
public class TabsAdapter extends FragmentPagerAdapter {
    String[] tabNames = {"Today", "Yesterday"};
    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position){
            case 0:
                return new TodayFragment();
            case 1:
                return new YesterdayFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    public String[] getTabNames() {
        return tabNames;
    }

    public void setTabNames(String[] tabNames) {
        this.tabNames = tabNames;
    }
}
