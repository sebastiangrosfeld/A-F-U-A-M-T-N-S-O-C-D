package com.study.carDealershipsServer.common.config.service;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.study.carDealershipsServer.common.errors.ServiceException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AzureService {

    @Value("${azure.blob.endpoint}")
    private String endpoint;

    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    private BlobContainerClient blobContainerClient;

    @PostConstruct
    public void init() {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(endpoint)
                .buildClient();
        blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
    }

    public void addFile(String filepath, String blobName) {
        blobContainerClient.getBlobClient(blobName).uploadFromFile(filepath, true);
        log.info("Successfully uploaded file {} to {}", filepath, blobName);
    }

    public void getFile(String filepath, String blobName) {
        if (!blobContainerClient.getBlobClient(blobName).exists()) {
            throw new ServiceException("Downloaded file not found", HttpStatus.NOT_FOUND);
        }

        blobContainerClient.getBlobClient(blobName).downloadToFile(filepath);
        log.info("File downloaded to: {}", filepath);
    }

    public void deleteFile(String blobName) {
        blobContainerClient.getBlobClient(blobName).delete();
        log.info("File deleted from blob: {}", blobName);
    }

}
