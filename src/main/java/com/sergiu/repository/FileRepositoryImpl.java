package com.sergiu.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.sergiu.model.FileCSV;
import com.sergiu.util.TypeFile;

@Repository
public class FileRepositoryImpl implements FileRepository {

	private HashOperations<String, TypeFile, FileCSV> hashOperations;

	public FileRepositoryImpl(RedisTemplate<String, FileCSV> redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(FileCSV fileCustomCSV) {
		hashOperations.put("FILE", fileCustomCSV.getFileType(), fileCustomCSV);
	}

	@Override
	public void update(FileCSV fileCustomCSV) {
		save(fileCustomCSV);
	}

	@Override
	public FileCSV findByType(TypeFile id) {
		return (FileCSV) hashOperations.get("FILE", id);
	}

}
