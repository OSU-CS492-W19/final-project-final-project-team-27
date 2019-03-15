package com.example.swdb;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swdb.data.SWFilm;
import com.example.swdb.data.SWPerson;
import com.example.swdb.data.SWPlanet;
import com.example.swdb.data.SWSearchResult;
import com.example.swdb.data.SWSpecies;
import com.example.swdb.data.SWStarship;
import com.example.swdb.data.SWVehicle;
import com.example.swdb.utils.SWUtils;

public class SWSearchAdapter extends RecyclerView.Adapter<SWSearchAdapter.SearchResultViewHolder> {
    private SWSearchResult mResults;
    OnSearchItemClickListener mSearchItemClickListener;

    public interface OnSearchItemClickListener {
        void onSearchFilmClick(SWFilm person);
        void onSearchPersonClick(SWPerson person);
        void onSearchPlanetClick(SWPlanet person);
        void onSearchSpeciesClick(SWSpecies person);
        void onSearchStarshipClick(SWStarship person);
        void onSearchVehicleClick(SWVehicle person);
    }

    SWSearchAdapter(OnSearchItemClickListener searchItemClickListener) {
        mSearchItemClickListener = searchItemClickListener;
    }

    public void updateSearchResults(SWSearchResult results) {
        mResults = results;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mResults != null) {
            switch (SWUtils.SEARCH_PREF) {
                case "films":
                    return mResults.films.size();
                case "people":
                    return mResults.people.size();
                case "planets":
                    return mResults.planets.size();
                case "species":
                    return mResults.species.size();
                case "starships":
                    return mResults.starships.size();
                case "vehicles":
                    return mResults.vehicles.size();
                default:
                    return 0;
            }
        }
        return 0;
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
        switch (SWUtils.SEARCH_PREF) {
            case "films":
                holder.bindFilm(mResults.films.get(pos));
                break;
            case "people":
                holder.bindPerson(mResults.people.get(pos));
                break;
            case "planets":
                holder.bindPlanet(mResults.planets.get(pos));
                break;
            case "species":
                holder.bindSpecies(mResults.species.get(pos));
                break;
            case "starships":
                holder.bindStarship(mResults.starships.get(pos));
                break;
            case "vehicles":
                holder.bindVehicle(mResults.vehicles.get(pos));
                break;
            default:
                break;
        }
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private TextView mSearchResultTV;

        public SearchResultViewHolder(View itemView) {
            super(itemView);
            mSearchResultTV = itemView.findViewById(R.id.tv_search_result);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                switch (SWUtils.SEARCH_PREF) {
                    case "films":
                        SWFilm film = mResults.films.get(getAdapterPosition());
                        mSearchItemClickListener.onSearchFilmClick(film);
                        break;
                    case "people":
                        SWPerson person = mResults.people.get(getAdapterPosition());
                        mSearchItemClickListener.onSearchPersonClick(person);
                        break;
                    case "planets":
                        SWPlanet planet = mResults.planets.get(getAdapterPosition());
                        mSearchItemClickListener.onSearchPlanetClick(planet);
                        break;
                    case "species":
                        SWSpecies species = mResults.species.get(getAdapterPosition());
                        mSearchItemClickListener.onSearchSpeciesClick(species);
                        break;
                    case "starships":
                        SWStarship starship = mResults.starships.get(getAdapterPosition());
                        mSearchItemClickListener.onSearchStarshipClick(starship);
                        break;
                    case "vehicles":
                        SWVehicle vehicle = mResults.vehicles.get(getAdapterPosition());
                        mSearchItemClickListener.onSearchVehicleClick(vehicle);
                        break;
                    default:
                        break;
                }
                }
            });
        }

        public void bindFilm(SWFilm film) {
            mSearchResultTV.setText(film.title);
        }

        public void bindPerson(SWPerson person) {
            mSearchResultTV.setText(person.name);
        }

        public void bindPlanet(SWPlanet planet) {
            mSearchResultTV.setText(planet.name);
        }

        public void bindSpecies(SWSpecies species) {
            mSearchResultTV.setText(species.name);
        }

        public void bindStarship(SWStarship starship) {
            mSearchResultTV.setText(starship.name);
        }

        public void bindVehicle(SWVehicle vehicle) {
            mSearchResultTV.setText(vehicle.name);
        }
    }
}


