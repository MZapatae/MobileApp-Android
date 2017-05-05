package cl.mzapatae.mobileApp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.base.BaseActivity;
import cl.mzapatae.mobileApp.base.BaseFragment;
import cl.mzapatae.mobileApp.fragments.EmptyFragment;
import cl.mzapatae.mobileApp.fragments.UserListFragment;
import cl.mzapatae.mobileApp.utils.Constants;
import cl.mzapatae.mobileApp.utils.LocalStorage;

public class MainActivity extends BaseActivity implements Drawer.OnDrawerItemClickListener, Drawer.OnDrawerNavigationListener,
        BaseFragment.OnToolbarAddedListener ,
        BaseFragment.OnLockDrawerMenuListener {
    private static final String TAG = "Main Activity";
    @BindView(R.id.fragment_container) FrameLayout fragmentContainer;

    //TODO: Change for default starter section
    private static final int START_ITEM_ID = 1;

    private Drawer mDrawerMenu;
    private int mLastMenuItemSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        createDrawerMenu(SetUpDrawerItems());

        loadScreen(getIntent());
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mDrawerMenu.setSelection(mLastMenuItemSelected,false);
    }

    private List<IDrawerItem> SetUpDrawerItems() {
        PrimaryDrawerItem userListItem = new PrimaryDrawerItem().withName("Lista Usuarios").withIdentifier(1);
        PrimaryDrawerItem testItem = new PrimaryDrawerItem().withName("Test").withIdentifier(2);
        PrimaryDrawerItem logoutItem = new PrimaryDrawerItem().withName("Cerrar Sesion").withIdentifier(4);

        List<IDrawerItem> menuDrawerItems = new ArrayList<>();
        menuDrawerItems.add(userListItem);
        menuDrawerItems.add(testItem);
        menuDrawerItems.add(new DividerDrawerItem());
        menuDrawerItems.add(logoutItem);
        return menuDrawerItems;
    }

    private void createDrawerMenu(List<IDrawerItem> drawerItems) {
        DrawerImageLoader.init(new DrawerImageLoader.IDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                //Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder, String tag) {

            }

            @Override
            public void cancel(ImageView imageView) {
                //Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }

            @Override
            public Drawable placeholder(Context ctx) {
                return null;
            }

            @Override
            public Drawable placeholder(Context ctx, String tag) {
                return null;
            }
        });

        ProfileDrawerItem userProfile = new ProfileDrawerItem()
                .withName(LocalStorage.getInstance(this).getPrefUserFirstname() + " " + LocalStorage.getInstance(this).getPrefUserLastname())
                .withEmail(LocalStorage.getInstance(this).getPrefUserEmail())
                .withIcon(LocalStorage.getInstance(this).getPrefUserAvatar());

        AccountHeader headerMenu = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.app_primary_dark)
                .addProfiles(userProfile)
                .withCurrentProfileHiddenInList(true)
                .withSelectionListEnabled(false)
                .withOnAccountHeaderSelectionViewClickListener(new AccountHeader.OnAccountHeaderSelectionViewClickListener() {
                    @Override
                    public boolean onClick(View view, IProfile iProfile) {
                        //TODO: Implement edit profile
                        return false;
                    }
                })
                .build();

        mDrawerMenu = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(headerMenu)
                .withDrawerItems(drawerItems)
                .withCloseOnClick(true)
                .withSelectedItem(1)
                .withOnDrawerNavigationListener(this)
                .withOnDrawerItemClickListener(this)
                .withActionBarDrawerToggleAnimated(true)
                .build();
    }

    @SuppressWarnings("ConstantConditions")
    private void loadScreen(Intent intent) {
        //TODO: Manage Intent Filter from Push Notification here!
        try {
            switch (intent.getAction()) {
                case Constants.ACTION_MAIN:
                    mDrawerMenu.setSelection(START_ITEM_ID, true);
                    break;

                case Constants.OPEN_SECTION:
                    mDrawerMenu.setSelection(Integer.valueOf(intent.getExtras().getString("section")), true);
                    break;
            }
        } catch (Exception e) {
            mDrawerMenu.setSelection(START_ITEM_ID, true);
            Log.e(TAG, "Invalid Intent");
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        if (drawerItem.getIdentifier() != mLastMenuItemSelected) {
            Fragment fragment = null;

            switch (((int) drawerItem.getIdentifier())) {
                case 1:
                    fragment = getSupportFragmentManager().findFragmentByTag(UserListFragment.class.getName());
                    if (!UserListFragment.class.isInstance(fragment))
                        fragment = UserListFragment.newInstance();
                    break;

                case 2:
                    fragment = getSupportFragmentManager().findFragmentByTag(EmptyFragment.class.getName());
                    if (!EmptyFragment.class.isInstance(fragment))
                        fragment = EmptyFragment.newInstance(null, null);
                    break;

                case 4:
                    logoutUser();
                    break;
            }

            try {
                if (fragment != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(fragment.getClass().getName());
                    transaction.commit();
                    mLastMenuItemSelected = (int) drawerItem.getIdentifier();
                }
            }catch(Exception e){
                Log.e(TAG, "Exception: " + e.getMessage());
                mDrawerMenu.setSelection(mLastMenuItemSelected, false);
            }
        } else {
            mDrawerMenu.closeDrawer();
        }
        return false;
    }

    private void logoutUser() {
        LocalStorage.getInstance(this).logoutUser();
        Intent launchWelcomeScreen = new Intent(MainActivity.this, LandingActivity.class);
        this.startActivity(launchWelcomeScreen);
        finish();
    }

    @Override
    public void onBackPressed() {
        onBackNavigation();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onToolbarAdded(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        mDrawerMenu.setToolbar(this, toolbar, true);
    }

    @Override
    public void onLockDrawerMenuStatus(boolean lock) {
        if (lock) {
            mDrawerMenu.getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            mDrawerMenu.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
            ActionBar actionBar = this.getSupportActionBar();
            if (actionBar != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        } else {
            mDrawerMenu.getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            ActionBar actionBar = this.getSupportActionBar();
            if (actionBar != null) getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            mDrawerMenu.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
        }
    }

    private void onBackNavigation() {
        if (mDrawerMenu != null && mDrawerMenu.isDrawerOpen()) {
            mDrawerMenu.closeDrawer();
        } else {
            if ((mDrawerMenu != null ? mDrawerMenu.getDrawerLayout().getDrawerLockMode(Gravity.START) : 0) == DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
                getSupportFragmentManager().popBackStackImmediate();
            } else {
                if (mLastMenuItemSelected == START_ITEM_ID) {
                    finish();
                } else {
                    String firstTag = getSupportFragmentManager().getBackStackEntryAt(0).getName();
                    getSupportFragmentManager().popBackStackImmediate(firstTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    mDrawerMenu.setSelection(START_ITEM_ID, true);
                }
            }
        }
    }

    @Override
    public boolean onNavigationClickListener(View clickedView) {
        onBackNavigation();
        return true;
    }
}
