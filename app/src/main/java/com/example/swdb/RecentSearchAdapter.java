package com.example.swdb;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swdb.utils.SWUtils;


public class RecentSearchAdapter extends RecyclerView.Adapter<RecentSearchAdapter.SearchResultViewHolder> {

    private String[] mTheRecentSearches;
    OnSearchItemClickListener mSeachItemClickListener;

    public interface OnSearchItemClickListener {
        void onSearchItemClick(String searches);
    }

    RecentSearchAdapter(OnSearchItemClickListener searchItemClickListener) {
        mSeachItemClickListener = searchItemClickListener;
    }

    public void updateSearchResults(String[] searches) {
        mTheRecentSearches = searches;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTheRecentSearches != null) {
            return mTheRecentSearches.length;
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recent_search_result, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
        holder.bind(mTheRecentSearches[position]);
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private TextView mSearchResultTV;

        public SearchResultViewHolder(View itemView) {
            super(itemView);
            mSearchResultTV = itemView.findViewById(R.id.tv_search_result);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String searchResult = mTheRecentSearches[getAdapterPosition()];
                    mSeachItemClickListener.onSearchItemClick(searchResult);
                }
            });
        }

        public void bind(String search) {
            mSearchResultTV.setText(search);
        }
    }


}
