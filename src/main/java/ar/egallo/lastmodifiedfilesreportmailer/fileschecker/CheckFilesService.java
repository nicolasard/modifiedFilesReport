package ar.egallo.lastmodifiedfilesreportmailer.fileschecker;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CheckFilesService {

    Logger logger = LoggerFactory.getLogger(CheckFilesService.class);

    /*
     * 	Recursive function to get all the files in a give path that where modified in a given time frame.
     */
    public List<File> checkFiles(
            final String path, final Instant filesFrom, final Instant filesTo) {
        final File folder = new File(path);
        final File[] listOfFiles = folder.listFiles();
        List<File> result = new ArrayList<>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                final Long lastModified = listOfFiles[i].lastModified();
                if (filesFrom.toEpochMilli() <= lastModified
                        && filesTo.toEpochMilli() >= lastModified) {
                    final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    logger.info(
                            "File "
                                    + listOfFiles[i].getAbsolutePath()
                                    + " / "
                                    + sdf.format(lastModified));
                    result.add(listOfFiles[i]);
                }
            } else if (listOfFiles[i].isDirectory()) {
                result.addAll(
                        checkFiles(path + "/" + listOfFiles[i].getName(), filesFrom, filesTo));
            }
        }
        return result;
    }
}
