package com.sastit.joinus.utils;

import com.sastit.joinus.model.entity.Candidate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExcelHelper {
    public static final String TYPE =
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String[] HEADER =
        {"ID", "姓名", "学号", "手机号", "QQ", "专业", "辅导员", "社团", "选择1", "选择2", "原因", "创建时间"};

    public static ByteArrayInputStream candidatesToExcel(List<Candidate> candidates) {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("候选人");
            // Header
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < HEADER.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADER[col]);
            }
            int rowIdx = 0;
            for (Candidate candidate : candidates) {
                Row row = sheet.createRow(++rowIdx);
                row.createCell(0).setCellValue(rowIdx);
                row.createCell(1).setCellValue(candidate.getName());
                row.createCell(2).setCellValue(candidate.getStudentId());
                row.createCell(3).setCellValue(candidate.getPhone());
                row.createCell(4).setCellValue(candidate.getQq());
                row.createCell(5).setCellValue(candidate.getMajor());
                row.createCell(6).setCellValue(candidate.getCounselor());
                row.createCell(7).setCellValue(candidate.getClub().getName());
                row.createCell(8).setCellValue(candidate.getChoice1().getName());
                row.createCell(9).setCellValue(candidate.getChoice2().getName());
                row.createCell(10).setCellValue(candidate.getReason());
                row.createCell(11)
                    .setCellValue(LocalDateTime
                        .ofInstant(candidate.getCreatedAt(), ZoneId.of("Asia/Shanghai"))
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
