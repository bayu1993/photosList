package io.github.bayu1993.photosapps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.github.bayu1993.photosapps.adapter.PhotoAdapter;
import io.github.bayu1993.photosapps.model.PhotoModel;
import io.github.bayu1993.photosapps.network.RestApi;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcPhoto;
    private PhotoAdapter adapter;
    private ArrayList<PhotoModel> photoModels;
    private CompositeDisposable compositeDisposable;

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compositeDisposable = new CompositeDisposable();
        rcPhoto = findViewById(R.id.rc_photo);
        loadData();
    }

    private void loadData() {
        RestApi restApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RestApi.class);
        compositeDisposable.add(restApi.getphotos()
                .flatMap(Observable::fromIterable)
                .filter(photoModel -> photoModel.getId() > 6)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::onResponse, this::onError));
    }

    private void onResponse(List<PhotoModel> photoList) {
        photoModels = new ArrayList<>(photoList);
        adapter = new PhotoAdapter(photoModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcPhoto.setLayoutManager(linearLayoutManager);
        rcPhoto.setAdapter(adapter);
    }

    private void onError(Throwable error) {
        Log.d("On error", error.getLocalizedMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
