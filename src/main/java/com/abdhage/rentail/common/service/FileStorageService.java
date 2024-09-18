package com.abdhage.rentail.common.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    public void init();

    public void save(MultipartFile multipartFile);

    public Resource load(String fileName);

    public void deleteAll();

    public Stream<Path> loadAll();
}
