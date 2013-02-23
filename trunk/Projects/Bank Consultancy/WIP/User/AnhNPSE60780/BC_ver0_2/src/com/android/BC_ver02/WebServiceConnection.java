package com.android.BC_ver02;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * TODO Put here a description of what this class does.
 *
 * @author vuph60062.
 *         Created Jan 23, 2013.
 */
public class WebServiceConnection {

	private final static String SERVICE_URI = "http://10.0.2.2:8801/Service.svc";
	private final static int TIMEOUT_MILLISEC = 20000;
	
	
	
	public static boolean CheckVersion(String version)
	{
		 String url = SERVICE_URI +"/Version/"+ version;
		 
		 boolean result = getResult(url);
		 
		 return result;
		
	}	
	
	 private static boolean getResult(String url)
	 {
		    HttpPost request = new HttpPost(url);
	        request.setHeader("Accept", "application/json");
	        request.setHeader("Content-type", "application/json");
	        
	        boolean result = false;
	        
	        try {

	            HttpParams httpParams = new BasicHttpParams();
	            HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
	            HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
	            DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
	            HttpResponse response = httpClient.execute(request);

	            HttpEntity responseEntity = response.getEntity();	            
	            InputStream stream = responseEntity.getContent();
	           
	            //result =  convertStreamToString(stream);
	            String res = convertStreamToString(stream);
	            result = res.contains("true");
	            //return result;

	        } catch (NumberFormatException exception) {
	            // TODO Auto-generated catch-block stub.
	            exception.printStackTrace();
	        }catch (UnsupportedEncodingException exception) {
	            // TODO Auto-generated catch-block stub.
	            exception.printStackTrace();
	        } catch (ClientProtocolException exception) {
	            // TODO Auto-generated catch-block stub.
	            exception.printStackTrace();
	        } catch (IOException exception) {
	            // TODO Auto-generated catch-block stub.
	            exception.printStackTrace();
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	        
	        return result;
	 }
	
	 
	 private static String getResultWithStream(String url, JSONObject json)
	 {
		 HttpParams httpParams = new BasicHttpParams();
         HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
         HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);

         // Instantiate an HttpClient
         HttpClient httpclient = new DefaultHttpClient(httpParams);
         HttpPost request = new HttpPost(url);
         InputStream is = new ByteArrayInputStream(json.toString().getBytes());
         InputStreamEntity ise = new InputStreamEntity(is, json.toString().getBytes().length);
         ise.setContentType("application/x-www-form-urlencoded");

         request.setEntity(ise);

         
        HttpResponse response;
		try {
			response = httpclient.execute(request);
		    HttpEntity responseEntity = response.getEntity();

		        if (responseEntity != null) {
		            InputStream stream = responseEntity.getContent();
		            String result = convertStreamToString(stream);
		            Log.e("dasdas", result);
		            return  result;
		        }  		        
   
		} catch (ClientProtocolException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		} catch (IOException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		
		}
		
		 return  "";
	 }
	
	 
	 
	 public static String convertStreamToString(InputStream is) {
		    /*
		     * To convert the InputStream to String we use the BufferedReader.readLine()
		     * method. We iterate until the BufferedReader return null which means
		     * there's no more data to read. Each line will appended to a StringBuilder
		     * and returned as String.
		     */
		    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		    StringBuilder sb = new StringBuilder();

		    String line = null;
		    try {
		        while ((line = reader.readLine()) != null) {
		            sb.append(line + "\n");
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            is.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		    return sb.toString();
		}
	
	
	 public static boolean CheckServer()
	 {
		    String url = SERVICE_URI;
		 	HttpPost request = new HttpPost(url);
	        request.setHeader("Accept", "application/json");
	        request.setHeader("Content-type", "application/json");
	        	        
	        
	        try {

	            HttpParams httpParams = new BasicHttpParams();
	            HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
	            HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
	            DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
	            HttpResponse response = httpClient.execute(request);

	            HttpEntity responseEntity = response.getEntity();	            
	            InputStream stream = responseEntity.getContent();
	           
	            //result =  convertStreamToString(stream);
	            String res = convertStreamToString(stream);
	            
	            return true;

	        } catch (NumberFormatException exception) {
	            // TODO Auto-generated catch-block stub.
	            exception.printStackTrace();
	        }catch (UnsupportedEncodingException exception) {
	            // TODO Auto-generated catch-block stub.
	            exception.printStackTrace();
	        } catch (ClientProtocolException exception) {
	            // TODO Auto-generated catch-block stub.
	            exception.printStackTrace();
	        } catch (IOException exception) {
	            // TODO Auto-generated catch-block stub.
	            exception.printStackTrace();
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	        
	        return false;
	 }
}
