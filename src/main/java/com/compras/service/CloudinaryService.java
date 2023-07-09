package com.compras.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
@SuppressWarnings("unchecked")
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
	
	
	@SuppressWarnings("rawtypes")
	public Map<Object, Object> upload(MultipartFile file) throws IOException {
		Map<Object, Object> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", "lista-compras", "transformation",
				new Transformation().aspectRatio("1.0").gravity("auto").width(500).crop("fill")));
		return result;
	}
	

	public Map<Object, Object> delete(String id) throws IOException {
		Map<Object, Object> result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
		return result;
	}

}
