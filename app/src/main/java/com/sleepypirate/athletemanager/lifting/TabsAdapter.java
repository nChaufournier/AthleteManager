package com.sleepypirate.athletemanager.lifting;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.sleepypirate.athletemanager.lifting.TodayFragment;
import com.sleepypirate.athletemanager.lifting.YesterdayFragment;

/**
 * Created by Nic on 1/6/2015.
 */
public class TabsAdapter extends FragmentPagerAdapter {
    String[] tabNames = {"Yesterday", "Today"};
    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position){
            case 0:
                return new YesterdayFragment();
            case 1:
                return new TodayFragment();

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
