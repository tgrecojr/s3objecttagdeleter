package com.greco.s3.objecttagdeleter;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class S3ObjectTags {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void deleteObjectTagsForBucket(String amazonProfile,String amazonRegion, String amazonBucket){
        AmazonS3Client.builder();
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(amazonRegion)
                .withCredentials(new ProfileCredentialsProvider(amazonProfile))
                .build();
    }


}
