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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Context mContext;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EmptyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmptyFragment newInstance(String param1, String param2) {
        EmptyFragment fragment = new EmptyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        Log.d(TAG, " - On Create - ");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
