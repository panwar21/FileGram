package org.java.webapp.service;

import org.java.webapp.dao.FIleTableDao;
import org.java.webapp.model.FileTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FileTableService {
	
	@Autowired
	private FIleTableDao fileTableDao;
	
	//TODO by merge
	public boolean update(FileTable file, int id) {
		FileTable fileEntity = this.getFileAllData(id);
		if(fileEntity!=null) {
			fileEntity.setFileName(file.getFileName());
			//fileEntity.setTags(file.getTags());
			fileTableDao.update(fileEntity);
			return true;
		}
		return false;
	}
	
	public void delete(FileTable file) {
		
		fileTableDao.delete(file);
	}
	
	public FileTable getFileAllData(int fileId) {
		//if fileExists
		FileTable file = fileTableDao.getFileAllData(fileId);
		if(file!= null) {
			return file;
		}
		return null;
	}
	
	public void save(FileTable file) {
		
		fileTableDao.save(file);
	}

	public void update(FileTable fileTable) {
		fileTableDao.update(fileTable);
		
	}

}
