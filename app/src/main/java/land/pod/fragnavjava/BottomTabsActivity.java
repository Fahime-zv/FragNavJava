package land.pod.fragnavjava;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.ncapdevi.fragnav.FragNavController;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class BottomTabsActivity extends AppCompatActivity implements basesfrag.FragmentNavigation, FragNavController.TransactionListener, FragNavController.RootFragmentListener {
    //Better convention to properly name the indices what they are in your app
    private final int INDEX_RECENTS = FragNavController.TAB1;
    private final int INDEX_FAVORITES = FragNavController.TAB2;
    private final int INDEX_NEARBY = FragNavController.TAB3;
    private final int INDEX_FRIENDS = FragNavController.TAB4;
    private final int INDEX_FOOD = FragNavController.TAB5;
    private BottomBar mBottomBar;
    private FragNavController mNavController;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tabs);

        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mBottomBar.selectTabAtPosition(INDEX_NEARBY);
        mNavController =  new FragNavController(getSupportFragmentManager(), R.id.container);
//
        mNavController .setTransactionListener(this);
        mNavController.setRootFragmentListener(this);

//        mNavController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.container)
//                .transactionListener(this)
//                .rootFragmentListener(this, 5)
//                .build();



        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public  void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.bb_menu_recents:
                        mNavController.switchTab(INDEX_RECENTS);
                        break;
                    case R.id.bb_menu_favorites:
                        mNavController.switchTab(INDEX_FAVORITES);
                        break;
                    case R.id.bb_menu_nearby:
                        mNavController.switchTab(INDEX_NEARBY);
                        break;
                    case R.id.bb_menu_friends:
                        mNavController.switchTab(INDEX_FRIENDS);
                        break;
                    case R.id.bb_menu_food:
                        mNavController.switchTab(INDEX_FOOD);
                        break;
                }
            }
        });

        mBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public  void onTabReSelected(@IdRes int tabId) {
                mNavController.clearStack();
            }
        });

    }

    @Override
    public  void onBackPressed() {
        if (!mNavController.isRootFragment()) {
            mNavController.popFragment();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected  void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mNavController != null) {
            mNavController.onSaveInstanceState(outState);
        }
    }

    @Override
    public  void pushFragment(Fragment fragment) {
        if (mNavController != null) {
            mNavController.pushFragment(fragment);
        }
    }

    @Override
    public  void onTabTransaction(Fragment fragment, int index) {
        // If we have a backstack, show the back button
        if (getSupportActionBar() != null && mNavController != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(!mNavController.isRootFragment());
        }
    }


    @Override
    public  void onFragmentTransaction(Fragment fragment, FragNavController.TransactionType transactionType) {
        //do fragmentty stuff. Maybe change title, I'm not going to tell you how to live your life
        // If we have a backstack, show the back button
        if (getSupportActionBar() != null && mNavController != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(!mNavController.isRootFragment());
        }
    }

    @Override
    public Fragment getRootFragment(int index) {
        switch (index) {
            case INDEX_RECENTS:
                return freFrag.newInstance(0);
            case INDEX_FAVORITES:
                return freFrag.newInstance(0);
            case INDEX_NEARBY:
                return freFrag.newInstance(0);
            case INDEX_FRIENDS:
                return freFrag.newInstance(0);
            case INDEX_FOOD:
                return freFrag.newInstance(0);
        }
        throw new IllegalStateException("Need to send an index that we know");
    }


    @Override
    public int getNumberOfRootFragments() {
        return 5;
    }
}



