package com.message.system.network.controller;

import com.message.system.network.document.Message;
import com.message.system.network.document.Photo;
import com.message.system.network.repository.MessageRepository;
import com.message.system.network.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/photo")
@RequiredArgsConstructor
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

    private final ReactiveGridFsTemplate gridFsTemplate;

    @PostMapping(value = "add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<Photo>> upload(@RequestPart Mono<FilePart> fileParts) {

        /*Document document = new Document();
        document.append("userId",)*/

        return fileParts
                .flatMap(part -> this.gridFsTemplate.store(part.content(), part.filename()))
                .map((id) -> ResponseEntity.ok().body(new Photo(id.toHexString())));
    }


    @GetMapping("{id}")
    public Flux<Void> read(@PathVariable String id, ServerWebExchange exchange) {
        return this.gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id)))
                .log()
                .flatMap(gridFsTemplate::getResource)
                .flatMapMany(r -> exchange.getResponse().writeWith(r.getDownloadStream()));
    }
}
