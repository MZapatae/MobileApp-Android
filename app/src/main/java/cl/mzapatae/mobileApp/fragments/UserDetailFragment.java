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
import cl.mzapatae.mobileApp.utils.VectorialImage;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 05-14-17
 * E-mail: miguel.zapatae@gmail.com
 */

public class UserDetailFragment extends BaseFragment {
    private static final String TAG = "UserDetail Fragment";
    private static final String ARG_USER_NAME = "userName";
    private static final String ARG_USER_ID = "userId";

    @BindView(R.id.toolbar) Toolbar mToolbar;

    private Context mContext;
    private String mUserName;
    private String mUserAge;
    private OnToolbarAddedListener mOnToolbarAddedListener;
    private OnLockDrawerMenuListener mOnLockDrawerMenuListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnToolbarAddedListener = setOnViewsCreatedListener(context);
        mOnLockDrawerMenuListener = setOnLockDrawerMenuListener(context);
    }

    public UserDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id : User id of the user we get detail info.
     * @param name : Name of the user we get detail info.
     * @return A new instance of fragment UserDetailFragment
     */
    public static UserDetailFragment newInstance(int id, String name) {
        UserDetailFragment fragment = new UserDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, String.valueOf(id));
        args.putString(ARG_USER_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        if (getArguments() != null) {
            mUserName = getArguments().getString(ARG_USER_NAME);
            mUserAge = getArguments().getString(ARG_USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_detail, container, false);
        ButterKnife.bind(this, view);

        mOnToolbarAddedListener.onToolbarAdded(mToolbar);
        mOnLockDrawerMenuListener.onLockDrawerMenuStatus(true);
        mToolbar.setNavigationIcon(VectorialImage.setVectorialDrawable(mContext, R.drawable.ic_arrow_back_24dp, R.color.toolbar_arrow_light));

        //TODO: Arreglar funcionamiento de boton navegacion (abre menu, deberia retroceder)
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mOnLockDrawerMenuListener.onLockDrawerMenuStatus(false);
    }
}
