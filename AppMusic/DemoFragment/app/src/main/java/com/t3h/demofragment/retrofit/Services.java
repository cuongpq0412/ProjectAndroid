package com.t3h.demofragment.retrofit;

import com.t3h.demofragment.mediaplayer.MusicOffline;
import com.t3h.demofragment.mediaplayeronline.GetLinkMusic;
import com.t3h.demofragment.mediaplayeronline.ItemMusicOnline;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Services {
    @GET("/api/searchSong")
    Call<List<ItemMusicOnline>> searSong(
            @Query(value = "songName") String songName,
            @Query(value = "currentPage") int currentPage
    );

    @GET("/api/linkMusic")
    Call<GetLinkMusic> getLinkMusic(
            @Query(value = "linkSong") String linkSong
    );
}
