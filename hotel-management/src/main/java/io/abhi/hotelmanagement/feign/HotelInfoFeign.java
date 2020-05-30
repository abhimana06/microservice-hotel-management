package io.abhi.hotelmanagement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.abhi.hotelmanagement.model.request.HotelInfoRequest;
import io.abhi.hotelmanagement.model.response.HotelInfoResponse;

@FeignClient(url = "http://localhost:8081/hotelInfo", name = "HOTEL-INFO")
public interface HotelInfoFeign {
	
	@GetMapping("/getHotelInfo?hotelId={id}")
	public HotelInfoResponse getHotelInfo(@RequestParam("id") Long id);
	
	@PostMapping("/addHotelInfo?hotelId={id}")
	public void addHotelInfo(@RequestParam("hotelId") Long id, @RequestBody HotelInfoRequest request);
	
	@PutMapping("updateHotelInfo?hotelId={id}")
	public void updateHotelInfo(@RequestParam("hotelId") Long id, @RequestBody HotelInfoRequest request);

}
