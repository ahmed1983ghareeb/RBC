package com.ghareeb.rbc.fragment;


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
public class InteracFragment extends Fragment {

    AccountDao accountDao;

    public InteracFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_interac, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        accountDao = AccountFactory.getAccountDao();
        String email = this.getArguments().getString("default");
        final Account account = accountDao.getAccountByEmail(email);

        final EditText emailEditText = view.findViewById(R.id.emailEditText);
        final EditText amountToSendTextField = view.findViewById(R.id.amountEditText);

        Button sendBtn = view.findViewById(R.id.buttonSend);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailEditText.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(),"Reciever email can not be empty",Toast.LENGTH_SHORT).show();
                }else if (accountDao.getAccountByEmail(emailEditText.getText().toString()) != null) {
                    if (amountToSendTextField.getText().toString().isEmpty()) {
                        Toast.makeText(v.getContext(), "Amount can not be empty", Toast.LENGTH_SHORT).show();
                    } else {
                        Account recieverAccount = accountDao.getAccountByEmail(emailEditText.getText().toString());
                        recieverAccount.setBalance(recieverAccount.getBalance() + Double.valueOf(amountToSendTextField.getText().toString()));
                        account.setBalance(account.getBalance() - Double.valueOf(amountToSendTextField.getText().toString()));
                        Toast.makeText(v.getContext(), "Transaction was succesfull your new balance is " + account.getBalance(), Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(v.getContext(),"Reciever email is not in your contacts",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
