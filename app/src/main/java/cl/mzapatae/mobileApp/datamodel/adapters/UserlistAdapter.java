package cl.mzapatae.mobileApp.datamodel.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.mzapatae.mobileApp.R;
import cl.mzapatae.mobileApp.datamodel.gson.CelmedianosResponse;

/**
 * @author Miguel A. Zapata - MZapatae
 * @version 1.0
 * Created on: 28-02-17
 * E-mail: miguel.zapatae@gmail.com
 */


public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private CelmedianosResponse mDataset;
    private Context mContext;

    public UserListAdapter(Context context, CelmedianosResponse dataset) {
        this.mContext = context;
        this.mDataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Resources resources = mContext.getResources();
        String name = String.format(resources.getString(R.string.itemlist_name), mDataset.getUserList().get(position).getName());
        String email = String.format(resources.getString(R.string.itemlist_email), mDataset.getUserList().get(position).getEmail());

        holder.mTextViewName.setText(name);
        holder.mTextViewEmail.setText(email);
    }

    @Override
    public int getItemCount() {
        return mDataset.getUserList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_name) TextView mTextViewName;
        @BindView(R.id.textView_email) TextView mTextViewEmail;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
