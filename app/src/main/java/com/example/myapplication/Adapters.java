package com.example.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Adapters extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;
    public Adapters(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        context = context;
        this.totalTabs = totalTabs;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Register register = new Register();
                return  register;
            case 1:
                Purchase purchase = new Purchase();
                return purchase;
            case 2:
                Checkout checkout = new Checkout();
                return checkout;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
