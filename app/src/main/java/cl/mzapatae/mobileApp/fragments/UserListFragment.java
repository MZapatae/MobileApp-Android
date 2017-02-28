package cl.mzapatae.mobileApp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.apiclient.RestServices;
import cl.mzapatae.mobileApp.apiclient.RetrofitCallback;
import cl.mzapatae.mobileApp.apiclient.RetrofitClient;
import cl.mzapatae.mobileApp.base.BaseFragment;
import cl.mzapatae.mobileApp.datamodel.adapters.UserListAdapter;
import cl.mzapatae.mobileApp.datamodel.gson.CelmedianosResponse;
import cl.mzapatae.mobileApp.datamodel.objects.APIError;
import cl.mzapatae.mobileApp.utils.DialogManager;
import cl.mzapatae.mobileApp.utils.LocalStorage;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class UserListFragment extends BaseFragment {
    private static final String TAG = "UserList Fragment";
    @BindView(R.id.imageView_banner) ImageView mImageViewBanner;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.collapsingToolbarLayout) CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.appbarLayout) AppBarLayout mAppbarLayout;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.nestedScrollView) NestedScrollView mNestedScrollView;

    private Context mContext;
    private OnToolbarAddedListener mOnToolbarAddedListener;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnToolbarAddedListener = setOnViewsCreatedListener(context);
    }

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, view);

        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserList();
        mOnToolbarAddedListener.onToolbarAdded(mToolbar);
    }

    private void getUserList() {
        final MaterialDialog loadingDialog = DialogManager.createLoadingDialog(getActivity()).build();
        final Handler runLoadingIndicator = new Handler();
        final Runnable showLoadingIndicator = new Runnable() {
            @Override
            public void run() {
                loadingDialog.show();
            }
        };

        RestServices restServices = RetrofitClient.setAuthConnection(RestServices.class, LocalStorage.getPrefUserToken());
        Call<CelmedianosResponse> call = restServices.getUserList();
        call.enqueue(new RetrofitCallback<CelmedianosResponse>() {

            @Override
            public void onStart() {
                runLoadingIndicator.postDelayed(showLoadingIndicator, 600);
            }

            @Override
            public void onFinish() {
                runLoadingIndicator.removeCallbacks(showLoadingIndicator);
                if (loadingDialog.isShowing()) loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Call<CelmedianosResponse> call, Response<CelmedianosResponse> response) {
                mAdapter = new UserListAdapter(mContext, response.body());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<CelmedianosResponse> call, APIError error) {
                //TODO: Add validation for Invalid Token. Call Logout and launch intent to LandingScreen class
                DialogManager.createErrorDialog(mContext, RetrofitClient.buildErrorMessage(error));
            }

            @Override
            public void onError(Call<CelmedianosResponse> call, Throwable t) {
                DialogManager.createErrorDialog(mContext, t.getMessage());
            }
        });
    }
}