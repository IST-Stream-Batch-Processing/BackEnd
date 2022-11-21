package cn.ist.lowcoding.common.client;

import cn.hutool.core.io.FileUtil;
import cn.ist.lowcoding.common.response.Result;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Data
public class FileClient {
    String url;
    String port;

    @Autowired
    RestTemplate restTemplate;

    public String save(File file, String name) throws IOException {
        String finalUrl = url.concat(":").concat(port).concat("/file");
        MultiValueMap<String, Object> entity = new LinkedMultiValueMap<>();
        entity.add("file", new FileSystemResource(file));
        entity.add("filename", name);
        ResponseEntity<Result> response = restTemplate.exchange(finalUrl, HttpMethod.POST,
                new HttpEntity<>(entity, null), Result.class);
        Result<String> result = response.getBody();
        return result.getData();
    }

    public File findById(String id) throws IOException {
        String finalUrl = url.concat(":").concat(port).concat("/file/").concat(id);
        ResponseEntity<Resource> entity = restTemplate.exchange(finalUrl, HttpMethod.GET,
                new HttpEntity<>(null, null), Resource.class);
        File file = new File(FileUtil.getTmpDir(), id);
        if (!file.exists()) {
            file.createNewFile();
            InputStream in = entity.getBody().getInputStream();
            FileUtil.writeFromStream(in, file);
        }
        return file;
    }

    public void deleteById(String id){
        String finalUrl = url.concat(":").concat(port).concat("/file/").concat(id);
        Result result = restTemplate.exchange(finalUrl, HttpMethod.DELETE,
                new HttpEntity<>(null, null), Result.class).getBody();
        // FixMe 检查调用结果
    }
}
