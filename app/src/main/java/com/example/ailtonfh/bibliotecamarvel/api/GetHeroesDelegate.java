package com.example.ailtonfh.bibliotecamarvel.api;

import com.example.ailtonfh.bibliotecamarvel.heroesModels.Models;

import retrofit2.Response;

/**
 * Created by AiltonFH on 08/06/2016.
 */
public interface GetHeroesDelegate {
    void manageHeroes(Response<Models> response);
    void manageHeroesError(Response<Models> response);
}
