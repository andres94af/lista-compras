package com.compras.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
@SuppressWarnings({ "unchecked" })
public class CloudinaryService {

	Cloudinary cloudinary;

	private static final String cloud_name = "da52tfqfk";
	private static final String api_key = "848363848961237";
	private static final String api_secret = "Yyf8y_2_XUaFvu80CxaZLsmal90";

	private Map<String, String> valuesMap = new HashMap<>();

	public CloudinaryService() {
		valuesMap.put("cloud_name", cloud_name);
		valuesMap.put("api_key", api_key);
		valuesMap.put("api_secret", api_secret);
		cloudinary = new Cloudinary(valuesMap);
	}

	
	public Map<Object, Object> upload(MultipartFile multipartFile) throws IOException {
		File file = convert(multipartFile);
		Map<Object, Object> result = cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", "lista-compras", "transformation",
				new Transformation<>().aspectRatio("1.0").gravity("auto").width(650).crop("fill")));
		file.delete();
		return result;
	}

	public Map<Object, Object> delete(String id) throws IOException {
		Map<Object, Object> result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
		return result;
	}

	private File convert(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartFile.getBytes());
		fo.close();
		return file;
	}
}