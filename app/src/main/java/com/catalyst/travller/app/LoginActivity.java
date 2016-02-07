package com.catalyst.travller.app;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.catalyst.travller.app.fragment.LoginFragment;
import com.catalyst.travller.app.fragment.RegisterFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.action_bar);
//        TextView textTitle = (TextView) findViewById(R.id.titleLogin);
//        textTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callMainActivity();
//            }
//        });
        setupTabLayout();
    }

    private ViewPager mViewPager;

    private void setupTabLayout() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.SIGN_IN));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Sign_up));

        mViewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
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
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    private void callMainActivity() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(android.support.v4.app.FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    LoginFragment tab1 = new LoginFragment();
                    return tab1;
                case 1:
                    RegisterFragment registerFragment = new RegisterFragment();
                    return registerFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

    public void requestToOpenSignUpFragment(int pageToSelect) {
        if (mViewPager != null) {
            mViewPager.setCurrentItem(pageToSelect);
        }
    }


}
