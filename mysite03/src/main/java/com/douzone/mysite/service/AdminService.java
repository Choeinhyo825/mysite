package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.repository.AdminRepository;
import com.douzone.mysite.vo.SiteVo;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	private static final String SAVE_PATH = "/mysite-uploads";
	private static final String URL = "/images";

	public SiteVo selectProfile() {
		return adminRepository.selectProfile();
	}

	public boolean restore(SiteVo vo, MultipartFile multipartFile) {
		String url;
		try {
			String originFilename = multipartFile.getOriginalFilename();

			int lastIndex = originFilename.lastIndexOf('.');
			String saveFilename = generateSaveFilename(originFilename.substring(lastIndex + 1));
//			long fileSize = multipartFile.getSize();

			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(fileData);
			os.close();
			url = URL + "/" + saveFilename;
			if (!multipartFile.isEmpty()) {
				vo.setProfileImage(url);
			}
			System.out.println(vo);
			adminRepository.profileUpdate(vo);

			return true;

		} catch (IOException e) {
			throw new RuntimeException("file upload error : " + e);
		}

	}

	private String generateSaveFilename(String extName) {

		String filename = "";

		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(calendar.YEAR);
		filename += calendar.get(calendar.MONTH);
		filename += calendar.get(calendar.DATE);
		filename += calendar.get(calendar.HOUR);
		filename += calendar.get(calendar.MINUTE);
		filename += calendar.get(calendar.SECOND);
		filename += calendar.get(calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}

}
