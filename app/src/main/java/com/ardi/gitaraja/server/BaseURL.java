package com.ardi.gitaraja.server;

public class BaseURL {

    public static String baseUrl ="http://192.168.43.149:5050/";
    public static String login = baseUrl + "user/login";
    public static String regis = baseUrl + "user/registrasi";

    //buku
    public static String dataGitar = baseUrl + "gitar/datagitar";
    public static String editDataGitar = baseUrl + "gitar/ubah/";
    public static String hapusData = baseUrl + "gitar/hapus/";
    public static String inputGitar = baseUrl + "gitar/input";
}
