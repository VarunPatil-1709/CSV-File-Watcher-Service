package com.FileWatcherService.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.*;

@Component
public class CsvWatcherService implements Runnable {

    @Value("${file.import.path}")
    private String folderPath;

    private final CsvProcessorService processorService;

    public CsvWatcherService(CsvProcessorService processorService) {
        this.processorService = processorService;
    }

    @Override
    public void run() {

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {

            Path path = Paths.get(folderPath);

            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            while (true) {

                WatchKey key = watchService.take();

                for (WatchEvent<?> event : key.pollEvents()) {

                    Path fileName = (Path) event.context();

                    if (fileName.toString().endsWith(".csv")) {

                        Path fullPath = path.resolve(fileName);

                        // Wait to ensure file writing is complete
                        Thread.sleep(1000);

                        try {
                            processorService.process(fullPath);

                            Files.move(fullPath,
                                    path.resolve("processed").resolve(fileName),
                                    StandardCopyOption.REPLACE_EXISTING);

                        } catch (Exception ex) {
                            Files.move(fullPath,
                                    path.resolve("error").resolve(fileName),
                                    StandardCopyOption.REPLACE_EXISTING);
                        }
                    }
                }

                key.reset();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}