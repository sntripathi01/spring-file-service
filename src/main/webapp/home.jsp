<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		out.print("HOME");
	System.out.print("foldername "+request.getParameter("foldername"));
	System.out.print("fileName "+request.getParameter("fileName"));

		//process only if its multipart content
		if (ServletFileUpload.isMultipartContent(request)) {
			String UPLOAD_DIRECTORY = "D:/uploads";
			System.out.print("HOME :"+UPLOAD_DIRECTORY);
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
					}
				}

				//File uploaded successfully
				request.setAttribute("message", "File Uploaded Successfully");
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);
				ex.printStackTrace();
			}

		} else {
			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
			System.out.print("else");
		}
	%>
</body>
</html>