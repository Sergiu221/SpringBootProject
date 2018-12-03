package com.sergiu.files;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FileCustomCSVRepositoryImpl implements FileCustomCSVRepository {

	private HashOperations<String, String, FileCustomCSV> hashOperations;

	public FileCustomCSVRepositoryImpl(RedisTemplate<String, FileCustomCSV> redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(FileCustomCSV fileCustomCSV) {
		hashOperations.put("FILE", fileCustomCSV.getId(), fileCustomCSV);

	}

	@Override
	public void update(FileCustomCSV fileCustomCSV) {
		// TODO Auto-generated method stub

	}

	@Override
	public FileCustomCSV findById(String id) {
		// TODO Auto-generated method stub
		return (FileCustomCSV) hashOperations.get("FILE", id);
	}

}
