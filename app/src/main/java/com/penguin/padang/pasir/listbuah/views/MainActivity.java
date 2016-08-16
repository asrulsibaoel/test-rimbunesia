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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class MainActivity extends ListActivity implements MainViewInterface {
    private MainPresenterInterface presenter;
    private ProgressDialog dialog;

    private FruitListAdapter fruitAdapter;

    private List<String> additionalKey;

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

        NavigableMap<String, List<Fruit>> mappedFruit = new TreeMap<>();
        NavigableMap<String, List<Fruit>> additionalFruit = new TreeMap<>();
        Set<String> keyOfFruit = new HashSet<>();
        additionalKey = new ArrayList<>();

        for (Fruit fruites : fruit) {
            String key = fruites.getCategory();
            if (mappedFruit.get(key) == null) {
                mappedFruit.put(key, new ArrayList<Fruit>());
            }
            mappedFruit.get(key).add(fruites);
            keyOfFruit.add(key);
        }


//        for(int i = 0; i < mappedFruit.size(); i++){
//            mappedFruit.get(i).contains(mappedFruit.get(i));
//        }

//        int mappedFruitSize = keyOfFruit.size();
//        int keys = keyOfFruit.size();

//        for (int i = 0; i < mappedFruitSize; i++) {
//
//            keyOfFruit.add(keyOfFruit.toArray()[i].toString() + "," + keyOfFruit.toArray()[keys - 1].toString());
//            keys = keyOfFruit.size();
//        }

        for (Map.Entry<String, List<Fruit>> listFruit : mappedFruit.entrySet()) {
            List<Fruit> newFruit = listFruit.getValue();

            for (int j = 0; j < newFruit.size(); j++) {
                String keyName = listFruit.getKey().toString();
                for (Map.Entry<String, List<Fruit>> compaFruit : mappedFruit.entrySet()) {
                    List<Fruit> compFruit = compaFruit.getValue();

                    for (int i = 0; i < compFruit.size(); i++) {
                        if (!compFruit.get(i).getCategory().equals(newFruit.get(j).getCategory())) {
                            if (compFruit.get(i).getName().equals(newFruit.get(j).getName())) {
                                String key = compFruit.get(i).getCategory();
                                keyName = keyName + "," + key;
                            }
                        }
                    }
                }

                String[] splitedName = keyName.split(",");
                if (splitedName.length > 1) {
                    if(j < (mappedFruit.size())){
                        additionalKey.add(keyName);
                        if(additionalFruit.get(keyName) == null){
                            additionalFruit.put(keyName, new ArrayList<Fruit>());
                        }
                        boolean isInside = false;
                        int countSplited = splitedName.length;
                        for(int t = 0; t < countSplited; t++){
                            if(newFruit.get(j).getCategory().equals(splitedName[t])){
                                isInside = true;
                            }
                        }
                        if(isInside == true){
                            additionalFruit.get(keyName).add(newFruit.get(j));
                        }
                    }
                }
            }
        }

        int countSplittedAfter = 0;
        for(Map.Entry<String, List<Fruit>> addedFruit : additionalFruit.entrySet()){
            String[] splittedKey = addedFruit.getKey().split(",");
            int countSplitted = splittedKey.length;

            if(countSplitted != countSplittedAfter){
                mappedFruit.put(addedFruit.getKey(), addedFruit.getValue());
            }
            countSplittedAfter = countSplitted;
        }
        System.out.println(mappedFruit);

        int header = 0;
        for(Map.Entry<String, List<Fruit>> iterateToView : mappedFruit.entrySet()){
            fruitAdapter.addSectionHeaderItem(iterateToView.getKey());
            List<Fruit> listIsi = iterateToView.getValue();

            for(int i = 0; i < listIsi.size(); i++){
//                if(i == 0){
//                    fruitAdapter.addSectionHeaderItem(iterateToView.getKey());
//                }
                fruitAdapter.addItem(listIsi.get(i).getName());
            }
        }

        setListAdapter(fruitAdapter);
    }

    @Override
    public ProgressDialog getDialog() {
        return dialog;
    }
}
