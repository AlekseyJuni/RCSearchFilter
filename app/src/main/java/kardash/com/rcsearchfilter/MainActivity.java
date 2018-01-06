package kardash.com.rcsearchfilter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SearchView searchView;
    RecyclerView listshowrcy;
    List<Item> productlists = new ArrayList<>();

    MainActivityAdapter adapter;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productlists.add(new Item("BMW Motorcycle 2015 Motorrad Konzept ERR", R.drawable.bmw_motorcycle_2015_motorrad_konzept_err));
        productlists.add(new Item("BMW Motorcycle Mountains 2017 G 310 GS Back View", R.drawable.bmw_motorcycle_mountains_2017_g_310_gs_back_view));
        productlists.add(new Item("Ducati 2015 17 Scrambler Classic Side", R.drawable.ducati_2015_17_scrambler_classic_side));
        productlists.add(new Item("Ducati 2018 Panigale V4 S Black Background Red", R.drawable.ducati_2018_panigale_v4_s_black_background_red));
        productlists.add(new Item("Husqvarna Vitpilen Motorcyclist Motion", R.drawable.husqvarna_vitpilen_motorcyclist_motion));
        productlists.add(new Item("Roads Honda CBR1000RR Motion", R.drawable.roads_honda_cbr1000rr_motion));
        productlists.add(new Item("Sriumph Street Triple S Side", R.drawable.sriumph_street_triple_s_side));
        productlists.add(new Item("Triumph Bonneville Bobber Side", R.drawable.triumph_bonneville_bobber_side));
        productlists.add(new Item("Triumph Street Scrambler Side", R.drawable.triumph_street_scrambler_side));
        productlists.add(new Item("Yamaha 2018 YZF R1 Motorcyclist Helmet Motion", R.drawable.yamaha_2018_yzf_r1_motorcyclist_helmet_motion));
        productlists.add(new Item("Yamaha 2018 YZF R1M Side", R.drawable.yamaha_2018_yzf_r1m_side));
        productlists.add(new Item("Yamaha Trike 2016 17 Tricity 125", R.drawable.yamaha_trike_2016_17_tricity_125));
        productlists.add(new Item("Yamaha Trike 2016 Tricity", R.drawable.yamaha_trike_2016_tricity));
        productlists.add(new Item("Yamaha YZF R1 2018 Blue", R.drawable.yamaha_yzf_r1_2018_blue));


        listshowrcy = (RecyclerView) findViewById(R.id.listshow);
        listshowrcy.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        adapter = new MainActivityAdapter(productlists, MainActivity.this);
        listshowrcy.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchfile, menu);

        final MenuItem myActionMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        changeSearchViewTextColor(searchView);
        ((EditText) searchView.findViewById(
                android.support.v7.appcompat.R.id.search_src_text)).
                setHintTextColor(getResources().getColor(R.color.white));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();

                return false;
            }

            @Override            public boolean onQueryTextChange(String newText) {
                final  List<Item> filtermodelist=filter(productlists,newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });

        return true;
    }
    private List<Item> filter(List<Item> pl,String query)
    {
        query=query.toLowerCase();
        final List<Item> filteredModeList=new ArrayList<>();
        for (Item model:pl)
        {
            final String text=model.getName().toLowerCase();
            if (text.startsWith(query))
            {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
    }

    private void changeSearchViewTextColor(View view) {
        if (view != null) {
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(Color.WHITE);
            return;
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                changeSearchViewTextColor(viewGroup.getChildAt(i));
            }
        }
    }
}
}
