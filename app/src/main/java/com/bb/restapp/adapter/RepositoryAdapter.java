package com.bb.restapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bb.restapp.R;
import com.bb.restapp.model.GitResult;
import com.bb.restapp.util.DebugLogger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    private List<GitResult> resultList;

    public RepositoryAdapter(List<GitResult> resultList) {
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.git_item_layout, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryAdapter.RepositoryViewHolder holder, int position) {
        String dateUpdate = "";

        try
        {
            dateUpdate = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(resultList
                .get(position).getUpdatedAt()).toString();
        }
        catch (ParseException e){
//            e.printStackTrace();   log errors in debug file instead
            DebugLogger.logError(e);
        }
        holder.repoNameTextView.setText(resultList.get(position).getName());
        holder.repoDateTextView.setText(dateUpdate);

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    class RepositoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.repo_name_textView)
        TextView repoNameTextView;

        @BindView(R.id.repo_date_textView)
        TextView repoDateTextView;

        public RepositoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

