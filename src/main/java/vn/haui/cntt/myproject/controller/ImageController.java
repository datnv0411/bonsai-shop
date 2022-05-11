package vn.haui.cntt.myproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.haui.cntt.myproject.service.impl.ImageServiceImpl;

@Controller
@RequiredArgsConstructor
public class ImageController {

    @Autowired
    private final ImageServiceImpl imageService;

    //get image's url
    @RequestMapping("admin/images/{folder}/{id}/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String folder,@PathVariable String id, @PathVariable String fileName) {
        try {
            byte[] bytes = imageService.readFileContent(folder+"/"+id+"/"+fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping("admin/images/{folder}/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile2(@PathVariable String folder, @PathVariable String fileName) {
        try {
            byte[] bytes = imageService.readFileContent(folder+"/"+fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }
}
