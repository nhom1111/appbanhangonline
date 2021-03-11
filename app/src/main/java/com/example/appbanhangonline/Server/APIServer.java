package com.example.appbanhangonline.Server;

public class APIServer {
    private static final String base_url="https://doanbanhoaonline.000webhostapp.com/";


    public static Dataserver getServer(){
        return APIRetrofitClient.getClient(base_url).create(Dataserver.class);
    }
}
