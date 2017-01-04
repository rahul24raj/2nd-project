package com.niit.chat.dao;

import com.niit.chat.model.FileUpload;

public interface FileUploadDAO {
	void save(FileUpload fileUpload);
	FileUpload getFile(String username);
}
