/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.utils;

import com.scottishfriendly.timereporting.utils.IStandardFields.Status;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.net.ssl.*;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author chriconn
 */
public class GeneralUtils {
    
    
    
    public static Map sortByValue(Map map) {
     List list = new LinkedList(map.entrySet());
     Collections.sort(list, new Comparator() {
          public int compare(Object o1, Object o2) {
               return ((Comparable) ((Map.Entry) (o1)).getValue())
              .compareTo(((Map.Entry) (o2)).getValue());
          }
     });

    Map result = new LinkedHashMap();
    for (Iterator it = list.iterator(); it.hasNext();) {
        Map.Entry entry = (Map.Entry)it.next();
        result.put(entry.getKey(), entry.getValue());
    }
    return result;
} 
    public static void main(String[] args){
        Map<Integer,String> testval = new HashMap<Integer, String>();
        testval.put(1, "B");
        testval.put(2, "A");
        testval.put(3, "Z");
        
        testval=sortByValue(testval);
        
        System.out.println(testval);
        
        
    }
    
    public static List<String> getStatusNames(Status[] status){
        List<String> retVal = new ArrayList<String>();
        for(Status s: status){
            retVal.add(s.getName());
        }
        return retVal;
    }
    
    public static void disableCertificateValidation() {
  // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { 
          new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() { 
              return new X509Certificate[0]; 
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        }};

        // Ignore differences between given hostname and certificate hostname
        HostnameVerifier hv = new HostnameVerifier() {
          public boolean verify(String hostname, SSLSession session) { return true; }
        };

        // Install the all-trusting trust manager
        try {
          SSLContext sc = SSLContext.getInstance("SSL");
          sc.init(null, trustAllCerts, new SecureRandom());
          HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
          HttpsURLConnection.setDefaultHostnameVerifier(hv);
        } catch (Exception e) {}
    }
    
    public static String reverseDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    
}
