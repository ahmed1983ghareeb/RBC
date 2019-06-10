package com.ghareeb.rbc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ghareeb.rbc.fragment.AccountFragment;
import com.ghareeb.rbc.fragment.InteracFragment;
import com.ghareeb.rbc.model.Account;
import com.ghareeb.rbc.model.AccountDao;
import com.ghareeb.rbc.model.AccountFactory;

public class MainActivity extends AppCompatActivity {

    AccountDao accountDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountDao = AccountFactory.getAccountDao();
        accountDao.addAccount(new Account(1000,"person@sample.com"));

        accountDao.addAccount(new Account(1000,"p1@sample.com"));
        accountDao.addAccount(new Account(1000,"p2@sample.com"));
        accountDao.addAccount(new Account(1000,"p2@sample.com"));

        initialize();

    }

    private void initialize() {
        Button accountBtn = findViewById(R.id.accountButton);
        accountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAccountFragment();
            }
        });

        Button interacBtn = findViewById(R.id.interacButton);
        interacBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInteracFragment();
            }
        });
    }

    private void showInteracFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        InteracFragment interacFragment = new InteracFragment();

        Bundle bundle = new Bundle();
        bundle.putString("default","person@sample.com");
        interacFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.container,interacFragment);
        fragmentTransaction.addToBackStack("interac");
        fragmentTransaction.commit();
    }

    private void showAccountFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AccountFragment accountFragment = new AccountFragment();

        Bundle bundle = new Bundle();
        bundle.putString("default","person@sample.com");
        accountFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.container,accountFragment);
        fragmentTransaction.addToBackStack("account");
        fragmentTransaction.commit();
    }


}
