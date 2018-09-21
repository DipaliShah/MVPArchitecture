package com.dipalimvpsample.mvparchitecture.api;


import com.dipalimvpsample.mvparchitecture.main.SampleDto;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/**
 * Created by Mitesh on 2/22/2018.
 */

public interface IMyWebAPI {
    String BASEURL_API = "baseurl/api/";

    //Home page
    @GET(BASEURL_API + "common/HomePageDetail")
    Single<List<SampleDto>> getHomePageRecord(@Query("latitude") double latitude, @Query("longitude") double longitude,
                                              @Query("date") String date, @Query("zone") double zone);

    //BusinessCategory
    @GET(BASEURL_API + "BusinessCategory/getBusinessCategoryTree")
    Single<List<SampleDto>> getCategoryTree();

    @GET(BASEURL_API + "JainTiming")
    Single<SampleDto> sunTimings(@Query("latitude") double latitude, @Query("longitude") double longitude,
                                 @Query("date") String date, @Query("zone") double zone);


    //Search Module Business

    @POST(BASEURL_API + "common/SearchAll")
    Single<List<SampleDto>> getAllSearchRecords(@Body SampleDto searchRequestModel);

    @GET(BASEURL_API + "Location/Search/{location}")
    Single<List<SampleDto>> searchLocation(@Path("location") String locationKeyword);

    @POST(BASEURL_API + "Business/Search")
    Single<List<SampleDto>> searchBusiness(@Body SampleDto searchRequestModel);

    @GET(BASEURL_API + "common/GetNearByLocation/{id}")
    Single<List<List<SampleDto>>> getNearByBusiness(@Path("id") int id);


    //Reviews
    @GET(BASEURL_API + "review/ReviewList")
    Single<List<SampleDto>> getReviewListForBusiness(@QueryMap Map<String, Object> options);


    //Locations Address
    @GET(BASEURL_API + "Location/GetAddress")
    Single<List<SampleDto>> getUserAddress();

    @GET(BASEURL_API + "Location/getStatesByCountryId/{id}/{name}")
    Single<List<SampleDto>> getStates(@Path("id") int mCountryID, @Path("name") String
            mStateName);

    @GET(BASEURL_API + "Location/getCitysByStateId/{id}/{name}")
    Single<List<SampleDto>> getCities(@Path("id") int mStateID, @Path("name") String
            mCityName);

    @GET(BASEURL_API + "Location/getPinCodesByCityId/{id}/{pincode}")
    Single<List<SampleDto>> getPincodes(@Path("id") int mCityID, @Path("pincode") String
            mPinCode);


    //Contact Us
    @POST(BASEURL_API + "ContactUs/Create")
    Call<ResponseBody> contactUs(@Body SampleDto dto);


    //Upload File To Server
    @Multipart
    @POST(BASEURL_API + "Gallery/GallerySave")
    Call<List<SampleDto>> uploadTempFile(@Part MultipartBody.Part filePart);
}
