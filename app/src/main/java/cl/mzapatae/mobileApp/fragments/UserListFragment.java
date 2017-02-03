package cl.mzapatae.mobileApp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.base.FragmentBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserListFragment extends FragmentBase {
    private static final String TAG = "UserList Fragment";
    private Context mContext;


    public UserListFragment() {
        // Required empty public constructor
    }

    public static UserListFragment newInstance() {
        return new UserListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        mContext = getActivity();
        Log.d(TAG, " - On Create - ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }

}
