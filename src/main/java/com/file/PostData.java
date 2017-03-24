package com.file;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;




public class PostData {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//sendPost();
		sendFile();
	}

	// HTTP POST request
	private  static void sendPost() throws Exception {

		String url = "http://localhost:6061/home.jsp";
		URL obj = new URL(url);
		File file = new File("D:\\a.txt");
		FileOutputStream fileOutputStream = new FileOutputStream(file);

		byte[] b = new byte[(int) file.length()];
		fileOutputStream.write(b);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add reuqest header
		con.setRequestMethod("POST");

		String urlParameters = "foldername=assignment&fileName=abc.pdf";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.write(b);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}
	
	private static void sendFile() throws Exception{
		String urlParameters = "foldername=assignment&fileName=abc.pdf";
		String urls = "http://localhost:6061/home.jsp?"+urlParameters;
		URL url = new URL(urls);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");

		FileBody fileBody = new FileBody(new File("D:\\file.xml"));
		MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.STRICT);
		multipartEntity.addPart("file", fileBody);
	
		connection.setRequestProperty("Content-Type", multipartEntity.getContentType().getValue());
		connection.setRequestProperty("foldername", "assignment");
		connection.setRequestProperty("fileName", "abc.txt");
	
		OutputStream out = connection.getOutputStream();
		
		 MultipartEntityBuilder.create().addTextBody("field1", "value1").
				addBinaryBody("file", new File("D:\\file.xml"),
						ContentType.create(ContentType.MULTIPART_FORM_DATA.getMimeType()), "file.txt").build().writeTo(out);;
		try {
		   // multipartEntity.writeTo(out);
		} finally {
		    out.close();
		}
		int status = connection.getResponseCode();
	}
	
	
	
	
	

}
