package com.example.ailtonfh.bibliotecamarvel;

import com.example.ailtonfh.bibliotecamarvel.api.APIClient;
import com.example.ailtonfh.bibliotecamarvel.api.AuthenticationAPI;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void md5_isCorrect() throws Exception {
        AuthenticationAPI api = new AuthenticationAPI();
        assertEquals(api.md5("asa") , "457391c9c82bfdcbb4947278c0401e41");
    }

}