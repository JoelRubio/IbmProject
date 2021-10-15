package org.academy.jra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${feignClient.api.name}", url = "${feignClient.api.methodGET.hostname}")
public interface BanamexFeignClient {

	@GetMapping("${feignClient.api.methodGET.uri}")
	byte[] getJsonFile();
}
