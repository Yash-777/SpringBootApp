package com.github.yash777.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * A Configuration instance is the central place to store the application level settings of FreeMarker.
 * Also, it deals with the creation and caching of pre-parsed templates (i.e., Template objects).
 * <br />
 * <b>Warning!</b>
 * Do not needlessly re-create Configuration instances; it's expensive, among others because you lose
 * the template cache. Configuration instances meant to be application-level singletons.

 * <a href="https://freemarker.apache.org/docs/pgui_quickstart_createconfiguration.html">Ref</a>, 
 * <a href="https://o7planning.org/en/11765/spring-boot-file-download-example">Examples</a>
 * 
 * @author yashwanth.m
 *
 */
@Controller
@RequestMapping("/freemarker")
public class FreeMarkerFileDownload {
	static String tempPath = System.getProperty("java.io.tmpdir");
	
	@RequestMapping(value = { "/downloadfile" }, method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> downlaodFile(@RequestParam String message) throws Exception {
		
		@SuppressWarnings("deprecation")
		Configuration cfg = new freemarker.template.Configuration();
		cfg.setClassForTemplateLoading(FreeMarkerFileDownload.class, "templates");
		cfg.setClassForTemplateLoading(FreeMarkerFileDownload.class, "/");
		
		String fileName = "dyanamicPage";
		Template template = cfg.getTemplate("public/"+fileName+".ftl"); //"public/dyanamicPage.ftl"
		Writer consoleWriter = new OutputStreamWriter(System.out);
		
		// To replace value of ${message} in ftl file.
		Map<String, Object> repaceKey = new HashMap<String, Object>();
		repaceKey.put("message", message);
		
		template.process(repaceKey, consoleWriter);
		
		// To solve problem of Multiple request used data format.
		String savedfileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String tempDynamicFile = tempPath + "\\record"+savedfileName+".ftl";
		File dynamicFile = new File(tempDynamicFile);
		
		Writer fileWriter = new FileWriter(dynamicFile);
		try {
			template.process(repaceKey, fileWriter);
		} finally {
			fileWriter.close();
		}
		System.out.println("Temparay Dynamic file to a request : "+ tempDynamicFile);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(dynamicFile));
		
		String downloadAsFileName = fileName+".html"; // Sample.jnlp;
		return ResponseEntity.ok()
				// Content-Disposition - "Content-disposition"
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+downloadAsFileName)
				// Content-Type - MediaType.parseMediaType("application/octet-stream")
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				 // Content-Length
				.contentLength(dynamicFile.length())
				.body(resource);
	}
}
