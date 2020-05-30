package io.abhi.hotelmanagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.abhi.hotelmanagement.feign.HotelInfoFeign;
import io.abhi.hotelmanagement.feign.RatingFeign;
import io.abhi.hotelmanagement.model.request.HotelInfoRequest;
import io.abhi.hotelmanagement.model.request.RatingsRequest;
import io.abhi.hotelmanagement.model.response.HotelInfoResponse;
import io.abhi.hotelmanagement.model.response.RatingsResponse;
import io.abhi.hotelmanagement.service.InfoRatingService;
import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InfoRatingServiceImpl implements InfoRatingService{
	
	
	@Autowired
	private HotelInfoFeign hotelInfoFeign ;
	
	@Autowired
	private RatingFeign ratingFeign ;
	
	@Autowired
	private RestTemplate resttemplate;
	
	
	// The bulkhead implementation in Hystrix limits the number of concurrent calls to a component 
		  @Override
		  /**@HystrixCommand(fallbackMethod = "getFallbackHotelInfo", threadPoolKey =
		  "hotelInfoPool", 
		  threadPoolProperties = {
		  @HystrixProperty(name="coreSize" , value ="20"),
		  @HystrixProperty(name="maxQueueSize" , value ="10")}, 
		  commandProperties = {
		  @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value= "2000")})**/
		 public HotelInfoResponse getHotelInfo(Long id) {
			HotelInfoResponse infoResponse =  hotelInfoFeign.getHotelInfo(id);
			return infoResponse;
		 }
		
		@Override
		@HystrixCommand(fallbackMethod = "getFallbackAddHotelInfo", threadPoolKey =
		  "addPool", 
		  threadPoolProperties = {
		  @HystrixProperty(name="coreSize" , value ="20"),
		  @HystrixProperty(name="maxQueueSize" , value ="10")}, 
		  commandProperties = {
		  @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value= "2000")})
		public void addHotelInfo(Long id, HotelInfoRequest request) {
			 hotelInfoFeign.addHotelInfo(id,request);
		}

		@Override
		@HystrixCommand(fallbackMethod = "getFallbackUpdateHotelInfo", threadPoolKey =
		  "updatePool", 
		  threadPoolProperties = {
		  @HystrixProperty(name="coreSize" , value ="20"),
		  @HystrixProperty(name="maxQueueSize" , value ="10")}, 
		  commandProperties = {
		  @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value= "2000")})
		public void updateHotelInfo(Long id, HotelInfoRequest request) {
			hotelInfoFeign.updateHotelInfo(id, request);
		}
		
		@Override
		  /**@HystrixCommand(fallbackMethod = "getFallbackRating", commandProperties = { 
		  @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value= "2000"),
		  @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value ="5"),
		  @HystrixProperty(name="circuitBreaker.errorThresholdPercentage" , value="50"),
		  @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="4000")}) **/
		  public RatingsResponse getHotelRating(Long id) {
			RatingsResponse ratingResponse = ratingFeign.getHotelRating(id);
			//RatingsResponse ratingResponse =  resttemplate.getForObject("http://rating-service:8082/ratings/getHotelRating?id=" + id,RatingsResponse.class);
			return ratingResponse;
		  }
		
		
		@Override
		public void addRating(Long id, RatingsRequest request) {
			ratingFeign.addRating(id, request);
			
		}

		@Override
		public void updateRating(Long id, RatingsRequest request) {
			ratingFeign.updateRating(id, request);
			
		}
		
		public HotelInfoResponse getFallbackHotelInfo(Long hotelId) {
			HotelInfoResponse infoResponse =  new HotelInfoResponse();
			infoResponse.setHotelId(hotelId);
			infoResponse.setDescription("FallBack Message:Please wait while we fetch Hotel Information, Your patience is appreciated! ");
			return infoResponse;
		}
		
		
		public RatingsResponse getFallbackRating(Long hotelId) {
			RatingsResponse ratingResponse =  new RatingsResponse();
			ratingResponse.setFoodFeedback("FallBack Message: Please wait while we fetch Hotel Rating, Your patience is appreciated! ");
			return ratingResponse;
		}
		
		public void getFallbackAddHotelInfo(Long id, HotelInfoRequest request) {
			log.info("FallBack Message: Please wait while we try to bring back the service, Your patience is appreciated!");
		}
		
		public void getFallbackUpdateHotelInfo(Long id, HotelInfoRequest request) {
			log.info("FallBack Message: Please wait while we try to bring back the service, Your patience is appreciated!");

		}

		

}
