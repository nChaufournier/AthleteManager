package com.sleepypirate.athletemanager;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Nic on 1/6/2015.
 */
public class LiftActivity extends Activity implements ActionBar.TabListener{
    private ViewPager viewPager;
    private TabsAdapter tabsAdapter;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lift_navigation);

        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        tabsAdapter = new TabsAdapter(getFragmentManager());

        viewPager.setAdapter(tabsAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //add the tabs
        for(String tabName : tabsAdapter.getTabNames()){
            ActionBar.Tab aTab = actionBar.newTab()
                                          .setText(tabName)
                                          .setTabListener(this);
            actionBar.addTab(aTab);
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                getActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this, "Settings!", Toast.LENGTH_LONG).show();
                return true;
            case R.id.addLift:
                Toast.makeText(this, "Add a Lift!", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
