package cl.mzapatae.mobileApp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
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

import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.base.ActivityBase;
import cl.mzapatae.mobileApp.enums.Animation;
import cl.mzapatae.mobileApp.fragments.EmptyFragment;
import cl.mzapatae.mobileApp.fragments.UserDetailFragment;
import cl.mzapatae.mobileApp.fragments.UserListFragment;
import cl.mzapatae.mobileApp.utils.LocalStorage;

public class MainActivity extends ActivityBase implements Drawer.OnDrawerItemClickListener {
    private static final String TAG = "Main Activity";
    private Drawer mDrawerMenu;
    private int mDrawerSelectedIdentifier = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDrawerMenu(SetUpDrawerItems());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawerMenu.setSelection(1, true);
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
                .withDelayDrawerClickEvent(450)
                .build();
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        if (drawerItem.getIdentifier() != mDrawerSelectedIdentifier) {
            Fragment fragment = null;

            switch (((int) drawerItem.getIdentifier())) {
                case 1:
                    fragment = UserListFragment.newInstance();
                    break;
                case 2:
                    fragment = UserDetailFragment.newInstance();
                    break;
                case 3:
                    fragment = EmptyFragment.newInstance();
                    break;
                case 4:
                    logoutUser();
                default:
                    break;
            }
            loadFragmentFromMenu(fragment);
            mDrawerSelectedIdentifier = (int) drawerItem.getIdentifier();
        } else mDrawerMenu.closeDrawer();
        return false;
    }

    private void loadFragmentFromMenu(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        boolean fragmentPopped = getSupportFragmentManager().popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped) {
            replaceFragment(fragment, fragment.getClass().getName(), Animation.FADE, true);
        }
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
                getSupportFragmentManager().popBackStack(0, 0);
                mDrawerSelectedIdentifier = 1;
                mDrawerMenu.setSelection(1, false);
            }
        }
    }
}
