package org.richcode.campusdiary;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.richcode.campusdiary.Adapter.PagerAdapter;
import org.richcode.campusdiary.DataClass.DiaryData;
import org.richcode.campusdiary.DataClass.HomeWorkData;
import org.richcode.campusdiary.DataClass.MemoData;
import org.richcode.campusdiary.Dialog.DialogExit;
import org.richcode.campusdiary.Dialog.MyDialogListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("과제"));
        tabLayout.addTab(tabLayout.newTab().setText("메모"));
        tabLayout.addTab(tabLayout.newTab().setText("일기"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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

    }

    @Override
    public void onBackPressed() {
        DialogExit exitDialog = new DialogExit(MainActivity.this);
        exitDialog.setCanceledOnTouchOutside(false);
        exitDialog.setDialogListener(new MyDialogListener() {
            @Override
            public void onPositiveClicked(String input) {
                finish();
            }

            @Override
            public void onMemoClicked(MemoData input) {

            }

            @Override
            public void onHomeWorkClicked(HomeWorkData input) {

            }

            @Override
            public void onDiaryClicked(DiaryData input) {

            }

            @Override
            public void onNegativeClicked() {

            }
        });
        exitDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



}
