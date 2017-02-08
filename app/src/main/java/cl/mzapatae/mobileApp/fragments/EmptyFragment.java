package cl.mzapatae.mobileApp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.base.BaseFragment;


/**
 * Only for Development or Test Concepts.
 * TODO: Delete in Productions Releases
 */
public class EmptyFragment extends BaseFragment {
    private static final String TAG = "Test Fragment";
    private Context mContext;


    public EmptyFragment() {
        // Required empty public constructor
    }

    public static EmptyFragment newInstance() {
        return new EmptyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        mContext = getActivity();
        Log.d(TAG, " - On Create - ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, " - OnCreateView - ");
        return inflater.inflate(R.layout.fragment_empty, container, false);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, " - OnAttach - ");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, " - OnViewCreated - ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, " - OnStart - ");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, " - OnResume - ");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, " - OnPause - ");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, " - OnStop - ");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, " - OnDestroy - ");

    }

}
