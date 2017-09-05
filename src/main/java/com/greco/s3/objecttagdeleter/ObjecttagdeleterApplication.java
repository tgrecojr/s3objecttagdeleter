package com.greco.s3.objecttagdeleter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication(scanBasePackages={"com.greco.s3.*"})
@EnableAutoConfiguration
public class ObjecttagdeleterApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${aws.s3.bucket}")
	private String amazonS3Bucket;
	@Value("${aws.profile}")
	private String amazonProfile;
	@Value("${aws.region}")
	private String amazonRegion;

	public static void main(String[] args) {
		SpringApplication.run(ObjecttagdeleterApplication.class, args);
	}

	@Override
	public void run(String[] args) throws Exception{
		long startTime = System.currentTimeMillis();
		logger.info("starting object tag deletion");
		S3ObjectTags.deleteObjectTagsForBucket(amazonProfile,amazonRegion,amazonS3Bucket);


		logger.info("object tab deletion completed in " + (System.currentTimeMillis() - startTime) + " ms");
	}

}
