package org.java.webapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.java.webapp.dao.TagDao;
import org.java.webapp.model.FileTable;
import org.java.webapp.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TagService {
	
	
	@Autowired
	private TagDao tagDao;

	public Tag getTagLazyFetch(String tagName) {
		
		return tagDao.getTagLazyFetch(tagName);
	}
	
	public boolean saveTag(Tag tag) {
		
		tagDao.save(tag);
		
		return true;
	}

	public Tag getTagAllData(String tagName) {
		
		return tagDao.getTagAllData(tagName);
	}

	public void updateTag(Tag tag) {
		 
		tagDao.update(tag);
		
	}

}
