package com.compras.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.compras.service.CloudinaryService;

@RestController
@RequestMapping("/cloudinary")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CloudinaryController {
	
	@Autowired
	CloudinaryService cloudinaryService;
	
	@PostMapping
	public ResponseEntity<Map> subirImagen(@RequestParam("file") MultipartFile file){
		Map infoImg = new HashMap<>();

		try {
			infoImg = cloudinaryService.upload(file);
		} catch (Exception e) {
			infoImg.put("Mensaje", "No se pudo cargar la imagen");
			infoImg.put("Error", e.toString());
			return ResponseEntity.badRequest().body(infoImg);
		}
		
		return ResponseEntity.ok(infoImg);
	}

}
