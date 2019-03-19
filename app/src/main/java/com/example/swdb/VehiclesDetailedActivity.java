package com.example.swdb;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;

import com.example.swdb.data.SWVehicle;
import com.example.swdb.utils.SWUtils;


public class VehiclesDetailedActivity extends AppCompatActivity {

    private TextView mName;
    private TextView mModel;
    private TextView mManu;
    private TextView mCost;
    private TextView mLength;
    private TextView mSpeed;
    private TextView mCrew;
    private TextView mPassenger;
    private TextView mCargo;
    private TextView mConsum;
    private TextView mVehClass;


    private SWVehicle mVehic;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_search_detail);

        mName = findViewById(R.id.tv_name);
        mModel = findViewById(R.id.tv_model);
        mManu = findViewById(R.id.tv_manu);
        mCost = findViewById(R.id.tv_cost);
        mLength = findViewById(R.id.tv_length);
        mSpeed = findViewById(R.id.tv_speed);
        mCrew = findViewById(R.id.tv_crew);
        mPassenger = findViewById(R.id.tv_passenger);
        mCargo = findViewById(R.id.tv_cargo);
        mConsum = findViewById(R.id.tv_consum);
        mVehClass = findViewById(R.id.tv_vehclass);




        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(SWVehicle.EXTRA_VEH_ITEM)) {
            mVehic = (SWVehicle) intent.getSerializableExtra(
                    SWVehicle.EXTRA_VEH_ITEM
            );
            fillInLayout(mVehic);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_images:
                taketoimages();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void taketoimages() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String searchingfor = mVehic.name;

        Uri geoUri = Uri.parse("geo:0,0").buildUpon()
                .appendQueryParameter("q", searchingfor)
                .build();
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://images.google.com/search?num=10&hl=en&site=&tbm=isch&source=hp‌​&biw=1366&bih=667&q=" + mVehic.name + "&oq=cars&gs_l=img.3..0l10.748.1058.0.1306.4.4.0.0.0.0.165‌​.209.2j1.3.0...0.0...1ac.1.8RNsNEqlcZc"));

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    private void fillInLayout(SWVehicle vehicItem) {


        String vehicname = (vehicItem.name);
        String vehicmodel = getString(R.string.vehic_item_model, vehicItem.model);
        String vehicmanu = getString(R.string.vehic_item_manu, vehicItem.manufacturer);
        String vehiccost = getString(R.string.vehic_item_cost, vehicItem.cost_in_credits);
        String vehiclen = getString(R.string.vehic_item_length, vehicItem.length);
        String vehicspeed = getString(R.string.vehic_item_speed, vehicItem.max_atmosphering_speed);
        String vehiccrew = getString(R.string.vehic_item_crew, vehicItem.crew);
        String vehicpass = getString(R.string.vehic_item_pass, vehicItem.passengers);
        String vehiccargo = getString(R.string.vehic_item_cargo, vehicItem.cargo_capacity);
        String vehicconsum = getString(R.string.vehic_item_consum, vehicItem.consumables);
        String vehicclass = getString(R.string.vehic_item_class, vehicItem.vehicle_class);

        mName.setText(vehicname);
        mModel.setText(vehicmodel);
        mManu.setText(vehicmanu);
        mCost.setText(vehiccost);
        mLength.setText(vehiclen);
        mSpeed.setText(vehicspeed);
        mCrew.setText(vehiccrew);
        mPassenger.setText(vehicpass);
        mCargo.setText(vehiccargo);
        mConsum.setText(vehicconsum);
        mVehClass.setText(vehicclass);


    }
}