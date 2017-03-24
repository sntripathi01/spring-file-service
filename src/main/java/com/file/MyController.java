package com.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MyController {

	
	@RequestMapping(method = RequestMethod.POST ,value = "/test",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void test(@RequestPart MultipartFile multipartFile,@RequestParam String data){
           
           System.out.println(multipartFile);
           
           try {
                  FileCopyUtils.copy(multipartFile.getBytes(), new File(this.getClass().getClassLoader().getResource(".").getFile()+multipartFile.getOriginalFilename()));
           } catch (IOException e) {
                  e.printStackTrace();
           }
    }


	
	/*
	@RequestMapping(method = RequestMethod.POST, headers = ("content-type=multipart/form-data;boundary=----WebKitFormBoundary9Vbm8lHl0GBQqfOK;charset=UTF-8"), value = "/test1")
	public String test(@RequestPart File file) throws Exception {
		String name = "file" + Calendar.getInstance().getTimeInMillis();
		
		 byte[] byteArray = new byte[(int) file.length()];
		    byteArray = FileUtils.readFileToByteArray(file);  
		String ext = file.getName().substring(file.getName().lastIndexOf("."));
		File temp = new File(this.getClass().getClassLoader().getResource(".").getFile() + name + ext);
		if (temp.createNewFile()) {
			System.out.println("File is created!");
		} else {
			System.out.println("File already exists.");
		}
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(temp));
		stream.write(byteArray);

		stream.close();
		stream.close();
		System.out.println(file);
		return name+ext + "uploaded";
	}
	
	@RequestMapping(value = ("/upload1"), headers = ("multipart/form-data;boundary=----WebKitFormBoundary9Vbm8lHl0GBQqfOK;charset=UTF-8"), method = RequestMethod.POST)
	public String handleFileUpload1(@RequestPart("file") MultipartFile file){
		return "success";
	}

	@RequestMapping(value = ("/upload"), headers = ("multipart/form-data;boundary=----WebKitFormBoundary9Vbm8lHl0GBQqfOK;charset=UTF-8"), method = RequestMethod.POST)
	public String handleFileUpload(@RequestPart("file") MultipartFile file, @RequestBody Data data)
			throws FileNotFoundException {
		String name = "file" + Calendar.getInstance().getTimeInMillis();

		if (!file.isEmpty()) {
			try {
				String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				byte[] bytes = file.getBytes();
				File temp = new File(this.getClass().getClassLoader().getResource(".").getFile() + name + ext);
				if (temp.createNewFile()) {
					System.out.println("File is created!");
				} else {
					System.out.println("File already exists.");
				}
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(temp));
				stream.write(bytes);

				stream.close();

				return "You successfully uploaded " + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name + " because the file was empty.";
		}
	}
*/
}
