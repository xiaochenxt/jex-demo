package com.dc;

import io.avaje.http.api.Controller;
import io.avaje.http.api.Get;
import io.avaje.http.api.Post;
import io.avaje.jex.file.upload.FileUploadService;
import io.avaje.jex.http.Context;
import io.avaje.jex.http.sse.SseClient;
import io.avaje.jsonb.Json;

import java.util.logging.Logger;

/**
 *
 * @author xiaochen
 * @since 2025/10/27
 */
@Controller
public class HelloController {

    private static final Logger log = Logger.getLogger(HelloController.class.getName());

    @Json
    public record IpDTO (String ip, Long ipNum) {}

    @Get("/hello")
    public String hello(){
        return "Hello World!";
    }

    @Get("/ip")
    public IpDTO ip(){
        return new IpDTO("114.114.114.114", 123456789L);
    }

    @Get("/sse")
    public void sse(Context ctx) throws Exception {
        SseClient.handler(sseClient -> {
            for (int i = 0; i < 10; i++) {
                sseClient.sendEvent("test","hello");
            }
        }).handle(ctx);
    }

    @Post("/upload")
    public void upload(Context ctx) throws Exception {
       var fileUploadService = ctx.attribute(FileUploadService.class);
        try (var multiPart = fileUploadService.uploadedFile("file")) {
            log.info("uploaded file: " + multiPart);
        }
    }

}
