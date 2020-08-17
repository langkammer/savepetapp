package lrsystemsweb.com.br.savepetapp.core.io;

import org.springframework.core.io.Resource;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Stream;


public interface StorageService {
    void init();

    void store(String fileString, Long pk);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}
