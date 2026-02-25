package com.FileWatcherService.Starter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.FileWatcherService.Service.CsvWatcherService;

@Component
public class WatcherRunner implements CommandLineRunner {

    private final CsvWatcherService watcherService;

    public WatcherRunner(CsvWatcherService watcherService) {
        this.watcherService = watcherService;
    }

    @Override
    public void run(String... args) {
        new Thread(watcherService).start();
    }
}