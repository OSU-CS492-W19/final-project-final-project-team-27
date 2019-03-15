package com.example.swdb;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swdb.data.SWPerson;

import java.util.List;

public class SWSearchAdapter extends RecyclerView.Adapter<SWSearchAdapter.SearchResultViewHolder> {
    private List<SWPerson> mPeople;
    OnSearchItemClickListener mSearchItemClickListener;

    public interface OnSearchItemClickListener {
        void onSearchItemClick(SWPerson person);
    }

    SWSearchAdapter(OnSearchItemClickListener searchItemClickListener) {
        mSearchItemClickListener = searchItemClickListener;
    }

    public void updateSearchResults(List<SWPerson> people) {
        mPeople = people;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mPeople != null) {
            return mPeople.size();
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_result_item, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int pos) {
        holder.bind(mPeople.get(pos));
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private TextView mSearchResultTV;

        public SearchResultViewHolder(View itemView) {
            super(itemView);
            mSearchResultTV = itemView.findViewById(R.id.tv_search_result);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SWPerson searchResult = mPeople.get(getAdapterPosition());
                    mSearchItemClickListener.onSearchItemClick(searchResult);
                }
            });
        }

        public void bind(SWPerson person) {
            mSearchResultTV.setText(person.name);
        }
    }
}


