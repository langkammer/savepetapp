package lrsystemsweb.com.br.savepetapp.core.io;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Service
public class FileUtil {
    public void parseStringFile(File file ,String stringFile) {
        byte[] decodedBytes = Base64.getDecoder().decode(stringFile);
        try {
            FileUtils.writeByteArrayToFile(file, decodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
