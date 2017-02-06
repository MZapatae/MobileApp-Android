package cl.mzapatae.mobileApp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.base.FragmentBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailFragment extends FragmentBase {
    private static final String TAG = "UserDetail Fragment";
    private Context mContext;

    public UserDetailFragment() {
        // Required empty public constructor
    }

    public static UserDetailFragment newInstance() {
        return new UserDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_detail, container, false);
    }
}
