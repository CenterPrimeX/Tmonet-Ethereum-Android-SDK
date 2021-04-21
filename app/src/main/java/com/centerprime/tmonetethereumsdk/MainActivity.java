package com.centerprime.tmonetethereumsdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.centerprime.tmonet_ethereum_sdk.EthManager;

import java.math.BigDecimal;
import java.math.BigInteger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EthManager ethManager = EthManager.getInstance();
        ethManager.init("https://ropsten.infura.io/v3/a396c3461ac048a59f389c7778f06689");

        String password = "xxxx12345";
        ethManager.createWallet(password, this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wallet -> {
                    String walletAddress = wallet.getAddress();
                    String keystore = wallet.getKeystore();
                    Toast.makeText(this, wallet.getAddress(), Toast.LENGTH_SHORT).show();
                    System.out.println(wallet.getAddress());
                }, error -> {
                    error.printStackTrace();
                });
    }
}