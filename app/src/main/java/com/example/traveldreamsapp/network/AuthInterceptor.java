package com.example.traveldreamsapp.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private String authToken;

    // Constructor
    public AuthInterceptor(String token) {
        this.authToken = token;
    }

    // Método para actualizar el token
    public void setToken(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        // Obtener la solicitud original
        Request original = chain.request();

        // Construir la solicitud con el token de autorización
        Request.Builder builder = original.newBuilder()
                .header("Authorization", "Bearer " + authToken);  // Agregar el token al header

        // Crear la nueva solicitud con el token agregado
        Request request = builder.build();

        // Proceder con la solicitud
        return chain.proceed(request);
    }
}