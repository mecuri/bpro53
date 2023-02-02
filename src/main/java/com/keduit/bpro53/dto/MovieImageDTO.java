package com.keduit.bpro53.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieImageDTO {
	
	private String uuid;
	private String imgname;
	private String path;
	
	public String getImageURL() {
		try {
			
			return URLEncoder.encode(path + "/" + uuid + "_" + imgname, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public String getThumbnailURL() {
		try {
			return URLEncoder.encode(path + "/s_" + uuid + "_" + imgname, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
