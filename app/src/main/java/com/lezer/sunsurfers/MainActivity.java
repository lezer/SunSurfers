package com.lezer.sunsurfers;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.lezer.sunsurfers.fragments.ChatFragment;
import com.lezer.sunsurfers.fragments.ForumFragment;
import com.lezer.sunsurfers.fragments.MapFragment;
import com.lezer.sunsurfers.fragments.SettingsFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


public class MainActivity extends AppCompatActivity {

    private String TAG = "lezer";
    private Drawer.Result drawerResult;
    public int CHAT = 2;
    public int FORUM = 3;
    public int MAP = 4;
    public int SETTINGS = 5;

    private ChatFragment chatFragment;
    private ForumFragment forumFragment;
    private MapFragment mapFragment;
    private SettingsFragment settingsFragment;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        initNavDrawer(toolbar);

        manager = getSupportFragmentManager();

        chatFragment = new ChatFragment();
        forumFragment = new ForumFragment();
        mapFragment = new MapFragment();
        settingsFragment = new SettingsFragment();
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
                        Log.i(TAG, iDrawerItem.getIdentifier() + "");

                        transaction = manager.beginTransaction();

                        switch (iDrawerItem.getIdentifier()) {
                            case 2:
                                if (manager.findFragmentByTag("ChatFragmentTag") == null) {
                                    transaction.add(R.id.container, chatFragment, chatFragment.TAG);
                                }
                                break;
                            case 3:
                                if (manager.findFragmentByTag("ForumFragmentTag") == null) {
                                    transaction.add(R.id.container, forumFragment, forumFragment.TAG);
                                }
                                break;
                            case 4:
                                if (manager.findFragmentByTag("MapFragmentTag") == null) {
                                    transaction.add(R.id.container, mapFragment, mapFragment.TAG);
                                }
                                break;
                            case 5:
                                if (manager.findFragmentByTag("SettingsFragmentTag") == null) {
                                    transaction.add(R.id.container, settingsFragment, settingsFragment.TAG);
                                }
                                break;
                        }
                        transaction.commit();
                    }
                })
                .build();
    }

    private IDrawerItem[] initDrawerItems() {
        return new IDrawerItem[]{new PrimaryDrawerItem()
                .withName(R.string.draw_chat)
                .withIdentifier(CHAT)
                .withIcon(R.drawable.ic_chat_black_18dp),
                new SecondaryDrawerItem()
                        .withName(R.string.draw_map)
                        .withIdentifier(MAP)
                        .withIcon(R.drawable.ic_map_black_18dp),
                new SecondaryDrawerItem()
                        .withName(R.string.draw_adt)
                        .withIdentifier(FORUM)
                        .withIcon(R.drawable.ic_forum_black_18dp),
                new DividerDrawerItem(),
                new SecondaryDrawerItem()
                    .withName(R.string.draw_settings)
                    .withIcon(R.drawable.ic_settings_black_18dp)
                .withIdentifier(SETTINGS)
        };
    }

    private AccountHeader.Result createAccHeader() {

        IProfile profile = new ProfileDrawerItem()
                .withName("Lezer")
                .withEmail("lezerlezerlezer@gmail.com")
                .withIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.anim_man, null));

        return new AccountHeader()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background_material)
                .addProfiles(profile)
                .build();
    }
}
