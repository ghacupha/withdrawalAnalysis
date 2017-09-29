package com.gha.wa.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gha.wa.api.WorkbookController;
import com.google.common.base.Optional;
import com.google.common.io.Files;

public class WorkbookControllerImpl implements WorkbookController{
	
	private static final Logger log = LoggerFactory.getLogger(WorkbookControllerImpl.class);
	private static final WorkbookController instance = new WorkbookControllerImpl();

	private WorkbookControllerImpl() {
	}

	public Optional<Workbook> acquireWorkbook(String file){

		Workbook workbook = null;

		InputStream excelFile = acquireBufferedInputStream(file).get();

		if(Files.getFileExtension(file) == "xlsx") {

			try {
				workbook = new SXSSFWorkbook(new XSSFWorkbook(excelFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			
			try {
				workbook = new HSSFWorkbook(excelFile);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}

		return Optional.of(workbook);
	}

	private Optional<InputStream> acquireBufferedInputStream(String file) {

		InputStream excelFile = null;

		try {
			excelFile = new BufferedInputStream(
					new FileInputStream(new File(file)));
		} catch (FileNotFoundException e) {
			
			log.error("The file : {} could not be found " ,file);
			
			e.printStackTrace();
		}

		return Optional.of(excelFile);		
	}

	/**
	 * @return the instance
	 */
	public static synchronized WorkbookController getInstance() {
		return instance;
	}
		
}
