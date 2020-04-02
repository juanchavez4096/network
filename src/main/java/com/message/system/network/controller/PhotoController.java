package com.message.system.network.controller;

import com.message.system.network.document.Message;
import com.message.system.network.document.Photo;
import com.message.system.network.repository.MessageRepository;
import com.message.system.network.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("api/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    /*@PostMapping("/add")
    public Mono<List<String>> process(@RequestPart("files") Flux<FilePart> filePartFlux) {
        return photoService.addPhoto(filePartFlux).col;
    }*/

    /*@GetMapping("/photos/{id}")
    public String getPhoto(@PathVariable String id) {
        Mono<Photo> photo = photoService.getPhoto(id);
        FileCopyUtils.copy(photo., response.getOutputStream());
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image",
                Base64.getEncoder().encodeToString(photo..getData()));
        return "photos";
    }*/
}
