package com.ghareeb.rbc.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ghareeb.rbc.R;
import com.ghareeb.rbc.model.Account;
import com.ghareeb.rbc.model.AccountDao;
import com.ghareeb.rbc.model.AccountFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    AccountDao accountDao;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        accountDao = AccountFactory.getAccountDao();

        final String email = this.getArguments().getString("default");
        final Account account = accountDao.getAccountByEmail(email);

        String balance = String.valueOf(account.getBalance());

        final EditText addFundTextField = view.findViewById(R.id.amountEditText);
        final TextView balanceTextView = view.findViewById(R.id.balanceTextView);
        balanceTextView.setText(balance);

        Button addFundBtn = view.findViewById(R.id.buttonAdd);
        addFundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!addFundTextField.getText().toString().isEmpty()){
                    accountDao.getAccountByEmail(email).setBalance(account.getBalance()+ Double.valueOf(addFundTextField.getText().toString()));
                    addFundTextField.setText("");
                    balanceTextView.setText(String.valueOf(account.getBalance()));
                    Toast.makeText(v.getContext(),"Funds added Successfully!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(v.getContext(),"Amount cannot be empty!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
