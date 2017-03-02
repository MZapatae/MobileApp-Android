package cl.mzapatae.mobileApp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.base.BaseFragment;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class UserDetailFragment extends BaseFragment {
    private static final String TAG = "UserDetail Fragment";

    @BindView(R.id.toolbar) Toolbar mToolbar;

    private Context mContext;
    private OnToolbarAddedListener mOnToolbarAddedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnToolbarAddedListener = setOnViewsCreatedListener(context);
    }

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
        View view = inflater.inflate(R.layout.fragment_user_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mOnToolbarAddedListener.onToolbarAdded(mToolbar);
    }
}
