package com.example.ailtonfh.bibliotecamarvel.api;

import com.example.ailtonfh.bibliotecamarvel.comicsModels.ComicsModel;
import com.example.ailtonfh.bibliotecamarvel.heroesModels.Models;

import retrofit2.Response;

/**
 * Created by AiltonFH on 09/06/2016.
 */
public interface GetComicsDelegate {
    void manageComics(Response<ComicsModel> response);
    void manageComicsError(Response<ComicsModel> response);
}
