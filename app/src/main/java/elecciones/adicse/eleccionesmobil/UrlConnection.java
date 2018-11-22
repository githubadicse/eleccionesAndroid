package elecciones.adicse.eleccionesmobil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class UrlConnection {

    static String urlConn = "http://192.168.24.113:8080/eleccion";

    static String token;

    static Integer idUsuario;

    static Integer idPersonero;

    static String nombrePersonero;

    static String nombreUsuario;





    public UrlConnection() {
        //urlConn = "http://192.168.24.113:8080";
    }

    public static String getUrlConn() {
        return urlConn;
    }

    public static void setUrlConn(String urlConn) {
        UrlConnection.urlConn = urlConn;
    }


    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        UrlConnection.token = token;
    }


    public static Integer getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(Integer idUsuario) {
        UrlConnection.idUsuario = idUsuario;
    }

    public static Integer getIdPersonero() {
        return idPersonero;
    }

    public static void setIdPersonero(Integer idPersonero) {
        UrlConnection.idPersonero = idPersonero;
    }

    public static String getNombrePersonero() {
        return nombrePersonero;
    }

    public static void setNombrePersonero(String nombrePersonero) {
        UrlConnection.nombrePersonero = nombrePersonero;
    }

    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    public static void setNombreUsuario(String nombreUsuario) {
        UrlConnection.nombreUsuario = nombreUsuario;
    }

    public static String getPostDataString(HashMap<String,String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String,String> entry : params.entrySet()){
            if(first){

                first = false;
                result.append(URLEncoder.encode( entry.getKey(),"UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
            }else{
                result.append("&");
                result.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
            }
        }
        return result.toString();
    }
}
