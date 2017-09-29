package com.gha.wa.api;

import org.apache.poi.ss.usermodel.Workbook;

import com.google.common.base.Optional;

public interface WorkbookController {

	public Optional<Workbook> acquireWorkbook(String file);
}
