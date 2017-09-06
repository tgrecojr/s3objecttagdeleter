package com.greco.s3.objecttagdeleter;

import com.amazonaws.services.s3.model.DeleteObjectTaggingRequest;
import com.amazonaws.services.s3.model.DeleteObjectTaggingResult;
import com.greco.imagetag.aws.AWSConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.ArrayList;

@SpringBootApplication(scanBasePackages={"com.greco.s3.*"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ObjecttagdeleterApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${aws.s3.bucket}")
	private String amazonBucket;
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
        AWSConnector s3Conn = new AWSConnector(amazonProfile,amazonRegion);
        logger.info("getting keys for bucket");
        ArrayList<String> keys = s3Conn.getS3ObjectKeysForBucket(amazonBucket);
        for (String key : keys){
            logger.info("removing tags for: " + key);
            s3Conn.deleteObjectTagsForBuckeyAndKey(amazonBucket,key);
        }
        logger.info("object tab deletion completed in " + (System.currentTimeMillis() - startTime) + " ms");
	}

}
