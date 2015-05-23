package com.lezer.sunsurfers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        initNavDrawer(toolbar);
    }

    private void initNavDrawer(Toolbar toolbar) {

        AccountHeader.Result accHeaderResult = new AccountHeader()
                .withActivity(this)
                //.withHeaderBackground(R.drawable.background_material)
                .build();

        Drawer.Result drawerResult  = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(accHeaderResult)
                .withActionBarDrawerToggleAnimated(true)
                .withDisplayBelowToolbar(true)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.draw_chat)
                                .withIcon(R.drawable),
                        new SecondaryDrawerItem()
                                .withName(R.string.draw_map),
                        new SecondaryDrawerItem()
                                .withName(R.string.draw_adt)
                )
                .build();
    }
}
