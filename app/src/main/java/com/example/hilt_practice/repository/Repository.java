package com.example.hilt_practice.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.hilt_practice.network.NetworkService;
import com.example.hilt_practice.network.model.BlogData;
import com.example.hilt_practice.repository.util.ResultState;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository{

    private static final String TAG = "Repository class - ";
    private NetworkService _networkService;
    private MediatorLiveData<List<BlogData>> _mediatorLivedata;
    private int index = 0;

    @Inject
    public Repository(NetworkService serviceInstance){
        this._networkService = serviceInstance;
        if(_networkService != null){
            Log.d(TAG, "Repository: network service not null");
        }
        this._mediatorLivedata = new MediatorLiveData<>();
    }

    public MediatorLiveData<List<BlogData>> fetchData(){
        Log.d(TAG, "fetchData: start fetchdata");

        LiveData<ResultState<List<BlogData>>> source = LiveDataReactiveStreams.fromPublisher(
                _networkService.getBlogs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ResultState::success)
                .onErrorReturn(ResultState::error)
        );

        _mediatorLivedata.addSource(source, new Observer<ResultState<List<BlogData>>>() {
            @Override
            public void onChanged(ResultState<List<BlogData>> listResultState) {
                Log.d(TAG, "onChanged: started");
                if(listResultState.get_result() != null){
                    Log.d(TAG, "onChanged: "+ listResultState.get_result().get(1).getBody());
                    _mediatorLivedata.setValue(listResultState.get_result());
                    Log.d(TAG, "onChanged: if end");
                }
                else{
                    Log.d(TAG, "onChanged: error" + listResultState.get_error().toString());
                }
            }
        });
        return _mediatorLivedata;
       /* _networkService.getBlogs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<BlogData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<BlogData> blogData) {
                        Log.d(TAG, "onNext: "+ blogData.get(index).getBody());
                        index++;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+ e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: completed");
                    }
                }); */

    }
}
