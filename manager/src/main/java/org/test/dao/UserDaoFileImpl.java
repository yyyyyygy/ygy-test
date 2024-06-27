package org.test.dao;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.test.po.AuthInfo;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Component
public class UserDaoFileImpl implements UserDao {
    Map<Long, Set<String>> auth = new HashMap<>();

    @PostConstruct
    public void refresh() {
        List<String> content = new ArrayList<>();
        try {
            content = Files.readAllLines(Paths.get("list.txt"));
        } catch (Exception e) {
            System.out.println("error");
        }
        content.forEach(a -> {
            AuthInfo userInfo = JSON.parseObject(a, AuthInfo.class);
            if (userInfo.getStatus() == 1) {
                Set<String> orDefault = auth.getOrDefault(userInfo.getUserId(), new HashSet<>());
                orDefault.add(userInfo.getResource());
                auth.put(userInfo.getUserId(), orDefault);
            }
        });
    }

    @Override
    public Boolean hasAuth(Long userId, String resource) {
        Set<String> orDefault = auth.getOrDefault(userId, new HashSet<>());
        return orDefault.contains(resource);
    }

    @Override
    public Boolean insert(List<AuthInfo> list) {
        try (FileChannel channel = (FileChannel) Files.newByteChannel(Paths.get("list.txt"),
                StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            list.forEach(a -> {
                byte[] data = (JSON.toJSONString(a) + System.lineSeparator()).getBytes();
                buffer.put(data);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    try {
                        channel.write(buffer);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                buffer.clear();
            });
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        refresh();
        return true;
    }
}
