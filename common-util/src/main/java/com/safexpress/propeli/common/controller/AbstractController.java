package com.safexpress.propeli.common.controller;

import java.io.Serializable;

import com.safexpress.propeli.common.dto.ResponseDto;

public class AbstractController<T extends Serializable> {

	protected ResponseDto response(T dto) {
		return new ResponseDto();
	}
}
