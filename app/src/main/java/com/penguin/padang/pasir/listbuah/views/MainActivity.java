package com.penguin.padang.pasir.listbuah.views;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.penguin.padang.pasir.listbuah.R;
import com.penguin.padang.pasir.listbuah.helpers.adapter.FruitListAdapter;
import com.penguin.padang.pasir.listbuah.interfaces.MainPresenterInterface;
import com.penguin.padang.pasir.listbuah.interfaces.MainViewInterface;
import com.penguin.padang.pasir.listbuah.models.Fruit;
import com.penguin.padang.pasir.listbuah.models.Fruits;
import com.penguin.padang.pasir.listbuah.presenters.MainPresenterImp;

import java.util.Collections;
import java.util.List;

public class MainActivity extends ListActivity implements MainViewInterface {
    private MainPresenterInterface presenter;
    private ProgressDialog dialog;

    private FruitListAdapter fruitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);



        presenter = new MainPresenterImp(this);
        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.show();
        presenter.getData();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void showData(Fruits fruits) {
        List<Fruit> fruit = fruits.getFruit();
        Collections.sort(fruit);

        fruitAdapter = new FruitListAdapter(this);
        for(int i = 0; i< fruit.size(); i++){
            if(i == 0){
                fruitAdapter.addSectionHeaderItem(fruit.get(i).getCategory());
            } else if(!fruit.get(i).getCategory().toString().equals(fruit.get(i - 1).getCategory().toString())){
                fruitAdapter.addSectionHeaderItem(fruit.get(i).getCategory());
            }


            fruitAdapter.addItem(fruit.get(i).getName());
        }
        setListAdapter(fruitAdapter);
    }

    @Override
    public ProgressDialog getDialog() {
        return dialog;
    }
}
