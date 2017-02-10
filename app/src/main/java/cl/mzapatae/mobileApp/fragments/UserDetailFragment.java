package cl.mzapatae.mobileApp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.base.BaseFragment;

/**
 */
public class UserDetailFragment extends BaseFragment {
    private static final String TAG = "UserDetail Fragment";

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.constraintLayout) ConstraintLayout mConstraintLayout;

    private Context mContext;
    private OnFragmentLoadedListener mOnFragmentLoadedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnFragmentLoadedListener = (OnFragmentLoadedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentLoadedListener");
        }
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
        mOnFragmentLoadedListener.onToolbarViewLoaded(mToolbar);
        mOnFragmentLoadedListener.onConstraintLayoutLoaded(mConstraintLayout);
    }
}
