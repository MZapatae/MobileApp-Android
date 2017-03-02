package cl.mzapatae.mobileApp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import cl.mzapatae.mobileApp.fragments.UserDetailFragment;
import cl.mzapatae.mobileApp.fragments.UserListFragment;
import cl.mzapatae.mobileApp.helpers.transitions.FragmentsDrawerTransitionSet;
import cl.mzapatae.mobileApp.utils.LocalStorage;

public class MainActivity extends BaseActivity implements Drawer.OnDrawerItemClickListener, BaseFragment.OnToolbarAddedListener {
    private static final String TAG = "Main Activity";
    @BindView(R.id.fragment_container) FrameLayout fragmentContainer;

    private Drawer mDrawerMenu;
    private int mDrawerSelectedIdentifier;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        createDrawerMenu(SetUpDrawerItems());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        //mDrawerMenu.setSelection(mDrawerSelectedIdentifier,false);
    }

    private List<IDrawerItem> SetUpDrawerItems() {
        PrimaryDrawerItem userListItem = new PrimaryDrawerItem().withName("Lista Usuarios").withIdentifier(1);
        PrimaryDrawerItem userDetailItem = new PrimaryDrawerItem().withName("Detalle Usuario").withIdentifier(2);
        PrimaryDrawerItem testItem = new PrimaryDrawerItem().withName("Test").withIdentifier(3);
        PrimaryDrawerItem logoutItem = new PrimaryDrawerItem().withName("Cerrar Sesion").withIdentifier(4);

        List<IDrawerItem> menuDrawerItems = new ArrayList<>();
        menuDrawerItems.add(userListItem);
        menuDrawerItems.add(userDetailItem);
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
                .withName(LocalStorage.getPrefUserFirstname() + " " + LocalStorage.getPrefUserLastname())
                .withEmail(LocalStorage.getPrefUserEmail())
                .withIcon(LocalStorage.getPrefUserAvatar());

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
                }).build();

        mDrawerMenu = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(headerMenu)
                .withDrawerItems(drawerItems)
                .withCloseOnClick(true)
                .withSelectedItem(1)
                .withOnDrawerItemClickListener(this)
                .build();

        mDrawerMenu.setSelection(1, true);
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        if (drawerItem.getIdentifier() != mDrawerSelectedIdentifier) {
            Fragment fragment = null;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            switch (((int) drawerItem.getIdentifier())) {
                case 1:
                    fragment = getSupportFragmentManager().findFragmentByTag(UserListFragment.class.getName());
                    if (!UserListFragment.class.isInstance(fragment))
                        fragment = UserListFragment.newInstance();
                    break;

                case 2:
                    fragment = getSupportFragmentManager().findFragmentByTag(UserDetailFragment.class.getName());
                    if (!UserDetailFragment.class.isInstance(fragment))
                        fragment = UserDetailFragment.newInstance();
                    break;

                case 3:
                    fragment = getSupportFragmentManager().findFragmentByTag(EmptyFragment.class.getName());
                    if (!EmptyFragment.class.isInstance(fragment))
                        fragment = EmptyFragment.newInstance();
                    break;

                case 4:
                    logoutUser();
                    break;
            }

            try {
                if (fragment != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        fragment.setSharedElementEnterTransition(new FragmentsDrawerTransitionSet());
                        fragment.setSharedElementReturnTransition(new FragmentsDrawerTransitionSet());

                        if (mToolbar != null)
                            transaction.addSharedElement(mToolbar, "ToolbarTransition");
                    }

                    transaction.replace(R.id.fragment_container, fragment, fragment.getClass().getName());
                    transaction.addToBackStack(fragment.getClass().getName());
                    transaction.commit();

                    mDrawerSelectedIdentifier = (int) drawerItem.getIdentifier();
                }
            }catch(Exception e){
                Log.e(TAG, "Exception: " + e.getMessage());
                mDrawerMenu.setSelection(mDrawerSelectedIdentifier, false);
            }
        } else {
            mDrawerMenu.closeDrawer();
        }
        return false;
    }

    private void logoutUser() {
        LocalStorage.logoutUser();
        Intent launchWelcomeScreen = new Intent(MainActivity.this, LandingActivity.class);
        this.startActivity(launchWelcomeScreen);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerMenu != null && mDrawerMenu.isDrawerOpen()) {
            mDrawerMenu.closeDrawer();
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
            } else {
                // Empty all backstack and fragments references and Create initial fragment again
                FragmentManager.BackStackEntry first = getSupportFragmentManager().getBackStackEntryAt(0);
                getSupportFragmentManager().popBackStackImmediate(first.getId(), 0);
                mDrawerSelectedIdentifier = 1;
                mDrawerMenu.setSelection(1, false);
            }
        }
    }

    @Override
    public void onToolbarAdded(Toolbar toolbar) {
        mToolbar = toolbar;
        mDrawerMenu.setToolbar(this, toolbar, true);
    }
}
