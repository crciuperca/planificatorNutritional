package controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* Provides pictures to front-end */

@RestController
public class StaticDataController {

    @RequestMapping(value = "/images/{pictureName}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(HttpServletResponse response, @PathVariable String pictureName) throws IOException {
        ClassPathResource imgFile;

        if(pictureName.contains("jpg")) {
            imgFile = new ClassPathResource("images/" + pictureName.substring(0,pictureName.length() - 3) + ".jpg");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        } else if (pictureName.contains(".png")) {
            imgFile = new ClassPathResource("images/" + pictureName);
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        } else {
            imgFile = new ClassPathResource("images/" + pictureName + ".png");
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }

        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }
}
