package com.penguin.padang.pasir.listbuah.interfaces;

import android.app.ProgressDialog;
import android.content.Context;

import com.penguin.padang.pasir.listbuah.models.Fruits;

/**
 * Created by jowy on 8/11/16.
 */
public interface MainViewInterface {

    Context getViewContext();

    void showData(Fruits fruits);

    ProgressDialog getDialog();
}
