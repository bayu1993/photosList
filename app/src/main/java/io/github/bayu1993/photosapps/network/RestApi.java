package io.github.bayu1993.photosapps.network;

import java.util.List;

import io.github.bayu1993.photosapps.model.PhotoModel;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Bayu teguh pamuji on 10/4/18.
 * email : bayuteguhpamuji@gmail.com.
 */
public interface RestApi {

    @GET("photos/")
    Observable<List<PhotoModel>> getphotos();
}
