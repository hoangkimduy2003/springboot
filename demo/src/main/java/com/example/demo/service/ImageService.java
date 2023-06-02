package com.example.demo.service;

import com.example.demo.entity.Image;
import com.example.demo.reponsitory.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    private ImageRepo imageRepo;

    public void saveImage(Image image){
        imageRepo.save(image);
    }

    public Image getImageById(Long id){
        return imageRepo.findById(id).orElse(null);
    }

    public void deleteImage(Long id){
        imageRepo.deleteById(id);
    }

    public Byte[] getImageDataById(long id){
        Image image = imageRepo.findById(id).orElse(null);
        return image.getData();
    }
}
