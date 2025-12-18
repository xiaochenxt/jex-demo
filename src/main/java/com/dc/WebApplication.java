package com.dc;

import io.avaje.config.Config;
import io.avaje.inject.BeanScope;
import io.avaje.jex.Jex;
import io.avaje.jex.Routing;
import io.avaje.jex.file.upload.FileUploadPlugin;
import io.avaje.jex.http.HttpResponseException;
import io.avaje.jex.http.HttpStatus;
import io.avaje.jex.staticcontent.StaticContent;

import java.util.Map;

import static io.avaje.jex.file.upload.MultipartConfig.FileSize.MB;

public class WebApplication {

    void main() {
        try (BeanScope beanScope = BeanScope.builder().build()) {
            var services = beanScope.list(Routing.HttpService.class);
            Jex.create()
                    .plugin(StaticContent.ofClassPath("/static/").route("/").directoryIndex("index.html").preCompress().build())
                    .plugin(StaticContent.ofFile("./static/").route("/").directoryIndex("index.html").preCompress().build())
                    .plugin(FileUploadPlugin.create(multipartConfig -> multipartConfig.maxFileSize(50, MB)))
                    .port(Config.getInt("server.port"))
                    .routing(services)
                    .error(HttpResponseException.class, (ctx, exception) -> {
                        ctx.status(HttpStatus.OK_200).json(Map.of("code", 400,"message",exception.getMessage()));
                    })
                    .start();
        }
    }

}
