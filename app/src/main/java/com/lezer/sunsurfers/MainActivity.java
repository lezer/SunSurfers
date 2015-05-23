package com.lezer.sunsurfers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


public class MainActivity extends AppCompatActivity {

    private Drawer.Result drawerResult;

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

    @Override
    public void onBackPressed() {
        if (drawerResult != null && drawerResult.isDrawerOpen()) {
            drawerResult.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void initNavDrawer(Toolbar toolbar) {

        AccountHeader.Result accHeaderResult = createAccHeader();

        drawerResult = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(accHeaderResult)
                .withActionBarDrawerToggleAnimated(true)
                .withDisplayBelowToolbar(true)
                .addDrawerItems(
                        initDrawerItems()
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                        startActivity(intent);
                    }
                })
                .build();
    }

    private IDrawerItem[] initDrawerItems() {
        return new IDrawerItem[]{new PrimaryDrawerItem()
                .withName(R.string.draw_chat)
                .withIcon(R.drawable.ic_chat_black_18dp),
                new SecondaryDrawerItem()
                        .withName(R.string.draw_map)
                        .withIcon(R.drawable.ic_map_black_18dp),
                new SecondaryDrawerItem()
                        .withName(R.string.draw_adt)
                        .withIcon(R.drawable.ic_forum_black_18dp)};
    }

    private AccountHeader.Result createAccHeader() {

        IProfile profile = new ProfileDrawerItem()
                .withName("Lezer")
                .withEmail("lezerlezerlezer@gmail.com")
                .withIcon(getResources().getDrawable(R.drawable.anim_man));

        return new AccountHeader()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background_material4)
                .addProfiles(profile)
                .build();
    }
}
