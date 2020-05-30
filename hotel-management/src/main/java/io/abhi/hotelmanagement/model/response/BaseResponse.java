package io.abhi.hotelmanagement.model.response;

import io.abhi.hotelmanagement.common.WebAppConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

	private  String responseCode = WebAppConstants.SUCCESS_RESPONSE_CODE;
	private  String responseMessage = WebAppConstants.SUCCESS_RESPONSE_MESSAGE;
	
	
	
}
